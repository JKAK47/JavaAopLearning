package org.vincent.deadlock;

/**
 * @author PengRong
 * @package org.vincent.deadlock
 * @ClassName JStackDemo.java
 * @date 2019/7/21 - 16:39
 * @ProjectName JavaAopLearning
 * @Description: 死锁 案例
 */
public class JStackDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new DeadLockclass(true),"DeadLock-Demo-1");//建立一个线程
        Thread t2 = new Thread(new DeadLockclass(false),"DeadLock-Demo-2");//建立另一个线程
        t1.start();//启动一个线程
        Thread.sleep(100);
        t2.start();//启动另一个线程
    }
}
class DeadLockclass implements Runnable {
    public boolean falg;// 控制线程
    DeadLockclass(boolean falg) {
        this.falg = falg;
    }
    public void run() {
        /**
         * 如果falg的值为true则调用t1线程
         */
        if (falg) {
            while (true) {
                synchronized (Suo.o1) {
                    System.out.println("o1 " + Thread.currentThread().getName());
                    synchronized (Suo.o2) {
                        System.out.println("o2 " + Thread.currentThread().getName());
                    }
                }
            }
        }
        /**
         * 如果falg的值为false则调用t2线程
         */
        else {
            while (true) {
                synchronized (Suo.o2) {
                    System.out.println("o2 " + Thread.currentThread().getName());
                    synchronized (Suo.o1) {
                        System.out.println("o1 " + Thread.currentThread().getName());
                    }
                }
            }
        }
    }
}

class Suo {
    static Object o1 = new Object();
    static Object o2 = new Object();
}

