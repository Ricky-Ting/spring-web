package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.MyAppInfo;
import com.example.springweb.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class AppController {
    @Autowired
    AppService appService;
    public final static Logger logger = LoggerFactory.getLogger(AppController.class);



    @RequestMapping("NewAppInfo")
    public String newAppInfo(String UserId, String UserName, Model model) {
        model.addAttribute("user_id",UserId);
        model.addAttribute("user_name",UserName);
        return "AppInfo";
    }

    @RequestMapping("/SubmitAppInfo")
    public String appInfoSubmit(String AppId, String UserId, String AppName, Integer Classifier, Integer Security, Model model) {
        logger.info("AppInfo Receive " + AppId + "," + UserId + "," + AppName + "," + String.valueOf(Classifier) + "," + String.valueOf(Security) );
        MyAppInfo NewAppInfo = new MyAppInfo(AppId,UserId,AppName,Classifier,Security);
        if(appService.CheckAppInfo(NewAppInfo)) {
            appService.InsertAppInfo(NewAppInfo);
            return "hello";
        }else {
            model.addAttribute("AppInfoErrorMessage","对不起，您填写的信息不符合规范请重新填写");
            return "AppInfo";
        }

    }


    @RequestMapping("/DeleteAppInfo")
    public String deleteAppInfo(String AppId, String UserId) {
        MyAppInfo deleted_appinfo = appService.getOne(AppId);
        if(deleted_appinfo!=null && deleted_appinfo.getUserId().equals(UserId)) {
            appService.DeleteByID(AppId);
            return "hello";
        } else {
            //非本人
            return "hello";
        }
    }

    @RequestMapping("/ReviseAppInfo")
    public String reviseAppInfo(String AppId, String UserId, String AppName, Integer Classifier, Integer Security, Model model) {
        MyAppInfo revised_appinfo = appService.getOne(AppId);
        if(revised_appinfo!=null && revised_appinfo.getUserId().equals(UserId)) {
            appService.UpdateByID(revised_appinfo);
            return "hello";
        } else {
            //非本人
            return "hello";
        }
    }


}
