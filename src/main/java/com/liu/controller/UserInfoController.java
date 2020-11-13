package com.liu.controller;

import com.liu.common.api.CommonResult;
import com.liu.dto.UserInfo;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserInfoController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UserInfo> getUserInfo(@RequestParam String username){
        UserInfo userInfo=userService.getUserInfo(username);
        if (userInfo==null){
            return CommonResult.failed();
        }
        return CommonResult.success(userInfo);
    }
}
