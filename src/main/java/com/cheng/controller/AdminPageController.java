package com.cheng.controller;

import com.cheng.pojo.Admin;
import com.cheng.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class AdminPageController {

    @GetMapping("/admin")
    public String toAdminPage(){
        return "admin";
    }

    @GetMapping("toEditPage/{uid}")
    public String toEditPage(@PathVariable int uid, Model model, HttpSession session){
        Admin user = (Admin) session.getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("uid",uid);
        return "edit";
    }

    @GetMapping("/main")
    public String toMain(Model model, HttpSession session){
        Admin user = (Admin) session.getAttribute("user");
        model.addAttribute("user",user);
        return "main";
    }

    @GetMapping("/add")
    public String toAddPage(Model model, HttpSession session){
        Admin user = (Admin) session.getAttribute("user");
        model.addAttribute("user",user);
        return "add";
    }

    @GetMapping("/auth_cert")
    public String toAuth_certPage(Model model, HttpSession session){
        Admin user = (Admin) session.getAttribute("user");
        model.addAttribute("user",user);
        return "auth_cert";
    }

    @GetMapping("/auth_project")
    public String toAuth_project(Model model, HttpSession session){
        Admin user = (Admin) session.getAttribute("user");
        model.addAttribute("user",user);
        return "auth_project";
    }
}
