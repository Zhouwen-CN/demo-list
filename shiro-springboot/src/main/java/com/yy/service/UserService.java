package com.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.domain.User;

import java.util.List;

/**
 * @Author: chen
 * @Date: 2022/10/10 11:50
 * @Desc:
 */
public interface UserService extends IService<User> {

    List<String> getUserRoleInfo(String principle);

    List<String> getUserPremonitionsInfo(List<String> roles);
}
