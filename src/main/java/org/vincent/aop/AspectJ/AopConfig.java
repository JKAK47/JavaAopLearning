package org.vincent.aop.AspectJ;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;



/**
 * @author PengRong
 * @package org.vincent.aop.AspectJ
 * @ClassName AopConfig.java
 * @date 2019/6/16 - 10:07
 * @ProjectName JavaAopLearning
 * @Description: 基于Spring 的java 配置类
 */
@Configuration
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE) /** bean 是单例模式 。*/
@EnableAspectJAutoProxy //开启 AspectJ 支持
@ComponentScan(basePackages = "org.vincent.aop.AspectJ")
public class AopConfig {
}
