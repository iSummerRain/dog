package com.dog.service.login.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.dog.api.IUserInfoService;
import com.dog.beans.login.UserBean;
import com.dog.domain.user.UserInfoRepository;
import com.dog.domain.user.jdbcTemplate.UserInfoJdbcTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * dubbo服务接口实现
 */

@Service(version = "1.0.0", interfaceName="com.dog.api.IUserInfoService")
@Transactional
/*
dubbo中springboot的事务注解要是使用会引起服务提供不了...
解决办法:
1、修改dubbo源码：
1.1、让代理类可以获取@com.alibaba.dubbo.config.annotation.Service ,在该注解上加入
    java.lang.annotation.Inherited  意思是: 使代理类继承该注解,使之可以通过
    bean.getClass().getAnnotation(Service.class) 获取到.
    也就是说修改dubbo的Service注解，增加java.lang.annotation.Inherited
1.2、在application.properties增加启用spring AOP使用CGlib代理：spring.aop.proxy-target-class=true
1.3、修改使用 @Service(version = "1.0.0") 为 @Service(version = "1.0.0", interfaceName="com.dog.api.IUserInfoService")

 */
public class UserInfoServiceImpl implements IUserInfoService {

    private static final Log logger = LogFactory.getLog(UserInfoServiceImpl.class);

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
