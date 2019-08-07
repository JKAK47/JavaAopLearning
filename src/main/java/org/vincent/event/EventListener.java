package org.vincent.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName EventListener.java
 * @date 2019/6/16 - 16:56
 * @ProjectName JavaAopLearning
 * @Description: 事件监听者，监听系统里面发生的发生的指定事情。订阅者
 */
@Component
public class EventListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();
        System.out.println(/**/"事件源： "+event.getSource());
        System.out.println(" msg = "+event.getMsg());
    }
}
