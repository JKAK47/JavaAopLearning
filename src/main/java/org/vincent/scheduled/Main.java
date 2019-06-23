package org.vincent.scheduled;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author PengRong
 * @package org.vincent.scheduled
 * @ClassName Main.java
 * @date 2019/6/18 - 22:45
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(ScheduledConfig.class);
        configApplicationContext.refresh();
    }
}
