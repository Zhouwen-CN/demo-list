package com.yy.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.mapper.UserMapper;
import com.yy.domain.User;
import com.yy.service.UserService;
/**
 * @Author: chen
 * @Date: 2022/10/10 11:50
 * @Desc: 
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
