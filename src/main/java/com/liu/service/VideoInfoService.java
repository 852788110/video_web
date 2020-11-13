package com.liu.service;

import com.liu.nosql.mongodb.document.VideoInfo;

import java.util.List;

/*
 *   视频信息管理Service
 * */
public interface VideoInfoService {
    /*
     *   生成视频信息
     * */
    int create(VideoInfo videoInfo);

    /*
     *   删除视频信息
     * */
    int delete(List<String> ids);

    /*
     *   根据category得到视频信息
     * */
    List<VideoInfo> listByCategory(Integer category);

    /*
     *   根据作者得到视频信息
     * */
    List<VideoInfo> listByAuthor(String username);


    /*
    *   根据video_id得到视频信息
    * */
    VideoInfo findByVideoId(String videoId);
}
