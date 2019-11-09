package com.example.springweb.dao;

import java.io.Serializable;

public class MyAppInfo implements Serializable {
    private String AppId;
    private String UserId;
    private String AppName;
    private Integer Classifier;
    private Integer Security;




    public MyAppInfo(){
        AppId = null;
        UserId = null;
        AppName = null;
        Classifier = -1;
        Security = -1;
    }
    public MyAppInfo(String AppId, String UserId, String AppName, Integer Classifier, Integer Security){
        this.AppId = AppId;
        this.UserId = UserId;
        this.AppName = AppName;
        this.Classifier = Classifier;
        this.Security = Security;
    }

    public String getAppId() {
        return AppId;
    }
    public String getUserId() {
        return UserId;
    }
    public String getAppName() {
        return AppName;
    }
    public Integer getClassifier() {return Classifier;}
    public  Integer getSecurity() {return Security;}

    public void setAppId(String AppId) {
        this.AppId = AppId;
    }
    public void setUserId(String UserId) {this.UserId = UserId;}
    public void setAppName(String AppName) {
        this.AppName = AppName;
    }
    public void setClassifier(Integer Classifier) {this.Classifier = Classifier;}
    public void setSecurity(Integer Security) {this.Security = Security;}



    @Override
    public String toString() {
        return AppId + "," + UserId + "," + AppName;
    }
}
