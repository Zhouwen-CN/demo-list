package com.yy.controller;

import com.yy.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.util.Optional;

/**
 * @Author: chen
 * @Date: 2022/10/10 13:59
 * @Desc:
 */
@Controller
public class MyController {

    // http://localhost:8080/login?username=zhangsan&password=z3
    @PostMapping("/login")
    public String login(User user, HttpSession session){
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 2.封装请求数据到token
        UsernamePasswordToken token = new UsernamePasswordToken(
            user.getUsername(),
            user.getPassword(),
            Optional.ofNullable(user.getRememberMe()).orElse(false)
        );

        // 3. 调用login方法进行登入认证
        try {
            subject.login(token);
            return "redirect:/main";

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "login";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/main")
    public String toMain(HttpSession session){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        session.setAttribute("user",username);
        return "main";
    }

    @GetMapping("/logout")
    public String logout(){
        return "";
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    @ResponseBody
    public String toAdmin(){
        return "角色验证成功";
    }


    @RequiresPermissions("user:add")
    @GetMapping("/user/add")
    @ResponseBody
    public String userAdd(){
        return "权限验证成功";
    }
}

