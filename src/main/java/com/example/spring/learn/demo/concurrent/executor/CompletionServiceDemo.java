package com.example.spring.learn.demo.concurrent.executor;

import java.util.concurrent.*;

/**
 * @ClassName CompletionServiceDemo
 * @Description: CompletionService与ExecutorService最主要的区别在于
 * 前者submit的task不一定是按照加入时的顺序完成的。CompletionService对ExecutorService进行了包装，
 * 内部维护一个保存Future对象的BlockingQueue。
 * 只有当这个Future对象状态是结束的时候，才会加入到这个Queue中，take()方法其实就是Producer-Consumer中的Consumer。
 * 它会从Queue中取出Future对象，如果Queue是空的，就会阻塞在那里，直到有完成的Future对象加入到Queue中。
 * 所以，先完成的必定先被取出。这样就减少了不必要的等待时间。
 * @Author clark
 * @Date 2021/5/28 11:49
 **/
public class CompletionServiceDemo {
    /**
     * 并行计算求和.
     * 本例中，把一个整数数组的求和分解到每个线程中，每个线程求该数值的部分和，
     * 然后主程序把各个和再次求和就能得到最后的数字。从这个架构上跟mapreduce有点神似。
     */

    private int coreCpuNum;
    private ExecutorService executor;

    /**
     * CompletionService has a internal bloking queue to save the result of each
     * thread's sum calculation. so List<FutureTask<Long>> tasks appears unnecessary now
     */
    private CompletionService<Long> mcs;
    /*
     * save the result of each thread's sum calculation
     *
     */

    public CompletionServiceDemo() {
        coreCpuNum = Runtime.getRuntime().availableProcessors();

        System.out.println("this host has " + coreCpuNum + " CPU(s)");

        //for before Java 8.0
        //executor = Executors.newFixedThreadPool(coreCpuNum);

        //this CPU parallelism API is Java8 or later ONLY
        executor = Executors.newWorkStealingPool(coreCpuNum);
        mcs = new ExecutorCompletionService<>(executor);
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
        for (int i = 0; i < coreCpuNum; i++) {
            try {
                /*
                 * get one complete result from CompletionServer internal
                 * blocking queue
                 */
                sum += mcs.take().get();
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
            CalculatorTask mthread = new CalculatorTask(nums, start, end);
            if (!executor.isShutdown()) {
                mcs.submit(mthread);
            }
        }

        return getFinalSum();
    }

    public void close() {
        executor.shutdown();

    }
}
