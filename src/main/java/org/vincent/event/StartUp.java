package org.vincent.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName StartUp.java
 * @date 2019/8/7 - 20:41
 * @ProjectName JavaAopLearning
 * @Description: 事件监听者，事件消费者 必须注册为Spring Bean
 */
@Component
public class StartUp implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(" start Spring App");
    }
}
