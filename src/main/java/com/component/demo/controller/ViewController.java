package com.component.demo.controller;

import com.component.demo.aop.AopService;
import com.component.demo.aop.BaseTxService;
import com.component.demo.entity.Student;
import com.component.demo.service.transaction.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Dely
 * @Date: 2019/12/9 13:47
 */

@RestController
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AopService aopService;
    @Autowired
    private BaseTxService txService;

    @Value("${server.port}")
    private int port;
    @Value("${spring.application.name}")
    private String application;


    @GetMapping("student/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Student st = txService.getById(id);
        return ResponseEntity.ok(st);
    }

    @PostMapping("/student/update")
    public ResponseEntity update(@RequestBody Student student) {
        String update = null;
        try {
            update = txService.update(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(update);
    }

    @PostMapping("/student/insert")
    public ResponseEntity insert(@RequestBody Student student) {
        int res = txService.insert(student);
        return ResponseEntity.ok(res);
    }


    /**
     * 用来测试基于注解的mybatis的搭建  success
     *
     * @return
     */
    @GetMapping("/student")
    public ResponseEntity getStudents() {
        List<Student> all = studentService.getAll();
        return ResponseEntity.ok(all);
    }

    /**
     * 用于测试nginx负载均衡时的策略  success
     *
     * @return
     */
    @GetMapping("/hello")
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello" + "我是" + application + port);
    }

    /**
     * 用来测试spring aop 的实现。
     *
     * @return
     */
    @GetMapping("/aop/test")
    public ResponseEntity test() {
        aopService.test();
        return ResponseEntity.ok("0000");
    }
}
