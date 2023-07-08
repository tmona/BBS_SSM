package com.bbs.service;

import com.bbs.pojo.User;

import java.util.List;

public interface UserService {

     int addUser(User u);
     User selectUserByUsername(String username);
     User selectUserById(int id);

//     对用户名进行模糊查询

     List<User> searchUserByUsername(String username);

//     查询所有管理员用户

     List<User> getAllAdmin();

     boolean checkUser(String username,String userpass);

//     根据用户id修改用户名

     int updateUsername(Integer userid,String username);


//     更新用户的信息

     int updateUserInfo(User u);

//     管理员删除管理员用户

     int deleteAdminUser(Integer id);

//     添加管理员用户

     int insertAdminUser(String username,String userpass);

}
