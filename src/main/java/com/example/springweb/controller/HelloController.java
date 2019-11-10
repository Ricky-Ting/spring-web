package com.example.springweb.controller;

import com.example.springweb.dao.MyAppInfo;
import com.example.springweb.service.HelloService;
import com.example.springweb.service.AppService;

import com.example.springweb.dao.HelloUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    AppService appService;
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
            return "user_main";
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
            model.addAttribute("RegisterErrorMessage","您的输入不合法");
            return "register";
        }


    }

    @RequestMapping("/JmpToRevisePass")
    public String JmpToRevisePass(String id, String name,Model model) {
        model.addAttribute("user_name", name);
        model.addAttribute("user_id",id);
        return "RevisePassword";
    }


    @RequestMapping("/JmpToUserMain")
    public String JmpToUserMain(String id, String name, Model model) {
        model.addAttribute("user_name", name);
        model.addAttribute("user_id",id);
        return "user_main";
    }

    @RequestMapping("/JmpToDeleteUser")
    public String JmpToDeleteUser(String id, String name, Model model) {
        model.addAttribute("user_name", name);
        model.addAttribute("user_id",id);
        return "delete_user";
    }


    @RequestMapping("/RevisePass")
    public String RevisePasswd(String id, String name, String Oldpasswd, String Newpasswd, String Newpasswd2, Model model) {
        model.addAttribute("user_name", name);
        model.addAttribute("user_id",id);
        if(id==null || name==null || Oldpasswd==null || Newpasswd==null || Newpasswd2==null || Newpasswd.equals("") ) { model.addAttribute("RevisePassErrorMessage","字段不能为空"); return "RevisePassword";}
        if(!Newpasswd.equals(Newpasswd2)) {
            model.addAttribute("RevisePassErrorMessage","新密码不一致");
                    return "RevisePassword";
        }
        HelloUser thisuser = helloService.getOne(id);
        if(thisuser==null) {
            model.addAttribute("RevisePassErrorMessage","此用户不存在，请重新登录");
            return "RevisePassword";
        }
        if(thisuser.getPassword().equals(Oldpasswd)) {
            thisuser.setPassword(Newpasswd);
            helloService.UpdateByID(thisuser);
            model.addAttribute("RevisePassErrorMessage", "密码更新成功");
            return "RevisePassword";
        }else {
            model.addAttribute("RevisePassErrorMessage","密码不正确");
            return "RevisePassword";
        }


    }


    @RequestMapping("/DeleteUser")
    public String DeleteUser(String id, String name, String password, Model model) {
        model.addAttribute("user_name", name);
        model.addAttribute("user_id",id);
        if(id==null || name==null || password==null) {
            model.addAttribute("DeleteUserErrorMessage","字段不能为空");
            return "delete_user";
        }
        HelloUser thisuser = helloService.getOne(id);
        if(thisuser==null) {
            model.addAttribute("DeleteUserErrorMessage","此用户不存在，请重新登录");
            return "delete_user";
        }
        if(thisuser.getPassword().equals(password)) {

            helloService.DeleteByID(id);
            model.addAttribute("DeleteUserErrorMessage", "用户注销成功");
            return "index";
        }else {
            model.addAttribute("DeleteUserErrorMessage","密码不正确");
            return "delete_user";
        }
    }



}
