package org.vincent.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author PengRong
 * @package org.vincent.annotation
 * @ClassName MyAnnotation.java
 * @date 2019/6/16 - 23:16
 * @ProjectName JavaAopLearning
 * @Description: 定义一个组合注解 MyAnnotation; 对于组合
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan
public @interface MyAnnotation {
    /**
     * 对原来注解的属性，需要在新的注解重新申明
     */
    @AliasFor(annotation = ComponentScan.class)
    String[] basePackages() default {};
}
