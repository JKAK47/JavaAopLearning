package org.vincent.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author PengRong
 * @package org.vincent.profile
 * @ClassName ProfileConfig.java
 * @date 2019/6/18 - 22:02
 * @ProjectName JavaAopLearning
 * @Description: Profile 支持不同环境创建不同的Bean
 */
@Configuration
public class ProfileConfig {
    @Bean
    @Profile(value = {"dev"})
    public DemoBean devDemoBean() {
        return new DemoBean("dev demo Bean");
    }
    @Bean
    @Profile(value = {"prod"})
    public DemoBean prodDemoBean(){
        return new DemoBean("prod Demo Bean");
    }
}
