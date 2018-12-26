package org.vincent.aop;

import org.junit.Test;
import org.vincent.aop.cglib.CglibProxyGenerator;
import org.vincent.aop.cglib.CustomAspect;
import org.vincent.aop.cglib.IUserService;
import org.vincent.aop.cglib.UserServiceImpl;

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
public class testAopCglibKProxy {
    @Test
    public void testCglibProxy() {
        System.out.println("before Proxy......");
        IUserService userService = new UserServiceImpl();
        userService.saveUser("zby", "1234567890");
        System.out.println("引入Cglib  Proxy代理库 后......");
        IUserService proxyUserService = (IUserService) CglibProxyGenerator.generatorCglibProxy(userService, new CustomAspect());
        proxyUserService.saveUser("zby", "1234567890");
    }
}
