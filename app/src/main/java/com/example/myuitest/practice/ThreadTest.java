package com.example.myuitest.practice;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    /**
     * 何为进程?
     *
     * 进程是程序的一次执行过程，是系统运行程序的基本单位，因此进程是动态的。系统运行一个程序即是一个进程从创建，运行到消亡的过程。
     * 在 Java 中，当我们启动 main 函数时其实就是启动了一个 JVM 的进程，而 main 函数所在的线程就是这个进程中的一个线程，也称主线程。
     */

    /**
     * 何为线程?
     *
     * 线程与进程相似，但线程是一个比进程更小的执行单位。一个进程在其执行的过程中可以产生多个线程。与进程不同的是同类的多个线程共享进程的堆和方法区资源，
     * 但每个线程有自己的程序计数器、虚拟机栈和本地方法栈，所以系统在产生一个线程，或是在各个线程之间作切换工作时，负担要比进程小得多，也正因为如此，
     * 线程也被称为轻量级进程
     */

    /**
     * 请简要描述线程与进程的关系,区别及优缺点？
     *
     * 一个进程中可以有多个线程，多个线程共享进程的堆和方法区 (JDK1.8 之后的元空间)资源，但是每个线程有自己的程序计数器、虚拟机栈 和 本地方法栈。
     *
     * 总结： 线程是进程划分成的更小的运行单位。线程和进程最大的不同在于基本上各进程是独立的，而各线程则不一定，
     * 因为同一进程中的线程极有可能会相互影响。线程执行开销小，但不利于资源的管理和保护；而进程正相反。
     *
     * 堆和方法区是所有线程共享的资源，其中堆是进程中最大的一块内存，主要用于存放新创建的对象 (几乎所有对象都在这里分配内存)，
     * 方法区主要用于存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
     */

    /**
     * 程序计数器为什么是私有的?
     *
     * 程序计数器私有主要是为了线程切换后能恢复到正确的执行位置。
     */

    /**
     * 虚拟机栈和本地方法栈为什么是私有的?
     *
     * 虚拟机栈： 每个 Java 方法在执行的同时会创建一个栈帧用于存储局部变量表、操作数栈、常量池引用等信息。
     * 从方法调用直至执行完成的过程，就对应着一个栈帧在 Java 虚拟机栈中入栈和出栈的过程。
     *
     * 本地方法栈： 和虚拟机栈所发挥的作用非常相似，区别是： 虚拟机栈为虚拟机执行 Java 方法 （也就是字节码）服务，
     * 而本地方法栈则为虚拟机使用到的 Native 方法服务。 在 HotSpot 虚拟机中和 Java 虚拟机栈合二为一。
     *
     * 所以，为了保证线程中的局部变量不被别的线程访问到，虚拟机栈和本地方法栈是线程私有的。
     */

    /**
     * 并发与并行的区别
     *
     * 并发：两个及两个以上的作业在同一 时间段 内执行。
     * 并行：两个及两个以上的作业在同一 时刻 执行。
     * 最关键的点是：是否是 同时 执行。
     */

    /**
     * 同步和异步的区别
     *
     * 同步 ： 发出一个调用之后，在没有得到结果之前， 该调用就不可以返回，一直等待。
     * 异步 ：调用在发出之后，不用等待返回结果，该调用直接返回。
     */

    /**
     * 说说线程的生命周期和状态?
     *
     * NEW: 初始状态，线程被创建出来但没有被调用 start() 。
     * RUNNABLE: 运行状态，线程被调用了 start()等待运行的状态。
     * BLOCKED ：阻塞状态，需要等待锁释放。
     * WAITING：等待状态，表示该线程需要等待其他线程做出一些特定动作（通知或中断）。
     * TIME_WAITING：超时等待状态，可以在指定的时间后自行返回而不是像 WAITING 那样一直等待。
     * TERMINATED：终止状态，表示该线程已经运行完毕。
     *
     * 线程在生命周期中并不是固定处于某一个状态而是随着代码的执行在不同状态之间切换。
     */

    /**
     * 什么是上下文切换?
     *
     * 线程在执行过程中会有自己的运行条件和状态（也称上下文），比如上文所说到过的程序计数器，栈信息等。当出现如下情况的时候，线程会从占用 CPU 状态中退出。
     *
     * 主动让出 CPU，比如调用了 sleep(), wait() 等。
     * 时间片用完，因为操作系统要防止一个线程或者进程长时间占用 CPU 导致其他线程或者进程饿死。
     * 调用了阻塞类型的系统中断，比如请求 IO，线程被阻塞。
     * 被终止或结束运行
     *
     * 这其中前三种都会发生线程切换，线程切换意味着需要保存当前线程的上下文，留待线程下次占用 CPU 的时候恢复现场。并加载下一个将要占用 CPU 的线程上下文。这就是所谓的 上下文切换。
     *
     * 上下文切换是现代操作系统的基本功能，因其每次需要保存信息恢复信息，这将会占用 CPU，内存等系统资源进行处理，也就意味着效率会有一定损耗，如果频繁切换就会造成整体效率低下。
     *
     */

    /**
     * 死锁
     */
    public class DeadLockDemo {
        private Object resource1 = new Object();//资源 1
        private Object resource2 = new Object();//资源 2

        public void main(String[] args) {
            new Thread(() -> {
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource2");
                    synchronized (resource2) {
                        System.out.println(Thread.currentThread() + "get resource2");
                    }
                }
            }, "线程 1").start();

            new Thread(() -> {
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource1");
                    synchronized (resource1) {
                        System.out.println(Thread.currentThread() + "get resource1");
                    }
                }
            }, "线程 2").start();
        }
    }

    /**
     * 避免死锁
     */
    public class DeadLockDemo2 {
        private  Object resource1 = new Object();//资源 1
        private  Object resource2 = new Object();//资源 2

        public  void main(String[] args) {
            new Thread(() -> {
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource2");
                    synchronized (resource2) {
                        System.out.println(Thread.currentThread() + "get resource2");
                    }
                }
            }, "线程 1").start();

            new Thread(() -> {
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource2");
                    synchronized (resource2) {
                        System.out.println(Thread.currentThread() + "get resource2");
                    }
                }
            }, "线程 2").start();
        }
    }

    /**
     * 双重校验锁实现对象单例（线程安全）
     */
    public class Singleton {

        private volatile Singleton uniqueInstance;

        private Singleton() {
        }

        public  Singleton getUniqueInstance() {
            //先判断对象是否已经实例过，没有实例化过才进入加锁代码
            if (uniqueInstance == null) {
                //类对象加锁
                synchronized (Singleton.class) {
                    if (uniqueInstance == null) {
                        uniqueInstance = new Singleton();
                    }
                }
            }
            return uniqueInstance;
        }
    }

    /**
     * volatile 关键字能保证变量的可见性，但不能保证对变量的操作是原子性的。
     */
    public class VolatoleAtomicityDemo {
        public volatile int inc = 0;

        public void increase() {
            inc++;
        }

        public void main(String[] args) throws InterruptedException {
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            VolatoleAtomicityDemo volatoleAtomicityDemo = new VolatoleAtomicityDemo();
            for (int i = 0; i < 5; i++) {
                threadPool.execute(() -> {
                    for (int j = 0; j < 500; j++) {
                        volatoleAtomicityDemo.increase();
                    }
                });
            }
            // 等待1.5秒，保证上面程序执行完成
            Thread.sleep(1500);
            System.out.println(inc);
            threadPool.shutdown();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public class ThreadLocalExample implements Runnable {

        // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
        private final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

        public void main(String[] args) throws InterruptedException {
            ThreadLocalExample obj = new ThreadLocalExample();
            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(obj, "" + i);
                Thread.sleep(new Random().nextInt(1000));
                t.start();
            }
        }

        @Override
        public void run() {
            System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //formatter pattern is changed here by thread, but it won't reflect to other threads
            formatter.set(new SimpleDateFormat());

            System.out.println("Thread Name= " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
        }
    }
}
