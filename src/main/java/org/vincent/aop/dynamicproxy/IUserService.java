package org.vincent.aop.dynamicproxy;

/**
 * @Package: org.vincent.aop.dynamicproxy <br/>
 * @Description： AOP基于动态代理 实现  <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26 <br/>
 */
public interface IUserService {
    void saveUser(String username, String password) throws Exception;
}
