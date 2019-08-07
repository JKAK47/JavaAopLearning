package org.vincent.event.pojo;

import java.math.BigDecimal;

/**
 * @author PengRong
 * @package org.vincent.event.pojo
 * @ClassName AA.java
 * @date 2019/8/7 - 22:15
 * @ProjectName JavaAopLearning
 * @Description: 自定义类作为一个事件对象
 */
public class AA {
    private String name;
    private Integer integer;
    private BigDecimal bigDecimal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public AA(String name, Integer integer, BigDecimal bigDecimal) {
        this.name = name;
        this.integer = integer;
        this.bigDecimal = bigDecimal;
    }
}
