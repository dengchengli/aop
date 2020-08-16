package com.component.demo.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.component.demo.dao.StudentMapper;
import com.component.demo.entity.Course;
import com.component.demo.entity.Student;
import com.component.demo.service.transaction.CourseService;
import com.component.demo.service.transaction.StudentService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: Dely
 * @Date: 2019/12/9 13:48
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseService courseService;

    @Override
    public Student getById(int id) {
        return studentMapper.getById(id);
    }

    @Override
    public String update(Student student) {
        try {
            int count = studentMapper.update(student);
            if (count != 1) {
                return "更新失败!";
            }
        } catch (Exception e) {
            LOG.error("更新失败：{}", e.getMessage());
            return "更新失败!";
        }
        return "更新成功!";
    }

    @Override
    public String insert(Student student) {
        try {
            int count = studentMapper.insert(student);
            if (count != 1) {
                return "添加失败";
            }
        } catch (Exception e) {
            LOG.error("添加失败：{}", e.getMessage());
            return "添加失败!";
        }
        return "添加成功";
    }

    @Override
    public boolean deleteById(int id) {
        try {
            studentMapper.delete(id);
        } catch (Exception e) {
            LOG.error("删除失败: {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Student> getAll() {
        return studentMapper.getAll();
    }

    /**
     *
     *
     *
     * @param id
     * @param newName
     * @param course
     * @return
     */
    @Transactional
    @Override
    public boolean propagation(int id, String newName, Course course) {
        Student student = new Student(id, newName);
        update(student);
        courseService.insert(course);
        // throw new RuntimeException("调用方抛异常.");
        return true;

        /*try {
            Student student = new Student(id, newName);
            update(student);
            courseService.insert(course);
        } catch (Exception e) {
            LOG.error("操作失败：{}", e.getMessage());
            // throw e;
            return false;
        }
        return true;*/
    }

    /**
     * 不同类间事务传播：
     * 1.被调用方添加事务，调用方无事务，被调用方：
     * -- 被调用方抛异常，调用方捕获并抛出, 则被调用方修改不生效，调用方生效。
     * -- 被调用方抛异常，调用方捕获不抛出，则被调用方修改不生效，调用方生效。
     * 2.调用方有添加事务，被调用方不加事务：
     * -- 被调用方抛异常，调用方不捕获，则都不生效。
     * -- 被调用方抛异常，调用方捕获不抛出，则都生效。
     * -- 被调用方不抛异常，调用方抛异常，则都不生效。
     * 3.调用方和被调用方都加事务：
     * -- 被调用方抛异常，调用方不捕获，则修改都不生效。
     * -- 被调用方抛异常，调用方捕获不抛出，则修改也都不生效。
     * -- 调用方抛异常，被调用方不抛异常，则修改都不生效。
     *
     * ** 总结：在不同 service 类中，最外层的事务抛异常，则修改都不生效。
     *          如果最外层事务被非事务的方法调用，则修改无效的范围只在最外层事务以内，调用最外层事务的方法修改都生效。
     *          即在不同类中默认事务都是以最外层事务为界限。
     *
     */
}
