package org.vincent.aop.dynamicproxy;

import java.util.Objects;

/**
 * @Package: org.vincent.aop.dynamicproxy <br/>
 * @Description： 改类作为AOP 模型中切面角色类， 实现切面接口，切面接口定义了两个切面方法，分别在切点接口方法执行前和执行后执行 。 <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26 <br/>
 */
public class CustomAspect implements IAspect {


    /**
     * 对参数 做判空处理
     * @param args 切点参数列表
     * @return
     */
    @Override
    public boolean startTransaction(Object... args) {
        Objects.nonNull(args);
        boolean result = true;
        for (Object temp :args) {
            if (Objects.isNull(temp)){
                 result =false;
                 break;
            }
        }
        return result;
    }

    public void endTrasaction() {
        System.out.println("I get datasource here and end transaction");
    }
}
