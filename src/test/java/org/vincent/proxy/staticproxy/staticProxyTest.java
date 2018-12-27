package org.vincent.proxy.staticproxy;

import org.vincent.proxy.staticproxy.Cat;
import org.vincent.proxy.staticproxy.FeederProxy;
import org.vincent.proxy.staticproxy.Lion;

/**
 * @author PengRong
 * @package org.vincent.proxy
 * @date 2018/12/15 - 18:31
 * @ProjectName JavaAopLearning
 * @Description: 静态代理类测试
 */
public class staticProxyTest {
    public static void main(String[] args) {
        Lion lion = new Lion();
        lion.setName("狮子 小王");
        lion.setRunningSpeed(100);
        /**
         * new 静态代理类，静态代理类在编译前已经创建好了，和动态代理的最大区别点
         */
        Cat proxy = new FeederProxy(lion);

        System.out.println(Thread.currentThread().getName()+" -- " + proxy.eatFood("水牛"));
        proxy.running();
    }
}
