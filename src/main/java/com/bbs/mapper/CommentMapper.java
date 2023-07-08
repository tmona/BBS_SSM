package com.bbs.mapper;

import com.bbs.pojo.CommentDat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    int addComment(@Param("comment")CommentDat comment);

    List<CommentDat> getAllRoot(Integer id);


    List<CommentDat> getChildren(@Param("originId")Integer originId,@Param("articleId") Integer articleId);

    int deletecomment(Integer id);

    CommentDat getCommentByid(Integer id);
}
