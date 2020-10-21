package com.liu.service.impl;

import com.github.pagehelper.PageHelper;
import com.liu.mbg.mapper.VideoMapper;
import com.liu.mbg.model.Video;
import com.liu.mbg.model.VideoExample;
import com.liu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> listAllVideo() {
        return videoMapper.selectByExample(new VideoExample());
    }

    @Override
    public int createVideo(Video video) {
        return videoMapper.insertSelective(video);
    }

    @Override
    public int deleteVideo(Long id) {
        return videoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Video getVideo(Long id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Video> listVideo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoMapper.selectByExample(new VideoExample());
    }
}
