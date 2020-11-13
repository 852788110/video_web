package com.liu.service;

import com.liu.dto.UserInfo;
import com.liu.mbg.mapper.UserMapper;
import com.liu.mbg.model.User;
import com.liu.mbg.model.UserExample;

public interface UserService {
    /*
    *   根据用户名从数据库中取得用户信息
    * */
    UserInfo getUserInfo(String username);
}
