package org.vincent.chain.manager;

import org.vincent.chain.Logger;
import org.vincent.chain.enumlevel.LogLevel;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Consumer;

/**
 * @author PengRong
 * @package org.vincent.chain
 * @ClassName LoggerManager.java
 * @date 2019/6/30 - 11:40
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class LoggerManager {
    /**
     * 创建 日志记录器
     *
     * @param levels
     * @param writeMessage
     * @return
     */
    static Logger logger(LogLevel[] levels, Consumer<String> writeMessage) {
        EnumSet<LogLevel> set = EnumSet.copyOf(Arrays.asList(levels));
        /** 创建并返回匿名内部类 */
        return (msg, severity) -> {
            // 判断当前logger是否能处理传递过来的日志级别
            if (set.contains(severity)) {
                writeMessage.accept(msg);
            }
        };
    }

    /**
     * 注入创建日志记录器
     *
     * @param levels
     * @return
     */
    public static Logger consoleLogger(LogLevel... levels) {
        return logger(levels, s -> System.err.println("consoleLogger 写到终端: " + s));
    }

    /**
     * 注入 创建日志记录器
     *
     * @param levels
     * @return
     */
    public static Logger emailLogger(LogLevel... levels) {
        return logger(levels, s -> System.err.println("emailLogger 通过邮件发送: " + s));
    }

    /**
     * 注入 创建日志记录器
     *
     * @param levels
     * @return
     */
    public static Logger fileLogger(LogLevel... levels) {
        return logger(levels, s -> System.err.println("fileLogger 写到日志文件中: " + s));
    }
}
