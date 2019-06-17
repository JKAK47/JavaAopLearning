package org.vincent.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author PengRong
 * @package org.vincent.taskexecutor
 * @ClassName MainTaskExecutor.java
 * @date 2019/6/16 - 17:50
 * @ProjectName JavaAopLearning
 * @Description: Spring 异步任务测试类
 */

public class MainTaskExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AnnotationConfigApplicationContext configApplicationContext =new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskExecutorService asyncTaskExecutorService = configApplicationContext.getBean(AsyncTaskExecutorService.class);
        AsyncTaskExecutorService2 asyncTaskExecutorService2 = configApplicationContext.getBean(AsyncTaskExecutorService2.class);
        for (int i=0;i<=20;i++){
            asyncTaskExecutorService.execute(i);
           asyncTaskExecutorService2.execute(i);
        }
        /** 提交一个异步任务并获取异步任务执行结果
         * */
        Future<String> execute = asyncTaskExecutorService2.execute(500);
        while (!execute.isDone()){

        }
        System.out.println(execute.get().toString());
        configApplicationContext.close();
    }
}
