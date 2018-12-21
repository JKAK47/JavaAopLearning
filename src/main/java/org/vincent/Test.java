package org.vincent;

import java.util.Random;

/**
 * Created by PengRong on 2018/12/21.
 */
public class Test {

    static final int MAX = 1000000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) throws Exception {
        //为长度为10的Integer数组随机赋值
        Integer[] sample = new Integer[10];
        Random random = new Random(1000);
        for (int i = 0; i < sample.length; i++) {
            sample[i] = random.nextInt();
        }
        //记录程序开始时间
        long t = System.currentTimeMillis();
        //使用/不使用intern方法为10万个String赋值，值来自于Integer数组的10个数
        for (int i = 0; i < MAX; i++) {
            // 152 153 144 ms
            // arr[i] = new String(String.valueOf(sample[i % sample.length]));
            // 275 261 246
            arr[i] = new String(String.valueOf(sample[i % sample.length])).intern();
        }
        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();
    }

}
