package com.liu.service;

import com.liu.nosql.mongodb.document.VideoReadHistory;

import java.util.List;

/*
*   视频浏览记录管理Ｓervice
* */
public interface VideoReadHistoryService {
    /*
    *   生成视频浏览记录
    * */
    int create(VideoReadHistory videoReadHistory);


    /*
    *   批量删除视频浏览记录
    * */
    int delete(List<String> ids);

    /*
    *   获取用户历史浏览记录
    * */
    List<VideoReadHistory> list(Long userId);
}
