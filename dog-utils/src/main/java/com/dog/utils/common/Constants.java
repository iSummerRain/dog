package com.dog.utils.common;

/**
 * 常量定义
 */
public class Constants {

    //存放系统常用配置
    public interface SYSTEM_CONFIG {
        String SYSTEM_CONFIG_ERROR = "-9999";//异常
        String SYSTEM_CONFIG_SUCCESS = "0";//成功
        public static final String ERROR_MSG = "查询无结果or错误";
        public static final String SUCCESS_MSG = "查询成功";
    }

    //Kafka消息中心topic
    public interface MQ_TOPIC {
        String TEST_TOPIC = "KAFKA_MQ_TEST"; //测试用topic
    }
}
