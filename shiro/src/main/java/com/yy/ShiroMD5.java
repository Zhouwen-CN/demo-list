package com.yy;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Author: chen
 * @Date: 2022/9/28 17:24
 * @Desc:
 */
public class ShiroMD5 {
    public static void main(String[] args) {
        //密码明文
        String password = "z3";

        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println("md5加密: "+md5Hash.toHex());

        Md5Hash md5Hash1 = new Md5Hash(password, "salt");
        System.out.println("带盐的md5加密: "+md5Hash1.toHex());

        Md5Hash md5Hash2 = new Md5Hash(password, "salt",3);
        System.out.println("带盐的md5加密迭代3次: "+md5Hash2.toHex());

        SimpleHash simpleHash=new SimpleHash("MD5",password,"salt",3);
        System.out.println("父类带盐md5加密迭代3次: "+simpleHash.toHex());
    }
}
