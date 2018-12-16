package org.vincent.annon;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * 对类属性注解和方法注解属性进行注入，将一个业务类实例涉及Seven 注解自动赋值给类属性
 * @ClassName: SevenAnnoInjectionHandle
 * @Description: 用于注入Seven 注解定义的属性
 * @author PengRong
 * @date 2017年3月5日 下午4:22:52
 *
 */
public class SevenAnnoInjectionHandle {

    public static Object getBean(Object obj) {
        try {
            // 获得类属性
            Field f[] = obj.getClass().getDeclaredFields();
            // 遍历属性，查找所有的属性注解
            for (Field ff : f) {
                // 获得属性上的注解
                Seven s = ff.getAnnotation(Seven.class);// 返回ff属性的Seven类型的注解
                if (s != null) {
                    System.err.println("注入 " + ff.getName() + " 属性" + "\t\t" + s.value());
                    // 反射调用public set方法,如果访问级别为private,那么可以直接使用属性的set(obj,value);
                    obj.getClass()
                            .getMethod("set" + ff.getName().substring(0, 1).toUpperCase() + ff.getName().substring(1), // 组配函数名称出来
                                    new Class<?>[]{String.class})
                            .invoke(obj, s.value());// 通过反射调用属性对应的setXXX函数将注解的值赋值给类属性
                }
            }
            // 获得所有方法，查找方法注解
            Method m[] = obj.getClass().getDeclaredMethods();
            for (Method mm : m) {
                // 获得方法注解
                Seven s = mm.getAnnotation(Seven.class);
                if (s != null) {
                    System.err.println("注入 " + mm.getName() + " 方法注解" + "\t" + s.Property());
                    mm.invoke(obj, s.Property());// 通过方法注入注解的值
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}