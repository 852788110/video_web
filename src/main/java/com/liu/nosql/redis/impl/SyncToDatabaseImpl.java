package com.liu.nosql.redis.impl;

import com.liu.nosql.mongodb.document.VideoInfo;
import com.liu.nosql.mongodb.repository.VideoInfoRepository;
import com.liu.nosql.redis.SyncToDatabase;
import com.liu.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SyncToDatabaseImpl implements SyncToDatabase {
    @Value("${viewCount.modifiedQueue}")
    private String modifiedQueue;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private VideoInfoRepository videoInfoRepository;

    /*
    *   将播放量同步回mongoDB数据库中
    *   1. 首先从redis中取出modifiedQueue
    *   2. 一一将modifiedQueue中的videoId所对应的播放量同步回mongoDB中去
    * */
    @Override
    public int syncViewCount() {
        Long size = redisTemplate.opsForSet().size(modifiedQueue);
        if (size<0) return 0;

        List<String> videoIds = redisTemplate.opsForSet().pop(modifiedQueue, size);

        List<VideoInfo> videoInfoList=new ArrayList<>();
        for (String videoId: videoIds){
            Optional<VideoInfo> videoData = videoInfoRepository.findById(videoId);
            if (videoData.isPresent()){
                VideoInfo _video=videoData.get();

                // 老的viewCount
                Long OldViewCount = _video.getViewCount();

                // 新增的viewCount
                Long newViewCount = Long.parseLong(redisTemplate.opsForValue().getAndSet(videoId, "0")) ;

                _video.setViewCount(OldViewCount+newViewCount);

                videoInfoList.add(_video);
            }
        }
        List<VideoInfo> videoInfos = videoInfoRepository.saveAll(videoInfoList);
        return videoInfos.size();
    }
}
