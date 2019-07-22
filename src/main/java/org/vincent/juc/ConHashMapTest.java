package org.vincent.juc;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author PengRong
 * @package org.vincent.juc
 * @date 2019/1/12 - 21:37
 * @ProjectName JavaAopLearning
 * @Description: TODO
 * <p>
 * <p>
 * <p>
 * thread.join(); 执行，表示thread 线程必须执行完 ，才能结束 执行  thread.join(); 语句的 线程；
 * 功能：该类中功能是 调用线程(main线程)在当前线程(thread)对象上进行等待
 * Thread.yield(); 静态调用方式， 他是一个静态本地方法 ，告诉调度器 当前线程可以释放CPU使用权
 * http://www.cnblogs.com/NewMan13/p/7743873.html
 * </p>
 */
public class ConHashMapTest {
    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    private static Lock lock = new ReentrantLock(true);
    private static String[] array = {"yy", "yy", "yy",
            "welcome", "welcome", "welcome", "welcome", "welcome", "welcome",
            "234", "234",
            "1234",
            "java", "java", "java", "java", "java",
            "AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA"};

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println("array size:" + array.length);
        for (String str : array) {
            Thread thread = new Thread(new MyTask(str), "ThreadName_" + str);
            thread.start();
        }
        // Thread.sleep(10);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        List<String> list =new ArrayList<>();
        while (true){
            list.add(new  String("sdfasdf"));
        }


    }

    static class MyTask implements Runnable {
        String key;

        public MyTask(String key) {
            this.key = key;
        }

        @Override
        public void run() {
            // map.putIfAbsent(key, 1);
            Integer value = map.get(key);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null == value) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(key, 1);
            } else {
                /** 只有当该 key-value 键值对不存在时候才会赋值 ，如果映射中存在该key，则返回原来的值不用新值覆盖旧值
                 *  但是如果map 的value 支持null 的话，那么这个value = null 他会判定为没有，会用新值覆盖原来的null。
                 * */
                //map.putIfAbsent(key, value+2);
                map.put(key, value + 1);
            }


            while (true) {
                lock.lock();
                try {
                    System.out.println("thread " + Thread.currentThread().getName());
                }finally {
                    lock.unlock();
                }

            }


        }

    }
}
