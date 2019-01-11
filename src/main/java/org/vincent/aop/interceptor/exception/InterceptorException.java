package org.vincent.aop.interceptor.exception;

/**
 * @author PengRong
 * @package org.vincent.aop.interceptor.exception
 * @date 2019/1/10 - 22:50
 * @ProjectName JavaAopLearning
 * @Description: InterceptorException 异常类
 */
public class InterceptorException extends   Exception{
    public InterceptorException(){
        this("msg...",null);

    }
    public InterceptorException(String msg){
        this(msg,null);
    }
    public InterceptorException(String msg,Throwable e){
        super(msg,e);
    }
}
