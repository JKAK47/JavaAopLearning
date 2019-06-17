package org.vincent.aop.AspectJ;

/**
 * @author PengRong
 * @package org.vincent.aop
 * @date 2019/1/4 - 0:20
 * @ProjectName JavaAopLearning
 * @Description:
 *  AspectJ 案例
 * https://www.baeldung.com/aspectj
 * https://howtodoinjava.com/spring-aop/spring-aop-aspectj-example-tutorial-using-annotation-config/
 * https://www.mkyong.com/spring3/spring-aop-aspectj-annotation-example/
 * https://blog.jayway.com/2015/09/03/aspectj-and-aop-the-black-magic-of-programming/
 */
public class Account {
    int balance = 20;

    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}
