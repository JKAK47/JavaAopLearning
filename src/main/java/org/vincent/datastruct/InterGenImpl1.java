package org.vincent.datastruct;

import java.util.function.Function;

/**
 * @author PengRong
 * @package org.vincent.datastruct
 * @ClassName InterGenImpl1.java
 * @date 2021/5/30 - 11:21
 * @ProjectName JavaAopLearning
 * @Description: 泛型解
 */
public class InterGenImpl1 implements InterGen<String>{
    @Override
    public String next() {
        System.out.println("next String ");
        return "asdf";
    }

    public static void main(String[] args) {
        System.out.println(new InterGenImpl1().next());
        System.out.println(new InterGenImpl1().isInterface("sdfas"));

    }
}
