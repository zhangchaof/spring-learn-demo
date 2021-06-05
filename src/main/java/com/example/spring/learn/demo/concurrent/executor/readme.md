Java SE5的java.util.concurrent包中的执行器（Executor）将为你管理Thread对象，从而简化了并发编程。
Executor在客户端和执行任务之间提供了一个间接层，Executor代替客户端执行任务。
Executor允许你管理异步任务的执行，而无须显式地管理线程的生命周期。
Executor在Java SE5/6中时启动任务的优选方法。
Executor引入了一些功能类来管理和使用线程Thread，其中包括线程池，Executor，Executors，ExecutorService，CompletionService，Future，Callable等
图片:images/1.png

CompletionService与ExecutorService最主要的区别在于submit的task不一定是按照加入时的顺序完成的。
CompletionService对ExecutorService进行了包装，内部维护一个保存Future对象的BlockingQueue。
只有当这个Future对象状态是结束的时候，才会加入到这个Queue中，take()方法其实就是Producer-Consumer中的Consumer。
它会从Queue中取出Future对象，如果Queue是空的，就会阻塞在那里，直到有完成的Future对象加入到Queue中。
所以，先完成的必定先被取出。这样就减少了不必要的等待时间。
                
