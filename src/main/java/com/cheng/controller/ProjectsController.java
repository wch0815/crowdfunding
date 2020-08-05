package com.cheng.controller;


import com.alipay.api.AlipayApiException;
import com.cheng.bean.AlipayBean;
import com.cheng.pojo.*;
import com.cheng.pojo.dto.IdList;
import com.cheng.service.ICrowdFundingService;
import com.cheng.service.IProjectService;
import com.cheng.service.IUserService;
import com.cheng.service.PayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectsController {

    @Resource
    private PayService payService;//调用支付服务​

    @Resource
    private IProjectService projectService;

    @Resource
    private ICrowdFundingService crowdFundingService;

    @Resource
    private IUserService userService;

    @GetMapping("/getProjects")
    @ResponseBody
    public List<Projects> getProjects(){
        List<Projects> projects = projectService.getProjects();
        System.out.println(projects);
        return projects;
    }

    @GetMapping("/getAllProjects")
    @ResponseBody
    public List<Projects> getAllProjects(){
        return projectService.getAllProject();
    }

    @GetMapping("/getType")
    @ResponseBody
    public List<CrowdfundingType> getType(){
        return crowdFundingService.getCrowdfundingType();
    }

    @GetMapping("/getProjectByPid/{pid}")
    @ResponseBody
    public int getProjectByPid(@PathVariable int pid, Model model, HttpSession session){
        User user = (User) session.getAttribute("users");
        System.out.println(user);
        if (user != null)
            return 0;
        else
            return 1;
    }

    @GetMapping("/getPayBackByPid/{pid}")
    @ResponseBody
    public List<PayBack> getPayBackByPid(@PathVariable int pid){
        return projectService.getPayBackByPid(pid);
    }

    @GetMapping("/getSponsorInfo/{pid}")
    @ResponseBody
    public Object getSponsorInfo(@PathVariable int pid){
        return projectService.getSponsorInfo(pid);
    }

    @PostMapping("/pagelogin")
    @ResponseBody
    public User login(@RequestBody User user, Model model, HttpSession session){
        System.out.println(user);
        User login = userService.login(user);
        session.setAttribute("users",login);
        System.out.println(login);
        return login;
    }

    @GetMapping("/saveAttention/{pid}")
    @ResponseBody
    public boolean saveAttention(@PathVariable int pid,HttpServletRequest request){
        System.out.println(pid);
        String suid = request.getParameter("uid");
        int uid = Integer.parseInt(suid);
        System.out.println("uid = "+uid);
        boolean b = projectService.saveAttention(pid, uid);
        System.out.println("boolean = " + b);
        return b;
    }

    @GetMapping("/getProject/{pid}")
    @ResponseBody
    public Projects getProject(@PathVariable int pid){
        Projects projectByPid = projectService.getProjectByPid(pid);
        System.out.println(projectByPid);
        return projectByPid;
    }

    @GetMapping("/getMyAttention/{uid}")
    @ResponseBody
    public MyAttentions getMyAttention(@PathVariable int uid, HttpServletRequest request){
        String spid = request.getParameter("pid");
        int pid = Integer.parseInt(spid);
        return projectService.getAttention(uid, pid);
    }

    @GetMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(HttpSession session){
        User user = (User) session.getAttribute("users");
        System.out.println("user = "+user);
        return user;
    }

    @PostMapping("/toPay")
    @ResponseBody
    public String toPay(String out_trade_no, String subject, String total_amount, String body) throws AlipayApiException {
        return  payService.aliPay(new AlipayBean()
                .setBody(body)
                .setOut_trade_no(out_trade_no)
                .setTotal_amount(new StringBuffer().append(total_amount))
                .setSubject(subject));
    }
}
