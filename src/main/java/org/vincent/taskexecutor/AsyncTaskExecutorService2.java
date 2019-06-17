package org.vincent.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author PengRong
 * @package org.vincent.taskexecutor
 * @ClassName AsyncTaskExecutorService.java
 * @date 2019/6/16 - 17:47
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
@Service
@Async
public class AsyncTaskExecutorService2 {
    /**
     * Spring 基于线程池 异步获取任务执行结果
     * @param integer
     * @return
     */
    public Future<String> execute(Integer integer) {
        System.out.println(" thread :"+Thread.currentThread().getName()+"执行异步任务2： " + integer++);
        return new AsyncResult<>("AsyncTaskExecutorService2 Return = " + integer);
    }
}
