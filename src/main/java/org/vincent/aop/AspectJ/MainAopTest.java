package org.vincent.aop.AspectJ;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author PengRong
 * @package org.vincent.aop.AspectJ
 * @ClassName MainAopTest.java
 * @date 2019/6/16 - 10:09
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class MainAopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext =new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = configApplicationContext.getBean(DemoAnnotationService.class);
        DemoMethodService demoMethodService = configApplicationContext.getBean(DemoMethodService.class);
        demoAnnotationService.add();
       // demoMethodService.add();
        configApplicationContext.close();
    }
}
