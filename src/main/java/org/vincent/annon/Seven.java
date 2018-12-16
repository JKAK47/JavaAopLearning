package org.vincent.annon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的注解,并通过注解类注入处理类 SevenAnnoInjectionHandle.java 去处理属性注入事情
 *
 * @author PengRong
 * @ClassName: Seven
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年3月5日 下午4:20:44
 */
@Retention(RetentionPolicy.RUNTIME) // 表示这个注解可以生存到运行期
@Target({ElementType.FIELD, ElementType.METHOD}) // 指定注解的使用范围
public @interface Seven {
    // 设定注解的方法，注解方法没有方法体，可以设置默认值
    public String value() default "小黑";

    public String Property() default "无属性";

}
