package org.vincent.proxy.jdkdynamicProxy;

import org.vincent.proxy.dynamicproxy.Person;
import org.vincent.proxy.dynamicproxy.PersonInvocationHandler;
import org.vincent.proxy.dynamicproxy.SoftwareEngineer;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

/**
 * 动态代理类测试
 * Created by PengRong on 2018/12/25.
 */
public class JdkDynamicProxyTest {
    public static void main(String[] args) throws Exception {
        // 打开保存JDK动态代理生成的类文件
        saveGeneratedJdkProxyFiles();


        /**
         * 第一种方法: 通过 Proxy.newProxyInstance 方法 获取代理对象
         */
        System.out.println("-------------------第一种创建代理类方法--------------");
        //创建一个实例对象，这个对象是被代理的对象，委托类
        Person person = new SoftwareEngineer("Vincent");
        //创建一个与代理类相关联的InvocationHandler,每一个代理类都有一个关联的 InvocationHandler，并将代理类引用传递进去
        InvocationHandler Handler = new PersonInvocationHandler<>(person);
        //创建一个 代理对象 personProxy 来代理 person，创建的代理对象的每个执行方法都会被替换执行Invocation接口中的invoke方法
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, Handler);
        /** 代理类信息 */
        System.out.println("package = " + personProxy.getClass().getPackage() + " SimpleName = " + personProxy.getClass().getSimpleName() + " name =" + personProxy.getClass().getName() + " CanonicalName = " +
                "" + personProxy.getClass().getCanonicalName() + " 实现的接口 Interfaces = " + Arrays.toString(personProxy.getClass().getInterfaces()) +
                " superClass = " + personProxy.getClass().getSuperclass() + " methods =" + Arrays.toString(personProxy.getClass().getMethods()));
        // 通过 代理类 执行 委托类的代码逻辑
        personProxy.goWorking(personProxy.getName(), "深圳");
        /** toString方法也会被代理 */
        System.out.println(personProxy.toString());
        System.out.println("-------------------第二种创建代理类方法--------------");
        /**
         *  动态代理对象步骤
         *      1、 创建一个与代理对象相关联的 InvocationHandler，以及真实的委托类实例
         *      2、Proxy类的getProxyClass静态方法生成一个动态代理类stuProxyClass，该类继承Proxy类，实现 Person.java接口；JDK动态代理的特点是代理类必须继承Proxy类
         *      3、通过代理类 proxyClass 获得他的带InvocationHandler 接口的构造函数 ProxyConstructor
         *      4、通过 构造函数实例 ProxyConstructor 实例化一个代理对象，并将  InvocationHandler 接口实例传递给代理类。
         */
        // 1、创建 InvocationHandler 实例并设置代理的目标类对象
        Person persontwo = new SoftwareEngineer("Vincent");
        InvocationHandler Handlertwo = new PersonInvocationHandler<>(persontwo);
        // 2 创建代理类,是一个字节码文件, 把 proxyClass 保存起来就能看到 他继承Proxy 类，实现Person接口
        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});
        /** 代理类信息 */
        System.out.println("package = " + proxyClass.getPackage() + " SimpleName = " + proxyClass.getSimpleName() + " name =" + proxyClass.getName() + " CanonicalName = " +
                "" + proxyClass.getCanonicalName() + " 实现的接口 Interfaces = " + Arrays.toString(proxyClass.getInterfaces()) +
                " superClass = " + proxyClass.getSuperclass() + " methods =" + Arrays.toString(proxyClass.getMethods()));

        // 3、  通过 proxyClass 获得 一个带有InvocationHandler参数的构造器constructor
        Constructor<?> ProxyConstructor = proxyClass.getConstructor(InvocationHandler.class);
        // 4、通过构造器创建一个  动态代理类 实例
        Person stuProxy = (Person) ProxyConstructor.newInstance(Handlertwo);
        /** 检测生成的类是否是代理类 */
        System.out.println("stuProxy isProxy "+Proxy.isProxyClass(stuProxy.getClass()));
        /** 获取 代理类关联的 InvocationHandler 是哪个*/
        InvocationHandler handlerObject = Proxy.getInvocationHandler(stuProxy);
        System.out.println(handlerObject.getClass().getName());
        stuProxy.goWorking(stuProxy.getName(), "广州");
        // 保存代理類
        saveClass("$PersonProxy0", proxyClass.getInterfaces(), "D:/123/");
    }

    /**
     * 生成代理类 class 并保持到文件中
     *
     * @param className  生成的代理类名称
     * @param interfaces 代理类需要实现的接口
     * @param pathdir    代理类保存的目录路径,以目录分隔符结尾
     */
    public static void saveClass(String className, Class<?>[] interfaces, String pathdir) {
        /**
         * 第一个参数是 代理类 名 。
         * 第二个参数是 代理类需要实现的接口
         */
        byte[] classFile = ProxyGenerator.generateProxyClass(className, interfaces);
        /**
         * 如果目录不存在就新建所有子目录
         */
        Path path1 = Paths.get(pathdir);
        if (!path1.toFile().exists()){
            path1.toFile().mkdirs();
        }
        String path = pathdir + className + ".class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }

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




}
