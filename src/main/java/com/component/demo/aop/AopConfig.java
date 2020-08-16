package com.component.demo.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: Dely
 * @Date: 2019/12/13 0:29
 */

/**
 * 配置类
 * spring 中开启 aspectj的方式有两种：
 *   *基于注解的，定义配置类，并且在类中加上@EnableAspectJAutoProxy注解。
 *   *基于xml的，则在xml中配置相关标签。
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

}
