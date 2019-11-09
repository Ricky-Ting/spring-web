package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    AppService appService;
    public final static Logger logger = LoggerFactory.getLogger(AppController.class);



    @RequestMapping("/AddAppInfo")
    public String AppInfoSubmit(String AppId, String UserId, String AppName, Integer Classifier, Integer Security) {
        logger.info("AppInfo Receive " + AppId + "," + UserId + "," + AppName + "," + String.valueOf(ClassLoader) + "," + String.valueOf(Security) );
        if(id==null) return "index";
        HelloUser newUserLogin = helloService.getOne(id);
        if(id.equals(newUserLogin.getId()) && name.equals(newUserLogin.getName()) && password.equals(newUserLogin.getPassword())) {
            logger.info("hello logging " + newUserLogin);
            return "hello";
        } else
            return "index";
    }


}
