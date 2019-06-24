package org.vincent.strategy;


/**
 * @Package org.vincent.strategy
 * @ClassName Handler.java
 * @date 2019/6/10 - 14:57
 * @description : 去除if else 策略接口, I  是輸入參數，O 是輸出參數
 * Created by PengRong .
 */
public interface Handler<I, O> {

    /**
     * 执行策略方法
     *
     * @param t
     */
    public O process(I t);

    /**
     * 返回策略类型
     *
     * @return
     */
    StrategyEnums getType();

}
