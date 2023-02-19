package com.yy.controller;

import com.yy.entity.User;
import com.yy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: chen
 * @Date: 2023/2/19 21:49
 * @Desc:
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 测试事务回滚
     * Transactional注解开启在service层
     *
     * @return
     */
    @RequestMapping("/tx")
    @ResponseBody
    public String getUser() {
        return userService.insertUser();
    }

    /**
     * 测试mybatis-plus
     *
     * @return
     */
    @RequestMapping("/users")
    @ResponseBody
    public List<User> getUsers() {
        return userService.list();
    }

    /**
     * 测试 controller-view
     *
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

    /**
     * 测试视图解析器
     *
     * @return
     */
    @RequestMapping("/vr")
    public String viewResolve() {
        return "view-resolve";
    }
}
