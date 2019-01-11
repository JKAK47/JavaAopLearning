package org.vincent.aop.intercepter;

import org.junit.Test;
import org.vincent.aop.interceptor.AccountServiceImpl;
import org.vincent.aop.interceptor.FirstInterceptor;
import org.vincent.aop.interceptor.Interceptor;
import org.vincent.aop.interceptor.InterceptorStackProxyGenerator;
import org.vincent.aop.interceptor.SecInterceptor;
import org.vincent.aop.interceptor.ThirInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PengRong
 * @package org.vincent.aop.intercepter
 * @date 2019/1/10 - 22:30
 * @ProjectName JavaAopLearning
 * @Description: 拦截器栈测试
 */
public class IntercepterTest {
    @Test
    public void intercepterTest() {
        /**
         * 构建拦截器栈, 每个切面add 到List的属性影响 拦截器 栈执行先后顺序
         */
        Interceptor interceptor1 = new FirstInterceptor();
        Interceptor interceptor2 = new SecInterceptor();
        Interceptor interceptor3 = new ThirInterceptor();
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(interceptor3);
        interceptors.add(interceptor1);
        interceptors.add(interceptor2);

        /**
         * 代理类构造工具方法
         */
        AccountServiceImpl generator = (AccountServiceImpl) InterceptorStackProxyGenerator.generatorCglibProxy(AccountServiceImpl.class, interceptors);
        /**
         * 调用业务方法
         */
        System.out.println(generator.login("asfdas", "12121212"));

    }
}
