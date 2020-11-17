package com.liu.nosql.mongodb.repository;

import com.liu.nosql.mongodb.document.VideoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VideoInfoRepository extends MongoRepository<VideoInfo, String> {
    /*
     *   根据视频作者获取视频信息
     * */
    List<VideoInfo> findByUsernameOrderByCreateTime(String username);

    /*
     *   根据视频的category获取视频信息
     *   按照视频的播放量排序
     * */
    List<VideoInfo> findByCategoryOrderByViewCount(String category);

    /*
    *   根据视频id修改视频信息
    * */

}
