package com.dog.web.controller.login;
//
//import com.dog.beans.login.UserBean;
//import com.dog.property.DogPropertiesConf;
//import com.dog.service.login.IUserInfoSV;
//import com.dog.utils.common.Constants;
//import com.dog.utils.common.DogResult;
//import com.dog.utils.common.JsonUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/demoHello")
/**
 * 该类仅仅作为spring-boot测试用
 * @author guoxg
 */

/**
 * 该类在集成dubbo后废弃
 */
public class HelloController {
//
//    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
//
//    @Autowired
//    IUserInfoSV userInfoSV;
//
//    @Autowired
//    DogPropertiesConf dogPropertiesConf;
//
//    /**
//     * http://localhost:18080/demoHello/getUserByIds
//     * {"params":{"ids":"40,41,42","page":1,"size":10}}
//     * POST 传递参数使用 @RequestBody 注解到方法参数
//     * @param param
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(path = "/getUserByIds", method = RequestMethod.POST)
//    public String sayHelloPost(@RequestBody String param) throws Exception {
//        logger.info("---sayHelloPost : param---->"+param);
//        String ret = userInfoSV.getUserByParsms(param);
//        logger.info("HelloController qry ret: "+ret);
//        return ret;
//    }
//
//    // http://localhost:18080/demoHello/getDogPropertiesConf?id=1 自定义属性测试
//    @RequestMapping(value = "/getDogPropertiesConf", method = RequestMethod.GET)
//    public String getDogPropertiesConf(@RequestParam String id) throws Exception {
//        logger.info(dogPropertiesConf.toString());
//        return dogPropertiesConf.toString();
//    }
//
//    // http://localhost:18080/demoHello/getUserById?id=1
//    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
//    public DogResult sayHelloGet(@RequestParam String id) throws Exception {
//        DogResult dogResult = new DogResult();
//        logger.info("---sayHelloGet : id---->"+id);
//        UserBean usr = userInfoSV.getUserById(id);
//        if (null != usr) {
//            dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_SUCCESS);
//            dogResult.setRtnMsg(Constants.SYSTEM_CONFIG.SUCCESS_MSG);
//            dogResult.setObject(JsonUtil.convertObject2Json(usr));
//        } else {
//            dogResult.setRtnCode(Constants.SYSTEM_CONFIG.SYSTEM_CONFIG_ERROR);
//            dogResult.setRtnMsg(Constants.SYSTEM_CONFIG.ERROR_MSG);
//            dogResult.setObject(null);
//        }
//        return dogResult;
//    }

}
