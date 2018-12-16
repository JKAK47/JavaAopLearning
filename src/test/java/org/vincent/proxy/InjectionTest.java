package org.vincent.proxy;


import org.vincent.annon.SevenAnnoInjectionHandle;
import org.vincent.annon.example.DogImp;
import org.vincent.proxy.service.AnimalInterface;

/**
 * @author PengRong  IOC 依赖注入注解定义的属性
 * @package org.vincent.proxy
 * @date 2018/12/15 - 14:46
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class InjectionTest {
    /***
     * 创建一个实例然后，通过注入逻辑自动将注解的内容赋值给实例属性
     */
    public static void main(String[] args) throws InterruptedException {
        AnimalInterface dogImp = new DogImp();
        dogImp = (DogImp) SevenAnnoInjectionHandle.getBean(dogImp);
        Thread.sleep(100);
        System.out.println(dogImp.getName());
        dogImp.getProperty();
    }
}
