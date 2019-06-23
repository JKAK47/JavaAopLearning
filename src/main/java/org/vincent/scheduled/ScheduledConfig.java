package org.vincent.scheduled;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author PengRong
 * @package org.vincent.scheduled
 * @ClassName ScheduledConfig.java
 * @date 2019/6/18 - 22:44
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
@Configuration
@ComponentScan(basePackages = "org.vincent.scheduled")
@EnableScheduling
public class ScheduledConfig {
}
