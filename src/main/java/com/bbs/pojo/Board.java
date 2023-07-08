package com.bbs.pojo;

import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;


public class Board {
   private int boardid;
    private String boardname;
   private String boardtopics;
   private String boardmanager;
   private String boardintroduce;
   private Date boardlasttime;
   List<Post> postList;
   public Board(){}

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }

    public String getBoardtopics() {
        return boardtopics;
    }

    public void setBoardtopics(String boardtopics) {
        this.boardtopics = boardtopics;
    }

    public String getBoardmanager() {
        return boardmanager;
    }

    public void setBoardmanager(String boardmanager) {
        this.boardmanager = boardmanager;
    }

    public Date getBoardlasttime() {
        return boardlasttime;
    }

    public void setBoardlasttime(Date boardlasttime) {
        this.boardlasttime = boardlasttime;
    }

    public String getBoardintroduce() {
        return boardintroduce;
    }

    public void setBoardintroduce(String boardintroduce) {
        this.boardintroduce = boardintroduce;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardid=" + boardid +
                ", boardname='" + boardname + '\'' +
                ", boardtopics='" + boardtopics + '\'' +
                ", boardmanager='" + boardmanager + '\'' +
                ", boardintroduce='" + boardintroduce + '\'' +
                ", boardlasttime=" + boardlasttime +
                ", postList=" + postList +
                '}';
    }
}
