package com.liu.controller;

import com.liu.mbg.model.Video;
import com.liu.service.EsVideoService;
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

    @Autowired
    private EsVideoService esVideoService;

    @RequestMapping(value = "/importAll", method = RequestMethod.GET)
    @ResponseBody
    public String importAllToEs() {
        int i = esVideoService.importALl();
        return "传输了"+i;
    }
}
