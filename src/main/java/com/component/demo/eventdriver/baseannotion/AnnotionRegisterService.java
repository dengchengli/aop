package com.component.demo.eventdriver.baseannotion;

/**
 * @Author: Dely
 * @Date: 2019/12/10 13:16
 */

import com.component.demo.eventdriver.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 基于注解的事件发布服务
 */

@Service
public class AnnotionRegisterService {
    @Autowired
    private ApplicationEventPublisher publisher;

    public String regist(String name) {
        String res = String.format("基于注解的用户%s注册成功", name);
        System.out.println(res);
        publisher.publishEvent(new UserRegisterEvent(name));
        return res;
    }
}
