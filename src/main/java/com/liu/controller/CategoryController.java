package com.liu.controller;

import com.liu.common.api.CommonResult;
import com.liu.nosql.mongodb.document.Category;
import com.liu.nosql.mongodb.document.VideoInfo;
import com.liu.service.CategoryService;
import com.liu.service.VideoInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private VideoInfoService videoInfoService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("根据分类得到视频")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<VideoInfo>> list(@RequestParam("categoryId") String category) {
        List<VideoInfo> videoInfos = videoInfoService.listByCategory(category);
        return CommonResult.success(videoInfos);
    }

    @ApiOperation("添加视频分类")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> add(@RequestBody Category category){
        int res = categoryService.add(category);
        return CommonResult.success(res);
    }

    @ApiOperation("得到分类列表")
    @RequestMapping(value = "/categorys",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Category>> getCategories(){
        return CommonResult.success(categoryService.list());
    }
}
