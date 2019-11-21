package com.example.springweb;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    HelloService helloService;

    @Test
    public void testInsertUser() throws Exception{
        Integer sz = helloService.getUserList().size();
        HelloUser inserted_user = new HelloUser("test_Insert","TestUser","TestPassword");
        helloService.InsertUser(inserted_user);
        HelloUser inserted_user2 = new HelloUser("test_Insert2","TestUser2","TestPassword");
        helloService.InsertUser(inserted_user2);
        assertEquals(helloService.getUserList().size(),sz+2);

    }

    @Test
    public void testGetOne()  throws Exception{
        HelloUser helloUser = helloService.getOne("test_Insert");
        assertEquals(helloUser.getName(),"TestUser");
        HelloUser helloUser2 = helloService.getOne("test_Insert2");
        assertEquals(helloUser2.getName(),"TestUser2");

    }

    @Test
    public void testUpdateByID() throws Exception{
        HelloUser updated_user = new HelloUser("test_Insert","UpdatedUser","UpdatePassword");
        helloService.UpdateByID(updated_user);
        assertEquals(helloService.getOne("test_Insert").getName(),"UpdatedUser");

        updated_user.setId("test_Insert2"); updated_user.setName("UpdatedUser2");
        helloService.UpdateByID(updated_user);
        assertEquals(helloService.getOne("test_Insert2").getName(),"UpdatedUser2");
    }



    @Test
    public void testCheck_User() throws Exception{
        HelloUser check_user = new HelloUser("test_Insert","balabla","pass");
        assertEquals(helloService.Check_User(check_user),false);
        check_user.setId("Noneexists");
        assertEquals(helloService.Check_User(check_user),true);
    }


    @Test
    public void testgetUserList() throws Exception {
        List<HelloUser> list = helloService.getUserList();
        boolean flag =false;
        for(Integer i=0;i<list.size();i++) {
            if(list.get(i).getName().equals("UpdatedUser"))
                flag=true;
        }
        assertTrue(flag);

        flag =false;
        for(Integer i=0;i<list.size();i++) {
            if(list.get(i).getName().equals("UpdatedUser2"))
                flag=true;
        }
        assertTrue(flag);



    }

    @Test
    public void testDeleteByID() throws Exception{
        Integer sz = helloService.getUserList().size();
        helloService.DeleteByID("test_Insert");
        helloService.DeleteByID("test_Insert2");
        assertEquals(helloService.getUserList().size(),sz-2);

    }




}

