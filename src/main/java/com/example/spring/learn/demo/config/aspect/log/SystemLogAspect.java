package com.example.spring.learn.demo.config.aspect.log;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author clark
 */
@Component
@Aspect
@Slf4j
public class SystemLogAspect {

    @Pointcut("execution(* com.example.spring.learn.demo.controller.*.*(..)) ")
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
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        Object[] args = pjp.getArgs();
        String targetClassName = pjp.getTarget().getClass().getName();
        try {
            Class<?> clazz = Class.forName(targetClassName);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    if (args.length == method.getParameterCount()) {

                        SystemLogStrategy strategy = new SystemLogStrategy();
                        strategy.setClassName(className);
                        strategy.setMethodName(methodName);

                        SystemControllerLog systemControllerLog = method.getAnnotation(SystemControllerLog.class);
                        if (null != systemControllerLog) {
                            strategy.setArguments(JSON.toJSONString(args));
                            strategy.setDescription(systemControllerLog.description());
                            strategy.setAsync(systemControllerLog.async());
                            strategy.setLocation(AnnotationTypeEnum.CONTROLLER.getName());
                            return strategy;
                        }
                        SystemServiceLog systemServiceLog = method.getAnnotation(SystemServiceLog.class);
                        if (null != systemServiceLog) {
                            strategy.setArguments(JSON.toJSONString(args));
                            strategy.setDescription(systemServiceLog.description());
                            strategy.setAsync(systemServiceLog.async());
                            strategy.setLocation(AnnotationTypeEnum.SERVICE.getName());
                            return strategy;
                        }

                        return null;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
