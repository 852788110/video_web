package com.liu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/video")
public class VideoController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String uploadVideo(){
        /*
        *   首先将数据插入到存储桶中
        *   然后将数据写入到mysql中，
        *   最后将数据从mysql中同步出来
        * */
        return "success";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public String searchVideo(){
        /*
        *   通过elasticsearch中的数据
        * */
        return "success";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String deleteVideo(){
        /*
        *   首先删除elasticsearch数据库中的数据，
        *   然后再删除存储桶中的数据，
        *   最后再删除mysql中的数据
        * */
        return "success";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public String listVideo(){
        /*
        *   返回elasticsearch中的数据
        * */
        return "success";
    }
}
