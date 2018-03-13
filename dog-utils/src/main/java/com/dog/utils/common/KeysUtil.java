package com.dog.utils.common;

//import com.dog.service.cache.ICacheService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class KeysUtil {

    private static final Logger logger = LoggerFactory.getLogger(KeysUtil.class);

    /**
     * redis主键生成策略
     * @param tableName 需要获取主键的表名
     */
//    public static String getSequence(ICacheService cacheService, String tableName) throws Exception {
//        if (StringUtils.isEmpty(tableName)) {
//            throw new Exception("获取主键时前缀不能为空!建议传入表名");
//        }
//        String redisKey = "NGKM_REDIS_TBL:" + tableName;
//        String id ;
//        try {
//            logger.info("开始获取主键 " + redisKey);
//            //若主键值长度超过9999，则清空
//            long redisValue;
//            if ((redisValue = cacheService.incr(redisKey)) > 999999){
//                cacheService.del(redisKey);
//                redisValue = cacheService.incr(redisKey);
//            }
//            id =  DateUtils.date2String(new Date(), DateUtils.DATE_PATTERN.YYMMDDHHMMSS) + ""
//                    + String.format("%06d",redisValue);
//            logger.info("获取主键成功id=" + id);
//        } catch (Exception e) {
//            //生成18位随机数
//            id = System.currentTimeMillis()+String.format("%05d",(int)(Math.random()*10000));
//            logger.error("NOT ERROR! 主键获取成功，key=" + redisKey, e);
//        }
//        return id;
//    }
}
