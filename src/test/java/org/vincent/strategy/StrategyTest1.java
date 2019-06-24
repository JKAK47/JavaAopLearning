package org.vincent.strategy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Package org.vincent.strategy
 * @ClassName StrategyTest.java
 * @date 2019/6/24 - 9:47
 * @description :
 * Created by PengRong .
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StrategyConfig.class})
public class StrategyTest1 {
    /**
     * 测试一个 输入，输出参数是 Integer， String
     */
    @Autowired
    StrategyManager<Integer, String> strategyManager;

    @Test
    public void test() {
        Assert.assertNotNull(strategyManager);
        String result = strategyManager.executeStrategy(StrategyEnums.OLD, 122);
        System.out.println(result);
    }
}
