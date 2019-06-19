package org.vincent.aop.AspectJ;

import org.springframework.stereotype.Service;

/**
 * @author PengRong
 * @package org.vincent.aop.AspectJ
 * @ClassName DemoAnnotationService.java
 * @date 2019/6/16 - 9:40
 * @ProjectName JavaAopLearning
 * @Description: 使用方法级别注解Action，作为切面建言方法切入的连接点
 */
@Service
public class DemoAnnotationService {
    @Action(name="注解式拦截的add操作")
    public String add(){
        System.out.println(" execute add  method annotation.");
        return " Action annotation.";
    }
    @Action(name = "sub")
    public String sub(){
        System.out.println("sub AspectJ ");
        return  "sub method invoke";
    }
}
