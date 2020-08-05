package com.cheng.controller;

import com.cheng.pojo.Admin;
import com.cheng.pojo.User;
import com.cheng.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminUserController {

    @Resource
    private IUserService userService;

    @PostMapping("/adminLogin")
    public String adminLogin(Admin admin, Model model, HttpSession session){
        Admin user = userService.adminLogin(admin);
        session.setAttribute("user",user);
        model.addAttribute("user",user);
        if (user != null)
            return "main";
        else
            return "admin";
    }

    @PostMapping("/getAllUserInfo/{page}")
    @ResponseBody
    public List<User> getAllUserInfo(@PathVariable int page){
        System.out.println(page);
        return userService.getAllUserInfo(page);
    }

    @GetMapping("/getUserOfNum")
    @ResponseBody
    public int getUserOfNum(){
        return userService.getUserOfNum();
    }

    @GetMapping("/getUserByUid/{uid}")
    @ResponseBody
    public User getUserByUid(@PathVariable int uid){
        return userService.getUserByUid(uid);
    }

    @PostMapping("/editUser")
    @ResponseBody
    public boolean editUser(@RequestBody User user){
        return userService.editUser(user);
    }

    @GetMapping("/deleteUserByUid/{uid}")
    @ResponseBody
    public boolean deleteUserByUid(@PathVariable int uid){
        return userService.deleteUserByUid(uid);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public boolean addUser(@RequestBody User user){
        System.out.println(user);
        return userService.addUser(user);
    }

    @GetMapping("/selectInfo/{info}")
    @ResponseBody
    public List<User> selectInfo(@PathVariable String info){
        System.out.println(info);
        return userService.selectInfo(info);
    }

}
