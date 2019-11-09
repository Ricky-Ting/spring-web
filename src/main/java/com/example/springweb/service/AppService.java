package com.example.springweb.service;


import com.example.springweb.dao.MyAppInfo;
import com.example.springweb.mapper.AppMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AppService {
    @Resource
    private AppMapper appMapper;
    static Integer cnt=0;

    public List<MyAppInfo> getUserList() {
        List<MyAppInfo> list = appMapper.findAll();
        return list;
    }

    /*public void InsertUser(HelloUser helloUser){
        helloMapper.insert(helloUser);
        System.out.println("Afterinsert:"+helloMapper.findAll());
    }*/
    public void InsertAppInfo(Map<String, String> params){
       // ObjectMapper objectMapper = new ObjectMapper();
        //MyAppInfo newAppinfo = objectMapper.convertValue(params, MyAppInfo.class);
        MyAppInfo newAppinfo = new MyAppInfo();
        newAppinfo.setAppId(params.get("AppId"));
        newAppinfo.setUserId(params.get("UserId"));
        newAppinfo.setAppName(params.get("AppName"));
        newAppinfo.setClassifier(Integer.parseInt(params.get("Classifier")));
        newAppinfo.setSecurity(Integer.parseInt((params.get("Security"))));
        appMapper.insert(newAppinfo);
    }


    public MyAppInfo getOne(String AppId){
        //HelloUser result = new HelloUser();
        MyAppInfo result = appMapper.getOne(AppId);
        System.out.println("getOne:"+result);
        if (result==null)
        {
            result=new MyAppInfo();//索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        System.out.println(result.toString());
        return result;
    }

    /*public void UpdateByID(HelloUser helloUser){
        helloMapper.updateByID(helloUser);
        System.out.println("AfterUpdate:"+ helloMapper.getOne(helloUser.getId()));
    }*/
    public void UpdateByID(Map<String, String> params){
        String AppId = params.get("AppId");
        //Long recordId = Long.parseLong(params.get("recordId"));
        //ObjectMapper objectMapper = new ObjectMapper();
        //HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        //helloMapper.updateByID(helloUser);
        MyAppInfo temp = appMapper.getOne(AppId);
        if(params.get("AppName")!=null)
            temp.setAppName(params.get("AppName"));
        if(params.get("UserId")!=null)
            temp.setUserId((params.get("UserId")));
        if(Integer.parseInt(params.get("Classifier"))!=-1)
            temp.setClassifier((Integer.parseInt(params.get("Classifier"))));
        if(Integer.parseInt(params.get("Security"))!=-1)
            temp.setSecurity((Integer.parseInt(params.get("Security"))));
        appMapper.updateByID(temp);
    }


    public void DeleteByID(String AppId){
        appMapper.deleteByID(AppId);
        System.out.println("AfterDelete:"+appMapper.getOne(AppId));
    }
}