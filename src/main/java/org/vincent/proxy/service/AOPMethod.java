package org.vincent.proxy.service;

import java.lang.reflect.Method;

/**
 * 这是一个AOP的一个切面；在每个接口方法中可以做一些类似于日志处理的功能
 *
 * @author PengRong
 * @ClassName: AOPMethod
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年3月5日 下午4:18:20
 */
public interface AOPMethod {
    // 实例方法执行前执行的方法，比如执行方法前,记录类状态,写入log.监控xx变量,,,
    void after(Object proxy, Method method, Object[] args);

    // 实例方法执行后执行的方法
    void before(Object proxy, Method method, Object[] args);
}