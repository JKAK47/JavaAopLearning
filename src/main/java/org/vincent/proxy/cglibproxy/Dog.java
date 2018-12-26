package org.vincent.proxy.cglibproxy;

/**
 * @Package: org.vincent.proxy.cglibproxy <br/>
 * @Description： Cglib 代理模式中 被代理的委托类 <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26-17:55 <br/>
 */
public class Dog {
    public String  call() {
        System.out.println("wang wang wang");
        return "Dog ..";
    }
}
