package com.yy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.dao.UserMapper;
import com.yy.entity.User;
import com.yy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author: chen
 * @Date: 2023/2/19 21:49
 * @Desc:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public String insertUser() {
        User user = new User(null, UUID.randomUUID().toString().substring(0, 5), 18);
        int num = userMapper.insert(user);
        int i = 1 / 0;
        return String.format("insert %d user", num);
    }
}
