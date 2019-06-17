package org.vincent.annotation;

import org.springframework.stereotype.Service;

/**
 * @author PengRong
 * @package org.vincent.annotation
 * @ClassName DemoService.java
 * @date 2019/6/16 - 23:19
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
@Service
public class DemoService {
    public void output(){
        System.out.println("output XXX.");
    }
}
