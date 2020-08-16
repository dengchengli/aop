package com.component.demo.controller;

import com.component.demo.entity.Course;
import com.component.demo.service.transaction.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Dely
 * @Date: 2020/7/28 22:12
 */

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable("id") int id) {
        Course student = courseService.getById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/info/add")
    public ResponseEntity updateInfo(@RequestBody Course student) {
        String result = courseService.insert(student);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/info/update")
    public ResponseEntity addInfo(@RequestBody Course student) {
        String result = courseService.update(student);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/info/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        boolean result = courseService.deleteById(id);
        return ResponseEntity.ok(result);
    }
}
