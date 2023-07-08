package com.bbs.service.Impl;

import com.bbs.mapper.CommentMapper;
import com.bbs.mapper.UserMapper;
import com.bbs.pojo.Comment;
import com.bbs.pojo.CommentDat;
import com.bbs.service.CommentService;
import com.bbs.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    private final StringUtil stringUtil = new StringUtil();


    @Override
    public CommentDat getCommentByid(Integer id) {

        CommentDat comment=commentMapper.getCommentByid(id);
//        comment.setMessage(stringUtil.filterSpecialChar(comment.getMessage()));
        comment.setUserHeaderImage(userMapper.selectUserById(comment.getUserId()).getUserheaderimage());
        List<CommentDat> children = commentMapper.getChildren(comment.getUserId(),id);
        children.forEach(child -> {
//            child.setMessage(stringUtil.filterSpecialChar(comment.getMessage()));
            child.setUserHeaderImage(userMapper.selectUserById(comment.getUserId()).getUserheaderimage());
        });
        comment.setChildren(children);
        return comment;

    }

    @Override
    public int addComment(CommentDat comment) {
//        将特殊字符处理为转义字符
    comment.setMessage(stringUtil.filterSpecialChar(comment.getMessage()));
        return commentMapper.addComment(comment);
    }

//    获取根评论下的评论
    @Override
    public List<CommentDat> getChildren(Integer originId, Integer articleId) {
        return commentMapper.getChildren(originId,articleId);
    }

//    获取全部评论

    public List<CommentDat> getAllComment(Integer id){

        //获取所有根评论
        List<CommentDat> comments=commentMapper.getAllRoot(id);
        comments.forEach(comment -> {
//            comment.setMessage(stringUtil.filterSpecialChar(comment.getMessage()));
            comment.setUserHeaderImage(userMapper.selectUserById(comment.getUserId()).getUserheaderimage());
            List<CommentDat> children = commentMapper.getChildren(comment.getId(),comment.getArticleId());
            children.forEach(child -> {
//                child.setMessage(stringUtil.filterSpecialChar(comment.getMessage()));
                child.setUserHeaderImage(userMapper.selectUserById(comment.getUserId()).getUserheaderimage());
            });
            comment.setChildren(children);
        });

        return comments;

    }

    @Override
    public int deletecomment(Integer id) {
        return commentMapper.deletecomment(id);
    }

}
