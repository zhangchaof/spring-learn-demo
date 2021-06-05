package com.example.spring.learn.demo.concurrent.executorService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @ClassName ExecutorServiceTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/5/25 13:58
 **/
public class ExecutorServiceTest {
    /**
     * ExecutorService有如下几个执行方法：
     * - execute(Runnable)
     * - submit(Runnable)
     * - submit(Callable)
     * - invokeAny(...)
     * - invokeAll(...)
     * 当我们使用完成ExecutorService之后应该关闭它，否则它里面的线程会一直处于运行状态。
     *
     * 举个例子，如果的应用程序是通过main()方法启动的，在这个main()退出之后，如果应用程序中的ExecutorService没有关闭，这个应用将一直运行。之所以会出现这种情况，是因为ExecutorService中运行的线程会阻止JVM关闭。
     *
     * 如果要关闭ExecutorService中执行的线程，我们可以调用ExecutorService.shutdown()方法。在调用shutdown()方法之后，ExecutorService不会立即关闭，但是它不再接收新的任务，直到当前所有线程执行完成才会关闭，所有在shutdown()执行之前提交的任务都会被执行。
     *
     * 如果我们想立即关闭ExecutorService，我们可以调用ExecutorService.shutdownNow()方法。这个动作将跳过所有正在执行的任务和被提交还没有执行的任务。但是它并不对正在执行的任务做任何保证，有可能它们都会停止，也有可能执行完成。
     *
     */
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 这个方法有个问题，就是没有办法获知task的执行结果。如果我们想获得task的执行结果，我们可以传入一个Callable的实例（下面会介绍）。
     */
    public void executeRunnableTest() {
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        executorService.shutdown();
    }

    /**
     * submit(Runnable)和execute(Runnable)区别是前者可以返回一个Future对象，通过返回的Future对象，我们可以检查提交的任务是否执行完毕
     * 如果任务执行完成，future.get()方法会返回一个null。注意，future.get()方法会产生阻塞。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void submitRunnableTest() throws ExecutionException, InterruptedException {
        Future future = executorService.submit(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        future.get();  //returns null if the task has finished correctly.
    }

    /**
     * submit(Callable)和submit(Runnable)类似，也会返回一个Future对象，
     * 但是除此之外，submit(Callable)接收的是一个Callable的实现，Callable接口中的call()方法有一个返回值，可以返回任务的执行结果，而Runnable接口中的run()方法是void的，没有返回值
     * 如果任务执行完成，future.get()方法会返回Callable任务的执行结果。注意，future.get()方法会产生阻塞。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void submitCallableTest() throws ExecutionException, InterruptedException {
        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println("future.get() = " + future.get());
    }

    /**
     * invokeAny(...)方法接收的是一个Callable的集合，执行这个方法不会返回Future，但是会返回所有Callable任务中其中一个任务的执行结果。这个方法也无法保证返回的是哪个任务的执行结果，反正是其中的某一个
     * 大家可以尝试执行下面代码，每次执行都会返回一个结果，并且返回的结果是变化的，可能会返回“Task2”也可是“Task1”或者其它。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void invokeAnyTest() throws ExecutionException, InterruptedException {
        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        String result = executorService.invokeAny(callables);
        System.out.println("result = " + result);
        executorService.shutdown();
    }

    /**
     * invokeAll(...)与 invokeAny(...)类似也是接收一个Callable集合，但是前者执行之后会返回一个Future的List，其中对应着每个Callable任务执行后的Future对象
     *
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void invokeAllTest() throws ExecutionException, InterruptedException {
        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        List<Future<String>> futures = executorService.invokeAll(callables);

        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }

        executorService.shutdown();
    }

}
