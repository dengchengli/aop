package com.component.demo.eventdriver;

/**
 * @Author: Dely
 * @Date: 2019/12/10 11:49
 */

/**
 * 事件驱动
 *  1.事件驱动的相关概念：
 *     **观察者模式：
 *     **发布订阅模式：发布订阅与观察者模式相似，但是发布订阅模式需要一个调度中心，但是观察者列表可以由被观察者维护。
 *     **MQ：是发布订阅模式的一种具体实现。 事件驱动->发布订阅->MQ  从抽象到具体的一个过程。
 *  2.spring中的事件驱动：
 *     **ApplicationContext通过ApplicationEvent类和ApplicationListener接口来处理事件。如果实现ApplicationListener接口的bean注入到上下文中，
 *                        则每次使用ApplicationContext发布ApplicationEvent时，都会通知该bean。本质上是观察者模式
 *     **spring4.2之后提供注解的支持，通过Java对象配合注解，可以达到上边等同的效果。
 *
 */
public class EventDriverTest {
    public static void main(String[] args) {

    }
}
