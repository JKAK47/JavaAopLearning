package org.vincent.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author PengRong
 * @package org.vincent.event
 * @ClassName EventPublisher.java
 * @date 2019/6/16 - 16:58
 * @ProjectName JavaAopLearning
 * @Description: 事件发布者 ，需要依赖 ApplicationContext
 */
@Component
public class EventPublisher implements ApplicationEventPublisherAware, ApplicationContextAware {
    @Autowired
    private ApplicationContext context;

    /**
     * 通过 实现ApplicationEventPublisherAware 接口，自动从Spring Container 容器获取系统能力
     */
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布一个事件
     * */
    public String publisherEvent() {
        String result = "sda";
        context.publishEvent(new DemoEvent(this, result));
        applicationEventPublisher.publishEvent(new DemoEvent(this, "applicationEventPublisher"));
        return result;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /** 通过Spring Container 容器获取到 ApplicationContext Spring核心系统服务 */
        /** 可以通过这种方式获取 ApplicationContext ，也可以通过 Autowired 注入方式注入进去。 */
    }
}
