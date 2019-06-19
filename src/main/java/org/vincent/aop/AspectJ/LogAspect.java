package org.vincent.aop.AspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author PengRong
 * @package org.vincent.aop.AspectJ
 * @ClassName LogAspect.java
 * @date 2019/6/16 - 9:46
 * @ProjectName JavaAopLearning
 * @Description: 定义一个切面 实现公共逻辑
 */
@Aspect
@Component
@Order(value = 4)
/** 对于 多个 Aspect 切面时候，多个切面的拦截调用顺序 通过Order 注解 标识 调用顺序， 越小 越先被调用。*/
public class LogAspect {
    /**
     * 定义一个针对 Action注解的切点， value 描述了连接点规则;
     * 切点表达式在 value 属性中指定，定义了连接到业务方法的连接点规则
     * 比如这里定义了在 所有使用了 Action 注解的地方作为连接点，其中切入建言逻辑
     * 切点是连接 业务方法连接点 和切面建言方法之间的桥梁
     */
    @Pointcut(value = "@annotation(org.vincent.aop.AspectJ.Action)")
    public void annotationPointCut() {
    }

    /**
     * 环绕式 建言， 参数是  ProceedingJoinPoint， 可以在调用 proceedingJoinPoint.proceed() 前后，分别加入增强逻辑。
     * <p>
     * [在同一个AspectJ 中，通知拦截方法调用顺序 ：Aroud 环绕式 拦截器链 -》 Before -》Method 方法调用 -》Aroud 环绕式拦截器 -》After 拦截器
     * -》AfterReturning 拦截器 。](https://blog.csdn.net/rainbow702/article/details/52185827)
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around(value = "annotationPointCut()")
    public Object aroud(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            System.out.println("aroud 拦截 begin");
            /**  增强后的类 */
            proceedingJoinPoint.getThis();
            /** 获取注解注入的 方法签名*/
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = signature.getMethod();
            Action annotation = method.getAnnotation(Action.class);
            /** 注解 name =sub  拦截，不调用  */
            if (annotation.name().equals("sub")) {
                System.out.println("被拦截，不能调用业务方法");
                return "ss";
            }

            /** 获取 AOP 增强的的原始类 */
            Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();


            Object proceed = proceedingJoinPoint.proceed(); /** 调用下一个 建言方法或者调用最终业务方法  */
            System.out.println("aroud 拦截 stop . proceed = " + proceed);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
    }

    /**
     * 设置一个切入 注解  Action 的建言（After 建言）；
     * 在被连接点规则描述的业务方法之后切入 。
     * value 属性 指定关联的PointCut 切点属性 这里引用了 annotationPointCut 方法设置的切点表达式
     *
     * @param joinPoint
     */
    @Before(value = "annotationPointCut()") //4
    public void after(JoinPoint joinPoint) {
        System.out.println("begin ... annotationPointCut(). ");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截 :" + action.name() + " . 被拦截方法名称 method = " + method.getName()); //5
    }

    /**
     * 建立一个基于 方法级别的建言， 在方法执行后执行
     *
     * @param joinPoint
     */
    @Before(value = "execution(* org.vincent.aop.AspectJ.DemoMethodService.*(..))") //6
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截 , 方法名称" + method.getName());

    }

}
