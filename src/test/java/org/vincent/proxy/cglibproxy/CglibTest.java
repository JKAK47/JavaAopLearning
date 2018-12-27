package org.vincent.proxy.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @Package: org.vincent.proxy.cglibproxy <br/>
 * @Description： TODO <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26-18:05 <br/>
 */
public class CglibTest {
    @Test
    public void testCglib() throws Exception {

        System.out.println(System.getProperty("user.dir"));
        /** 开启 保存cglib生成的动态代理类类文件*/
        saveGeneratedCGlibProxyFiles(System.getProperty("user.dir"));
        /** 第一种方法: 创建cglib 代理类 start */
        // 创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 为代理类指定需要代理的类，也即是父类
        enhancer.setSuperclass(Dog.class);
        // new 一个新的方法拦截器
        CglibMethodInterceptor cglibMethodInterceptor = new CglibMethodInterceptor();
        // 设置方法拦截器回调引用，对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept() 方法进行拦截
        enhancer.setCallback(cglibMethodInterceptor);
        // 获取动态代理类对象并返回
        Dog dog = (Dog) enhancer.create();
        /** 创建cglib 代理类 end */
        System.out.println(dog.call());

        // 对于上面这几步，可以新增一个工具方法 放置在 CglibMethodInterceptor 里面；也就有了第二种方法
        // new 一个新的方法拦截器，该拦截器还顺带一个用于创建代理类的工具方法。看起来简单很多
        cglibMethodInterceptor = new CglibMethodInterceptor();
        dog = (Dog) cglibMethodInterceptor.CglibProxyGeneratory(Dog.class);
        System.out.println(dog.call());

    }

    /**
     * 设置保存Cglib代理生成的类文件。
     *
     * @throws Exception
     */
    public void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
        props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
    }
}
