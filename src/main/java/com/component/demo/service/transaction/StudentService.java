package com.component.demo.service.transaction;

import com.component.demo.entity.Course;
import com.component.demo.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * @Author: Dely
 * @Date: 2020/7/28 21:44
 */
public interface StudentService {
    Student getById(int id);

    String update(Student student);

    String insert(Student student);

    boolean deleteById(int id);

    List<Student> getAll();

    boolean propagation(int id, String newName, Course course);
}
