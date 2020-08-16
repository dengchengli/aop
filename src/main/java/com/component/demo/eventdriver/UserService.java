package com.component.demo.eventdriver;

/**
 * @Author: Dely
 * @Date: 2019/12/10 12:17
 */

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 定义用户注册服务（即事件发布者）
 */

@Service
public class UserService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    public String register(String name) {
        System.out.println(String.format("用户%s已注册！",name));
        publisher.publishEvent(new UserRegisterEvent(name));  //通过事件发布器发布事件。
        return  "用户注册成功！";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;  //spring 自动注入事件发布器对象。
    }
}
