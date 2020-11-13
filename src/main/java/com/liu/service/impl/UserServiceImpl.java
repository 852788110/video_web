package com.liu.service.impl;

import com.liu.dto.UserInfo;
import com.liu.mbg.mapper.UserMapper;
import com.liu.mbg.model.User;
import com.liu.mbg.model.UserExample;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserInfo(String username) {
        UserExample example=new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<User> userList=userMapper.selectByExample(example);
        if (userList!=null&&userList.size()>0){
            UserInfo userInfo=new UserInfo();
            userInfo.setName(userList.get(0).getName());
            userInfo.setUsername(userList.get(0).getUserName());
            return userInfo;
        }
        return null;
    }
}
