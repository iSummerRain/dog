package com.dog.web.controller.login;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dog.api.IUserInfoService;
import com.dog.beans.login.UserBean;
import com.dog.utils.common.Constants;
import com.dog.utils.common.DogResult;
import com.dog.utils.common.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/dubbo")
/**
 * 该类仅仅作为spring-boot调用dubbo服务测试用
 * @author guoxg
 */
public class DubboUserController {

    private static final Logger logger = LoggerFactory.getLogger(DubboUserController.class);

    //web层调用dubbo服务提供的版本号
    @Reference(version = "1.0.0")
    private IUserInfoService userInfoService;

    //http://localhost:18080/dubbo/sayHello?str=guoxg
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(@RequestParam String str) {
        return userInfoService.sayHello(str);
    }

    //http://localhost:18080/dubbo/findUser
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public UserBean findUser() {
        return userInfoService.findUser();
    }

    //POSTMAN：http://localhost:18080/dubbo/addUserInfo
    //jdbcTemplate
    // {"id":"4","name":"王五","age":10,"address":"杭州"}
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    public DogResult addUserInfo(@RequestBody String param) throws Exception {
        DogResult dogResult = new DogResult();

        if (StringUtils.isNotBlank(param)) {
            //将json入参转换成对应的bean对象
            UserBean userBean = (UserBean) JsonUtil.convertJson2Object(param, UserBean.class);
            boolean ret = userInfoService.addUserInfo(userBean);
            if (ret) {
                dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_SUCCESS);
                dogResult.setRtnMsg("用户添加成功");
            } else {
                dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
                dogResult.setRtnMsg("用户添加失败");
            }
        } else {
            dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
            dogResult.setRtnMsg("传递参数error");

        }
        return dogResult;
    }

    // http://localhost:18080/dubbo/getUserById?id=1
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public DogResult getUserById(@RequestParam String id) throws Exception {
        DogResult dogResult = new DogResult();
        logger.info("---getUserById : id---->"+id);
        UserBean usr = userInfoService.getUserById(id);
        if (null != usr) {
            dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_SUCCESS);
            dogResult.setRtnMsg(Constants.SYSTEM_CONFIG.SUCCESS_MSG);
            dogResult.setObject(JsonUtil.convertObject2Json(usr));
        } else {
            dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
            dogResult.setRtnMsg(Constants.SYSTEM_CONFIG.ERROR_MSG);
            dogResult.setObject(null);
        }
        return dogResult;
    }

}
