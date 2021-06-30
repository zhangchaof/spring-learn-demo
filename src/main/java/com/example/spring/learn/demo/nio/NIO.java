package com.example.spring.learn.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName NIO
 * @Description: 简单的自己动手实现一个基于Reactor模型的通信服务端：基于阻塞队列，
 * 将连接的channel放入工作线程的队列里，由工作线程各自去消费自己的队列里的channel然后注册到系统内核，
 * 由各自工作线程处理channel的事件
 * @Author clark
 * @Date 2021/6/28 9:56
 **/
public class NIO {
    /**
     * 指定ByteBuffer缓冲区大小
     */
    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    //储存io事件处理线程
    private static final Set<WorkThread> selectorThreads = new HashSet<>();

    static int defaultThreads;

    static {
        defaultThreads = Runtime.getRuntime().availableProcessors() * 2;
    }

    public static void main(String[] args) throws Exception {

        //开放服务端4003端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(4003));

        //创建事件处理线程池，专门处理IO事件的读、写
        buildWorkThread(defaultThreads);
        start();

        //开辟一个线程专门处理线程的连接事件
        new Thread(() -> {
            Collection<WorkThread> workThreads = Collections.unmodifiableCollection(selectorThreads);
            Iterator<WorkThread> workThreadIterator = workThreads.iterator();

            try {
                //创建一个selector 查询器
                Selector selector = Selector.open();
                //将通道设置为非阻塞
                serverSocketChannel.configureBlocking(false);
                ///将端口的channel连接事件注册给selector
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {
                    //向系统内核查询有连接事件的注册channel，若没有则阻塞
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    //若工作事件池遍历完成，重头开始遍历工作线程池
                    if (!workThreadIterator.hasNext()) {
                        workThreadIterator = workThreads.iterator();
                    }

                    WorkThread workThread = workThreadIterator.next();

                    //遍历系统内核返回的连接事件
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        //拿到连接的channel，放到工作线程池里的队列
                        SocketChannel socket = serverSocketChannel.accept();
                        workThread.offer(socket);
                    }

                    workThread.wakeup();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }).start();

    }

    //创建若干工作线程
    static void buildWorkThread(int workNum) throws Exception {
        int i = 0;
        while (i++ < workNum) {
            selectorThreads.add(new WorkThread());
        }
    }

    //启动工作线程
    static void start() {
        Iterator<WorkThread> iterator = selectorThreads.iterator();
        while (iterator.hasNext()) {
            iterator.next().start();
        }
    }

    static class WorkThread extends Thread {
        private final Selector selector;
        private final BlockingQueue<SocketChannel> blockingQueue = new LinkedBlockingDeque<>();

        //对象实例化时，创建一个查询器
        WorkThread() throws IOException {
            selector = Selector.open();
        }

        public void wakeup() {
            selector.wakeup();
        }

        public void offer(SocketChannel socketChannel) {
            blockingQueue.offer(socketChannel);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //从队列里取出连接channel
                    SocketChannel socket = blockingQueue.poll();
                    //向系统内核注册读事件
                    if (Objects.nonNull(socket)) {

                        socket.configureBlocking(false);
                    }
                    socket.register(selector, SelectionKey.OP_READ);
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        selectionKeys.remove(selectionKey);
                        //获取有读事件的channel
                        if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            if (Objects.nonNull(channel)) {
                                byteBuffer.clear();
                                //将流数据读取到用户缓冲区
                                channel.read(byteBuffer);
                                //将byteBuffer读取位置设置为0
                                byteBuffer.flip();
                                //取出字节
                                int limit = byteBuffer.limit();
                                byte[] bytes = new byte[limit];
                                byteBuffer.get(bytes);

                                System.out.println(new String(bytes, "utf-8"));

                                //将关注事件设为可写状态
                                selectionKey.interestOps(SelectionKey.OP_WRITE);
                            }
                        }
                        //开始向channel写入回复消息
                        if (selectionKey.isWritable()) {

                            SocketChannel channel = (SocketChannel) selectionKey.channel();

                            byteBuffer.clear();
                            byteBuffer.put("hello".getBytes());

                            byteBuffer.flip();
                            channel.write(byteBuffer);
                            selectionKey.interestOps(SelectionKey.OP_READ);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
