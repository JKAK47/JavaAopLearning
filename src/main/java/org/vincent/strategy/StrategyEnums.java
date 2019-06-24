package org.vincent.strategy;

/**
 * @Package org.vincent.strategy
 * @ClassName StrategyEnums.java
 * @date 2019/6/10 - 14:58
 * @description : 策略接口类型定义 每个策略接口实现的枚举值；
 * 用於枚舉不同的情況值
 * Created by PengRong .
 */
public enum StrategyEnums {
    /**
     * NEW 枚举类型
     */
    NEW("00"),
    /**
     * OLD 枚举类型
     */
    OLD("01");

    private String strategyCode;

    StrategyEnums(String strategyCode) {
        this.strategyCode = strategyCode;
    }

    public String getStrategyCode() {
        return strategyCode;
    }

}