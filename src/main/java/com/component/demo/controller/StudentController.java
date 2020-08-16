package com.component.demo.controller;

import com.component.demo.entity.Student;
import com.component.demo.service.transaction.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Dely
 * @Date: 2020/7/28 22:04
 */

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable("id") int id) {
        Student student = studentService.getById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/info/add")
    public ResponseEntity addInfo(@RequestBody Student student) {
        String result = studentService.insert(student);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/info/update")
    public ResponseEntity updateInfo(@RequestBody Student student) {
        String result = studentService.update(student);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/info/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        boolean result = studentService.deleteById(id);
        return ResponseEntity.ok(result);
    }
}
