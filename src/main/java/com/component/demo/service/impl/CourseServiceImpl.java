package com.component.demo.service.impl;

import com.component.demo.dao.CourseMapper;
import com.component.demo.entity.Course;
import com.component.demo.service.transaction.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Dely
 * @Date: 2020/7/28 21:46
 */

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Course getById(int id) {
        return courseMapper.getById(id);
    }

    @Override
    public String update(Course course) {
        try {
            int count = courseMapper.update(course);
            if (count != 1) {
                return "更新失败!";
            }
        } catch (Exception e) {
            LOG.error("更新失败：{}", e.getMessage());
            return "更新失败!";
        }
        return "更新成功!";
    }

    @Transactional
    @Override
    public String insert(Course course) {
        try {
            int count = courseMapper.insert(course);
            if (count == 1) {
                // throw new  RuntimeException("添加失败");
                return "添加失败!";
            }
        } catch (Exception e) {
            LOG.error("添加失败：{}", e.getMessage());
            throw new  RuntimeException("添加失败: " + e.getMessage());
        }
        return "添加成功";
    }

    @Override
    public boolean deleteById(int id) {
        try {
            courseMapper.delete(id);
        } catch (Exception e) {
            LOG.error("删除失败: {}", e.getMessage());
            return false;
        }
        return true;
    }
}
