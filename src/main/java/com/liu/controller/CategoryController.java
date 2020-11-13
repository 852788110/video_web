package com.liu.controller;

import com.liu.common.api.CommonResult;
import com.liu.nosql.mongodb.document.VideoInfo;
import com.liu.service.VideoInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private VideoInfoService videoInfoService;

    @ApiOperation("根据分类得到视频")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<VideoInfo>> list(@RequestParam("category") Integer category) {
        List<VideoInfo> videoInfos = videoInfoService.listByCategory(category);
        return CommonResult.success(videoInfos);
    }
}
