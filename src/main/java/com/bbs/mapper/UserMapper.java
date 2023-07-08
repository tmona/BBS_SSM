package com.bbs.mapper;

import com.bbs.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

//    添加用户
    int addUser(User u);

//  通过username查询用户
    User selectUserByUsername(String username);

//    通过id查询用户
    User selectUserById(int id);


    int updateUsername(@Param("userid")Integer userid,@Param("username")String username);

    int updateUserInfo(@Param("u")User u);

    List<User> getAllAdmin();

    int deleteAdminUser(Integer id);

    int insertAdminUser(@Param("username")String username,@Param("userpass") String userpass);

    List<User> searchUserByUsername(String username);
}
