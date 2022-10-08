package com.sswl.controller;

import com.sswl.entity.User;
import com.sswl.mapper.MyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: chen @Date: 2022/1/12 17:43 @Desc:
 */
@Controller
public class MyController {
  @Autowired JdbcTemplate jdbcTemplate;

  @GetMapping({"/", "/index"})
  public String toIndex(@RequestParam(required = false, defaultValue = "1") int num, Model model) {
    String sql = "select * from t_user";
    List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

    model.addAttribute("users", list);
    return "index";
  }

  @GetMapping("/add")
  public String toAdd() {
    return "add";
  }

  @PostMapping("/add")
  public String addUser(User user) {
    String sql = "insert into t_user(name,age) values(?,?)";
    jdbcTemplate.update(sql, user.getName(), user.getAge());
    return "redirect:/index";
  }

  @GetMapping("/del/{id}")
  public String delUser(@PathVariable int id) {
    String sql = "delete from t_user where id = ?";
    jdbcTemplate.update(sql, id);
    return "redirect:/index";
  }

  @GetMapping("/update/{id}")
  public String getUser(@PathVariable int id, Model model) {
    String sql = "select * from t_user where id = ?";
    User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), id);
    model.addAttribute("user", user);
    return "update";
  }

  @PostMapping("/update/{id}")
  public String updateUser(@PathVariable int id, User user) {
    String sql = "update t_user set name=?,age=? where id=?";
    jdbcTemplate.update(sql, user.getName(), user.getAge(), id);
    return "redirect:/index";
  }

  @PostMapping("/login")
  public String login(User user) {
    System.out.println(user);
    return "index";
  }
}
