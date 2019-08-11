package org.vincent.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.vincent.event.pojo.AA;

import java.math.BigDecimal;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName MainTaskExecutor.java
 * @date 2019/6/16 - 17:01
 * @ProjectName JavaAopLearning
 * @Description:
 *
 * https://www.jianshu.com/p/dcbe8f0afbdb[Spring 事件机制]
 * https://blog.csdn.net/qq_41907991/article/details/88544777 【spring学习笔记（二）spring中的事件及多线程】
 *
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        AnnotationConfigApplicationContext configApplicationContext =new AnnotationConfigApplicationContext(JavaConfig.class);
        EventPublisher bean = configApplicationContext.getBean(EventPublisher.class);
        bean.publisherEvent();/** 发布事件*/
        bean.publisherEvent();/** 发布事件*/
        bean.publisherEvent(new AA("GG",200, BigDecimal.ONE));
        Thread.sleep(1000);
        /** 关闭上下文  */
        configApplicationContext.close();
    }


}
