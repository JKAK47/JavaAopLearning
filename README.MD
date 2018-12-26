[TOC]

# Java 代理模式

- 静态代理
- 动态代理
- CGlib代理模式

## 静态代理实现
**静态代理是代理类在编译期间就创建好了，不是编译器生成的代理类，而是手动创建的类。在编译时就已经将接口，被代理类，代理类等确定下来。**
### 实现步骤
- 委托类和代理类之间的约束接口Cat
- 约束接口实现类 Lion，实现 Cat 接口，委托角色
- 代理类实现 FeederProxy，实现Cat 接口，并含有一个 Cat接口引用属性。 代理角色，代理 cat接口属性引用实例的行为并可以新增公共逻辑
#### Cat接口
```java
package org.vincent.proxy.staticproxy;

/**
 * @author PengRong
 * @package org.vincent.proxy.staticproxy
 * @date 2018/12/15 - 17:12
 * @ProjectName JavaAopLearning
 * @Description: 静态代理类接口, 委托类和代理类都需要实现的接口规范。
 * 定义了一个猫科动物的两个行为接口，吃东西，奔跑。
 * 作为代理类 和委托类之间的约束接口
 */
public interface Cat {
    public String eatFood(String foodName);

    public boolean running();
}

```
#### 委托类 Lion
```java
package org.vincent.proxy.staticproxy;


/**
 * @author PengRong
 * @package org.vincent.proxy.staticproxy
 * @date 2018/12/15 - 17:15
 * @ProjectName JavaAopLearning
 * @Description: 狮子 实现了猫科动物接口Cat， 并实现了具体的行为。作为委托类实现
 */
public class Lion implements Cat {
    private String name;
    private int runningSpeed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRunningSpeed() {
        return runningSpeed;
    }

    public void setRunningSpeed(int runningSpeed) {
        this.runningSpeed = runningSpeed;
    }

    public Lion() {
    }

    @Override
    public String eatFood(String foodName) {
        String eat = this.name + " Lion eat food. foodName = " + foodName;
        System.out.println(eat);
        return eat;
    }

    @Override
    public boolean running() {
        System.out.println(this.name + " Lion is running . Speed :" + this.runningSpeed);
        return false;
    }
}

```
#### 代理类角色(FeederProxy)
```java
package org.vincent.proxy.staticproxy;

/**
 * @author PengRong
 * @package org.vincent.proxy.staticproxy
 * @date 2018/12/15 - 17:19
 * @ProjectName JavaAopLearning
 * @Description: 饲养员 实现Cat接口，作为静态代理类实现。代理狮子的行为。
 * 代理类中可以新增一些其他行为，在实践中主要做的是参数校验的功能。
 */
public class FeederProxy implements Cat {
    private Cat cat;

    public FeederProxy(){}
    public FeederProxy(Cat cat) {
        if (cat instanceof Cat) {
            this.cat = cat;
        }
    }

    public void setCat(Cat cat) {
        if (cat instanceof Cat) {
            this.cat = cat;
        }
    }

    @Override
    public String eatFood(String foodName) {
        System.out.println("proxy Lion exec eatFood ");
        return cat.eatFood(foodName);
    }

    @Override
    public boolean running() {
        System.out.println("proxy Lion exec running.");
        return cat.running();
    }
}


```

#### 静态代理类测试
```java
package org.vincent.proxy;

import org.vincent.proxy.staticproxy.Cat;
import org.vincent.proxy.staticproxy.FeederProxy;
import org.vincent.proxy.staticproxy.Lion;

/**
 * @author PengRong
 * @package org.vincent.proxy
 * @date 2018/12/15 - 18:31
 * @ProjectName JavaAopLearning
 * @Description: 静态代理类测试
 */
public class staticProxyTest {
    public static void main(String[] args) {
        Lion lion = new Lion();
        lion.setName("狮子 小王");
        lion.setRunningSpeed(100);
        /**
         * new 静态代理类，静态代理类在编译前已经创建好了，和动态代理的最大区别点
         */
        Cat proxy = new FeederProxy(lion);

        System.out.println(Thread.currentThread().getName()+" -- " + proxy.eatFood("水牛"));
        proxy.running();
    }
}


```

静态代理很好的诠释了代理涉及模式，代理模式最主要的就是有一个公共接口（Cat），一个委托类（Lion），一个代理类（FeederProxy）,代理类持有委托类的实例，代为执行具体类实例方法。
上面说到，代理模式就是在访问实际对象时引入一定程度的间接性，因为这种间接性，可以附加多种用途。这里的间接性就是指客户端不直接调用实际对象的方法，客户端依赖公共接口并使用代理类。
那么我们在代理过程中就可以加上一些其他用途。 就这个例子来说在 eatFood方法调用中，代理类在调用具体实现类之前添加` System.out.println("proxy Lion exec eatFood ");`语句
就是添加间接性带来的收益。代理类存在的意义是为了增加一些公共的逻辑代码。

## 动态代理类
静态代理是代理类在代码运行前已经创建好，并生成class文件；动态代理类 是代理类在程序运行时创建的代理模式。 

动态代理类的代理类并不是在Java代码中定义的，而是在运行时根据我们在Java代码中的“指示”动态生成的。相比于静态代理， 动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类中的方法。
想想你有100个静态代理类，现在有一个需求，每个代理类都需要新增一个处理逻辑，你需要打开100个代理类在每个代理方法里面新增处理逻辑吗？ 有或者代理类有5个方法，每个方法都需要新增一个处理逻辑，
你需要在每个方法都手动新增处理逻辑吗？ 想想就挺无趣的。动态代理类帮你一键搞定。

### 动态代理类涉及角色
- 委托类和代理类实现的公共接口(Person.java)
- 实现公共接口的具体委托类(SoftwareEngineer.java)
- InvocationHandler接口被Proxy类回调处理，一般实现 InvocationHandler 接口的类具有委托类引用，接口方法 invoke 中添加公共代码并调用委托类的接口方法。(PersonInvocationHandler.java)
- JDK提供生成动态代理类的核心类Proxy ( JDK 提供的Proxy.java)

### 动态代理类技术核心 Proxy类和一个 InvocationHandler 接口
java的java.lang.reflect包下提供了Proxy类和一个 InvocationHandler 接口，这个类Proxy定义了生成JDK动态代理类的方法  `getProxyClass(ClassLoader loader,Class<?>... interfaces)`生成动态代理类,返回class实例代表一个class文件。可以保存该 class 文件查看jdk生成的代理类文件长什么样

该生成的动态代理类继承Proxy类，(重要特性) ，并实现公共接口。

InvocationHandler这个接口 是被动态代理类回调的接口，我们所有需要增加的针对委托类的统一处理逻辑都增加到invoke 方法里面在调用委托类接口方法之前或之后 结束战斗。


#### 案例
##### 公共接口
```java
package org.vincent.proxy.dynamicproxy;

/**
 * Created by PengRong on 2018/12/25.
 * 创建Person 接口 用于定义 委托类和代理类之间的约束行为
 */
public interface Person
{
    /**
     *
     * @param name 人名
     * @param dst 工作目的地
     */
    void goWorking(String name, String dst);

    /**
     * 获取名称
     * @return
     */
    String getName( );

    /**
     * 设置名称
     * @param name
     */
    void  setName(String name);
}

```
##### 具体实现类，等下被委托，被代理的类 SoftwareEngineer.java
```java
package org.vincent.proxy.dynamicproxy;

/**
 * Created by PengRong on 2018/12/25.
 * 动态代理委托类实现， 实现接口 Person。 被动态生成的代理类代理
 */
public class SoftwareEngineer implements Person{
    public  SoftwareEngineer(){}
    public  SoftwareEngineer(String name){
        this.name=name;
    }
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void goWorking(String name, String dst) {
        System.out.println("name ="+name+" ， 去 "+dst +" 工作");
    }
}

```
##### InvocationHandler 接口实现 PersonInvocationHandler.java
```java
package org.vincent.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by PengRong on 2018/12/25.
 * PersonInvocationHandler 类 实现InvocationHandler接口，这个类中持有一个被代理对象(委托类)的实例target。该类别JDK Proxy类回调
 * InvocationHandler 接口中有一个invoke方法，当一个代理实例的方法被调用时，代理方法将被编码并分发到 InvocationHandler接口的invoke方法执行。
 */
public class PersonInvocationHandler<T> implements InvocationHandler {
    /**
     * 被代理对象引用，invoke 方法里面method 需要使用这个 被代理对象
     */
    T target;

    public PersonInvocationHandler(T target) {
        this.target = target;
    }


    /**
     * 在
     * @param proxy  代表动态生成的 动态代理 对象实例
     * @param method 代表调用代理类的接口方法，和生成的代理类实例调用的接口方法是一致的，它对应的Method 实例
     * @param args   代表调用接口方法对应的Object参数数组，如果接口是无参，则为null； 对于原始数据类型返回的他的包装类型。
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * 在转调具体目标对象之前，可以执行一些功能处理
         */
        System.out.println("被动态代理类回调执行, 代理类 proxyClass ="+proxy.getClass()+" 方法名: " + method.getName() + "方法. 方法返回类型："+method.getReturnType()
        +" 接口方法入参数组: "+(args ==null ? "null" : Arrays.toString(args)));
        /**
         * 代理过程中插入监测方法,计算该方法耗时
         */
        MonitorUtil.start();
        Thread.sleep(1);
        /** 调用呗代理对象的真实方法，*/
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}

```
##### PersonInvocationHandler invoke 方法中添加的公共代码，这里简单以统计方法执行时间为逻辑

```java
package org.vincent.proxy.dynamicproxy;

/**
 * Created by PengRong on 2018/12/25.
 * 方法用时监控类
 */
public class MonitorUtil {
    private static ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void start() {
        tl.set(System.currentTimeMillis());
    }

    /**
     * 结束时打印耗时
     * @param methodName 方法名
     */
    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        System.out.println(methodName + "方法执行耗时" + (finishTime - tl.get()) + "ms");
    }
}

```

##### 最后的是 怎么创建代理类 

```java
package org.vincent.proxy;

import org.vincent.proxy.dynamicproxy.Person;
import org.vincent.proxy.dynamicproxy.PersonInvocationHandler;
import org.vincent.proxy.dynamicproxy.SoftwareEngineer;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
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
        InvocationHandler Handler = new PersonInvocationHandler<Person>(person);
        //创建一个 代理对象 personProxy 来代理 person，创建的代理对象的每个执行方法都会被替换执行Invocation接口中的invoke方法
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, Handler);
        /** 代理类信息 */
        System.out.println("package = " + personProxy.getClass().getPackage() + " SimpleName = " + personProxy.getClass().getSimpleName() + " name =" + personProxy.getClass().getName() + " CanonicalName = " +
                "" + personProxy.getClass().getCanonicalName() + " 实现的接口 Interfaces = " + Arrays.toString(personProxy.getClass().getInterfaces()) +
                " superClass = " + personProxy.getClass().getSuperclass() + " methods =" + Arrays.toString(personProxy.getClass().getMethods()));
        // 通过 代理类 执行 委托类的代码逻辑
        personProxy.goWorking(personProxy.getName(),"深圳");

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
        InvocationHandler Handlertwo = new PersonInvocationHandler<Person>(persontwo);
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
        stuProxy.goWorking(stuProxy.getName(), "广州");

        saveClass("$PersonProxy0",proxyClass.getInterfaces(),"D:/123/");
    }

    /**
     * 生成代理类 class 并保持到文件中
     * @param className 生成的代理类名称
     * @param interfaces 代理类需要实现的接口
     * @param pathdir 代理类保存的目录路径 
     */
    public  static  void  saveClass(String className,Class<?>[] interfaces,String pathdir){
        /**
         * 第一个参数是 生成的动态代理类类名.
         * 第二个参数是 代理类需要实现的接口
         */
        byte[] classFile = ProxyGenerator.generateProxyClass(className, interfaces);
        String path = pathdir +className+".class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
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

    /**
     * 设置保存Cglib代理生成的类文件。
     *
     * @throws Exception
     */
    public static void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
        props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
    }


}


```
### 解析JDK生成的动态代理类
**saveGeneratedJdkProxyFiles方法 打开了存储jdk生成的动态代理类** 
以 接口方法 goWorking 为例讲解
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sun.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import org.vincent.proxy.dynamicproxy.Person;

public final class $Proxy0 extends Proxy implements Person {
    private static Method m1;
    private static Method m4;
    private static Method m3;
    private static Method m2;
    private static Method m5;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m1, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void setName(String var1) throws  {
        try {
            super.h.invoke(this, m4, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String getName() throws  {
        try {
            return (String)super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    /**
    * 对接口  goWorking 的调用 转变成   super.h.invoke(this, m5, new Object[]{var1, var2}); 调用。
    * h 就是Proxy.java类的一个 InvocationHandler 接口 属性，
    * 我们在创建 动态代理类实例时候都必须 传一个 InvocationHandler 接口的实例过去。 这里就是刚才我们定义的 PersonInvocationHandler 。
    * 回到过后是不是就回到了 PersonInvocationHandler.invoke方法里面，所以 PersonInvocationHandler 是我们生成的动态代理类的拦截器，拦截所有方法调用。
    */
    public final void goWorking(String var1, String var2) throws  {
        try {
            super.h.invoke(this, m5, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final int hashCode() throws  {
        try {
            return ((Integer)super.h.invoke(this, m0, (Object[])null)).intValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }
/**
* 静态代码块，根据动态代理实现的公共接口类接口方法 获取到所有接口方法 的 Method 实例
*/
    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m4 = Class.forName("org.vincent.proxy.dynamicproxy.Person").getMethod("setName", new Class[]{Class.forName("java.lang.String")});
            m3 = Class.forName("org.vincent.proxy.dynamicproxy.Person").getMethod("getName", new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            m5 = Class.forName("org.vincent.proxy.dynamicproxy.Person").getMethod("goWorking", new Class[]{Class.forName("java.lang.String"), Class.forName("java.lang.String")});
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}

```

dk为我们的生成了一个叫$Proxy0（这个名字后面的0是编号，有多个代理类会一次递增）的代理类，这个类文件时默认不会保存在文件，放在内存中的，我们在创建代理对象时，就是通过反射获得这个类的构造方法，然后创建代理对象实例。通过对这个生成的代理类源码的查看，我们很容易能看出，动态代理实现的具体过程。

我们可以对 InvocationHandler 看做一个中介类，中介类持有一个被代理对象，被Proxy类回调。在invoke方法中调用了被代理对象的相应方法。通过聚合方式持有被代理对象的引用，把外部对invoke的调用最终都转为对被代理对象的调用。

代理类调用自己方法时，通过自身持有的中介类对象引用来调用中介类对象的invoke方法，从而达到代理执行被代理对象的方法。也就是说，动态代理Proxy类提供了模板实现，对外提供扩展点，外部通过实现InvocationHandler接口将被代理类纳入JDK代理类Proxy。

### 一个典型的动态代理创建对象过程可分为以下四个步骤：
1、通过实现InvocationHandler接口创建自己的调用处理器 IvocationHandler handler = new InvocationHandlerImpl(...);
2、通过为Proxy类指定ClassLoader对象和一组interface创建动态代理类类文件，默认JDK并不会保存这个文件到文件中；可以保存起来观察生成的代理类结构
Class clazz = Proxy.getProxyClass(classLoader,new Class[]{...});
3、通过上面新建的代理clazz的反射机制获取动态代理类的一个构造函数，其构造函数入参类型是调用处理器接口(IvocationHandler)类型
Constructor constructor = clazz.getConstructor(new Class[]{InvocationHandler.class});
4、通过构造函数实例创建代理类实例，此时需将调用处理器对象作为参数被传入
Interface Proxy = (Interface)constructor.newInstance(new Object[] (handler));
为了简化对象创建过程，Proxy类中的newInstance工具方法封装了2~4，只需两步即可完成代理对象的创建。

### JDK动态代理总结
生成的代理类：$Proxy0 extends Proxy implements Person，我们看到代理类继承了Proxy类，所以也就决定了java动态代理只能对接口进行代理，Java的继承机制决定了JDK动态代理类们无法实现对 类 的动态代理。
### 动态代理不足
动态代理只能对接口产生代理，不能对类产生代理



## CGlib 代理类实现 
cglib是针对类来实现代理的，他的原理是对代理的目标类生成一个子类，并覆盖其中方法实现增强，因为底层是基于创建被代理类的一个子类，所以

但因为采用的是继承，所以不能对final修饰的类进行代理。final修饰的类不可继承。
### 导入maven 依赖
**cglib 是基于asm 字节修改技术。导入 cglib 会间接导入 asm, ant, ant-launcher 三个jar 包。**
```xml
  <!-- cglib 动态代理依赖 begin -->
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.2.5</version>
            <exclusions>
                <exclusion>
                    <artifactId>asm</artifactId>
                    <groupId>org.ow2.asm</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <!-- 直接引用  -->
        <dependency>
            <groupId>org.ow2.asm</groupId>
            <artifactId>asm</artifactId>
            <version>5.2</version>
        </dependency>
        <!-- cglib 动态代理依赖 stop -->
```

## 参考
---
[java动态代理实现与原理详细分析](http://www.cnblogs.com/gonjan-blog/p/6685611.html)

[保存Java代理框架生成的类文件](https://blog.csdn.net/iteye_6889/article/details/82552672)

[java的动态代理机制详解](http://www.cnblogs.com/xiaoluo501395377/p/3383130.html)

[彻底理解JAVA动态代理](https://www.cnblogs.com/flyoung2008/archive/2013/08/11/3251148.html)
还没看





[Java动态代理的两种实现方法](https://blog.csdn.net/heyutao007/article/details/49738887)

[JAVA反射与注解](https://www.daidingkang.cc/2017/07/18/java-reflection-annotations/)