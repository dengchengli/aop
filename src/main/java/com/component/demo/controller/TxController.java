package com.component.demo.controller;

import com.component.demo.aop.TxAService;
import com.component.demo.entity.Course;
import com.component.demo.entity.Student;
import com.component.demo.service.transaction.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Dely
 * @Date: 2019/12/15 19:45
 */

@RestController
public class TxController {

    @Autowired
    private TxAService txAService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/tx/update")
    public ResponseEntity update(@RequestBody Student student) {

        String update = txAService.update(student);
        return ResponseEntity.ok(update);
    }

    @PostMapping("/tx/default")
    public ResponseEntity defaultUpdate(@RequestBody Student student, String newName) throws Exception{

        student = txAService.defaultPropagation(student, newName);
        return ResponseEntity.ok(student);
    }

    /**
     * 更新学生信息，添加课程信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/tx/default/propagation/{id}")
    public ResponseEntity defaultPropagation(@RequestBody Course course, @RequestParam("name") String name, @PathVariable("id") int id) throws Exception{
        boolean result = studentService.propagation(id, name, course);
        return ResponseEntity.ok(result);
    }
}
