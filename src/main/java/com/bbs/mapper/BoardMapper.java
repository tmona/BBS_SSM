package com.bbs.mapper;

import com.bbs.pojo.Board;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BoardMapper {


    List<Board> selectAllBoard();
//添加模块
    int addBoard(Board b);
//删除板块
    int deleteBoard(Integer boardid);

    Board selectByBoardid(Integer boardid);

    int updateBoard(Integer boardid, @Param("b")Board board);

    int updateAddBoardTopic(Integer boardid);

    int updateBoardLastTime(@Param("boardid")Integer boardid,@Param("boardlasttime") Date boardlasttime);

    int updateSubBoardTopic(Integer boardid);

    int updateAddBoardChild(Integer boardid);

    List<Board> searchBoardByContent(String content);
}
