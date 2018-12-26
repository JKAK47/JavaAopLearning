package org.vincent.aop;

import org.junit.Test;
import org.vincent.aop.dynamicproxy.CustomAspect;
import org.vincent.aop.dynamicproxy.IUserService;
import org.vincent.aop.dynamicproxy.JDKDynamicProxyGenerator;
import org.vincent.aop.dynamicproxy.UserServiceImpl;

/**
 * @Package: org.vincent <br/>
 * @Description： 基于动态代理类AOP测试案例 <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26-16:56 <br/>
 */
public class testAopJDKProxy {
    @Test
    public void testJDKProxy() {
        System.out.println("无代理前 Proxy......");
        IUserService userService = new UserServiceImpl();
        userService.saveUser("zby", "1234567890");
        System.out.println("有代理后AOP 是怎么样的？ Proxy......");
        IUserService proxyUserService = (IUserService) JDKDynamicProxyGenerator.generatorJDKProxy(userService, new CustomAspect());
        proxyUserService.saveUser("zby", "1234567890");
    }
}
