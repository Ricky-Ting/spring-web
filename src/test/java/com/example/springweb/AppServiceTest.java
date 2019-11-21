package com.example.springweb;

import com.example.springweb.dao.MyAppInfo;
import com.example.springweb.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTest {
    @Autowired
    AppService appService;

    @Test
    public void testInsertAppInfo() throws Exception {
        Integer sz= appService.getAppinfoList("test_user").size();
        MyAppInfo insert_app =  new MyAppInfo("inserted_app","test_user","test_app",2,3);
        appService.InsertAppInfo(insert_app);
        insert_app =  new MyAppInfo("inserted_app2","test_user","test_app2",2,3);
        appService.InsertAppInfo(insert_app);
        assertEquals(appService.getAppinfoList("test_user").size(),sz+2);
    }


    @Test
    public void testgetOne() throws Exception {
        MyAppInfo insert_app=appService.getOne("inserted_app");
        assertEquals(insert_app.getAppName(),"test_app");
        assertEquals((Integer)insert_app.getClassifier(),(Integer)2);

        insert_app=appService.getOne("inserted_app2");
        assertEquals(insert_app.getAppName(),"test_app2");
        assertEquals((Integer) insert_app.getClassifier(),(Integer) 2);

    }

    @Test
    public void testUpdateByID() throws Exception {
        MyAppInfo insert_app =  new MyAppInfo("inserted_app","test_user","update_app",3,1);
        appService.UpdateByID(insert_app);
        assertEquals(appService.getOne("inserted_app").getAppName(),"update_app");
        assertEquals((Integer) appService.getOne("inserted_app").getClassifier(),(Integer) 3);

        insert_app =  new MyAppInfo("inserted_app2","test_user","update_app",3,1);
        appService.UpdateByID(insert_app);
        assertEquals(appService.getOne("inserted_app2").getAppName(),"update_app");
        assertEquals((Integer)(appService.getOne("inserted_app2").getClassifier()),(Integer)3);

    }


    @Test
    public void testgetAppinfoList() throws  Exception {
        List<MyAppInfo> list = appService.getAppinfoList("test_user");

        boolean flag =false;
        for(Integer i=0;i<list.size();i++){
            if(list.get(i).getAppId().equals("inserted_app"))
                flag = true;
        }
        assertTrue(flag);


        flag =false;
        for(Integer i=0;i<list.size();i++){
            if(list.get(i).getAppId().equals("inserted_app2"))
                flag = true;
        }
        assertTrue(flag);

    }

    @Test
    public void testCheckAppInfo() throws Exception {
        MyAppInfo check_appinfo = new MyAppInfo("inserted_app","test_user","update_app",2,3);
        assertEquals(appService.CheckAppInfo(check_appinfo),false);

        check_appinfo.setAppId("noneexists");
        assertEquals(appService.CheckAppInfo(check_appinfo),true);
    }




    @Test
    public void testDeleteById() throws Exception {
        Integer sz= appService.getAppinfoList("test_user").size();
        appService.DeleteByID("inserted_app");
        appService.DeleteByID("inserted_app2");
        assertEquals(appService.getAppinfoList("test_user").size(),sz-2);
    }


}

