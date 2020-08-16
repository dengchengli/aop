package com.component.demo.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: Dely
 * @Date: 2020/7/28 21:49
 */


@Data
@ToString
public class Course {
    private Integer id;

    private String name;

    private float grade;
}
