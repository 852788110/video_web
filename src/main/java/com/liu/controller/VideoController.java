package com.liu.controller;

import com.liu.common.api.CommonPage;
import com.liu.common.api.CommonResult;
import com.liu.nosql.elasticsearch.document.EsVideo;
import com.liu.service.EsVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private EsVideoService esVideoService;

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsVideo>> searchVideo(@RequestParam(value = "keyword") String keyword){
        /*
        *   查找elasticsearch中的数据
        * */
        Page<EsVideo> videos = esVideoService.search(keyword, 0, 5);
        return CommonResult.success(CommonPage.restPage(videos));
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
    public CommonResult<CommonPage<EsVideo>> listVideo(){
        /*
        *   返回elasticsearch中的数据
        * */
        Page<EsVideo> esVideos = esVideoService.list(0, 5);

        return CommonResult.success(CommonPage.restPage(esVideos));
    }
}
