package com.liu.nosql.redis;

import java.util.List;

/*
 *   redis操作service
 *   主要用于记录哪些视频的viewcount发生了变化
 * */
public interface ViewCountModifiedRepository {
    /*
     *   创建队列
     * */
    void set();

    /*
     *   清除队列
     * */
    void delete();

    /*
     *   添加数据
     * */
    void add(String videoId);

    /*
     *   读取数据
     * */
    List<String> get();
}
