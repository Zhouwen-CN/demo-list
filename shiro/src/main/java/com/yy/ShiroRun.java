package com.yy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroRun {
    public static void main(String[] args) {
        Ini ini = IniSecurityManagerFactory.loadDefaultClassPathIni();

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory(ini);

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "z3");
        try {
            // 登入认证
            subject.login(token);

            // 判断角色
            System.out.println(subject.hasRole("role1"));
            // 判断权限
            System.out.println(subject.isPermitted("user:insert"));
            // checkPermission没有返回值,如果没有对应的权限则直接抛出异常
            subject.checkPermission("user:select");

            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
        }
    }
}
