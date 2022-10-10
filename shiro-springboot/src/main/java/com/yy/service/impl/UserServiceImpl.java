package com.yy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.domain.User;
import com.yy.mapper.UserMapper;
import com.yy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chen
 * @Date: 2022/10/10 11:50
 * @Desc:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<String> getUserRoleInfo(String principle) {
        return userMapper.getUserRoleInfo(principle);
    }

    @Override
    public List<String> getUserPremonitionsInfo(List<String> roles) {
        return userMapper.getUserPremonitionsInfo(roles);
    }
}
