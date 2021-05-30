package org.vincent.datastruct;

/**
 * @author PengRong
 * @package org.vincent.datastruct
 * @ClassName InterGenImpl1.java
 * @date 2021/5/30 - 11:21
 * @ProjectName JavaAopLearning
 * @Description: 泛型接口2
 */
public class InterGenImpl2 implements InterGen<Integer>{

    @Override
    public Integer next() {
        System.out.println("next Integer");
        return 0;
    }

    public static void main(String[] args) {
        new InterGenImpl2().next();
        System.out.println(new InterGenImpl2().isInterface(10));
    }
}
