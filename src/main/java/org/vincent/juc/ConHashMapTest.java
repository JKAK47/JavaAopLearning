package org.vincent.juc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author PengRong
 * @package org.vincent.juc
 * @date 2019/1/12 - 21:37
 * @ProjectName JavaAopLearning
 * @Description: TODO
 *
 * <p>
 *
 *     thread.join(); 执行，表示thread 线程必须执行完 ，才能结束 执行  thread.join(); 语句的 线程；功效：该类中功能是 调用线程(main线程)在当前线程(thread)对象上进行等待
 *     Thread.yield(); 静态调用方式， 他是一个静态本地方法 ，告诉调度器 当前线程可以释放CPU使用权
 *     http://www.cnblogs.com/NewMan13/p/7743873.html
 * </p>
 */
public class ConHashMapTest {
    private static ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<String,Integer>();
    private static String[] array = {"yy","yy","yy",
            "welcome","welcome","welcome","welcome","welcome","welcome",
            "234","234",
            "1234",
            "java","java","java","java","java",
            "AAAAAA","AAAAAA","AAAAAA","AAAAAA","AAAAAA"};

    public static void main(String[] args) throws InterruptedException {

        System.out.println("array size:"+array.length);
        for (String str : array) {
            Thread thread= new Thread(new MyTask(str));
            thread.start();
            thread.join();
        }
       // Thread.sleep(10);

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    static class MyTask implements Runnable{
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
            if(null == value){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(key, 1);
            }else{
                map.put(key, value + 1);
            }
        }

    }
}
