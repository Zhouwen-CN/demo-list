package com.yy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Author: chen
 * @Date: 2022/9/28 16:28
 * @Desc:
 */
public class ShiroRun {
    public static void main(String[] args) {
        Ini ini = IniSecurityManagerFactory.loadDefaultClassPathIni();

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory(ini);

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("zhangsan",
                                                              "z3");
        try {
            subject.login(token);

            System.out.println(subject.hasRole("role1"));
            System.out.println(subject.isPermitted("user:insert"));

            System.out.println("登录成功");
        }
        catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户不存在");
        }
        catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
        catch (AuthenticationException ae) {
            ae.printStackTrace();
        }


    }
}
