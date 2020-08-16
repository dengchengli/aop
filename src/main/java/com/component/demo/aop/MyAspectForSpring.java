package com.component.demo.aop;

/**
 * @Author: Dely
 * @Date: 2019/12/12 23:04
 */


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面类
 */

/**
 * 匹配表达式中的通配符：
 * [1]..:方法中的任意参数 或者 当前包及其子包
 * [2]+ :表示给定类的任意子类 或者 给定接口的实现类
 * [3]* :表示任意的意思
 * <p>
 * 匹配指示符（关键字）：
 * [1]execution: 用来匹配方法
 * [2]within:用来匹配方法。
 * [3]bean: 匹配bean
 * [4]target: 匹配目标 对象的方法。
 */

@Component
@Aspect
public class MyAspectForSpring {


    /**
     * execution:
     */
    @Pointcut(value = "within(com.component.demo.aop.BaseHello+))")
    public void point() {
        System.out.println("我是切入点");
    }

    /**
     * 返回用于绑定通知的切入点表达式.
     * 返回参数名（应与带注释的方法参数名匹配）
     */
    @Before(value = "point() && args(val)")
    public void before(int val) {
        System.out.println("我是before、、、、"+val );
    }

    /**
     * 可以通过JoinPoint类对象作为参数传入通知方法里边来获取目标方法执行前的信息。
     * 也可以像before那种方式达到传参的目的，但是execution和bean匹配指示符帅选的就无法传参
     * @param joinPoint
     */
    @After(value = "point()")
    public void after(JoinPoint joinPoint) {
        System.out.println("我是after。。。。"+ ((Integer) joinPoint.getArgs()[0]));
    }

    @AfterReturning(value = "point()", returning = "val")
    public void afterReturn(JoinPoint joinPoint, String val) {
        System.out.println("我是afterReturning 。。。。。"+val);
    }

    @Around("point()")
    public String around(ProceedingJoinPoint joinPoint) {
        String proceed = null;
        try {
            System.out.println("我是around。。。。前");

             proceed = (String) joinPoint.proceed();


            System.out.println("我是around。。。。后");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;

    }

    @AfterThrowing("point()")
    public void throwAble() {
        System.out.println("我是afterThrowing ......");
    }

}
