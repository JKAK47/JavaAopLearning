package org.vincent.proxy;

import java.lang.reflect.Method;

import org.vincent.annon.example.DogImp;
import org.vincent.proxy.service.AOPMethod;
import org.vincent.proxy.service.AnimalInterface;

public class AOPTest {

    public static void main(String[] args) {

        /**
         * new AOPMethod() { // 这里写方法执行前的AOP切入方法
         *
         * @Override public void before(Object proxy, Method method, Object[]
         *           args) { if (method.getName().equals("getProperty")) {
         *           System.err.println("成功拦截" + method.getName() + "方法,启动"); }
         *           }
         *
         *           // 这里系方法执行后的AOP切入方法
         * @Override public void after(Object proxy, Method method, Object[]
         *           args) { if (method.getName().equals("getProperty"))
         *           System.err.println("成功拦截" + method.getName() + "方法,结束");
         *
         *           } }
         */
        /**
         * 返回的dog为代理实例
         */
        AnimalInterface dog = AnimalFactory.getAnimal(DogImp.class, new AOPMethod() { // 这里写方法执行前的AOP切入方法

            @Override
            public void before(Object proxy, Method method, Object[] args) {
                if (method.getName().equals("getProperty")) {
                    System.err.println("成功拦截" + method.getName() + "方法,启动");
                }
            }

            // 这里系方法执行后的AOP切入方法
            @Override
            public void after(Object proxy, Method method, Object[] args) {
                if (method.getName().equals("getProperty"))
                    System.err.println("成功拦截" + method.getName() + "方法,结束");

            }
        });// 返回一个代理类
        dog.say();// 实体的一个行为
        String name1 = "我的名字是= " + dog.getName();// 通过这个可以看到可以将注解注入到属性中
        System.out.println(name1);
        dog.setName("二狗子");
        String name2 = "我的名字是" + dog.getName();
        System.out.println(name2);
        dog.getProperty();
    }
}