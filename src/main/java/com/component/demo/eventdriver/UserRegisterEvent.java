package com.component.demo.eventdriver;

/**
 * @Author: Dely
 * @Date: 2019/12/10 12:09
 */

import org.springframework.context.ApplicationEvent;

/**
 *定义用户注册事件
 */

//该事件对象不需要交由spring容器来管理,注意：监听的服务，是传入的事件来进行响应的，避免业务相互影响。
public class UserRegisterEvent extends ApplicationEvent {
    /**
     *
     * @param source 事件对象
     */
    public UserRegisterEvent(String source) {
        super(source);
    }
}
