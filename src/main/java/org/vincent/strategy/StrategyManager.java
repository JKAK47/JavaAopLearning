package org.vincent.strategy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package com.imodule.externalquery.strategy
 * @ClassName UseStrategyClient.java
 * @date 2019/6/10 - 15:10
 * @description : 封裝所有策略模式访问入口  實現的管理類类，
 * 实现 ApplicationContextAware 接口用于获取 ApplicationContext 实例
 * 实现 InitializingBean 接口用于 在 启动后设置 TypeAndStatementHandle ,将所有策略 设置上去，
 * 泛型参数 I 是策略接口需要的入参类型
 * Created by PengRong .
 */
@Component
public class StrategyManager<I, O> implements ApplicationContextAware, InitializingBean, BeanNameAware ,BeanFactoryAware {
    /**
     * 实现 ApplicationContextAware 接口用于获取 ApplicationContext 实例
     */
    private ApplicationContext applicationContext;
    /**
     * 实现 BeanFactoryAware 接口用于获取 BeanFactory 实例
     */
    private BeanFactory beanFactory;
    /**
     * 所有的策略实现,以 handler 的type 为key， 对应的handler 实例 为value;
     * 基於Class 類型 自動注入進去
     */
    private Map<String, Handler> handlers = new ConcurrentHashMap<>();

    /**
     * 根据一个class 实例 获取  匹配 的Bean 引用；
     *
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据 入参，获取一个 匹配 入参的所有bean 实例，返回值以 map返回，bean名称作为key, bean 实例作为value 返回
     * 如果clazz是一个接口的类型，那么返回的就是这个接口的所有实现类。
     *
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 将所有策略模式都放置在 map中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        //初始化 handlers
        Map<String, Handler> beansOfType = getBeansOfType(Handler.class);
        for (Map.Entry<String, Handler> handlerEntry : beansOfType.entrySet()) {
            String beanName = handlerEntry.getKey();/** bean 名字*/
            Handler value = handlerEntry.getValue();/* Handler */
            System.out.println("beanName = " + beanName);
            handlers.put(value.getType().getStrategyCode(), value);

        }
    }

    /**
     * * 客户端类提供代理执行策略接口方法的方法
     *
     * @param strategyEnums 策略枚举类型
     * @param t             策略接口执行需要的参数
     * @return
     */
    public O executeStrategy(StrategyEnums strategyEnums, I t) {
        Object process = handlers.get(strategyEnums.getStrategyCode()).process(t);
        return (O) process;
    }

    /**
     * 在beanFactory 创建该类Bean时候 将该bean名字传递进来
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory =beanFactory;
        Assert.notNull(beanFactory, " beanFactory is not null.");
        boolean equals = Objects.equals(beanFactory, applicationContext);
        System.out.println(equals);
    }
}
