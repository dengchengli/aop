package com.component.demo.aop;

import com.component.demo.dao.StudentMapper;
import com.component.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Dely
 * @Date: 2019/12/15 19:31
 */

/**
 * 内层逻辑
 */
@Service
public class TxBService {
    @Autowired
    private StudentMapper dao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public String inUpdate(Student student) throws RuntimeException{
        int update = 0;
        System.out.println("修改名字前。。。。");
        update = dao.update(student);
        System.out.println("修改后");
        System.out.println("返回中、、、、、");
        throw new RuntimeException("B回滚了");
        //return "1";
    }
}
