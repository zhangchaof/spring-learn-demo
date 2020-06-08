package com.example.spring.learn.demo.log.aspect;


import com.alibaba.fastjson.JSON;
import com.example.spring.learn.demo.utils.GenericsUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author clark
 */
@Component
@Aspect
@Slf4j
public class SystemLogAspect {

    @Pointcut("execution(* com.example.spring.learn.demo.controller.*.*(..)) || execution(* com.example.spring.learn.demo.log.*.*.*(..)) ")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doInvoke(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();

        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error(throwable.getMessage(), throwable);
            throw new RuntimeException(throwable);
        } finally {
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;

            printLog(pjp, result, elapsedTime);

        }

        return result;
    }

    /**
     * 打印日志
     *
     * @param pjp         连接点
     * @param result      方法调用返回结果
     * @param elapsedTime 方法调用花费时间
     */
    private void printLog(ProceedingJoinPoint pjp, Object result, long elapsedTime) {
        SystemLogStrategy strategy = getFocus(pjp);

        if (null != strategy) {
            strategy.setThreadId(ThreadUtil.getThreadId());
            strategy.setResult(JSON.toJSONString(result));
            strategy.setElapsedTime(elapsedTime);
            if (strategy.isAsync()) {
                new Thread(() -> log.info(strategy.format(), strategy.args())).start();
            } else {
                log.info(strategy.format(), strategy.args());
            }
        } else {
            String methodName = this.getMethodName(pjp);
            String params = getParamsJson(pjp);
            log.info("request methodName:[{}],param:[{}],response result[{}],costTime:[{}]", methodName, params, result, elapsedTime);
        }
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String SHORT_METHOD_NAME_SUFFIX = "(..)";
        if (methodName.endsWith(SHORT_METHOD_NAME_SUFFIX)) {
            methodName = methodName.substring(0,
                    methodName.length() - SHORT_METHOD_NAME_SUFFIX.length());
        }
        return methodName;
    }

    private String getParamsJson(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(Objects.isNull(args)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            String param;
            if (arg instanceof HttpServletResponse) {
                param = HttpServletResponse.class.getSimpleName();
            } else if (arg instanceof HttpServletRequest) {
                param = HttpServletRequest.class.getSimpleName();
            } else if (arg instanceof MultipartFile) {
                long size = ((MultipartFile) arg).getSize();
                param = MultipartFile.class.getSimpleName() + " size:" + size;
            } else {
                param = JSON.toJSONString(arg);
            }
            sb.append(param).append(",");
        }
        if (sb.length() == 0) {
            return "";
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 获取注解
     */
    private SystemLogStrategy getFocus(ProceedingJoinPoint pjp) {

        Signature signature = pjp.getSignature();
        //拦截的实体类
        Object target = pjp.getTarget();
        //拦截的类名称
        String className = signature.getDeclaringTypeName();
        //拦截的方法名称
        String methodName = signature.getName();
        Method method = null;
        //拦截的放参数个数
        Object[] args = pjp.getArgs();
        //获取参数类型
        Class[] argsType = ((MethodSignature) signature).getMethod().getParameterTypes();
        try {
            //通过反射获得拦截的method
            method = target.getClass().getMethod(methodName, argsType);
            if (method.isBridge()) {
                for (int i = 0; i < args.length; i++) {
                    //获得泛型类型
                    Class genClazz = GenericsUtils.getSuperClassGenricType(target.getClass());
                    //根据实际参数类型替换parameterType中的类型
                    if (args[i].getClass().isAssignableFrom(genClazz)) {
                        argsType[i] = genClazz;
                    }
                    //获得parameterType参数类型的方法
                    method = target.getClass().getMethod(methodName, argsType);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        SystemLogStrategy strategy = new SystemLogStrategy();
        strategy.setClassName(className);
        strategy.setMethodName(methodName);
        SystemLog systemServiceLog = method.getAnnotation(SystemLog.class);
        if (null != systemServiceLog) {
            String realArgs = getArgs(args);
            strategy.setArguments(realArgs);
            strategy.setDescription(systemServiceLog.description());
            strategy.setAsync(systemServiceLog.async());
            strategy.setLocation(AnnotationTypeEnum.SERVICE.getName());
            return strategy;
        }
        return null;

    }

    private String getArgs(Object[] args) {
        StringBuilder realArgs = new StringBuilder();
        for (Object obj : args) {
            if (obj instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) obj;
                realArgs.append(JSON.toJSONString(request.getParameterMap()));
            } else {
                realArgs.append(obj);
            }
        }
        return realArgs.toString();
    }

}
