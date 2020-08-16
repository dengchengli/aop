package com.component.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @Author: Dely
 * @Date: 2019/12/8 18:02
 */


@Data
@ToString
@NoArgsConstructor
public class Student {
    private final static long serialVersionUID = -1;
    private int id;
    private String name;



    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
