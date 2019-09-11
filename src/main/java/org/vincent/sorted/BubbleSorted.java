package org.vincent.sorted;

/**
 * @author PengRong
 * @package org.vincent.sorted
 * @ClassName BubbleSorted.java
 * @date 2019/8/11 - 10:15
 * @ProjectName JavaAopLearning
 * @Description: 冒泡排序实现案例， 默认实现 升序排序
 */
public class BubbleSorted {
    /**
     * 冒泡排序第零版
     * 123
     *
     * @param table
     */
    public static void bubbleSortV0(int[] table) {
        // 总共比较n-1趟，每趟使得的最大值 沉入数组后面
        for (int i = 1; i < table.length; i++) {
            // 每趟比较 n-i 次
            for (int j = 0; j < (table.length - i); j++)
                if (table[j] > table[j + 1]) {
                    int temp = table[j];
                    table[j] = table[j + 1];
                    table[j + 1] = temp;
                }
        }
    }

    /**
     * 冒泡排序第一版
     * 增加一个 标识 ，如果一趟下来没有进行替换说明是已经有序了
     *
     * @param table
     */
    public static void bubbleSortV1(int[] table) {
        // 一趟比较下来是否交换的标记
        boolean exchange = true;
        // 总共比较n-1趟，每趟使得的最大值在沉入数组下面
        for (int i = 1; (i < table.length) && exchange; i++) {
            // 标记还没有交换
            exchange = false;
            // 每趟比较 n-i 次
            for (int j = 0; j < (table.length - i); j++)
                if (table[j] > table[j + 1]) {
                    int temp = table[j];
                    table[j] = table[j + 1];
                    table[j + 1] = temp;
                    /** 如果进行了交换 就 标识 还要继续替换 */
                    exchange = true;
                }
        }
    }

    /**
     * @param table
     * 记录替换边界下标然后划分有界区和无界区 是错误的算法
     *
     */
    public static void bubbleSortV2(int[] table) {
        // 一趟比较下来是否交换的标记
        boolean exchange = true;
        int sortBorder = 0;
        // 总共比较n-1趟，每趟使得的最大值在沉入数组下面
        for (int i = 1; (i < table.length) && exchange; i++) {
            // 标记还没有交换
            exchange = false;
            /** 记录最大无序区*/
            sortBorder = table.length - i;
            // 每趟比较 n-i 次
            for (int j = 0; j < sortBorder; j++)
                if (table[j] > table[j + 1]) {
                    int temp = table[j];
                    table[j] = table[j + 1];
                    table[j + 1] = temp;
                    /** 如果进行了交换 就 标识 还要继续替换 */
                    exchange = true;
                    /** 记录新的有序区边界*/
                    sortBorder = j;
                }
        }
    }


}
