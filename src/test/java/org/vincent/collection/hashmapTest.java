package org.vincent.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PengRong
 * @package org.vincent.collection
 * @ClassName hashmapTest.java
 * @date 2019/7/21 - 8:54
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */

public class hashmapTest {
    public static void main(String[] args) {
        int num =11;
        printNum(num);
        printNum(num<<1);
        printNum(num>>1);
        //printNum(num<<<1);
        printNum(num>>>1);
        num=-11;
        printNum(num);
        printNum(num<<1);
        printNum(num>>1);
        Map hashMap =new HashMap<String,String>(12,0.2f);
        hashMap.put("asd", "");


    }

    private static void printNum(int num) {
        System.out.println("num ="+ num +" BinaryNum = "+Integer.toBinaryString(num));
    }

}
