package org.vincent.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @Package: org.vincent.proxy.cglibproxy <br/>
 * @Description： TODO <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26-18:05 <br/>
 */
public class CglibTest {
    @Test
    public void testCglib(){
        CglibMethodInterceptor cglibMethodInterceptor =new CglibMethodInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(cglibMethodInterceptor);
        Dog dog = (Dog) enhancer.create();
        System.out.println(dog.call());
    }
}
