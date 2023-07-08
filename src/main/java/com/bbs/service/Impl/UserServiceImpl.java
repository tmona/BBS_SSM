package com.bbs.service.Impl;

import com.bbs.mapper.UserMapper;
import com.bbs.pojo.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User u) {
      //  System.out.println(u+"service");
        return userMapper.addUser(u);
    }

    @Override
    public  User selectUserByUsername(String username) {
       return userMapper.selectUserByUsername(username);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> searchUserByUsername(String username) {
        return userMapper.searchUserByUsername(username);
    }

    @Override
    public List<User> getAllAdmin() {
        return userMapper.getAllAdmin();
    }

    @Override
    public boolean checkUser(String username, String userpass) {
        User user=userMapper.selectUserByUsername(username);
        return user!=null&&user.getUserpass().equals(userpass);
    }

    @Override
    public int updateUsername(Integer userid, String username) {
        return userMapper.updateUsername(userid,username);
    }

    @Override
    public int updateUserInfo(User u) {
        return userMapper.updateUserInfo(u);
    }

    @Override
    public int deleteAdminUser(Integer id) {
        return userMapper.deleteAdminUser(id);
    }

    @Override
    public int insertAdminUser(String username, String userpass) {
        return userMapper.insertAdminUser(username,userpass);
    }


}
