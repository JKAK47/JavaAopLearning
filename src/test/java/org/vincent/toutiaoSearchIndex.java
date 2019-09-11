package org.vincent;

/**
 * @author PengRong
 * @package org.vincent
 * @ClassName toutiaoMain.java
 * @date 2019/8/16 - 10:17
 * @ProjectName JavaAopLearning
 * @Description: 头条二面 面试题 代码，找出最后一个1 的index，
 * 给定一个数组，数组形式是：左边区域是 1，右边区域是0，在中间某个位置从1变成了 0 ，找出这个位置
 */
public class toutiaoSearchIndex {
    public static void main(String[] args) {
        int[] ints ={1,1,1,1,1,0,0,0,0,0,0,0};
        System.out.println(ab(ints,0,ints.length-1));
    }
    public static int ab(int[] a,int left,int right){
        if(left > right) return -1;
        if(left == right) return left;
        int middle = (left + right)/2;

        if(a[middle] == 1){
            if((middle +1<= right) && a[middle+1] ==0){
                return middle;
            }else{
                return ab(a,middle+1,right);
            }

        }else{
            if((left <= middle -1) && a[middle -1] == 1){
                return middle-1;
            }else{
                return ab(a,left,middle);
            }

        }
    }
}
