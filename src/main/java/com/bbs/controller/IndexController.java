package com.bbs.controller;


import com.bbs.mapper.BbsMapper;
import com.bbs.pojo.Board;
import com.bbs.pojo.Post;
import com.bbs.service.BoardService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@Transactional
public class IndexController {


    @Autowired
    private BoardService boardService;

    @Autowired
    private  BbsMapper bbsService;

//    首页显示
    @RequestMapping("/")
    public String index(HttpSession session){
//     List<Board> boardList=  boardService.selectAllBoard();
//     System.out.println(boardList);
//     session.setAttribute("boardList",boardList);
        return "index";
    }

    //    游客点击精华区时，显示错误页面
//    会员用户首页,游客进行游览页面

    @RequestMapping("/consumer")
    public String consumer(HttpSession session){

        return "consumer";
    }
//
// 搜索功能
    @RequestMapping("/search")
    public String search(String content, Model model){

    //  模糊查询板块，
        List<Board> boards=boardService.searchBoardByContent(content);
    //
        List<Post> bbs=bbsService.searchBbsByContent(content);

        model.addAttribute("boards",boards);
        model.addAttribute("bbs",bbs);
        return "searchPage";

    }

//    会员用户点击个人中心时，跳转的页面
    @RequestMapping("/personalnfo")
    public String personaInfo(HttpSession session){
//        当用户登录之后，跳转用户中心，当用户没有登录，跳转错误页面
        if(session.getAttribute("user")!=null){
            return "personalnfo";
        }

        return "visitorError";

    }

//    游客和会员用户共同页面
}
