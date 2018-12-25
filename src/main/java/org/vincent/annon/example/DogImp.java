package org.vincent.annon.example;


import org.vincent.annon.Seven;
import org.vincent.service.AnimalInterface;

/**
 * 实现接口的具体业务类
 *
 * @author PengRong
 * @ClassName: DogImp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年3月5日 下午3:53:12
 */
public class DogImp implements AnimalInterface {

    @Seven(value = "Lumia")
    private String name;

    private String Property;

    public DogImp() {
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void say() {
        System.out.println("小狗:汪汪汪汪.....");
    }

    @Override
    @Seven(Property = "水陆两栖战士")
    public void setProperty(String Property) {
        this.Property = Property;
    }

    @Override
    public void getProperty() {
        System.out.println(this.name + "= " + this.Property);
    }
}