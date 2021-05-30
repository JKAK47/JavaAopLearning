package org.vincent.datastruct;

import java.util.function.Function;

/**
 * @author PengRong
 * @package org.vincent.datastruct
 * @ClassName FunctionTest.java
 * @date 2021/5/30 - 12:01
 * @ProjectName JavaAopLearning
 * @Description: 函数式接口 Function
 */
public class FunctionTest {
    public static void main(String[] args) {
        function1();
        functionandThen();
        functioncompose();
    }

    private static void function1()  {
        // 将入参S 解析为整数并加上 50( 从 入参S 类型数据转换为 R 类型)
        Function<String, Integer> function = s -> {
            Integer result = Integer.parseInt(s) + 50;
            System.out.println(result);
            return result;
        };
        System.out.println(function.apply("100"));
    }
    private static void functionandThen() {
        // 将入参S 解析为整数并加上 50( 从 入参S 类型数据转换为 R 类型)
        Function<String, Integer> function = s -> {
            Integer result = Integer.parseInt(s) + 50;
            System.out.println(result);
            return result;
        };
        Function<Integer,Integer> two = s -> s*10;
        // tt 数据类型变更 String -> Integer -> Integer( 或者 Number 类型即可)
        Function<String ,Number> tt = function.andThen(two);
        System.out.println(tt.apply("700"));
        System.out.println(function.andThen(two).apply("100"));
    }

    private static void functioncompose() {
        // 将入参S 解析为整数并加上 50( 从 入参S 类型数据转换为 R 类型)
        Function<String, Integer> function = s -> {
            Integer result = Integer.parseInt(s) + 40;
            System.out.println(result);
            return result;
        };
        Function<String,String> two = s -> {
            String upperStr = s.toUpperCase();
            System.out.println("upperStr = "+upperStr);
            return upperStr;
        };
        // tt 数据类型变更 String -> String -> Integer( 或者 Number 类型即可)
        // compose 方法先执行 two apply方法然后执行 function 方法
        Function<String ,Integer> tt = function.compose(two);
        System.out.println(tt.apply("700"));

    }
}
