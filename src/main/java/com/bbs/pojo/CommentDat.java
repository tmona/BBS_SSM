package com.bbs.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;

//数据实体返回类
public class CommentDat {
    private Integer id;
    //    评论人昵称
    private String username;
    //    评论人的头像
    private String userHeaderImage;
    //    评论人的id
    private Integer userId;
    //    评论文章id
    private Integer articleId;
    //    评论文章标题
    private String articleTitle;
    //    评论内容
    private String message;
    //    回复人id
    private Integer parentId;
    //    回复人昵称
    private String parentName;
    //    回复人头像
    private String parentHeaderImage;
    //    根评论人
    private Integer originalId;
    //    根评论人的昵称
    private String originName;
    //    评论时间

    private String createdAt;
    //    评论的子评论
    private List<CommentDat> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserHeaderImage() {
        return userHeaderImage;
    }

    public void setUserHeaderImage(String userHeaderImage) {
        this.userHeaderImage = userHeaderImage;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentHeaderImage() {
        return parentHeaderImage;
    }

    public void setParentHeaderImage(String parentHeaderImage) {
        this.parentHeaderImage = parentHeaderImage;
    }

    public Integer getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Integer originId) {
        this.originalId = originId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<CommentDat> getChildren() {
        return children;
    }

    public void setChildren(List<CommentDat> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CommentDat{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userHeaderImage='" + userHeaderImage + '\'' +
                ", userId=" + userId +
                ", articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", message='" + message + '\'' +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", parentHeaderImage='" + parentHeaderImage + '\'' +
                ", originalId=" + originalId +
                ", originName='" + originName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", children=" + children +
                '}';
    }
}
