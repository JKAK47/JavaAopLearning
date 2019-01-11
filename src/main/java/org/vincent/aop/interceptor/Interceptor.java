package org.vincent.aop.interceptor;

import org.vincent.aop.interceptor.exception.InterceptorException;

/**
 * @author PengRong
 * @package org.vincent.aop.interceptor
 * @date 2019/1/10 - 22:17
 * @ProjectName JavaAopLearning
 * @Description: 定义切面链 抽象接口，作为一个拦截器栈的基础接口
 */
public interface Interceptor {
    /**
     * 拦截器 之前实现用于代码逻辑
     * @param args 委托类参数
     */
    public void beforeIntercept(Object... args) throws InterceptorException;

    /**
     * 拦截器 之后 实现对计算结果实现公共逻辑
     * @param result
     */
    public void afterIntercept(Object  result)throws InterceptorException;
}
