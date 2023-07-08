package com.bbs.pojo;



import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Component;


import java.beans.Transient;


import java.util.Date;
import java.util.List;


public class Post {
   private int bbsid;
     private int  boardid;
   private int parentid;
     private int child; // 跟帖数
   private String username;
     private String expression;
   private String bbstitle;
     private String bbscontent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateandtime;
      private  int bbsclick;
  private String  bbshot;

    public Post(){}

    public int getBbsid() {
        return bbsid;
    }

    public void setBbsid(int bbsid) {
        this.bbsid = bbsid;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getBbstitle() {
        return bbstitle;
    }

    public void setBbstitle(String bbstitle) {
        this.bbstitle = bbstitle;
    }

    public String getBbscontent() {
        return bbscontent;
    }

    public void setBbscontent(String bbscontent) {
        this.bbscontent = bbscontent;
    }

    public Date getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(Date dateandtime) {
        this.dateandtime = dateandtime;
    }

    public int getBbsclick() {
        return bbsclick;
    }

    public void setBbsclick(int bbsclick) {
        this.bbsclick = bbsclick;
    }

    public String getBbshot() {
        return bbshot;
    }

    public void setBbshot(String bbshot) {
        this.bbshot = bbshot;
    }
}
