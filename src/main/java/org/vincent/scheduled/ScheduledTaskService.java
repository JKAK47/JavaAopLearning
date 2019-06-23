package org.vincent.scheduled;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author PengRong
 * @package org.vincent.scheduled
 * @ClassName ScheduledTaskService.java
 * @date 2019/6/18 - 22:38
 * @ProjectName JavaAopLearning
 * @Description: 定时任务
 */
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT= new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 5000)/** 5000 ms 执行一次*/
    public void scheduleFixTime(){
        System.out.println("每隔 5 s 执行一次该方法. "+ SIMPLE_DATE_FORMAT.format(new Date()));
    }
    @Scheduled(cron = "0 48 22 ? * *")/** 在 每天 22：48  执行这个方法一次*/
    public void schedulecron (){
        System.out.println("在指定时间 执行一次该方法. "+ SIMPLE_DATE_FORMAT.format(new Date()));
    }
}
