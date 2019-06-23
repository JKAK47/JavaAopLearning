package org.vincent.aop.AspectJ;

import org.springframework.stereotype.Service;

/**
 * @author PengRong
 * @package org.vincent.aop.AspectJ
 * @ClassName DemoMethodService.java
 * @date 2019/6/16 - 9:45
 * @ProjectName JavaAopLearning
 * @Description: 基于 调用的方法规则的AOP业务方法连接点； 方法拦截
 */
@Service
public class DemoMethodService {

    public void add(){
        System.out.println("add DemoMethodService .");
    }
}
