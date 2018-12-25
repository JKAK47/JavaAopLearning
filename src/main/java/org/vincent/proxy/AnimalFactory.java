package org.vincent.proxy;

import java.lang.reflect.Proxy;

import org.vincent.annon.SevenAnnoInjectionHandle;
import org.vincent.service.AOPMethod;

/**
 * 根据 传进来的委托实例引用创建并返回代理类引用
 *
 * @author PengRong
 * @ClassName: AnimalFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年3月5日 下午4:13:37
 */
public class AnimalFactory {

    /***
     * 获取代理对象方法
     *
     * @param obj
     * @return
     */
    private static Object getAnimalBase(Object obj, AOPMethod method) {
        // 获取代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                new AOPHandle(SevenAnnoInjectionHandle.getBean(obj), method));
    }

    /***
     * 获取对象方法
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAnimal(Object obj, AOPMethod aopMethod) {
        return (T) getAnimalBase(obj, aopMethod);
    }

    /***
     * 获取对象方法
     *
     * @param className
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAnimal(String className, AOPMethod method) {
        Object obj = null;
        try {
            obj = getAnimalBase(Class.forName(className).newInstance(), method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    /***
     * 获取对象方法
     *
     * @param clz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAnimal(Class<?> clz, AOPMethod method) {
        Object obj = null;
        try {
            obj = getAnimalBase(clz.newInstance(), method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }
}