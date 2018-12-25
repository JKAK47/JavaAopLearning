package org.vincent.proxy;

import org.vincent.service.AOPMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/*
 *   调用处理器，每一个代理类都必须有一个关联的调用处理器，当代理类上的一个方法被调用都会被分发到这个调用处理器上
 *   的invoke方法上面；  InvocationHandler接口被实现可以作为代理类的调用处理器功能
 * @ClassName: AOPHandle
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年3月5日 下午3:55:29
 *
 */
public class AOPHandle implements InvocationHandler {
    // 保存AOP切入的接口引用
    private AOPMethod method;
    /**
     * 被代理的对象
     */
    private Object target;

    /**
     * 创建一个新的实例 AOPHandle.
     *
     * @param target 委托类实例引用
     * @param method
     */
    public AOPHandle(Object target, AOPMethod method) {
        this.target = target;
        this.method = method;
    }

    /**
     * 这个方法会自动调用,Java动态代理机制 会传入下面是个参数
     *
     * @param proxy  proxy 代理对象的接口,不同于对象
     * @param method method 被调用方法业务接口
     * @param args   args 方法参数 不能使用invoke时使用proxy作为反射参数时,因为代理对象的接口,不同于对象
     *               这种代理机制是面向接口，而不是面向类的
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;
        if (this.method != null) {
            // 修改的地方在这里哦
            this.method.before(proxy, method, args);
            // 反射调用方法
            ret = method.invoke(target, args);
            // 修改的地方在这里哦
            this.method.after(proxy, method, args);
        } else {// 无AOP的路径
            System.out.println("invocation handler before");
            ret = method.invoke(target, args);
            System.out.println("invocation hander after");
        }
        return ret;
    }
}