package com.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: chen
 * @Date: 2022/10/10 11:50
 * @Desc:
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> getUserRoleInfo(@Param("principle") String principle);
}