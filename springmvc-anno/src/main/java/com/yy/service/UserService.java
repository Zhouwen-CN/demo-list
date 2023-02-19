package com.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.entity.User;

/**
 * @Author: chen
 * @Date: 2023/2/19 21:49
 * @Desc:
 */
public interface UserService extends IService<User> {
    String insertUser();
}
