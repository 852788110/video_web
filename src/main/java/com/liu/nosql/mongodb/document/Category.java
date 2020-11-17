package com.liu.nosql.mongodb.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/*
    主要用于记录系统中的视频分类
* */
@Document
public class Category {
    /*
    *   视频分类的id
    * */
    @Id
    private String id;

    /*
    *   视频分类的名字
    * */
    private String categoryName;

    /*
    *   视频编码
    * */
    @Indexed
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
