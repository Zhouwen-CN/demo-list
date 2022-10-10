package com.yy.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.domain.User;
import com.yy.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: chen
 * @Date: 2022/10/10 13:34
 * @Desc:
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1.创建对象,封装当前登入用户的角色,权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 2.存储角色
        simpleAuthorizationInfo.addRole("admin");
        // 3.返回信息
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.获取用户身份信息
        String username = token.getPrincipal().toString();
        // 2.调用业务层获取用户信息(数据库)
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        // 3.非空判断,将数据完成封装
        if (Optional.ofNullable(user).isPresent()) {
            return new SimpleAuthenticationInfo(
                token.getPrincipal(),
                user.getPassword(),
                ByteSource.Util.bytes("salt"),
                username
            );
        }

        return null;
    }
}
