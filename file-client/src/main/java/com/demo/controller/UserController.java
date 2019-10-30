package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.config.UserConstant;
import com.demo.pojo.UploadFile;
import com.demo.util.OkHttpClientUtil;
import com.demo.util.RSAHeadersUtil;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map map){
        logger.info("访问登录页!");
        return "login";
    }



    @RequestMapping("/user_login")
    public String user_login(String username,String password,HttpServletRequest request, Map map) throws Exception {
        if(password.equals(UserConstant.userMap.get(username))){
            logger.info("用户登录成功："+username);
            return "forward:/fileList";
        }
        return "login";
    }

    @RequestMapping("/user_register")
    public String user_register(String username,String password){
        UserConstant.userMap.put(username,password);
        logger.info("用户注册成功："+username);
        return "login";
    }


    @RequestMapping("/user_loginOut")
    public String user_loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        logger.info("用户注销成功");
        return "login";
    }
}
