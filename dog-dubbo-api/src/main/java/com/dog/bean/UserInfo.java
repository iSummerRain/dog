package com.dog.bean;

import lombok.Data;

import java.io.Serializable;

/***
 * 使用 lombok 注解 bean
 * 待调试
 */
@Data
public class UserInfo implements Serializable {
    private String id;
    private String name;
    private String age;
    private String address;

}
