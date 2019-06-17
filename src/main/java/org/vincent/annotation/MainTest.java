package org.vincent.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author PengRong
 * @package org.vincent.annotation
 * @ClassName MainTest.java
 * @date 2019/6/16 - 23:25
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext =new AnnotationConfigApplicationContext(JavaConfig.class);
        DemoService demoService = configApplicationContext.getBean(DemoService.class);
        demoService.output();
    }
}
