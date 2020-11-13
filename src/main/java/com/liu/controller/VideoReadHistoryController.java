package com.liu.controller;

import com.liu.common.api.CommonResult;
import com.liu.nosql.mongodb.document.VideoReadHistory;
import com.liu.service.VideoReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/readHistory")
public class VideoReadHistoryController {
    @Autowired
    private VideoReadHistoryService videoReadHistoryService;

    @ApiOperation("创建视频浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody VideoReadHistory videoReadHistory) {
        int count = videoReadHistoryService.create(videoReadHistory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除视频浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = videoReadHistoryService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("展示浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<VideoReadHistory>> list(Long userId) {
        List<VideoReadHistory> videoReadHistoryList = videoReadHistoryService.list(userId);
        return CommonResult.success(videoReadHistoryList);
    }
}
