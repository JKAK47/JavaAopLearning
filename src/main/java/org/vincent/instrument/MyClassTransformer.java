package org.vincent.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Package org.vincent.instrument
 * @ClassName MyClassTransformer.java
 * @date 2019/7/26 - 14:02
 * @description : 自定义 自己的 Class File Transformer  实现编译期 增强; 该 编译期增强 转换器需要注册到JVM 中
 * Created by PengRong .
 */
public class MyClassTransformer implements ClassFileTransformer {
    /**
     * 注意：transform方法会有一个返回值，类型是byte[]，表示转换后的字节码，
     * 但是如果返回为空，则表示不进行节码转换处理，千万不要当作是把原先类的字节码清空。
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("ClassLoader :"+ loader.getClass());
        System.out.println(" 当前AOP 织入类： "+ className);
        return null;
    }
}
