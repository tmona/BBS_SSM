package com.bbs.service;


import com.bbs.pojo.Comment;
import com.bbs.pojo.CommentDat;

import java.util.List;

public interface CommentService {

//根据id查询评论

    CommentDat getCommentByid(Integer id);

    int addComment(CommentDat comment);

    List<CommentDat> getChildren(Integer originId, Integer articleId);

    public List<CommentDat> getAllComment(Integer id);

//    删除评论

    int deletecomment(Integer id);

}
