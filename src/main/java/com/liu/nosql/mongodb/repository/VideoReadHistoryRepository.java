package com.liu.nosql.mongodb.repository;

import com.liu.nosql.mongodb.document.VideoReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/*
*   用户视频浏览记录
* */
public interface VideoReadHistoryRepository extends MongoRepository<VideoReadHistory,String> {
    /*
    *   根据用户id按时间顺序倒序获取浏览记录
    * */
    List<VideoReadHistory> findByUserIdOrderByCreateTime(Long userId);
}
