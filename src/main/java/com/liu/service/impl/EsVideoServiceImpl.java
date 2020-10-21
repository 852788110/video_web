package com.liu.service.impl;

import com.liu.mbg.mapper.VideoMapper;
import com.liu.mbg.model.Video;
import com.liu.mbg.model.VideoExample;
import com.liu.nosql.elasticsearch.document.EsVideo;
import com.liu.nosql.elasticsearch.repository.EsVideoRepository;
import com.liu.service.EsVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EsVideoServiceImpl implements EsVideoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsVideoServiceImpl.class);

    @Autowired
    private EsVideoRepository videoRepository;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public int importALl() {
        List<Video> videoList = videoMapper.selectByExample(new VideoExample());
        List<EsVideo> esVideoList = new ArrayList<>();
        for (Video video : videoList) {
            esVideoList.add(convert(video));
        }
        Iterable<EsVideo> esVideoIterable = videoRepository.saveAll(esVideoList);
        Iterator<EsVideo> iterator = esVideoIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    private static EsVideo convert(Video video) {
        EsVideo esVideo = new EsVideo();
        esVideo.setId(video.getId());
        esVideo.setUserId(video.getUserId());
        esVideo.setUserName(video.getUserName());
        esVideo.setName(video.getName());
        esVideo.setPath(video.getPath());
        esVideo.setPicPath(video.getPicPath());
        esVideo.setViewCounts(video.getViewCounts());
        return esVideo;
    }

    @Override
    public void delete(Long id) {
        videoRepository.deleteById(id);
    }

    @Override
    public int create(Video video) {
        EsVideo esVideo = convert(video);
        videoRepository.save(esVideo);
        return 1;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsVideo> esVideoList = new ArrayList<>();
            for (Long id : ids) {
                EsVideo esVideo = new EsVideo();
                esVideo.setId(id);
                esVideoList.add(esVideo);
            }
            videoRepository.deleteAll(esVideoList);
        }
    }

    @Override
    public Page<EsVideo> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return videoRepository.findByNameOrDescriptionOrTagsOrUserName(keyword, keyword, keyword, keyword, pageable);
    }
}
