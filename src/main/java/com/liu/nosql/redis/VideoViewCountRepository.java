package com.liu.nosql.redis;

/*
 *   redis操作service
 *   用于保存视频的播放量
 * */
public interface VideoViewCountRepository {
    /*
     *   存储数据
     *   key: videoId
     *   value: 视频的播放量
     * */
    void set(String key, String value);

    /*
     *   获取视频的播放量
     * */
    String get(String key);

    /*
     *   自增操作
     * */
    Long increment(String key, Long delta);
}
