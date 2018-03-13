package com.dog.web.controller.user;

//import com.dog.beans.login.UserBean;
//import com.dog.beans.login.UserBeanPage;
//import com.dog.service.login.IUserInfoSV;
//import com.dog.utils.common.Constants;
//import com.dog.utils.common.DogResult;
//import com.dog.utils.common.JsonUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/dog/userinfo")

/**
 * 该类在集成dubbo后废弃
 * 类中的方法{addUserInfo}合并到DubboUserController类
 */
public class UserInfoController {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
//
//    @Autowired
//    IUserInfoSV userInfoSV;
//
//    //POSTMAN：http://localhost:18080/dog/userinfo/addUserInfo
//    //jdbcTemplate
//    // {"id":"4","name":"王五","age":10,"address":"杭州"}
//    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
//    public DogResult addUserInfo(@RequestBody String param) throws Exception {
//        DogResult dogResult = new DogResult();
//
//        if (StringUtils.isNotBlank(param)) {
//            //将json入参转换成对应的bean对象
//            UserBean userBean = (UserBean) JsonUtil.convertJson2Object(param, UserBean.class);
//            boolean ret = userInfoSV.addUserInfo(userBean);
//            if (ret) {
//                dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_SUCCESS);
//                dogResult.setRtnMsg("用户添加成功");
//            } else {
//                dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
//                dogResult.setRtnMsg("用户添加失败");
//            }
//        } else {
//            dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
//            dogResult.setRtnMsg("传递参数error");
//
//        }
//        return dogResult;
//    }
//
//    /***
//     * http://localhost:18080/dog/userinfo/getUserInfosByPage
//     * {"id":"4","name":"王五","age":10,"address":"杭州","page":"0","size":"5"}
//     * JPA 分页查询开始页下标为0, 第0页，每页5条
//     * @param param
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(path = "/getUserInfosByPage", method = RequestMethod.POST)
//    public DogResult sayHelloPost(@RequestBody String param) throws Exception {
//        DogResult dogResult = new DogResult();
//        if (StringUtils.isNotBlank(param)) {
//            logger.info("---getUserInfosByPage : param---->"+param);
//            UserBeanPage userBeanPage = (UserBeanPage) JsonUtil.convertJson2Object(param, UserBeanPage.class);
//
//            Sort sort = new Sort(Sort.Direction.DESC, "age");//age排序desc
//            List<UserBean> userBeanList = userInfoSV.getAllUserList(Integer.valueOf(userBeanPage.getPage()), Integer.valueOf(userBeanPage.getSize()), sort);
//            String ret = JsonUtil.convertObject2Json(userBeanList);
//            if (StringUtils.isNotBlank(ret)) {
//                dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_SUCCESS);
//                dogResult.setRtnMsg("查询分页成功");
//                dogResult.setObject(ret);
//            } else {
//                dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
//                dogResult.setRtnMsg("查询分页用户失败");
//            }
//        } else {
//            dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
//            dogResult.setRtnMsg("传递参数error");
//        }
//        return dogResult;
//    }
}
