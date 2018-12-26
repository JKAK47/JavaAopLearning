package org.vincent.aop.cglib;

/**
 * @Package: org.vincent.aop.dynamicproxy <br/>
 * @Description： 定义切面接口，切面接口定义了两个切面方法，分别在切点接口方法执行前和执行后执行 <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26 <br/>
 */
public interface IAspect {
    /**
     * 在切点接口方法执行之前执行
     */
    void startTransaction();

    /**
     * 在切点接口方法执行之后执行
     */
    void endTrasaction();
}
