package com.bbs.controller;


import com.bbs.pojo.Board;
import com.bbs.pojo.User;
import com.bbs.service.BoardService;
import com.bbs.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;


    @RequestMapping("/register")
    public String register(){
        return "register";
    }

//
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//   游客首页登录
    @RequestMapping(value="/login",method={RequestMethod.POST},params={"username","userpass"})
    public String consumerLogin(String username,String userpass,String consumer,HttpSession session,Model model){

        if(consumer.equals("1")){
            return "consumer";
        }
        if(userService.checkUser(username,userpass)){
            User u=userService.selectUserByUsername(username);
            List<Board> board=boardService.selectAllBoard();
            session.setAttribute("user",u);
            session.setAttribute("board",board);
            if(u.getUsertype().equals("0")){
                model.addAttribute("user",u);
                return "redirect:/admin";
            }
            else{
                model.addAttribute("user",u);
                return "redirect:/consumer";
            }
        }
        else{
            model.addAttribute("error", "*用户名或密码错误，请重新输入");
            return "login";
        }
    }



    @RequestMapping("/admin")
    public String admin(){

        return "admin";
    }
    //    管理员页面多个子页面
    @RequestMapping("/header.html")
    public String header(){
        return "header";
    }
    @RequestMapping("/left.html")
    public String left(){
        return "left";
    }
    @RequestMapping("/right.html")
    public String right(){
        return "right";
    }






//    注册用户,注册之后，返回首页进行登录
    @ResponseBody
    @RequestMapping("/addUser")
    public ResponseEntity<?> addUser(String username,String pwd){
        User u=new User();
        User u2=userService.selectUserByUsername(username);
        Map<String, Object> responseMap = new HashMap<>();
//        用户名已存在
        if(u2!=null){
            responseMap.put("error","用户名已存在");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
        else{
            u.setUsername(username);
            u.setUserpass(pwd);
//        System.out.println(username+"-----------"+pwd);
            userService.addUser(u);
            responseMap.put("success",true);
            return ResponseEntity.ok(responseMap);
        }

    }

    @RequestMapping("/editPersonalnfo")
    public String editPersonalnfo(){
        return "editPersonalnfo";
    }

//    提交修改的个人信息

    @RequestMapping("/personalnfo/updatepersonInfo/{id}")
    public String updatePersonInfo(@PathVariable("id")Integer id,User user,HttpSession session){
        user.setId(id);
        userService.updateUserInfo(user);
        User u=userService.selectUserById(id);
        session.setAttribute("user",u);
        return "redirect:/personalnfo";
    }

//    管理员管理用户，显示所有管理员用户
    @RequestMapping("/adminUserManage")
    public String adminUserManage(Model model){
        List<User> users=userService.getAllAdmin();
        model.addAttribute("adminUserList",users);
        return "adminUserManage";
    }

//    管理员删除管理员用户，

    @RequestMapping(value = "/adminuser/delete/{id}",method = {RequestMethod.DELETE})
    public String deleteAdminUser(@PathVariable("id")Integer id){
        userService.deleteAdminUser(id);
        return "redirect:/adminUserManage";
    }


//修改管理员密码

    @RequestMapping(value="/adminuser/update/{userId}",method = {RequestMethod.POST})
    public ResponseEntity<?> updateAdminUser(@PathVariable("userId")Integer userid,String userpass){
        User u=userService.selectUserById(userid);
        u.setUserpass(userpass);
        userService.updateUserInfo(u);
        Map<String,Object> response=new HashMap<>();
        response.put("success",true);
         return ResponseEntity.ok(response);
    }



//    添加管理员用户

    @RequestMapping(value = "/adminuser/adduser/",method = {RequestMethod.POST})
    public String addAdminUser(String username,String userpass){
        userService.insertAdminUser(username,userpass);
        return "redirect:/adminUserManage";
    }

//搜索用户

    @RequestMapping("/searchUser")
    public String searchUser(String content,Model model){

        List<User> u=userService.searchUserByUsername(content);
        model.addAttribute("users",u);
        return "searchUser";
    }

}
