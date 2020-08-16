package com.component.demo.boot;

/**
 * @Author: Dely
 * @Date: 2019/12/22 13:59
 */


/**
 *  1.使用springboot的目的：简化spring应用的搭建和开发过程。
 *  2.springboot的特点：
 *    *内嵌Tomcat容器。不用部署war文件。
 *    *简化maven配置。
 *    *自动配置spring。
 *    *健康检查和外部配置。
 *    *不需要配置繁琐的xml。
 *
 *
 * springboot源码解读：
 *    [1]SpringApplication 启动 Spring:创建context应用上下文，并且通过上下文加载和刷新bean。
 *    [2]Starter自动化配置:Starter是怎样生效的。
 *          **通过主函数中的SpringbootApplication注解，里边有一个EnableAutoConfiguration注解，他是springboot的全局开关，如果把这个注解去掉，全部的Starter都会失效。
 *            而AutoConfigurationImportSelector类的selectImports方法会内部会调用读取spring.factories文件中配置的类。然后通过spring提供的BeanDefinitionRegistryPostProcessor扩展点
 *            来实现starter和spring的整合实现Starter配置类的解析和注册等功能。
 *
 *
 *
 *
 */
public class SourceCodeRead {
}
