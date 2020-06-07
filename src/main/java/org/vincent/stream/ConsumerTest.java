package org.vincent.stream;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author PengRong
 * @package org.vincent.stream
 * @ClassName ConsumerTest.java
 * @date 2020/6/6 - 10:35
 * @ProjectName JavaAopLearning
 * @Description: Consumer 接口是用于 在流中使用最多的地方就是 forEach。用于遍历每个元素时候Cousumer消费每个元素，对每个元素特殊处理。
 */
public class ConsumerTest {
    public static void main(String[] args) {
        testConsumer();
        testAndThen();
        List<String> strings= Arrays.asList("asdf","1234123sdf","asdfasdf","oqwrtuqwe23423alsdfk");
        /** 函数式接口Consumer 有四种实现方式*/
        /** 第一种写法 内部类形式 */
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        /** 第二种写法  lambda */
        strings.forEach(x-> System.out.println(x));

        /** 函数式接口 变量名 = 类实例::方法名” 的方式对该方法进行引用,将方法引用赋值给一个函数式接口*/
        /** 将一个方法实现赋值给函数式接口 Consumer ，然后对流中元素遍历时候对每个元素 执行accept 方法时候执行的具体逻辑是 函数式接口引用
         * 将方法复制给Consumer 接口引用有个前提就是，方法引用必须是Consumer
         * */
        /** 第三种写法： 方法引用*/
        Consumer<String> consumer = System.out::println;
        strings.forEach(consumer);
        /** 第四种写法： 函数式接口，使用自定义方法 */
        consumer = new A()::xx;
        strings.forEach(consumer);
        consumer = new A()::yy;
        strings.forEach(consumer);
        /** 复制给 consumer  函数接口引用的方法参数必须是 Consumer 接口操作参数数据类型，方法入参只能只有一个。*/
        //consumer = new A()::yyb;/** 报错 */

    }
    public static class A{
        public void yyb(String yy,int b){
            System.out.println(yy);
        }
        public void yy(String yy){
            System.out.println(yy);
        }
        public int xx(String aa){
            if (StringUtils.isNotBlank(aa)){
                System.out.println(aa.length());
                return aa.length();
            }else {
                return 0;
            }

        }
    }

    /**
     * 定义3个Consumer并按顺序进行调用andThen方法
     */
    private static void testAndThen() {
        Consumer<Integer> consumer1 = x -> {
            System.out.println("first");
            System.out.println("当前输入值 input :" + x);
        };
        Consumer<Integer> consumer2 = x -> {
            System.out.println("second");
            System.out.println("相加 ：" + (x + x));
        };
        Consumer<Integer> consumer3 = x -> {
            System.out.println("third");
            System.out.println("相乘 ：" + (x * x));
        };
        System.out.println("-----------------");
        /** 最后输入 10，然后从 consumer1，consumer2，consumer3 顺序消费，每个消费器都是获得输入值 10 */
        consumer1.andThen(consumer2).andThen(consumer3).accept(10);
        System.out.println("-----------------");

    }

    private static void testConsumer() {

        Consumer<Integer> square = x -> {
            System.out.println("print square : " + x * x);
            System.out.println("print max : " + x * x * x);
            return;
        };
        /** accept */
        square.accept(2);
    }
}
