package org.vincent.chain;


import org.vincent.chain.enumlevel.LogLevel;

/**
 * @author PengRong
 * @package org.vincent.chain
 * @ClassName Logger.java
 * @date 2019/6/30 - 10:10
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
@FunctionalInterface
public interface Logger {
    /**
     * 函数式接口中的唯一抽象方法
     * @param msg
     * @param severity 日志记录器 等级
     */
    void message(String msg, LogLevel severity);
    /**
     * 使用责任链模式，传递给下一个Logger 处理策略类
     *
     * @param nextLogger
     * @return
     */
    default Logger appendNext(Logger nextLogger) {
        System.out.println("appenNext " + nextLogger.getClass());
        /** 构造匿名类 */
        return (msg, severity) -> {
            /**前序logger处理完才用当前logger处理 */
            /** 调用成员方法，匿名内部类实现了这个方法  */
            message(msg, severity);
            /** 调用当前logger 的下家继续处理 */
            nextLogger.message(msg, severity);
        };
    }


}