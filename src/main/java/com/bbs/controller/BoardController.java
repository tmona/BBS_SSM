package com.bbs.controller;


import com.bbs.pojo.Board;
import com.bbs.pojo.Post;
import com.bbs.service.BbsService;
import com.bbs.service.BoardService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class BoardController {

    @Autowired
    private  BoardService boardService;

    @Autowired
    private BbsService bbsService;

    //管理员分页显示板块

    @RequestMapping(value="/showBoard/page/{pageNum}",method={RequestMethod.GET})
    public String showBoard(@PathVariable("pageNum") Integer pageNum,HttpSession session){
        PageInfo<Board> page=boardService.getBoardList(pageNum);
        session.setAttribute("page",page);
        return "showBoard";
    }


//    游客和普通用户

    @RequestMapping("/consumerBoard")
    public String showConsumerBoard(HttpSession session){
        List<Board> boardList=boardService.selectAllBoard();
        session.setAttribute("boardList",boardList);
        return "consumerBoard";
    }








//跳转到添加板块的页面
    @RequestMapping(value="/addBoard",method={RequestMethod.GET})
    public String jumpaddBoard(){
        return "addBoard";
    }


//添加板块
    @ResponseBody
    @RequestMapping(value="/addBoard",method={RequestMethod.POST},params = {"boardname","boardintroduce","boardmanager"})
    public ResponseEntity<?> addBoard(Board b, HttpSession session){

        boardService.addBoard(b);
//        更新session
        session.setAttribute("board",boardService.selectAllBoard());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success",true);
        return ResponseEntity.ok(responseMap);
    }


//删除板块


    @RequestMapping(value="/deleteBoard/page/{boardid}/{pageNum}",method={RequestMethod.DELETE})
    public String deleteBoard(@PathVariable("boardid") Integer boardid,@PathVariable("pageNum") Integer pageNum){


        boardService.deleteBoard(boardid);
        return "redirect:/showBoard/page/"+pageNum;
    }

// 修改板块

    @RequestMapping(value="/updateBoard/{boardid}/{pageNum}",method={RequestMethod.GET})
    public String updateBoard(@PathVariable("boardid")Integer boardid,@PathVariable("pageNum")Integer pageNum, Model model){
        Board b=boardService.selectByBoardid(boardid);
        model.addAttribute("board",b);
        model.addAttribute("pageNum",pageNum);
        return "updateBoard";
    }

    @RequestMapping(value="/updateBoard/{boardid}/{pageNum}",method={RequestMethod.POST},params = {"boardname","boardintroduce"})
    public String updateBoard(String boardname,String boardintroduce,@PathVariable("boardid")Integer boardid,@PathVariable("pageNum")Integer pageNum){
        Board b=boardService.selectByBoardid(boardid);
        b.setBoardname(boardname);
        b.setBoardintroduce(boardintroduce);
        boardService.updateBoard(boardid,b);
        return "redirect:/showBoard/page/"+pageNum;
    }

}
