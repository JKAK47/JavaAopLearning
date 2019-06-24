package org.vincent.strategy.strategyImpl;

import org.springframework.stereotype.Component;
import org.vincent.strategy.Handler;
import org.vincent.strategy.StrategyEnums;

/**
 * @Package com.imodule.externalquery.strategy.strategyImpl
 * @ClassName NewStrategy.java
 * @date 2019/6/10 - 15:05
 * @description : NEW 策略模式 组件
 * Created by PengRong .
 */
@Component
public class NewStrategy implements Handler<String,String> {


    @Override
    public String process(String t) {
        System.out.println("NewStrategy "+t);
        return t;
    }

    @Override
    public StrategyEnums getType() {
        return StrategyEnums.NEW;
    }
}

