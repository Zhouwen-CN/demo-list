package com.yy.controller;

import com.yy.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: chen
 * @Date: 2022/10/13 21:55
 * @Desc:
 */
@Controller
public class MyController {
    @RequestMapping("/dataType")
    public String dataType(Model model) {

        model.addAttribute("flag", true);
        model.addAttribute("date", new Date());
        model.addAttribute("numbers", Arrays.asList(18, 10000, 5.5467));
        model.addAttribute("messages", Arrays.asList("hello", "Freemarker"));
        model.addAttribute("stars", new String[]{"周杰伦", "林俊杰", "五月天"});
        model.addAttribute("cities", Arrays.asList("北京", "上海", "深圳", "长沙"));
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "zhangsand", 21));
        users.add(new User(2, "lisi", 36));
        users.add(new User(3, "wangwu", 18));
        model.addAttribute("users", users);

        HashMap<String, String> map = new HashMap<>();
        map.put("sh", "上海");
        map.put("bj", "北京");
        map.put("sz", "深圳");
        model.addAttribute("map", map);


        return "dataType";
    }

    @GetMapping("/commend")
    public String commend() {
        return "commend";
    }

    @GetMapping("/import")
    public String import_template() {
        return "import";
    }
}
