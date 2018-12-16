package org.vincent.proxy.staticproxy;

/**
 * @author PengRong
 * @package org.vincent.proxy.staticproxy
 * @date 2018/12/15 - 17:19
 * @ProjectName JavaAopLearning
 * @Description: 饲养员 实现Cat接口，作为静态代理类实现。代理狮子的行为。
 * 代理类中可以新增一些其他行为，在实践中主要做的是参数校验的功能。
 */
public class FeederProxy implements Cat {
    private Cat cat;

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String eatFood(String foodName) {
        System.out.println("proxy Lion exec eatFood ");
        return cat.eatFood(foodName);
    }

    @Override
    public boolean running() {
        System.out.println("proxy Lion exec running.");
        return cat.running();
    }
}
