package org.vincent.sorted;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author PengRong
 * @package org.vincent.sorted
 * @ClassName QuickSorted.java
 * @date 2019/8/11 - 10:49
 * @ProjectName JavaAopLearning
 * @Description: 快速排序 实现( 分治思想实现)
 */
public class QuickSorted {
    // 快速排序
    public static void quickSort(int[] table) {
        quickSort(table, 0, table.length - 1);

    }


    private static void quickSort(int[] table, int begin, int end) {
        /** 控制 边界，防止越界，递归结束条件  */
        if (begin >= end) {
            return;
        }
        int pivotIndex = partitionV1(table, begin, end);
        /**
         * 下面对基准值左右两个子序列递归使用快速排序实现
         */
        quickSort(table, begin, pivotIndex - 1);
        quickSort(table, pivotIndex + 1, end);

    }

    /**
     * 根据数组下标选取一个基准值，然后将
     * <p>
     * 基准值左边的数据 小于基准值；
     * 基准值右边的数据大于 基准值。
     * 该方法涉及到基准值的左右两边的 数据调整 所以称为双边循环法
     *
     * @param table 待排序的数组
     * @param begin 待排序的数组 左下标
     * @param end   待排序的数组 右下标
     * @return
     */
    private static int partitionV1(int[] table, int begin, int end) {
        // 序列要有效
        int L = begin, R = end;
        // 基准值取最右边的一个值
        int base = table[R];
        // 开始一趟遍历，只有L == R 时候才会退出循环
        while (L != R) {
            while ((L < R) && (table[L] <= base)) {
                L++;
            }
            /** 将L 下标的元素赋值给 R下标位置 */
            if (L < R) {
                table[R--] = table[L];
            }
            while ((L < R) && (table[R] >= base)) {
                R--;
            }
            if (L < R) {
                table[L++] = table[R];
            }
        }
        // 将基准值赋值到准确位置，使的基准值左边的所有元素 都小于 基准值，右边的元素 都大于 基准值
        table[L] = base;
        return L;
    }

    /**
     * 基于单边循环实现的分区函数
     *
     * @param table
     * @param begin
     * @param end
     * @return
     */
    private static int partitionV2(int[] table, int begin, int end) {
        /**
         * mark 标记了不大于基准值的下标区域；
         * 默认取 最左边第一个元素
         *
         * */
        int mark = begin;
        /**
         * 基准值取最左边的一个值, mark 标记了不大于基准值的下标区域
         */
        int base = table[begin];
        /** 从基准值 后一个元素开始遍历，
         * 每遇到一个 下标元素 小于 base 基准值的
         * */
        for (int i = begin + 1; i <= end; i++) {
            /** 如果存在一个元素小于基准值 ，那么要扩充 mark  小于的基准值的范围  */
            if (table[i] < base) {
                mark++;
                int p = table[mark];
                table[mark] = table[i];
                table[i] = p;
            }
        }
        /** 最后将 基准值 和 mark标记 位置两个元素 交换  */
        table[begin] = table[mark];
        table[mark] = base;
        return mark;
    }

    public static void quickSortStack(int[] table) {
        quickSortStack(table, 0, table.length - 1);

    }

    /**
     * 基于栈实现 非递归 算法
     *
     * @param table
     * @param startIndex
     * @param endIndex
     */
    private static void quickSortStack(int[] table, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        String startIndexKey = "start-key";
        String endIndexKey = "end-key";
        /**
         * 用一个栈来存放函数递归调用的集合栈
         * */
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        Map<String, Integer> rootParam = new HashMap<>(8);
        rootParam.put(startIndexKey, startIndex);
        rootParam.put(endIndexKey, endIndex);
        quickSortStack.push(rootParam);
        /**
         * 循环条件结束：栈为空
         * */
        while (!quickSortStack.isEmpty()) {
            /** 弹出一个下标区间 */
            Map<String, Integer> pop = quickSortStack.pop();
            Integer popStartIndex = pop.get(startIndexKey);
            Integer popEndIndex = pop.get(endIndexKey);
            /** 对指定区间进行 */
            int pivotIndex = partitionStack(table, popStartIndex, popEndIndex);
            if (popStartIndex < pivotIndex - 1) {
                Map<String,Integer> leftParam =new HashMap<>();
                leftParam.put(startIndexKey, popStartIndex);
                leftParam.put(endIndexKey, pivotIndex-1);
                quickSortStack.push(leftParam);
            }
            if (pivotIndex + 1 < popEndIndex) {
                Map<String,Integer> rightParam =new HashMap<>();
                rightParam.put(startIndexKey, pivotIndex +1);
                rightParam.put(endIndexKey, popEndIndex);
                quickSortStack.push(rightParam);
            }
        }

    }

    /**
     * 基于 堆栈实现 分区排序
     * @param table
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int partitionStack(int[] table, Integer startIndex, Integer endIndex) {
        /**
         * mark 标记了不大于基准值的下标区域；
         * 默认取 最左边第一个元素
         *
         * */
        int mark = startIndex;
        /**
         * 基准值取最左边的一个值, mark 标记了不大于基准值的下标区域*/
        int pivot = table[startIndex];
        /** 从基准值 后一个元素开始遍历，
         * 每遇到一个 下标元素 小于 base 基准值的就  需要替换。
         * */
        for (int i = startIndex + 1; i <= endIndex; i++) {
            /** 如果存在一个元素小于基准值 ，那么要扩充 mark  小于的基准值的范围  */
            if (table[i] < pivot) {
                mark++;
                int p = table[mark];
                table[mark] = table[i];
                table[i] = p;
            }
        }
        /** 最后将 基准值 和 mark标记 位置两个元素 交换  */
        table[startIndex] = table[mark];
        table[mark] = pivot;
        return mark;
    }
}
