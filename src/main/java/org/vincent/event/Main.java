package org.vincent.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName MainTaskExecutor.java
 * @date 2019/6/16 - 17:01
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext configApplicationContext =new AnnotationConfigApplicationContext(JavaConfig.class);
        EventPublisher bean = configApplicationContext.getBean(EventPublisher.class);
        bean.publisherEvent();/** 发布事件*/
        configApplicationContext.close();
    }


}
