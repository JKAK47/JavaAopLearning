package org.vincent.aop.AspectJ;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author PengRong
 * @package org.vincent.aop.AspectJ
 * @ClassName Action.java
 * @date 2019/6/16 - 9:38
 * @ProjectName JavaAopLearning
 * @Description: 定义一个方法级别 注解，当作AOP切入 的一个切入点
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name() default "name -GG";
}
