package com.liu.service;

import com.liu.mbg.model.Video;

import java.util.List;

public interface VideoService {
    List<Video> listAllVideo();

    int createVideo(Video video);

    int deleteVideo(Long id);

    Video getVideo(Long id);

    List<Video> listVideo(int pageNum, int pageSize);
}
