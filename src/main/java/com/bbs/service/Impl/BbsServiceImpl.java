package com.bbs.service.Impl;

import com.bbs.mapper.BbsMapper;
import com.bbs.pojo.Post;
import com.bbs.service.BbsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsServiceImpl implements BbsService {

    @Autowired
    private  BbsMapper bbsMapper;


    @Override
    public List<Post> selectByBoardid(Integer boardid) {
        return bbsMapper.selectByBoardid(boardid);
    }

    @Override
    public Post selectByBbsid(Integer bbsid) {
        return bbsMapper.selectByBbsid(bbsid);
    }

    @Override
    public List<Post> selectByBbsideqParent(Integer bbsid) {
        return bbsMapper.selectByBbsideqParent(bbsid);
    }

    //    对板块的主题分页
    @Override
    public PageInfo<Post> getBbsList(Integer pageNum,Integer boardid) {

        PageHelper.startPage(pageNum,10);
        List<Post> postList=bbsMapper.selectByBoardid(boardid);

        PageInfo<Post> page=new PageInfo<>(postList,3);
        return page;
    }

//    精华帖分页
    @Override
    public PageInfo<Post> getBbshotList(Integer pageNum) {

        PageHelper.startPage(pageNum,5);
        List<Post> bbshot=bbsMapper.getAllBbshot();
        PageInfo<Post> page=new PageInfo<>(bbshot,3);
        return page;
    }


    @Override
    public List<Post> getAllBbshot() {
        return bbsMapper.getAllBbshot();
    }

    @Override
    public List<Post> searchBbsByContent(String content) {
        return bbsMapper.searchBbsByContent(content);
    }

    @Override
    public int addBbs(Post post) {
        return bbsMapper.addBbs(post);
    }

    @Override
    public int updateBbs(Integer bbsid,String bbstitle, String bbscontent) {
        return bbsMapper.updateBbs(bbsid,bbstitle,bbscontent);
    }

    @Override
    public int updateBbsChild(Integer bbsid) {
        return bbsMapper.updateBbsChild(bbsid);
    }

    @Override
    public int updateSubBbsChild(Integer bbsid) {
        return bbsMapper.updateSubBbsChild(bbsid);
    }

    @Override
    public int updateBbshot(Integer bbsid) {
        return bbsMapper.updateBbshot(bbsid);
    }

    @Override
    public int updateBbsclick(Integer bbsid) {
        return bbsMapper.updateBbsclick(bbsid);
    }

    @Override
    public int deleteBbshot(Integer bbsid) {
        return bbsMapper.deleteBbshot(bbsid);
    }

    @Override
    public int deleteByBbsid(Integer bbsid) {
        return bbsMapper.deleteByBbsid(bbsid);
    }


}
