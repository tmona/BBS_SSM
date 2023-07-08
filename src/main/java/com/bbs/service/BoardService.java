package com.bbs.service;


import com.bbs.pojo.Board;
import com.github.pagehelper.PageInfo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface BoardService {
//    查询所有帖子
    List<Board> selectAllBoard();

//    对板块进行模糊查询

    List<Board> searchBoardByContent(String content);


    //通过boardid查询板块

    Board selectByBoardid(Integer boardid);

    //添加板块
    int addBoard(Board b);

    //分页显示板块
    PageInfo<Board> getBoardList(Integer pageNum);

    //通过boardid删除板块

    int deleteBoard(Integer boardid);

    //通过boardid修改板块
    int updateBoard(Integer boardid,Board board);


//    板块主题数+1
    int updateAddBoardTopic(Integer boardid);

//    板块主题数-1

    int updateSubBoardTopic(Integer boardid);

//    修改板块的最后发表时间


    int updateBoardLastTime(Integer boardid, Date time);


//    板块的帖子数+1

    int updateAddBoardChild(Integer boardid);

}
