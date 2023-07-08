package com.bbs.pojo;

import org.springframework.stereotype.Component;

import java.sql.Date;


public class User {
   private int id ;
    private String username;
    private String userpass;
     private String usertype;
    private String usermail;
     private  String userhomepage;
    private String homepagename;
       private String sex;
   private String comefrom;
     private String usersign;
    private String userheaderimage;
    private Date redate;


    public String getUserheaderimage() {
        return userheaderimage;
    }

    public void setUserheaderimage(String userheaderimage) {
        this.userheaderimage = userheaderimage;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserhomepage() {
        return userhomepage;
    }

    public void setUserhomepage(String userhomepage) {
        this.userhomepage = userhomepage;
    }

    public String getHomepagename() {
        return homepagename;
    }

    public void setHomepagename(String homepagename) {
        this.homepagename = homepagename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public String getUsersign() {
        return usersign;
    }

    public void setUsersign(String usersign) {
        this.usersign = usersign;
    }

    public Date getRedate() {
        return redate;
    }

    public void setRedate(Date redate) {
        this.redate = redate;
    }
}
