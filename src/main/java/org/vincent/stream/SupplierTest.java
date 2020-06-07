package org.vincent.stream;

import java.util.Optional;

/**
 * @author PengRong
 * @package org.vincent.stream
 * @ClassName SupplierTest.java
 * @date 2020/6/7 - 17:30
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class SupplierTest {
    public static void main(String[] args) {
        System.out.println(Optional.ofNullable(null).orElse("else"));
        System.out.println(Optional.ofNullable(null).orElseGet(() -> "get default"));
        Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("不能为空指针"));
    }
}
