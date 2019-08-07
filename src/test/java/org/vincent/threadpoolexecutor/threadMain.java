package org.vincent.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author PengRong
 * @package org.vincent.threadpoolexecutor
 * @ClassName threadMain.java
 * @date 2019/7/30 - 7:46
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class threadMain {
    /**
     * 自定义线程工厂，
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-test-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            /** 设置异常处理器 处理未捕获异常 */
            t.setUncaughtExceptionHandler(new UEHLogger());
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8),
            new DefaultThreadFactory(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(" 产生任务拒绝策略，直接丢弃任务");
        }
    }) {
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            System.out.println("------------ 开始执行任务：------------" + t.getName());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            System.out.println("------------ 执行任务抛异常：------------" + t);
        }
    };

    /**
     * 测试线程池
     */
    public static void main(String[] args) {
        /*
        * for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    int a = 2 / 0;
                } catch (Throwable e) {
                    throw e;
                }
            });
        }
        * */
        /**
         * submit 提交的任务 不可被 UncaughtExceptionHandler 接受处理
         * */
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    int a = 2 / 0;
                } catch (Throwable e) {
                    throw e;
                }
            });
        }
    }

    static class UEHLogger implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(" UncaughtExceptionHandler 针对未捕获异常线程退出时，JVM 将异常告知应用程序；UEHLogger >> " + t.getName());
        }
    }
}
