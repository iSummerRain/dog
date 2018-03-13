package com.dog.service.login.impl;

//import com.dog.beans.login.UserBean;
//import com.dog.domain.user.UserInfoRepository;
//import com.dog.domain.user.jdbcTemplate.UserInfoJdbcTemplate;
//import com.dog.service.jpa.DogPageable;
//import com.dog.service.jpa.DogSort;
import com.dog.service.login.IUserInfoSV;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;

//@Service
//@Transactional
/**
 * @Transactional
 * 增加事务管理 可以在service层上注解，也可以在service对应方法注解，如addUserInfo方法上
 * 需要注意项目启动时开启事务管理 @EnableTransactionManagement
 */
public class UserInfoSVImpl implements IUserInfoSV {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserInfoSVImpl.class);
//
////    @Resource //【javax.annotation.Resource】Note that this annotation may appear on private fields and methods of superclasses
//    //官方demo使用的是Autowired注解
//    @Autowired
//    private UserInfoRepository userInfoRepository;
//
//    @Autowired
//    private UserInfoJdbcTemplate userInfoJdbcTemplate;
//
//    @Override
//    public UserBean getUserById(String userId) {
//        logger.info("UserInfoSVImpl:"+userId);
//        Optional<UserBean> optional = userInfoRepository.findById(Long.valueOf(userId));
//        if (optional.isPresent()) { //JPA 返回的optional(jdk8)容器有值
//            logger.info("optional.get():"+optional.get().toString());
//            return optional.get();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    /***
//     * jpa 分页查询
//     */
//    public List<UserBean> getAllUserList(Integer page, Integer size, Sort sort) {
//
//        List<UserBean> retList = new ArrayList();
//
//        Pageable pageable = new PageRequest(page, size, sort);
//
//        Page<UserBean> userBeanPages = userInfoRepository.findAll(pageable);
//        if (null != userBeanPages) {
//            for (UserBean bean:userBeanPages) {
//                retList.add(bean);
//            }
//        }
//        return retList;
//    }
//
//    @Override
//    public String getUserByParsms(String params) {
//        logger.info(" UserInfoSVImpl: "+params);
//        String ret = "{\n" +
//                "    \"rtnMsg\": \"查询成功\",\n" +
//                "    \"rtnCode\": \"0\",\n" +
//                "    \"object\": {\n" +
//                "        \"userInfo\": [\n" +
//                "            {\n" +
//                "                \"userId\": \"12\",\n" +
//                "                \"userName\": \"张三\",\n" +
//                "                \"address\": \"江苏南京\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"userId\": \"13\",\n" +
//                "                \"userName\": \"李四\",\n" +
//                "                \"address\": \"浙江杭州\"\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }\n" +
//                "}";
//        return ret;
//    }
//
//    @Override
//    //JdbcTemplate
////    @Transactional
//    public boolean addUserInfo(UserBean bean) {
//        boolean ret = false;
//        if (null != bean) {
//            int flag = userInfoJdbcTemplate.create(bean.getId(), bean.getName(), Integer.valueOf(bean.getAge()), bean.getAddress());
//            if (flag > 0) {
//                ret = true;
//            }
//        }
//        return ret;
//    }
//

}
