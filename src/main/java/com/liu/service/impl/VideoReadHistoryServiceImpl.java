package com.liu.service.impl;

import com.liu.nosql.mongodb.document.VideoReadHistory;
import com.liu.nosql.mongodb.repository.VideoReadHistoryRepository;
import com.liu.service.VideoReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *   视频浏览记录管理Service实现类
 * */
@Service
public class VideoReadHistoryServiceImpl implements VideoReadHistoryService {
    @Autowired
    private VideoReadHistoryRepository videoReadHistoryRepository;

    @Override
    public int create(VideoReadHistory videoReadHistory) {
        videoReadHistory.setId(null);
        videoReadHistory.setCreateTime(new Date());
        videoReadHistoryRepository.save(videoReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<VideoReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            VideoReadHistory videoReadHistory = new VideoReadHistory();
            videoReadHistory.setId(id);
            deleteList.add(videoReadHistory);
        }
        videoReadHistoryRepository.deleteAll(deleteList);
        return deleteList.size();
    }

    @Override
    public List<VideoReadHistory> list(Long userId) {
        return videoReadHistoryRepository.findByUserIdOrderByCreateTime(userId);
    }
}
