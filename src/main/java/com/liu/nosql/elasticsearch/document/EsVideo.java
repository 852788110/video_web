package com.liu.nosql.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "video", shards = 1, replicas = 0)
public class EsVideo implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private Long userId;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String userName;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Keyword)
    private String path;
    @Field(type = FieldType.Keyword)
    private String picPath;
    private Long viewCounts;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String tags;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Date,format = DateFormat.basic_date)
    private String createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Long getViewCounts() {
        return viewCounts;
    }

    public void setViewCounts(Long viewCounts) {
        this.viewCounts = viewCounts;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
