package org.vincent.chain.test;

import org.vincent.chain.Logger;
import org.vincent.chain.enumlevel.LogLevel;
import org.vincent.chain.manager.LoggerManager;

/**
 * @author PengRong
 * @package org.vincent.chain.test
 * @ClassName MainTest.java
 * @date 2019/6/30 - 11:42
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class MainTest {
    public static void main(String[] args) {
        /**
         * 构建一个固定顺序的链 【终端记录——邮件记录——文件记录】
         * consoleLogger：终端记录，可以打印所有等级的log信息
         * emailLogger：邮件记录，打印功能性问题 FUNCTIONAL_MESSAGE 和 FUNCTIONAL_ERROR 两个等级的信息
         * fileLogger：文件记录，打印 WARNING 和 ERROR 两个等级信息
         */
        /**
         * 创建所有日志级别的日志记录器
         */
        Logger logger = LoggerManager.consoleLogger(LogLevel.all());
        logger = logger.appendNext(LoggerManager.emailLogger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR));
        logger = logger.appendNext(LoggerManager.fileLogger(LogLevel.WARNING, LogLevel.ERROR));

        // consoleLogger 可以记录所有 level 的信息
        logger.message("进入到订单流程，接收到参数，参数内容为 XXOO .", LogLevel.DEBUG);
        logger.message("订单记录生成.", LogLevel.INFO);

        // consoleLogger 处理完，fileLogger 要继续处理
        logger.message("订单详细地址缺失", LogLevel.WARNING);
        logger.message("订单省市区信息缺失", LogLevel.ERROR);

        // consoleLogger 处理完，emailLogger 继续处理
        logger.message("订单短信通知服务失败", LogLevel.FUNCTIONAL_ERROR);
        logger.message("订单已派送.", LogLevel.FUNCTIONAL_MESSAGE);
    }
}
