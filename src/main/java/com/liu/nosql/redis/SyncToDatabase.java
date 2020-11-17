package com.liu.nosql.redis;

/*
*   将redis中的内容同步回数据库中
* */

public interface SyncToDatabase {
    /*
    *   视频播放量同步会mongoDB数据库中
    * */
    int syncViewCount();
}
