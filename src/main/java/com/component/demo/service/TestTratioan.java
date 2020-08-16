package com.component.demo.service;

/**
 * @Author: Dely
 * @Date: 2019/12/8 18:01
 */

import com.component.demo.entity.Student;

/**
 *
 */

public class TestTratioan {
    public void insertSave(int i) {
        Student student = new Student();
        student.setId(i);
        student.setName("dely");

        i=i/(i-i);
        System.out.println(i);
    }
}
