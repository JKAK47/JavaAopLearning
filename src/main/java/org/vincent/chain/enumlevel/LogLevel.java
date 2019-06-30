package org.vincent.chain.enumlevel;

/**
 * @author PengRong
 * @package org.vincent.chain
 * @ClassName LogLevel.java
 * @date 2019/6/30 - 11:39
 * @ProjectName JavaAopLearning
 * @Description: 日志等级 枚举
 */
public enum  LogLevel {
    //定义 log 等级
    INFO, DEBUG, WARNING, ERROR, FUNCTIONAL_MESSAGE, FUNCTIONAL_ERROR;

    public static LogLevel[] all() {
        return values();
    }
}
