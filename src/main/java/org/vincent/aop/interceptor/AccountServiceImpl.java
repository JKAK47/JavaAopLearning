package org.vincent.aop.interceptor;

/**
 * @author PengRong
 * @package org.vincent.aop.interceptor
 * @date 2019/1/10 - 23:13
 * @ProjectName JavaAopLearning
 * @Description: 作为一个业务测试类使用 被代理 委托的类
 */
public class AccountServiceImpl {
    public String login(String name,String password){
        System.out.println("name "+name +" password: "+password);
        return "login return ";
    }
}
