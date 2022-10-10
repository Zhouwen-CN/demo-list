package com.yy.config;

import com.yy.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chen
 * @Date: 2022/10/10 13:46
 * @Desc:
 */
@Configuration
public class ShiroConfig {
    @Autowired
    MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        // 1.创建defaultWebSecurityManager
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 2.创建加密对象,设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(3);
        // 3.将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(matcher);
        // 4.将myRealm存入defaultWebSecurityManager
        defaultWebSecurityManager.setRealm(myRealm);
        // 5.设置remember me
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager());
        // 6.设置session
        // defaultWebSecurityManager.setSessionManager(sessionManager());
        // 7.返回
        return defaultWebSecurityManager;
    }

    // session 设置
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager= new DefaultWebSessionManager();
        //  设置保留工夫；默认是30分钟
        sessionManager.setGlobalSessionTimeout(60*60*1000);
        return sessionManager;
    }

    // cookie设置
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();

        // cookie 属性设置
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置跨域
        // cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        cookieRememberMeManager.setCookie(cookie);

        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 设置不认证可以访问的资源
        definition.addPathDefinition("/login","anon");
        // 登出操作
        definition.addPathDefinition("/logout","logout");
        // 设置需要进行登入认证的拦截范围
        definition.addPathDefinition("/**","authc");
        // 添加存在用户的过滤器（rememberMe）
        definition.addPathDefinition("/**","user");
        return definition;
    }
}
