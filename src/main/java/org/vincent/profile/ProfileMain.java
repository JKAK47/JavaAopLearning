package org.vincent.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author PengRong
 * @package org.vincent.profile
 * @ClassName ProfileMain.java
 * @date 2019/6/18 - 22:29
 * @ProjectName JavaAopLearning
 * @Description: 基于Profile 注解测试类
 */

public class ProfileMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        /** 第一步： 设置环境*/
        //configApplicationContext.getEnvironment().setActiveProfiles("dev");
        /** 第二步：设置配置类 */
        configApplicationContext.register(ProfileConfig.class);
        /** 刷新配置类 */
        configApplicationContext.refresh();
        /** 启动容器 */
        configApplicationContext.start();
        DemoBean bean = configApplicationContext.getBean(DemoBean.class);
        System.out.println(bean.getContent());
        configApplicationContext.close();

    }
}
