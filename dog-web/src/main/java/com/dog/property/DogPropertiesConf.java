package com.dog.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/***
 * 自定义属性,封装application-dev.properties配置以dog开头的实体类
 * 注册成组件
 * 2.0的版本需要引入 spring-boot-configuration-processor
 * https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/html/configuration-metadata.html
 */
@Component
@ConfigurationProperties(prefix = "dog")
public class DogPropertiesConf {

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 描述
     */
    private String desc;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString() {
        return "DogPropertiesConf{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
