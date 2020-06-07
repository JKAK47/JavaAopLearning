package org.vincent.stream;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PengRong
 * @package org.vincent.stream
 * @ClassName StreamCreate.java
 * @date 2020/6/6 - 9:20
 * @ProjectName JavaAopLearning
 * @Description: Stream 创建，流的特点
 * 1、流是一组相同数据类型元素集合，流具有顺序性，流提供对元素的设置和计算功能，流不提供对对原生的保存
 * 2、流中数据来源 集合，数组，IO；
 * 3、流提供聚合操作，filter, map, limit, reduce, find, match
 * 4、流水线，流提供的操作分两种，中间操作，终端操作。中间操作接口获取流元素输入（filter，map），进行处理然后返回一个新流（collect）。
 * 5、流内部操作提供对元素迭代循环操作
 */
public class StreamCreate {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(10, 20, 30, 40);
        integerStream.forEach(integer -> {
            System.out.println("out > " + integer);
            return;
        });
        String[] cityArr = {"Beijing", "Shanghai", "Chengdu"};
        Stream<String> cityStream = Stream.of(cityArr);
        Stream<String> nameStream = Arrays.asList("Daniel", "Peter", "Kevin").stream();
        Stream<String> cityStream2 = Arrays.stream(cityArr, 0, 1);
        Stream<String> emptyStream = Stream.empty();
        /** stream 流 运用*/
        /** forEach 方法提供对流 stream 方法 遍历的每个元素方法 。*/
        Random random = new Random();
        random.ints().limit(10).distinct().forEach(System.out::println);
        /** map 方法 将流每个元素映射到另外一个结果集；*/
        List<String> strings = Arrays.asList("abc", "", "bcd", "efg", "abcde", "", "jkl");
        System.out.println(strings);
        List<String> join = strings.stream().parallel().map(i -> "xxoo" + i).distinct().collect(Collectors.toList());
        System.out.println(join);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);
        /**  filter 过滤 方法，执行过滤逻辑 */
        strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> StringUtils.isNotBlank(string)).collect(Collectors.toList());
        System.out.println(filtered);

        /**  limit  方法 类似于SQL 的limit 字段 截取指定条数记录 ;  */
        List<String> collect = strings.stream().filter(e -> StringUtils.isNotBlank(e)).limit(3).collect(Collectors.toList());
        System.out.println(collect);
        /**  sorted 排序流方法，将流元素按照给定排序规则进行排序 */
        List<Integer> lists = Arrays.asList(12, 11, 19, 14, 33);
        List<Integer> collect1 = lists.stream().sorted().collect(Collectors.toList());
        System.out.println(collect1);
        collect1 = lists.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect1);
        /** 自定义 比较器 进行排序 */
        collect1 = lists.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());
        System.out.println(collect1);
        collect1 = lists.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println(collect1);

    }
}
