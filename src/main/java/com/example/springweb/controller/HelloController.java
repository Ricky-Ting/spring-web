package com.example.springweb.controller;

import com.example.springweb.service.HelloService;
import com.example.springweb.dao.HelloUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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



    @RequestMapping("/Register")
    public String Register() {
        logger.info("Jump to register page");
        return "register";
    }

    @RequestMapping("/index")
    public String goToIndex() {
        logger.info("Jump to index page");
        return "index";
    }


    @RequestMapping("/UserLogin")
    public String User_Login(String id, String name, String password, Model model) {
        logger.info("Login Receive " + id + "," +name +"," + password );
        if(id==null || name==null || password==null) {model.addAttribute("LoginErrorMessage","所有字段均不能为空");  return "index";}
        HelloUser newUserLogin = helloService.getOne(id);
        if(newUserLogin==null) {model.addAttribute("LoginErrorMessage","您还尚未注册"); return "index";}

        if(id.equals(newUserLogin.getId()) && name.equals(newUserLogin.getName()) && password.equals(newUserLogin.getPassword())) {
            logger.info("hello logging " + newUserLogin);
            model.addAttribute("user_name", name);
            model.addAttribute("user_id",id);
            return "hello";
        } else {
            model.addAttribute("LoginErrorMessage", "对不起，您输入的密码有误");
            return "index";
        }
    }


    @RequestMapping("/UserRegister")
    public String User_Register(String id, String name, String password, Model model) {
        HelloUser register_user=new HelloUser(id,name,password);
        if(helloService.Check_User(register_user)) {
            helloService.InsertUser(register_user);
            model.addAttribute("LoginErrorMessage","恭喜您，注册成功");
            return "index";
        } else {
            model.addAttribute("RegisterErrorMessage","你的输入不合法");
            return "register";
        }


    }





}
