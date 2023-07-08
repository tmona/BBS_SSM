package com.bbs.service;

import com.bbs.pojo.Post;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BbsService {

    //根据板块id查看该板块的主题
    List<Post> selectByBoardid(Integer boardid);


    //根据bbsid查询帖子

    Post selectByBbsid(Integer bbsid);

//    查看所有parentid等于bbsid的帖子

    List<Post> selectByBbsideqParent(Integer bbsid);

    //分页显示主题

    PageInfo<Post> getBbsList(Integer pageNum,Integer boardid);

//    分页显示精华帖
    PageInfo<Post> getBbshotList(Integer pageNum);


//    显示所有精华贴
    List<Post> getAllBbshot();

//    根据内容对文章进行进行模糊查询

    List<Post> searchBbsByContent(String content);

//    添加帖子

    int addBbs(Post post);



//    更改帖子
    int updateBbs(Integer bbsid,String bbstitle,String bbscontent);

//    跟帖数+1

    int updateBbsChild(Integer bbsid);

//    跟帖数 -1
    int updateSubBbsChild(Integer bbsid);

//    将帖子修改为精华帖

    int updateBbshot(Integer bbsid);

//    点击量+1
    int updateBbsclick(Integer bbsid);

//    将帖子由精华帖变为普通帖子

    int deleteBbshot(Integer bbsid);


//    根据bbsid删除帖子
    int deleteByBbsid(Integer bbsid);

}
