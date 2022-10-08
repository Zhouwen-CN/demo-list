package com.yy.controller;

import com.yy.entity.Student;
import com.yy.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chen
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentService iStudentService;

    @PostMapping("/add")
    public String add(Student student) {
        iStudentService.save(student);
        return "add";
    }

    @PostMapping("/update")
    public String update(Student student) {
        iStudentService.updateById(student);
        return "update";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        iStudentService.removeById(id);
        return "delete";
    }


    @GetMapping("/list")
    public List<Student> list() {
        return iStudentService.list();
    }

}