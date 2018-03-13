package com.dog.api;

import com.dog.beans.login.UserBean;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IUserInfoService {
    //test method
    String sayHello(String str);
    UserBean findUser();

    //JPA, this method copy from com.dog.service.login.IUserInfoSV
    UserBean getUserById(String userId);
    List<UserBean> getAllUserList(Integer page, Integer size, Sort sort);

    //used JdbcTemplate , this method copy from com.dog.service.login.IUserInfoSV
    boolean addUserInfo(UserBean bean);

}
