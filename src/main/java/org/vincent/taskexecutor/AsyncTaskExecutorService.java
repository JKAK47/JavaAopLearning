package org.vincent.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author PengRong
 * @package org.vincent.taskexecutor
 * @ClassName AsyncTaskExecutorService.java
 * @date 2019/6/16 - 17:47
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
@Service
public class AsyncTaskExecutorService {
    @Async
    public void execute(Integer integer) {
        System.out.println("执行异步任务： " + integer);

    }
}
