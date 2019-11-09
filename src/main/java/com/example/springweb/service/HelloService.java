package com.example.springweb.service;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.mapper.HelloMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HelloService {
    @Resource
    private HelloMapper helloMapper;

    public List<HelloUser> getUserList() {
        List<HelloUser> list = helloMapper.findAll();
        return list;
    }

    /*public void InsertUser(HelloUser helloUser){
        helloMapper.insert(helloUser);
        System.out.println("Afterinsert:"+helloMapper.findAll());
    }*/
    public void InsertUser(Map<String, String> params){
        ObjectMapper objectMapper = new ObjectMapper();
        HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        helloMapper.insert(helloUser);
    }

    public void InsertUser(HelloUser inserted_user){
        helloMapper.insert(inserted_user);
    }


    public HelloUser getOne(String id){
        //HelloUser result = new HelloUser();
        HelloUser result = helloMapper.getOne(id);
        System.out.println("getOne:"+result);
        if (result==null)
        {
            //result=new HelloUser();//索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        //System.out.println(result.toString());
        return result;
    }

    /*public void UpdateByID(HelloUser helloUser){
        helloMapper.updateByID(helloUser);
        System.out.println("AfterUpdate:"+ helloMapper.getOne(helloUser.getId()));
    }*/
    public void UpdateByID(Map<String, String> params){
        String id = params.get("id");
        //Long recordId = Long.parseLong(params.get("recordId"));
        //ObjectMapper objectMapper = new ObjectMapper();
        //HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        //helloMapper.updateByID(helloUser);
        HelloUser temp = helloMapper.getOne(id);
        if(params.get("name")!=null)
            temp.setName(params.get("name"));
        if(params.get("password")!=null)
            temp.setPassword((params.get("password")));
        helloMapper.updateByID(temp);
    }


    public void DeleteByID(String id){
        helloMapper.deleteByID(id);
        System.out.println("AfterDelete:"+helloMapper.getOne(id));
    }

    public boolean Check_User(HelloUser checked_user) {
        String UserId = checked_user.getId();
        String Username = checked_user.getName();
        String passwd = checked_user.getPassword();
        if(UserId==null || Username==null || passwd==null)
            return false;
        if(Username.length()>=40 || UserId.length()>=40 || passwd.length()>=40)
            return false;

        HelloUser exist_user = helloMapper.getOne(UserId);
        if(exist_user!=null) return false;


        return true;
    }


}
