package com.liu.controller;

import com.liu.common.api.CommonResult;
import com.liu.dto.UserAdminLoginParam;
import com.liu.dto.UserInfo;
import com.liu.mbg.model.User;
import com.liu.service.UserAdminService;
import com.liu.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminController.class);
    @Autowired
    private UserAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<User> register(@RequestBody User userParam) {
        User user = adminService.registerUser(userParam);
        if (user == null) {
            LOGGER.info(String.valueOf(user==null));
            return CommonResult.failed();
        }
        LOGGER.info("ok");
        return CommonResult.success(user);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UserAdminLoginParam userAdminLoginParam) {
        String token = adminService.login(userAdminLoginParam.getUsername(), userAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        // tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("username",userAdminLoginParam.getUsername());
        return CommonResult.success(tokenMap);
    }
}
