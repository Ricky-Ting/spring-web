package com.example.springweb.mapper;

import com.example.springweb.dao.MyAppInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


/*
*   App表的结构：
*   create table appinfo (
*       AppId           char(40),
*       UserId          char(40),
*       AppName         char(40),
*       Classifier      int,
*       Security        int,
*   );
*
*
*
*/


@Mapper
public interface AppMapper {
    @Select("select * from appinfo")
    @Results({
            @Result(property = "AppId", column = "AppId"),
            @Result(property = "UserId", column = "UserId"),
            @Result(property = "AppName", column = "AppName"),
            @Result(property = "Classifier", column = "Classifier"),
            @Result(property = "Security", column = "Security")
    })
    List<MyAppInfo> findAll();

    @Insert("insert into appinfo(AppId,UserId,AppName,Classifier,Security) values(#{AppId},#{UserId},#{AppName},#{Classifier},#{Security})")
    void insert(MyAppInfo newAppInfo);


    @Select("select * from appinfo where AppId = #{AppId}")
    @Results({
            @Result(property = "AppId", column = "AppId"),
            @Result(property = "UserId", column = "UserId"),
            @Result(property = "AppName", column = "AppName"),
            @Result(property = "Classifier", column = "Classifier"),
            @Result(property = "Security", column = "Security")
    })
    MyAppInfo getOne(String AppId);

    @Update("update appinfo set UserId = #{UserId}, AppName = #{AppName}, Classifier = #{Classifier}, Security = #{Security}  where AppId = #{AppId}")
    void updateByID(MyAppInfo helloUser);//UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

    @Delete("delete from appinfo where AppId = #{AppId}")
    void deleteByID(String id);//DELETE FROM 表名称 WHERE 列名称 = 值
}
