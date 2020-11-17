package com.liu.controller;

import com.liu.common.api.CommonResult;
import com.liu.nosql.redis.VideoViewCountRepository;
import com.liu.nosql.redis.ViewCountModifiedRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/video")
public class ViewCountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewCountController.class);
    @Autowired
    private VideoViewCountRepository videoViewCountRepository;

    @Autowired
    private ViewCountModifiedRepository viewCountModifiedRepository;

    @ApiOperation("增加播放量")
    @RequestMapping(value = "/increment",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult viewIncrement(@RequestParam String videoId){
        Long result=videoViewCountRepository.increment(videoId,1L);
        viewCountModifiedRepository.add(videoId);
        LOGGER.info(videoId);
        return CommonResult.success(result);
    }
}
