package com.liu.service.impl;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.liu.common.utils.JwtTokenUtil;
import com.liu.mbg.mapper.UserMapper;
import com.liu.mbg.model.User;
import com.liu.mbg.model.UserExample;
import com.liu.service.UserAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAdminServiceImpl implements UserAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User registerUser(User userAdminParam) {
        User userAdmin = new User();
        BeanUtils.copyProperties(userAdminParam, userAdmin);
        userAdmin.setCreateTime(new Date());

        // 查询是否有相同用户名的用户
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userAdmin.getUserName());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return null;
        }

        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(userAdmin.getUserPassword());
        userAdmin.setUserPassword(encodePassword);
        userMapper.insert(userAdmin);
        return userAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token=null;
        try {
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token=jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }

    @Override
    public User getUserByUsername(String username) {
        UserExample example=new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<User> userList=userMapper.selectByExample(example);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
}
