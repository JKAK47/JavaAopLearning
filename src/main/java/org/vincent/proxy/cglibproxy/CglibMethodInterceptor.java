package org.vincent.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Package: org.vincent.proxy.cglibproxy <br/>
 * @Description： Cglib 方法拦截器,不用依赖被代理业务类的引用。  <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26-17:56 <br/>
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    /**
     * 用于生成cglib 动态代理类工具方法
     * @param target 代表需要 被代理的 委托类的Class 对象
     * @return
     */
    public Object CglibProxyGeneratory(Class target) {
        /** 创建cglib 代理类 start */
        // 创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 为代理类指定需要代理的类，也即是父类
        enhancer.setSuperclass(target);
        // 设置方法拦截器回调引用，对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept() 方法进行拦截
        enhancer.setCallback(this);
        // 获取动态代理类对象并返回
        return enhancer.create();
        /** 创建cglib 代理类 end */
    }

    /**
     * 功能主要是在调用业务类方法之前 之后添加统计时间的方法逻辑
     * @param obj    代表生成的动态代理类 对象
     * @param method 代理类中被拦截的接口方法
     * @param args   接口方法参数
     * @param proxy  用于调用父类真正的业务类方法。
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before");
        MonitorUtil.start();
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("after");
        MonitorUtil.finish(method.getName());
        return result;
    }
}
