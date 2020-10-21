package com.liu.nosql.elasticsearch.repository;

import com.liu.nosql.elasticsearch.document.EsVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsVideoRepository extends ElasticsearchRepository<EsVideo,Long> {
    Page<EsVideo> findByNameOrDescriptionOrTagsOrUserName(String name, String description, String tags, String username, Pageable page);
}
