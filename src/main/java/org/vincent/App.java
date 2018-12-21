package org.vincent;

import cn.hutool.core.lang.Assert;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        /**
         * 在堆上分配两个String对象，
         */
        String abc = new String("abc");
        String abc_a = new String("abc");
        /**
         * 在栈上分配两个
         */
        String abc_b = "abc";
        String abc_c = "abc";
        Assert.isFalse(abc == abc_a);
        Assert.isFalse(abc == abc_b);
        Assert.isTrue(abc_b == abc_c);
        System.out.println("Hello World!");
       /* String s0="kvill";
        String s1="kvill";
        String s2="kvi" + "ll";
        System.out.println( s0==s1 );
        System.out.println( s0==s2 );
*/

        String s0 = "kvill";

        String s1 = new String("kvill");

        String s2 = new String("kvi") + "ll";

        System.out.println(s0 == s1);
        System.out.println(s0 == s2);

        System.out.println(s1 == s2);


        System.out.println("**********");

        s1.intern(); // 在常量池先检查是否有和 s1 一致的字符串，如果有返回引用，没有则在常量池新建字符串 并返回引用。

        s2 = s2.intern(); //以s2变量代表的字符串在常量池创建一个一模一样的字符串，并返回引用 。即把常量池中“kvill”的引用赋给s2

        System.out.println(s0 == s1); //  s1.intern(); 返回的引用 并没有赋值给s1，所以 s1还是指向 原来的堆对象

        System.out.println(s0 == s1.intern());

        System.out.println(s0 == s2);

        System.out.println("__________");
        String s3 = "kvill";
        String s4 = "kvill";
        String s5 = "kvi" + "ll";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println("=======");
        String s6 = new String("asdfas");
        String s7 = s6.intern();
        System.out.println(s7 == s6);
        System.out.println(s7 == s6.intern());
        System.out.println(s7 == "asdfas");
        s7 += "aaaa" + "bbbb";
        System.out.println(s7);
        System.out.println(s7 == s7.intern());

       /* s6 = new String("abcd");
        s7 = "abc";
        s7 += "d";
        System.out.println(s6 == s7);
        System.out.println(s7 == s6.intern());
        System.out.println(s7 == s7.intern());*/

        s6 = new String("abcd");
        s7 = s6.intern();

        System.out.println(s6 == s7);
        System.out.println(s7 == s6.intern());
        System.out.println(s7 == s7.intern());
        s7 += "cccc";
        System.out.println(s7 == s7.intern());

        System.out.println("______+________");
        String str2 = "SEUCalvin";//新加的一行代码，其余不变
        String str1 = new String("SEU") + new String("Calvin");
        System.out.println(str1.intern() == str1);
        System.out.println(str1 == "SEUCalvin");
        System.out.println(str2 == str1.intern());
        System.out.println(str2 == "SEUCalvin");


        s3 = new String("221") + new String("1");
        System.out.println(s3);
        System.out.println(s3.intern() == s3);


        String s = new String("1");
        s.intern();
        s2 = "1";
        System.out.println(s == s2);
        System.out.println(s == s.intern());
    }
}
