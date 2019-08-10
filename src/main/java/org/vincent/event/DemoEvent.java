package org.vincent.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName DemoEvent.java
 * @date 2019/6/16 - 16:54
 * @ProjectName JavaAopLearning
 * @Description: 自定义一个事件类 在 事件发布者和 事件监听者之间 进行信息流转流通
 */

public class DemoEvent extends ApplicationEvent implements Serializable {
    private String msg;

    public String getMsg() {
        return msg;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DemoEvent(Object source, String msg) {
        super(source);/** 事件源 一般就是指的是 发布事件的那个类，可以手动赋值任何类实例 */
        this.msg = msg;
    }
}
