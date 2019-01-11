package org.vincent.aop.interceptor;

import org.vincent.aop.interceptor.exception.InterceptorException;

/**
 * @author PengRong
 * @package org.vincent.aop.interceptor
 * @date 2019/1/10 - 22:29
 * @ProjectName JavaAopLearning
 * @Description: ThirInterceptor 第三个 切面类
 */
public class ThirInterceptor implements Interceptor {


    @Override
    public void beforeIntercept(Object... args) throws InterceptorException {
        System.out.println("ClassName: "+this.getClass().getSimpleName()+" methodName ="+new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void afterIntercept(Object result) throws InterceptorException {
        System.out.println("ClassName: "+this.getClass().getSimpleName()+" methodName ="+new Exception().getStackTrace()[0].getMethodName());
    }
}
