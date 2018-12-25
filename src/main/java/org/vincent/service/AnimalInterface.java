package org.vincent.service;

/**
 * 动态代理的业务接口定义
 *
 * @author PengRong
 * @ClassName: AnimalInterface
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年3月5日 下午3:52:15
 */
public interface AnimalInterface {
    // 设置名字
    void setName(String name);

    // 获取名字
    String getName();

    // 叫声
    void say();

    // 获取栖性
    void getProperty();

    // 设置栖性
    void setProperty(String Property);
}
