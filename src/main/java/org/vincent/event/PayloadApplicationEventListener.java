package org.vincent.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName PayloadApplicationEventListener.java
 * @date 2019/8/8 - 6:59
 * @ProjectName JavaAopLearning
 * @Description: 监听 PayloadApplicationEvent 类型事件。
 * 该监听类也可以通过 基于注解实现(对应实现：org.vincent.event.AnnoEventLisenter#PayloadApplicationEventLisenter(org.springframework.context.PayloadApplicationEvent))
 */
@Component
public class PayloadApplicationEventListener implements ApplicationListener<PayloadApplicationEvent>{

    @Override
    public void onApplicationEvent(PayloadApplicationEvent event) {
        Object payload = event.getPayload();
        if (payload instanceof String){
            System.out.println(payload);
        }
    }
}
