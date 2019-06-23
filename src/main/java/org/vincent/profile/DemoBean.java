package org.vincent.profile;

/**
 * @author PengRong
 * @package org.vincent.profile
 * @ClassName DemoBean.java
 * @date 2019/6/18 - 22:01
 * @ProjectName JavaAopLearning
 * @Description: 是Profile 注解案例，用于解说 基于不同环境实例化不同的Bean
 *
 */

public class DemoBean {
    private String content;

    public DemoBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
