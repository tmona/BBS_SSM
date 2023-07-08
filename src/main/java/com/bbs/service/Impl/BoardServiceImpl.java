package com.bbs.service.Impl;

import com.bbs.mapper.BbsMapper;
import com.bbs.mapper.BoardMapper;

import com.bbs.pojo.Board;
import com.bbs.service.BoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BbsMapper bbsMapper;

    @Override
    public List<Board> selectAllBoard() {
        return boardMapper.selectAllBoard();
    }

    @Override
    public List<Board> searchBoardByContent(String content) {
        return boardMapper.searchBoardByContent(content);
    }

    @Override
    public Board selectByBoardid(Integer boardid) {
        return boardMapper.selectByBoardid(boardid);
    }

    @Override
    public int addBoard(Board b) {
        return boardMapper.addBoard(b);
    }

    @Override
    public PageInfo<Board> getBoardList(Integer pageNum) {
        //PageHelper.startPage(pageNum,5);从pageNum分页，每页显示5条数据
        PageHelper.startPage(pageNum,4);
        List<Board> boardList=boardMapper.selectAllBoard();
        PageInfo<Board> pageInfo=new PageInfo<>(boardList,2);
        return pageInfo;
    }

    @Override
    public int deleteBoard(Integer boardid) {
        return boardMapper.deleteBoard(boardid);
    }

    @Override
    public int updateBoard(Integer boardid, Board board) {
        return boardMapper.updateBoard(boardid,board);
    }

    @Override
    public int updateAddBoardTopic(Integer boardid) {
        return boardMapper.updateAddBoardTopic(boardid);
    }

    @Override
    public int updateSubBoardTopic(Integer boardid) {
        return boardMapper.updateSubBoardTopic(boardid);
    }



    @Override
    public int updateBoardLastTime(Integer boardid, Date time) {
        return boardMapper.updateBoardLastTime(boardid,time);
    }

    @Override
    public int updateAddBoardChild(Integer boardid) {
        return boardMapper.updateAddBoardChild(boardid);
    }
}
