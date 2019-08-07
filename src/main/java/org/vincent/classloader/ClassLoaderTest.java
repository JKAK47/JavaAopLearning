package org.vincent.classloader;


import java.io.IOException;
import java.io.InputStream;

/**
 * @Package org.vincent.classloader
 * @ClassName ClassLoaderTest.java
 * @date 2019/7/29 - 13:39
 * @description :
 * Created by PengRong .
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /**
         * 自定义类加载器
         * */
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException E) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object object =classLoader.loadClass("org.vincent.classloader.ClassLoaderTest").newInstance();
        /**  获取类 package */
        System.out.println(object.getClass().getPackage());
        /** 获取类全路径名称*/
        System.out.println(object.getClass().getName());
        /** 返回false 说明不是同一个类加载器加载的类，
         * object 是使用自定义类加载器加载的类, 和  ClassLoaderTest 对比返回 false
         * 说明不是 同一个类加载器加载的
         * */
        System.out.println(object instanceof ClassLoaderTest);
        Object twoObject =new ClassLoaderTest();
        /** 返回 true 说明不是同一个类加载器加载的类，
         * object 是使用自定义类加载器加载的类, 和  ClassLoaderTest 对比返回 true
         * 说明是 同一个类加载器加载的,都是系统类加载器加载
         * */
        System.out.println(twoObject instanceof  ClassLoaderTest);
    }
}
