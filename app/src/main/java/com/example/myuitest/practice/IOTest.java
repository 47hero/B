package com.example.myuitest.practice;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOTest {

    private void Input() throws IOException {
        /**
         * 装饰器模式 更侧重于动态地增强原始类的功能，装饰器类需要跟原始类继承相同的抽象类或者实现相同的接口。并且，装饰器模式支持对原始类嵌套使用多个装饰器
         */
        BufferedInputStream  bis = new BufferedInputStream(new FileInputStream("input.txt"));

        /**
         * 适配器模式 更侧重于让接口不兼容而不能交互的类可以一起工作，当我们调用适配器对应的方法时，
         * 适配器内部会调用适配者类或者和适配类相关的类的方法，这个过程透明的。
         * 就比如说 StreamDecoder （流解码器）和StreamEncoder（流编码器）就是分别基于
         * InputStream 和 OutputStream 来获取 FileChannel对象并调用对应的 read 方法和 write 方法进行字节数据的读取和写入。
         */
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("input.txt"), "UTF-8"));

        String generatorLogoPath = " ";
        /**
         * 工厂模式用于创建对象，NIO 中大量用到了工厂模式，比如 Files 类的 newInputStream 方法用于创建 InputStream 对象（静态工厂）、
         * Paths 类的 get 方法创建 Path 对象（静态工厂）、ZipFileSystem 类（sun.nio包下的类，
         * 属于 java.nio 相关的一些内部实现）的 getPath 的方法创建 Path 对象（简单工厂）。
         */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            InputStream is = Files.newInputStream(Paths.get(generatorLogoPath));
        }

        /**
         * NIO 中的文件目录监听服务使用到了观察者模式。
         * NIO 中的文件目录监听服务基于 WatchService 接口和 Watchable 接口。WatchService 属于观察者，Watchable 属于被观察者。
         * Watchable 接口定义了一个用于将对象注册到 WatchService（监控服务） 并绑定监听事件的方法 register 。
         */


        /**
         * Java 中 3 种常见 IO 模型
         * BIO (Blocking I/O)
         * BIO 属于同步阻塞 IO 模型 。
         *
         * 同步阻塞 IO 模型中，应用程序发起 read 调用后，会一直阻塞，直到内核把数据拷贝到用户空间。
         *
         * 在客户端连接数量不高的情况下，是没问题的。但是，当面对十万甚至百万级连接的时候，传统的 BIO 模型是无能为力的。因此，我们需要一种更高效的 I/O 处理模型来应对更高的并发量。
         */

        /**
         * NIO (Non-blocking/New I/O)
         * Java 中的 NIO 于 Java 1.4 中引入，对应 java.nio 包，提供了 Channel , Selector，Buffer 等抽象。NIO 中的 N 可以理解为 Non-blocking，
         * 不单纯是 New。它是支持面向缓冲的，基于通道的 I/O 操作方法。 对于高负载、高并发的（网络）应用，应使用 NIO 。
         *
         * Java 中的 NIO 可以看作是 I/O 多路复用模型。也有很多人认为，Java 中的 NIO 属于同步非阻塞 IO 模型。
         *
         * 同步非阻塞 IO 模型中，应用程序会一直发起 read 调用，等待数据从内核空间拷贝到用户空间的这段时间里，线程依然是阻塞的，直到在内核把数据拷贝到用户空间。
         * 相比于同步阻塞 IO 模型，同步非阻塞 IO 模型确实有了很大改进。通过轮询操作，避免了一直阻塞。
         * 但是，这种 IO 模型同样存在问题：应用程序不断进行 I/O 系统调用轮询数据是否已经准备好的过程是十分消耗 CPU 资源的。
         *
         * IO 多路复用模型中，线程首先发起 select 调用，询问内核数据是否准备就绪，等内核把数据准备好了，
         * 用户线程再发起 read 调用。read 调用的过程（数据从内核空间 -> 用户空间）还是阻塞的。
         * IO 多路复用模型，通过减少无效的系统调用，减少了对 CPU 资源的消耗。
         * Java 中的 NIO ，有一个非常重要的选择器 ( Selector ) 的概念，也可以被称为 多路复用器。通过它，
         * 只需要一个线程便可以管理多个客户端连接。当客户端数据到了之后，才会为其服务。
         */

        /**
         * AIO (Asynchronous I/O)
         *
         * AIO 也就是 NIO 2。Java 7 中引入了 NIO 的改进版 NIO 2,它是异步 IO 模型。
         * 异步 IO 是基于事件和回调机制实现的，也就是应用操作之后会直接返回，不会堵塞在那里，当后台处理完成，操作系统会通知相应的线程进行后续的操作。
         */
    }



}
