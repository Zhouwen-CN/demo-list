package com.yy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: chen
 * @Date: 2022/10/9 16:06
 * @Desc:
 */
public class MyRealm extends AuthenticatingRealm {
    /**
     * 自定义登入认证方法,shiro的login方法,底层会调用该类的认证方法进行认证
     * 需要配置定义的realm生效,在ini文件中配置,在springboot中配置
     * 该方法只是获取进行对比的信息,认证逻辑还是按照shiro底层认证逻辑完成
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.获取身份信息
        String principal = token.getPrincipal().toString();
        // 2.获取凭证信息
        String password = new String((char[]) token.getCredentials());

        System.out.printf("认证用户信息: %s----%s\n",principal,password);
        // 3.获取数据库中存储的用户信息
        if ("zhangsan".equals(principal)){
            // 3.1数据库中存储加盐3次迭代的密码
            String passwordInfo = "7174f64b13022acd3c56e2781e098a5f";

            // 4.创建封装校验逻辑的对象,封装数据返回
            return new SimpleAuthenticationInfo(
                token.getPrincipal(),
                passwordInfo,
                ByteSource.Util.bytes("salt"),
                token.getPrincipal().toString()
            );
        }

        return null;
    }
}
