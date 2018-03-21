package com.dog.service.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/***
 * 测试springboot task
 */
@Component
public class SpringBootTask {

    private static final Log logger = LogFactory.getLog(SpringBootTask.class);

    private int count = 0;

//    @Scheduled(fixedRate = 6000)

    /***
     * 使用cron表达式,可参考在线生成cron exp： http://cron.qqe2.com/
     * 0/1 * * * * ?  -- 一秒钟执行一次
     * 0 0/1 * * * ?  -- 一分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void printCountByTask () {
        logger.info("this is springboot scheduler task runing, current count value: "+(count++));
    }

}
