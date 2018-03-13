package com.dog.service.login.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dog.api.IUserInfoService;
import com.dog.beans.login.UserBean;
import com.dog.domain.user.UserInfoRepository;
import com.dog.domain.user.jdbcTemplate.UserInfoJdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * dubbo服务接口实现
 */

@Service(version = "1.0.0")
//@Transactional //dubbo中springboot的事务注解不能使用 引起服务提供不了...
public class UserInfoServiceImpl implements IUserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoJdbcTemplate userInfoJdbcTemplate;

    @Override
    public String sayHello(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date()) + ": " + str;
    }

    @Override
    public UserBean findUser() {
        UserBean user = new UserBean();
        user.setId("1000");
        user.setName("guoxg");
        user.setAddress("Xi'an city, Shaanxi prov");
        return user;
    }

    @Override
    public UserBean getUserById(String userId) {
        logger.info("UserInfoSVImpl:"+userId);
        Optional<UserBean> optional = userInfoRepository.findById(userId);
        if (optional.isPresent()) { //JPA 返回的optional(jdk8)容器有值
           logger.info("optional.get():"+optional.get().toString());
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public List<UserBean> getAllUserList(Integer page, Integer size, Sort sort) {
        List<UserBean> retList = new ArrayList();
//        Pageable pageable = new PageRequest(page, size, sort);
//        Page<UserBean> userBeanPages = userInfoRepository.findAll(pageable);
//        if (null != userBeanPages) {
//            for (UserBean bean:userBeanPages) {
//                retList.add(bean);
//            }
//        }
        return retList;
    }

    /***
     * 使用JdbcTemplate模板操作mysql数据库
     * @param bean
     * @return
     */
    @Override
    public boolean addUserInfo(UserBean bean) {
        boolean ret = false;
        if (null != bean) {
            int flag = userInfoJdbcTemplate.create(bean.getId(), bean.getName(), Integer.valueOf(bean.getAge()), bean.getAddress());
            if (flag > 0) {
                ret = true;
            }
        }
        return ret;
    }
}
