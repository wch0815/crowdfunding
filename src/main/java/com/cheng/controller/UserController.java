package com.cheng.controller;

import com.cheng.pojo.*;
import com.cheng.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session){
        System.out.println(user);
        User login = userService.login(user);
        System.out.println(login);
        if (login!=null){
            model.addAttribute("user",login);
            session.setAttribute("users",login);
            return "member";
        }else {
            return "login";
        }
    }

    @PostMapping("/toReg")
    public String toReg(User user, Model model, HttpSession session){
        boolean b = userService.toReg(user);
        if (b){
            System.out.println(user);
            model.addAttribute("user",user);
            session.setAttribute("users",user);
        }
        return "member";
    }

    @GetMapping("/minecrowdfunding/{uid}")
    public String getAllInitiateProjects(@PathVariable int uid, Model model, HttpSession session){

        System.out.println(uid);
        User user = (User) session.getAttribute("users");
        System.out.println(user);
        model.addAttribute("user",user);
        model.addAttribute("support",userService.getMySupper(uid));
        List<MyInitiate> myInitiate = userService.getMyInitiate(uid);
        List<Projects> project = new ArrayList<>();
        for (MyInitiate initiate : myInitiate) {
            List<Projects> project1 = initiate.getProject();
            for (Projects projects : project1) {
                project.add(projects);
            }
        }
        System.out.println(project);
        model.addAttribute("pro",project);
        model.addAttribute("attention",userService.getMyAttention(uid));
        return "minecrowdfunding";
    }

    @GetMapping("/lookRealNameState/{uid}")
    @ResponseBody
    public Object lookRealNameState(@PathVariable int uid){
        System.out.println(uid);
        PatriarchRealNameInfo patriarchRealNameInfo = userService.lookPatRealNameState(uid);
        System.out.println(patriarchRealNameInfo);
        StuRealNameInfo stuRealNameInfo = userService.lookStuRealNameState(uid);
        System.out.println(stuRealNameInfo);
        TeaRealNameInfo teaRealNameInfo = userService.lookTeaRealNameState(uid);
        System.out.println(teaRealNameInfo);
        if (stuRealNameInfo != null)
            return stuRealNameInfo;
        if (patriarchRealNameInfo != null)
            return patriarchRealNameInfo;
        if (teaRealNameInfo != null)
            return teaRealNameInfo;
        else
            return null;
    }

    @GetMapping("/looEasyPayInfo/{uid}")
    @ResponseBody
    public EasyPay looEasyPayInfo(@PathVariable int uid){
        EasyPay userEasyPay = userService.getUserEasyPay(uid);
        System.out.println(userEasyPay);
        return userEasyPay;
    }

    @PostMapping("/saveEasyPay")
    @ResponseBody
    public boolean saveEasyPay(@RequestBody EasyPay easypay){
        System.out.println(easypay);
        boolean flag = userService.saveEasyPay(easypay);
        return flag;
    }


}
