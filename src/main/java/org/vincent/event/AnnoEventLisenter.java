package org.vincent.event;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.vincent.event.pojo.AA;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName AnnoEventLisenter.java
 * @date 2019/8/8 - 6:37
 * @ProjectName JavaAopLearning
 * @Description: 基于注解监听事件
 */
@Component
public class AnnoEventLisenter {
    /**
     * EventListener 注解 属性  classes 是必须的 参数 ，申明你监听的事件。
     * 至于参数是否需要形参拿到对应的事件实例，具体根据情况在决定
     */
    @EventListener(classes = DemoEvent.class)
    public void DemoEventLisenter(){
        System.out.println("接受到事件");
    }
    /**
     * EventListener 注解必须申明 classes 属性值，classes 申明的属性值如果是一个参数的话
     * 方法可以申明一个入参
     */
    @EventListener(classes = DemoEvent.class)
    public void DemoEventLisenter(DemoEvent demoEvent){
        System.out.println("接受到事件");
        System.out.println(demoEvent.getMsg());
    }

    /**
     * 基于注解监听 发布事件时候 没有对事件封装，直接发送事件对象Object ，Spirng 对事件封装成 PayloadApplicationEvent 类实例
     * 所以只需要监听 PayloadApplicationEvent 事件即可。然后获取数据有效载荷 ，然后根据载荷的数据类型 处理不同的事件
     * @param demoEvent
     */
    @EventListener(classes = PayloadApplicationEvent.class)
    public void PayloadApplicationEventLisenter(PayloadApplicationEvent demoEvent){
        System.out.println("接受到事件");
        Object payload = demoEvent.getPayload();
        if (payload instanceof AA){
            AA aa = (AA) payload;
            System.out.println(aa.toString());
            System.out.println(aa.getBigDecimal());
            System.out.println(aa.getInteger());
            System.out.println(aa.getName());
        }else if (payload instanceof String){
            System.out.println(payload);
        }
    }
}
