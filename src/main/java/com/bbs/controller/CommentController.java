package com.bbs.controller;


import com.bbs.pojo.Comment;
import com.bbs.pojo.CommentDat;
import com.bbs.pojo.Result;
import com.bbs.pojo.User;
import com.bbs.service.BbsService;
import com.bbs.service.CommentService;
import com.bbs.service.UserService;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class CommentController {

    @Autowired
    private BbsService bbsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

//   添加评论
    @PostMapping(value="/showBbs/{bbsid}/addcomment")
    public ResponseEntity<?> addComment(@PathVariable("bbsid")Integer bbsid, CommentDat commentDat, HttpSession session){

//        添加评论
        commentService.addComment(commentDat);
        System.out.println(commentDat);
//
        CommentDat commentDat2=commentService.getCommentByid(commentDat.getId());
        System.out.println(commentDat2);

//        文章的回复数+1
        bbsService.updateBbsChild(bbsid);
        User u=(User)session.getAttribute("user");
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("comment", commentDat2);
        responseMap.put("user",u);
        return ResponseEntity.ok(responseMap);

    }



//    回复评论

    @PostMapping("/showBbs/{boardid}/{bbsid}/addreplycomment/{parentid}")
    public ResponseEntity<?> addreplycomment(@PathVariable("bbsid")Integer bbsid,CommentDat commentDat){

        User u=userService.selectUserById(commentDat.getUserId());
        commentDat.setUserHeaderImage(u.getUserheaderimage());
//        添加评论
        commentService.addComment(commentDat);
//        查询该评论
        CommentDat commentDat2=commentService.getCommentByid(commentDat.getId());
        //        文章的回复数+1
        bbsService.updateBbsChild(bbsid);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("comment", commentDat2);
        return ResponseEntity.ok(responseMap);
    }

//    删除评论

    @GetMapping("/showBbs/{bbsid}/deletecomment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("bbsid")Integer bbsid,@PathVariable("id")Integer id){
            commentService.deletecomment(id);

//            回复数 -1
        bbsService.updateSubBbsChild(bbsid);
        Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success",true);
           return ResponseEntity.ok(responseMap);
    }

}
