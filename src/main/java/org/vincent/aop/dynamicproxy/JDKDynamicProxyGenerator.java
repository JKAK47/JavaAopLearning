package org.vincent.aop.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Package: org.vincent.aop.dynamicproxy <br/>
 * @Description： JDK动态代理类生成器 <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26-16:48 <br/>
 */
public class JDKDynamicProxyGenerator {
    /**
     * @param TargetJoinPoint 需要被代理的委托类对象
     * @param aspect 切面对象,该对象方法将在切点方法之前或之后执行
     * @return
     */
    public static Object generatorJDKProxy(IUserService TargetJoinPoint, final IAspect aspect) {

        return Proxy.newProxyInstance(
                /**
                 *   委托类使用的类加载器
                 */
                TargetJoinPoint.getClass().getClassLoader(),
                /**
                 * 委托类实现的接口
                 */
                TargetJoinPoint.getClass().getInterfaces(),
                /**
                 * 生成的动态代理类关联的 执行处理器，代理我们的业务逻辑被生成的动态代理类回调
                 * 具体逻辑代码执行,返回值为方法执行结果, 在aop模型中，委托类的接口方法称为切点。
                 */
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 执行切面方法,对入参进行校验
                       boolean prepareAction = aspect.startTransaction(args);
                       if (prepareAction){
                           // 具体逻辑代码执行,返回值为方法执行结果
                           Object result = method.invoke(TargetJoinPoint, args);
                           aspect.endTrasaction();
                           return result;
                       }else {
                           throw  new RuntimeException("args: "+ Arrays.toString(args)+"不能为null ");
                       }
                    }
                });
    }
}
