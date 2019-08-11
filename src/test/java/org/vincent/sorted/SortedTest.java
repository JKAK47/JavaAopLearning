package org.vincent.sorted;

import java.util.Arrays;

/**
 * @author PengRong
 * @package org.vincent.sorted
 * @ClassName SortedTest.java
 * @date 2019/8/11 - 10:16
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class SortedTest {
    public static void main(String[] args) {
        int[] array = new int[]{1,5,60,18,99,10,20};
        print(array);
        //BubbleSorted.bubbleSortV2(array);
        //QuickSorted.quickSort(array);
        QuickSorted.quickSortStack(array);
        print(array);

    }
    public static void print(int[] table){
        System.out.print("[ ");
        for (int i=0;i<table.length;i++){
            System.out.print(table[i]+", ");
        }
        System.out.println(" ]");
    }
}
