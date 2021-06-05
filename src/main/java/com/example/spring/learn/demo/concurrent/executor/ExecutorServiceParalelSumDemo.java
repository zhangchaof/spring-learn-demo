package com.example.spring.learn.demo.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName ExecutorServiceParalelSumDemo
 * @Description:
 *   并行计算求和.
 *   本例中，把一个整数数组的求和分解到每个线程中，每个线程求该数值的部分和，
 *   然后主程序把各个和再次求和就能得到最后的数字。从这个架构上跟mapreduce有点神似。
 *
 * 在本例子中，getResult()方法的实现过程中，迭代了FutureTask的数组，
 * 如果任务还没有完成则当前线程会阻塞，如果我们希望任意任务完成后就把其结果加到result中，而不用依次等待每个任务完成，可以使用CompletionService。
 *
 *
 * @Author clark
 * @Date 2021/5/28 11:44
 **/
public class ExecutorServiceParalelSumDemo {
    private int coreCpuNum;
    private ExecutorService executor;

    /**
     * save the result of each thread's sum calculation
     *
     */
    private List<FutureTask<Long>> tasks = new ArrayList<FutureTask<Long>>();

    public ExecutorServiceParalelSumDemo() {
        coreCpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println("this host has " + coreCpuNum + " CPU(s)");

        //for before Java 8.0
        //executor = Executors.newFixedThreadPool(coreCpuNum);
        //this CPU parallelism API is Java8 or later ONLY
        executor = Executors.newWorkStealingPool(coreCpuNum);
    }

    /**
     * thread main body
     */
    class CalculatorTask implements Callable<Long> {
        int nums[];
        int start;
        int end;

        public CalculatorTask(final int nums[], int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += nums[i];
            }

            return sum;
        }
    }

    private long getFinalSum() {
        long sum = 0;
        System.out.println(tasks.size() + " future tasks in pool");
        for (int i = 0; i < tasks.size(); i++) {
            try {
                /*
                 * If this future's thread not return its result,
                 * get() will block here. So perf issue introduced.
                 * we can use CompletionService to solve this potential issue.
                 */
                sum += tasks.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    public long ParallelSum(int[] nums) {
        int start, end, increment;

        // 根据CPU核心个数拆分任务，创建每个thread和对应的 FutureTask，并提交到ExecutorService中。
        for (int i = 0; i < coreCpuNum; i++) {
            increment = (nums.length / coreCpuNum) + 1;
            start = i * increment;
            end = start + increment;
            if (end > nums.length) {
                end = nums.length;
            }

            //create thread tasks
            CalculatorTask calculator = new CalculatorTask(nums, start, end);
            //create each future result per thread task
            FutureTask<Long> task = new FutureTask<Long>(calculator);
            tasks.add(task);

            if (!executor.isShutdown()) {
                //execute() can't return result
                executor.submit(task);
            }
        }

        return getFinalSum();
    }

    public void close() {
        executor.shutdown();
    }
}
