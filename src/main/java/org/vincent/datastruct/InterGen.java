package org.vincent.datastruct;

/**
 * @author PengRong
 * @package org.vincent.datastruct
 * @ClassName InterGen.java
 * @date 2021/5/30 - 11:21
 * @ProjectName JavaAopLearning
 * @Description: 泛型接口, 接口存储的数据类型是 T
 */
public interface InterGen<T> {
    T next();

    default String isInterface(T t){
        return "data key："+t.getClass().getSimpleName() +" value: "+ t.toString();
    }
}
