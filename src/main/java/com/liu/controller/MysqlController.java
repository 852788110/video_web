package com.liu.controller;

import com.liu.mbg.model.Video;
import com.liu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/mysql")
public class MysqlController {
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String uploadVideo(@RequestParam(value = "name") String name,
                           @RequestParam(value = "username") String username,
                           @RequestParam(value = "tags") String tags,
                           @RequestParam(value = "description") String description){
        Video video=new Video();
        video.setCreateTime(new Date());
        video.setDescription(description);
        video.setName(name);
        video.setUserName(username);
        video.setTags(tags);
        videoService.createVideo(video);
        return "success";
    }
}
