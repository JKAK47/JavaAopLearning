package org.vincent.aop.interceptor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.vincent.aop.interceptor.exception.InterceptorException;

import java.util.List;
import java.util.Objects;

/**
 * @author PengRong
 * @package org.vincent.aop.interceptor
 * @date 2019/1/10 - 22:59
 * @ProjectName JavaAopLearning
 * @Description: 基于Cglib 实现动态代理类，并实现在业务方法之前 之后插入 切面 通知逻辑
 */
public class InterceptorStackProxyGenerator {
    /**
     * @param targetClass  需要被代理的委托类对象的Class实例，Cglib需要继承该类生成子类
     * @param interceptors 切面对象列表栈 ，每个元素都是一个切面, 该对象方法将在切点方法之前或之后执行
     * @return 返回 targetClass 创建的代理类
     */
    public static Object generatorCglibProxy(final Class targetClass, final List<Interceptor> interceptors) {
        //3.1 new Enhancer
        Enhancer enhancer = new Enhancer();
        //3.2 设置需要代理的父类
        enhancer.setSuperclass(targetClass);
        //3.3 设置回调
        enhancer.setCallback((MethodInterceptor) (proxy, method, args, methodProxy) -> {
            if (Objects.isNull(interceptors)){
                throw  new  InterceptorException("interceptors is not null");
            }
            // 执行切面方法
            interceptors.forEach((Interceptor item) -> {
                try {
                    item.beforeIntercept(args);
                } catch (InterceptorException e) {
                    e.printStackTrace();
                }
            });
            // 具体逻辑代码执行,返回值为方法执行结果
            Object result = methodProxy.invokeSuper(proxy, args);
            // 执行切面方法
            interceptors.forEach((Interceptor item) -> {
                try {
                    item.afterIntercept(result);
                } catch (InterceptorException e) {
                    e.printStackTrace();
                }
            });
            // 返回方法执行结果
            return result;
        });
        // 3.4 创建代理对象
        return enhancer.create();
    }
}
