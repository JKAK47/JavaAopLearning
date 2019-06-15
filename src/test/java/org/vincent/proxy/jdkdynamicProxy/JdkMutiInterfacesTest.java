package org.vincent.proxy.jdkdynamicProxy;

import org.vincent.proxy.dynamicproxy.Company;
import org.vincent.proxy.dynamicproxy.Person;
import org.vincent.proxy.dynamicproxy.PersonInvocationHandler;
import org.vincent.proxy.dynamicproxy.SoftwareEngineer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * @author PengRong
 * @package org.vincent.proxy.jdkdynamicProxy
 * @date 2019/1/8 - 8:17
 * @ProjectName JavaAopLearning
 * @Description: JDK多 委托类多接口测试
 */
public class JdkMutiInterfacesTest {
    /**
     * 设置保存Java动态代理生成的类文件。
     *
     * @throws Exception
     */
    public static void saveGeneratedJdkProxyFiles() throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    public static void main(String[] args) throws Exception {
        {
            saveGeneratedJdkProxyFiles();

            //创建一个实例对象，这个对象是被代理的对象，委托类
            SoftwareEngineer person = new SoftwareEngineer("Vincent");
            //创建一个与代理类相关联的InvocationHandler,每一个代理类都有一个关联的 InvocationHandler 代理处理器，并将委托类引用实例传递进去
            InvocationHandler Handler = new PersonInvocationHandler<>(person);
            //创建一个 代理对象 personProxy 来代理 person，创建的代理对象的每个执行方法都会被替换执行Invocation接口中的invoke方法，多接口代理测试，SoftwareEngineer 实现多个接口
            Person personProxy = (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), Handler);
            System.out.println(personProxy.getName());
            personProxy.setName("aaaaaaaBB");
            personProxy.goWorking("大A", "shenzhen");
            /** 生成的动态代理类实现两个接口 ，可以强制到另外一个接口*/
            Company company = (Company) personProxy;
            company.setCompanyName("CCCCCCCCC");
            /** 将生产的代理类 写入文件 . */
            JdkDynamicProxyTest.saveClass("Xx.class", personProxy.getClass().getInterfaces(), "D:/123/");
        }
    }

}
