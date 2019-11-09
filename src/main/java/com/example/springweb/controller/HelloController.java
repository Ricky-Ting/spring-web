package com.example.springweb.controller;

import com.example.springweb.service.HelloService;
import com.example.springweb.dao.HelloUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello(){
        logger.info("hello logging" + helloService.getUserList());
        return "hello";
    }

    @RequestMapping("/UserLogin")
    public String Login(String id, String name, String password) {
        logger.info("Login Receive " + id + "," +name +"," + password );
        if(id==null) return "index";
        HelloUser newUserLogin = helloService.getOne(id);
        if(id.equals(newUserLogin.getId()) && name.equals(newUserLogin.getName()) && password.equals(newUserLogin.getPassword())) {
            logger.info("hello logging " + newUserLogin);
            return "hello";
        } else
            return "index";
    }


}
