package com.yy.controller;

import com.yy.bean.Admin;
import com.yy.bean.AdminExample;
import com.yy.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chen
 * @Date: 2022/7/10 15:47
 * @Desc:
 */
@RestController
public class AdminController {

    @Autowired
    AdminDao adminDao;

    @RequestMapping("/all")
    public List<Admin> getAll() {
        AdminExample adminExample = new AdminExample();
        adminExample.setDistinct(false);

        return adminDao.selectByExample(adminExample);
    }
}
