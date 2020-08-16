package com.component.demo.aop;

/**
 * @Author: Dely
 * @Date: 2019/12/12 23:01
 */

import org.springframework.stereotype.Component;

/**
 * demo中代表业务逻辑类
 */

@Component
public class HelloWord implements BaseHello{

    private int id = 10;
    public void print() {
        System.out.println(7777);
    }

    public String count(int val) {
        System.out.println(val);
        return String.valueOf(val);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
