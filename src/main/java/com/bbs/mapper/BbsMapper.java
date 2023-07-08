package com.bbs.mapper;

import com.bbs.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BbsMapper {
    List<Post> selectByBoardid(Integer boardid);

    int addBbs(@Param("p")Post post);

    Post selectByBbsid(Integer bbsid);

    //    更改帖子
    int updateBbs(@Param("bbsid")Integer bbsid,@Param("bbstitle")String bbstitle,@Param("bbscontent")String bbscontent);

    int deleteByBbsid(Integer bbsid);

    int updateBbsChild(Integer bbsid);

    List<Post> selectByBbsideqParent(Integer bbsid);

    int updateBbshot(Integer bbsid);

    List<Post> getAllBbshot();

    int deleteBbshot(Integer bbsid);

    int updateBbsclick(Integer bbsid);

    int updateSubBbsChild(Integer bbsid);

    List<Post> searchBbsByContent(String content);
}
