package com.cheng.controller;

import com.cheng.pojo.EasyPay;
import com.cheng.pojo.PayBack;
import com.cheng.pojo.Projects;
import com.cheng.pojo.User;
import com.cheng.pojo.dto.IdList;
import com.cheng.service.ICrowdFundingService;
import com.cheng.service.IProjectService;
import com.cheng.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class PageController {

    @Resource
    private ICrowdFundingService crowdFundingService;

    @Resource
    private IProjectService projectService;

    @Resource
    private IUserService userService;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/member")
    public String toMember(HttpSession session, Model model) {
        User users = (User) session.getAttribute("users");
        model.addAttribute("user", users);
        return "member";
    }

    @GetMapping("/success")
    public String toSuccess(HttpSession session, Model model) {
        User users = (User) session.getAttribute("users");
        model.addAttribute("user", users);
        return "apply-3";
    }

    @GetMapping("/toStartPage")
    public String toStartPage(HttpSession session, Model model) {
        User users = (User) session.getAttribute("users");
        model.addAttribute("user", users);
        return "start";
    }

    @GetMapping("/toStartStepPage")
    public String toStartStepPage(HttpSession session, Model model) {
        User users = (User) session.getAttribute("users");
        model.addAttribute("user", users);
        model.addAttribute("type", crowdFundingService.getCrowdfundingType());
        model.addAttribute("label", crowdFundingService.getCrowdfundingLabel());
        return "start-step-1";
    }

    @GetMapping("/toStartStepPage2")
    public String toStartStepPage2(HttpSession session, Model model) {
        User users = (User) session.getAttribute("users");
        model.addAttribute("user", users);
        return "start-step-2";
    }

    @GetMapping("/toStartStepPage3")
    public String toStartStepPage3(HttpSession session, Model model) {
        User user = (User) session.getAttribute("users");
        model.addAttribute("user", user);
        EasyPay easyPay = userService.getUserEasyPay(1);
        model.addAttribute("easyPay", easyPay);
        return "start-step-3";
    }

    @GetMapping("/toStartStepPage4")
    public String toStartStepPage4(HttpSession session, Model model) {
        User users = (User) session.getAttribute("users");
        model.addAttribute("user", users);
        return "start-step-4";
    }

    @GetMapping("/project/{pid}")
    public String toProjectPage(@PathVariable int pid, Model model, HttpSession session) {
        System.out.println("pid = " + pid);
        Projects project = projectService.getProjectByPid(pid);
        model.addAttribute("pro", project);
        User user = (User) session.getAttribute("users");
        model.addAttribute("users", user);
        return "project";
    }

    @GetMapping("/toPayPage")
    @ResponseBody
    public boolean toPayStep1Page(HttpSession session, HttpServletRequest request) {
        int uid = Integer.parseInt(request.getParameter("uid"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        int pbid = Integer.parseInt(request.getParameter("pbid"));
        IdList idList = new IdList();
        idList.setPbid(pbid);
        idList.setPid(pid);
        idList.setUid(uid);
        System.out.println(idList);
        session.setAttribute("projectByPid", projectService.getProjectByPid(pid));
        session.setAttribute("payBack", projectService.getPayBack(pbid));
        session.setAttribute("userEasyPay", userService.getUserEasyPay(uid));
        return true;
    }

    @GetMapping("/toPayPage1")
    public String toPayPage1(HttpSession session, Model model) {
        Projects projects = (Projects) session.getAttribute("projectByPid");
        PayBack payBack = (PayBack) session.getAttribute("payBack");
        EasyPay easyPay = (EasyPay) session.getAttribute("userEasyPay");
        User user = (User) session.getAttribute("users");
        model.addAttribute("user", user);
        model.addAttribute("projects", projects);
        model.addAttribute("payBack", payBack);
        model.addAttribute("easyPay", easyPay);
        Object sponsorInfo = projectService.getSponsorInfo(projects.getPid());
        model.addAttribute("obj", sponsorInfo);
        return "pay-step-1";
    }

    @GetMapping("/toPayPage2")
    public String toPayPage2(HttpSession session, Model model) {
        Projects projects = (Projects) session.getAttribute("projectByPid");
        PayBack payBack = (PayBack) session.getAttribute("payBack");
        EasyPay easyPay = (EasyPay) session.getAttribute("userEasyPay");
        User user = (User) session.getAttribute("users");
        model.addAttribute("user", user);
        model.addAttribute("projects", projects);
        model.addAttribute("payBack", payBack);
        model.addAttribute("easyPay", easyPay);
        Object sponsorInfo = projectService.getSponsorInfo(projects.getPid());
        model.addAttribute("obj", sponsorInfo);
        String pay = (String) session.getAttribute("pay");
        int payNum = Integer.parseInt(pay);
        projectService.addMoney(projects.getPid(), payNum, user.getUid());
        return "pay-step-2";
    }

    @GetMapping("/zhifu")
    public String test() {
        return "zhifu";
    }

    @GetMapping("/reg   ")
    public String regPage() {
        return "reg";
    }

    @GetMapping("/index")
    public String toIndexPage() {
        return "index";
    }

    @GetMapping("/exit")
    public String exit(HttpServletRequest request) {
        Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }
        return "index";
    }

    @GetMapping("/reg")
    public String toRegPage(){
        return "reg";
    }

    @GetMapping("binding/{uid}")
    public String toBinding(@PathVariable int uid, HttpSession session, Model model){
        User users = (User) session.getAttribute("users");
        model.addAttribute("user", users);
        model.addAttribute("u", uid);
        return "binding";
    }

    @GetMapping("/projects")
    public String toProjectsPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("users");
        model.addAttribute("user", user);
        return "projects";
    }
}
