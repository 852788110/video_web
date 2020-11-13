package com.liu.service;

import com.liu.mbg.model.User;

/*
*   用户管理Service
* */
public interface UserAdminService {
    /*
    *   注册功能
    * */
    User registerUser(User userAdminParam);

    /*
    *   登录功能
    *   ＠param username 用户名
    *   ＠param password　密码
    *   ＠return 生成的Jwt的token
    * */
    String login(String username,String password);


    /*
    *   根据用户名获取用户信息
    * */
    User getUserByUsername(String username);
}
