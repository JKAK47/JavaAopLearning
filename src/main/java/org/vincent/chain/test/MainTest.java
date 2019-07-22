package org.vincent.chain.test;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author PengRong
 * @package org.vincent.chain.test
 * @ClassName MainTest.java
 * @date 2019/6/30 - 11:42
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class MainTest {
    public static void main(String[] args) {
        /**
         * 构建一个固定顺序的链 【终端记录——邮件记录——文件记录】
         * consoleLogger：终端记录，可以打印所有等级的log信息
         * emailLogger：邮件记录，打印功能性问题 FUNCTIONAL_MESSAGE 和 FUNCTIONAL_ERROR 两个等级的信息
         * fileLogger：文件记录，打印 WARNING 和 ERROR 两个等级信息
         */
        /**
         * 创建所有日志级别的日志记录器
         */
    /*    Logger logger = LoggerManager.consoleLogger(LogLevel.all());
        logger = logger.appendNext(LoggerManager.emailLogger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR));
        logger = logger.appendNext(LoggerManager.fileLogger(LogLevel.WARNING, LogLevel.ERROR));

        // consoleLogger 可以记录所有 level 的信息
        logger.message("进入到订单流程，接收到参数，参数内容为 XXOO .", LogLevel.DEBUG);
        logger.message("订单记录生成.", LogLevel.INFO);

        // consoleLogger 处理完，fileLogger 要继续处理
        logger.message("订单详细地址缺失", LogLevel.WARNING);
        logger.message("订单省市区信息缺失", LogLevel.ERROR);

        // consoleLogger 处理完，emailLogger 继续处理
        logger.message("订单短信通知服务失败", LogLevel.FUNCTIONAL_ERROR);
        logger.message("订单已派送.", LogLevel.FUNCTIONAL_MESSAGE);*/


        /**
         * Stream.reduce() with Identity, Accumulator and Combiner 只有用于并发流中才有意义
         * Stream是支持并发操作的，为了避免竞争，对于reduce线程都会有独立的result，
         * 第三個參數 combiner的作用在于合并每个线程的result得到最终结果。这也说明了了第三个函数参数的数据类型必须为返回数据类型了。
         */
        System.out.println("------------------");
        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)
                .parallel() // 必须开始并发流才有效果
                .reduce(new ArrayList<>(),
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {

                                acc.add(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("BiFunction");
                                return acc;
                            }
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                System.out.println("BinaryOperator GG");
                                acc.addAll(item);
                                System.out.println("item: GG" + item);
                                System.out.println("acc+ : GG" + acc);
                                System.out.println("--------GG");
                                return acc;
                            }
                        });
        System.out.println("------------------");
        System.out.println("pr accResult_: " + accResult_);
        System.out.println("------------------");

        // 串行操作 和两个 参数的执行结果一致
        List<Integer> list = Arrays.asList(3, 2, 4, 1);
        System.out.println(list.stream().reduce(100
                , (acc, tmp) -> acc + tmp
                , (a, b) -> a + b).intValue());  // out ==> 110
        System.out.println("------------------");
        // 并行操作
        System.out.println(list.stream().parallel().reduce(100
                , (acc, tmp) -> acc + tmp
                , (a, b) -> a + b).intValue());  // out ==> 410
        System.out.println("------------------");
        /**
         * 分析：
         * list集合中四个值并行执行, 分别与初始值100相加后, 再进行合并操作, 即：
         * 1）3+100=103, 2+100=102, 4+100=104, 1+100=101
         * 2）103+102+104+101=410
         */

        List<Integer> list2 = Arrays.asList(5, 6, 7);
        /** 并行操作*/
        int res = list2.parallelStream().reduce(2, (s1, s2) -> s1 * s2, (p, q) -> p * q);
        System.out.println("res1 =" + res);
        /** 串行操作 */
        System.out.println("res2 =" + list2.stream().reduce(2, (s1, s2) -> s1 * s2, (p, q) -> p * q));
        /** 并行操作  每个元素 和 identity =1 相乘，然后累加 =18 (1*5 + 6*1 + 7*1 )*/
        res = list2.parallelStream().reduce(2, (s1, s2) -> s1 * s2, (p, q) -> p + q);
        System.out.println("res3 =" + res);
        /** 串行操作 */
        System.out.println("res4 =" + list2.stream().reduce(1, (s1, s2) -> s1 * s2, (p, q) -> p + q));
        System.out.println("----------------------------------");
        while (true){
            System.out.println("123");
        }
    }
}
