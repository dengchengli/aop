package com.component.demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: Dely
 * @Date: 2019/12/13 0:38
 */


/**
 * 一定要将该对象注入容器，由容器管理，否则aop无法实现，因为找不到对象。
 */
@Component
public class AopService {
    @Autowired
    private HelloWord helloWord;
    public void test() {
        //helloWord.print();
        helloWord.count(6);
    }
}
