package com.liu.service.impl;

import com.liu.nosql.mongodb.document.VideoInfo;
import com.liu.nosql.mongodb.repository.VideoInfoRepository;
import com.liu.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
 *   视频信息管理Services实现类
 * */
@Service
public class VideoInfoServiceImpl implements VideoInfoService {
    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Override
    public int create(VideoInfo videoInfo) {
        videoInfo.setId(null);
        videoInfo.setCreateTime(new Date());
        videoInfo.setModifiedTime(new Date());
        videoInfoRepository.save(videoInfo);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<VideoInfo> deleteList = new ArrayList<>();
        for (String id : ids) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setId(id);
            deleteList.add(videoInfo);
        }
        videoInfoRepository.deleteAll(deleteList);
        return deleteList.size();
    }

    @Override
    public List<VideoInfo> listByCategory(String category) {
        return videoInfoRepository.findByCategoryOrderByViewCount(category);
    }

    @Override
    public List<VideoInfo> listByAuthor(String username) {
        return videoInfoRepository.findByUsernameOrderByCreateTime(username);
    }

    @Override
    public VideoInfo findByVideoId(String videoId) {
        Optional<VideoInfo> videoInfo = videoInfoRepository.findById(videoId);
        return videoInfo.get();
    }

    @Override
    public int update(String videoId) {
        return 0;
    }
}
