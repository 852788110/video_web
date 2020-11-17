package com.liu.component;

import com.liu.nosql.redis.SyncToDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
*   这个类主要用于周期性的方法
*   例如：
*       周期性的扫描redis，将视频播放量同步回mongoDB数据库中
*       周期性的扫描redis，将视频点赞数同步回mongoDB数据库中
* */
@Component
public class CycleTask {
    private final Logger LOGGER= LoggerFactory.getLogger(CycleTask.class);

    @Autowired
    private SyncToDatabase syncToDatabase;

    /*
    *   cron表达式: Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
    *   每十分钟扫描一次modified队列
    * */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void syncViewCount(){
        //　此处调用同步方法
        int i = syncToDatabase.syncViewCount();
        LOGGER.info("此次同步了: "+i);
    }
}
