package com.bbs.controller;


import com.bbs.pojo.Board;
import com.bbs.pojo.CommentDat;
import com.bbs.pojo.Post;
import com.bbs.pojo.User;
import com.bbs.service.BbsService;
import com.bbs.service.BoardService;
import com.bbs.service.CommentService;
import com.bbs.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class BbsController {

    @Autowired
    private BbsService bbsService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    //管理员用户，分页显示模版下的主题
    @RequestMapping(value="/showBbs/{boardid}/{pageNum}",method={RequestMethod.GET})
    public String showbbs(@PathVariable("boardid")Integer boardid, @PathVariable("pageNum")Integer pageNum, Model model){
        PageInfo<Post> page=bbsService.getBbsList(pageNum,boardid);
        model.addAttribute("bbspage",page);
        model.addAttribute("boardid",boardid);
        return "showBbs";
    }

    //    不分页显示主题，会员用户
    @RequestMapping(value = "/customer/showBbs/{boardid}",method = {RequestMethod.GET})
    public String customerShowBbs(@PathVariable("boardid")Integer boardid,Model model){
        List<Post> postList=bbsService.selectByBoardid(boardid);
        model.addAttribute("postList",postList);
        return "consumerShowBbs";
    }


//    管理员用户管理 分页精华帖
    @RequestMapping("/showBbshot/page/{pageNum}")
    public String showBbshot(@PathVariable("pageNum")Integer pageNum,Model model){
        PageInfo<Post> page=bbsService.getBbshotList(pageNum);
        model.addAttribute("bbshotpage",page);
        return "showBbshot";
    }



//    显示主题里的内容

    @RequestMapping(value = "/article/{boardid}/{bbsid}",method = {RequestMethod.GET})
    public String showarticle(@PathVariable("boardid")Integer boardid,@PathVariable("bbsid")Integer bbsid,Model model){
//       存储文章
        Post post=bbsService.selectByBbsid(bbsid);
        model.addAttribute("post",post);

//        查询作者
        User u=userService.selectUserByUsername(post.getUsername());
        model.addAttribute("user",u);

//      文章下的所有评论
        List<CommentDat> comments= commentService.getAllComment(bbsid);
        model.addAttribute("comments",comments);
//       板块名字
        Board board=boardService.selectByBoardid(boardid);
        model.addAttribute("boardname",board.getBoardname());
//        文章的点击量+1
        bbsService.updateBbsclick(bbsid);
        return "article";
    }



    //   显示所有精华帖, 会员用户点击精华区的时，跳转的页面
    @RequestMapping("/essentialArea")
    public String essentialArea(Model model){
        List<Post> allbbshot=bbsService.getAllBbshot();
        model.addAttribute("bbshots",allbbshot);
        return "essentialArea";
    }


    //管理员添加主题

    @RequestMapping(value = "/addBbs/{boardid}",method = {RequestMethod.POST},params = {"bbstitle","bbscontent","username","expression"})
    public String admin_addbbs(@PathVariable("boardid")Integer boardid,String bbstitle,String bbscontent,String username,String expression){

        //添加主题
        Post post=new Post();
        post.setBbscontent(bbscontent);
        post.setBbstitle(bbstitle);
        post.setUsername(username);
        post.setBoardid(boardid);
        post.setExpression(expression);
        bbsService.addBbs(post);
//        板块的主题数加一
        boardService.updateAddBoardTopic(boardid);
        //        板块的最后发表时间修改
        Integer bbsid=post.getBbsid();
        Post bbs=bbsService.selectByBbsid(bbsid);
        Date time=bbs.getDateandtime();
        boardService.updateBoardLastTime(boardid,time);
        return "redirect:/showBbs/"+boardid+"/1";
    }

    //会员用户添加主题
    @RequestMapping(value = "/consumer/addBbs/{boardid}",method = {RequestMethod.POST},params = {"bbstitle","bbscontent","username","expression"})
    public String consumer_addbbs(@PathVariable("boardid")Integer boardid,String bbstitle,String bbscontent,String username,String expression){

        //添加主题
        Post post=new Post();
        post.setBbscontent(bbscontent);
        post.setBbstitle(bbstitle);
        post.setUsername(username);
        post.setBoardid(boardid);
        post.setExpression(expression);
        bbsService.addBbs(post);
//        板块的主题数加一
        boardService.updateAddBoardTopic(boardid);
        //        板块的最后发表时间修改
        Integer bbsid=post.getBbsid();
        Post bbs=bbsService.selectByBbsid(bbsid);
        Date time=bbs.getDateandtime();
        boardService.updateBoardLastTime(boardid,time);

        return "redirect:/customer/showBbs/"+boardid;
    }


//    将帖子修改为精华帖
    @ResponseBody
    @RequestMapping("/showBbs/addessentialbbs/{bbsid}")
    public ResponseEntity<?> updateBbshot(@PathVariable("bbsid")Integer bbsid){

            bbsService.updateBbshot(bbsid);
            Map<String,Object> responseMap=new HashMap<>();
            responseMap.put("success",true);
            return ResponseEntity.ok(responseMap);

    }


    //修改主题
    @RequestMapping(value = "/updateBbs/{boardid}/{bbsid}",method = {RequestMethod.GET})
    public String updateBbs(@PathVariable("bbsid") Integer bbsid,Model model){
        Post bbs=bbsService.selectByBbsid(bbsid);
        model.addAttribute("bbs",bbs);
        return "updateBbs";
    }


//    管理员修改普通帖子
    @RequestMapping(value="/updateBbs/{boardid}/{bbsid}",method={RequestMethod.POST},params = {"bbstitle","bbscontent"})
    public String updateBbs2(@PathVariable("boardid")Integer boardid,@PathVariable("bbsid")Integer bbsid,String bbstitle,String bbscontent){

        bbsService.updateBbs(bbsid,bbstitle,bbscontent);
        //修改板块的最后的发表时间
        Post bbs=bbsService.selectByBbsid(bbsid);
        Date time=bbs.getDateandtime();
        boardService.updateBoardLastTime(boardid,time);
        System.out.println("bbs.getBbshot()="+bbs.getBbshot());
        if(bbs.getBbshot().equals("1")){
            return "redirect:/showBbshot/page/"+"/1";
        }
        else{
            return "redirect:/showBbs/"+boardid+"/1";
        }
    }


////    管理员修改精华帖
//    @RequestMapping(value="/updateHotBbs/{boardid}/{bbsid}",method={RequestMethod.GET},params = {"bbstitle","bbscontent"})
//    public String updateHotBbs(@PathVariable("boardid")Integer boardid,@PathVariable("bbsid")Integer bbsid,String bbstitle,String bbscontent){
//
//    bbsService.updateBbs(bbsid,bbstitle,bbscontent);
//    //修改板块的最后的发表时间
//    Post bbs=bbsService.selectByBbsid(bbsid);
//    Date time=bbs.getDateandtime();
//    boardService.updateBoardLastTime(boardid,time);
//    return "redirect:/showBbshot/page/"+"/1";
//
//}




    //删除主题
    @RequestMapping(value = "/deleteBbs/{boardid}/{bbsid}",method = {RequestMethod.DELETE})
    public String deleteBbs(@PathVariable("boardid")Integer boardid,@PathVariable("bbsid")Integer bbsid){

        bbsService.deleteByBbsid(bbsid);
//        将板块的主题数减1
        boardService.updateSubBoardTopic(boardid);
        return "redirect:/showBbs/"+boardid+"/1";
    }


    //删除精华帖
    @RequestMapping(value = "/deleteBbshot/{boardid}/{bbsid}",method = {RequestMethod.DELETE})
    public String deleteBbshot(@PathVariable("bbsid")Integer bbsid){

//          取消精华帖
        bbsService.deleteBbshot(bbsid);
        return "redirect:/showBbshot/page/1";
    }



}
