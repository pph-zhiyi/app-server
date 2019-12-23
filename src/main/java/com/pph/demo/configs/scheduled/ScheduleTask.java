package com.pph.demo.configs.scheduled;

import com.pph.demo.service.TopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author: pph
 * @date: 2019/12/22 19:24
 * @description: 用于执行各种定时任务
 */
@Configuration
@EnableScheduling
public class ScheduleTask {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    public ScheduleTask(TopService topService) {
        this.topService = topService;
    }

    private final TopService topService;

    /*
     * 秒（0~59） 例如0/5表示每5秒
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
     */

    /**
     * 执行时间：每天 00:00:00
     * Top Excel 数据定时更新到 DB
     */
//    @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    private void topExcelDataToDb() throws Exception {
        LOGGER.info("----- Scheduled task [topExcelDataToDb] start!");
        topService.excelToDb();
        LOGGER.info("----- Scheduled task [topExcelDataToDb] end!");
    }
}
