package com.component.demo.service.transaction;


import com.component.demo.entity.Course;

/**
 * @Author: Dely
 * @Date: 2020/7/28 21:44
 */
public interface CourseService {
     Course getById(int id);

    String update(Course course);

    String insert(Course course);

    boolean deleteById(int id);

}
