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

    public List<MyAppInfo> getAppinfoList(String UserId) {
        List<MyAppInfo> list = appMapper.findAll(UserId);

        return list;
    }

    public boolean CheckAppInfo(MyAppInfo check_AppInfo) {
        String App_Id = check_AppInfo.getAppId();
        String User_Id = check_AppInfo.getUserId();
        String App_Name = check_AppInfo.getAppName();
        if(App_Id==null || User_Id==null || App_Name==null || App_Id.equals("") || User_Id.equals("") || App_Name.equals(""))
            return false;
        MyAppInfo exist_appinfo= appMapper.getOne(App_Id);
        if(exist_appinfo!=null) return false;
        return true;
    }




    public void InsertAppInfo(MyAppInfo Inserted_app_info){
        // ObjectMapper objectMapper = new ObjectMapper();
        //MyAppInfo newAppinfo = objectMapper.convertValue(params, MyAppInfo.class);
        appMapper.insert(Inserted_app_info);
    }


    public MyAppInfo getOne(String AppId){
        //HelloUser result = new HelloUser();
        MyAppInfo result = appMapper.getOne(AppId);
        System.out.println("getOne:"+result);
        if (result==null)
        {
            //result=new MyAppInfo();//索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        //System.out.println(result.toString());
        return result;
    }






    public void UpdateByID(MyAppInfo updated_appinfo){
        appMapper.updateByID(updated_appinfo);
    }

    public void DeleteByID(String AppId){
        appMapper.deleteByID(AppId);
        System.out.println("AfterDelete:"+appMapper.getOne(AppId));
    }
}
