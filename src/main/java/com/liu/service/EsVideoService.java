package com.liu.service;

import com.liu.mbg.model.Video;
import com.liu.nosql.elasticsearch.document.EsVideo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsVideoService {
    /*
    *   从数据库中导入所有商品到ES
    * */
    int importALl();

    /*
    *   根据商品id删除商品
    * */
    void delete(Long id);

    /*
    *   根据video创建商品
    * */
    int create(Video video);

    /*
    *   批量删除商品
    * */
    void delete(List<Long> ids);

    /*
    *   根据关键字搜索视频
    * */
    Page<EsVideo> search(String keyword, Integer pageNum, Integer pageSize);
}
