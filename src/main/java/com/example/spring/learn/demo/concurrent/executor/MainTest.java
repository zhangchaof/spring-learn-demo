package com.example.spring.learn.demo.concurrent.executor;

/**
 * @ClassName MainTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/5/28 13:46
 **/
public class MainTest {
    public static void main(String[] args) {
        System.out.println("ExcutorServer thread pool demo show");
        int[] nums = {12890, 345678, 2345, 5678, 865, 234, 3434, 454, 4656, 67678, 678, 1234, 6789};
        ExecutorServiceParalelSumDemo mysum = new ExecutorServiceParalelSumDemo();

        System.out.println("result per ExecutorServiceParalelSumdemo = "
                + mysum.ParallelSum(nums));

        System.out.println("CompletionServiceDemo thread pool demo show");
        CompletionServiceDemo mcom = new CompletionServiceDemo();
        System.out.println("result per CompletionServiceDemo = " + mcom.ParallelSum(nums));
        System.out.println("mcom = " + "test".equals(null));
    }
}
