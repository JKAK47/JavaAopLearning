package org.vincent.strategy.strategyImpl;


import org.springframework.stereotype.Component;
import org.vincent.strategy.Handler;
import org.vincent.strategy.StrategyEnums;

/**
 * @Package com.imodule.externalquery.strategy.strategyImpl
 * @ClassName OldStrategy.java
 * @date 2019/6/10 - 15:06
 * @description : Old 策略模式 组件
 * Created by PengRong .
 */
@Component
public class OldStrategy implements Handler<Integer,String> {


    @Override
    public String process(Integer t) {
        System.out.println("OldStrategy" + t);
        return "oldStategy";
    }

    @Override
    public StrategyEnums getType() {
        return StrategyEnums.OLD;
    }
}

