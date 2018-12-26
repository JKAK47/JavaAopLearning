package org.vincent.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Package: org.vincent.aop.cglib <br/>
 * @Description： 基于Cglib代理类生成器工具类 <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26-17:04 <br/>
 */
public class CglibProxyGenerator {
    /**
     * @param target 需要被代理的委托类对象，Cglib需要继承该类生成子类
     * @param aspect 切面对象,改对象方法将在切点方法之前或之后执行
     * @return
     */
    public static  Object generatorCglibProxy(final Object target, final IAspect aspect){
        //3.1 new Enhancer
        Enhancer enhancer = new Enhancer();
        //3.2 设置需要代理的父类
        enhancer.setSuperclass(target.getClass());
        //3.3 设置回调
        enhancer.setCallback(new MethodInterceptor() {

            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                // 执行切面方法
                aspect.startTransaction();
                // 具体逻辑代码执行,返回值为方法执行结果
                Object result = methodProxy.invokeSuper(proxy, args);
                // 执行切面方法
                aspect.endTrasaction();
                // 返回方法执行结果
                return result;
            }
        });
        // 3.4 创建代理
        return enhancer.create();
    }

}
