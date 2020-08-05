package com.cheng.controller;

import com.cheng.pojo.*;
import com.cheng.service.IRealNameService;
import com.cheng.utils.QiniuUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class RealNameController {

    @Resource
    private IRealNameService realNameService;

    @GetMapping("/getRealNameType")
    public String getRealNameType(Model model, HttpSession session){
        List<RealNameAuthenticationType> realNameType = realNameService.getRealNameType();

        User users = (User) session.getAttribute("users");
        model.addAttribute("realNameType",realNameType);
        model.addAttribute("user",users);
        System.out.println(realNameType);
        return "accttype";
    }

    @GetMapping("/realNameAuthenticationTypeById/{rtid}")
    public String toRealNameTypePage(@PathVariable int rtid, HttpSession session, Model model){
        User users = (User) session.getAttribute("users");
        session.setAttribute("rtid", rtid);
        model.addAttribute("user",users);
        System.out.println(rtid);
        if (rtid == 1){
            return "student";
        }
        if (rtid == 2){
            return "tea";
        }
        else {
            return "patriarch";
        }
    }

    @PostMapping("/saveStuInfo")
    public String saveStuInfo(StuRealNameInfo stuRealNameInfo, HttpSession session, Model model){
        User users = (User) session.getAttribute("users");
        model.addAttribute("user",users);
        stuRealNameInfo.setUid(users.getUid());
        stuRealNameInfo.setRtid((int) session.getAttribute("rtid"));
        System.out.println(stuRealNameInfo);
        session.setAttribute("stu", stuRealNameInfo);
        return "apply-1";
    }

    @PostMapping("/saveTeaInfo")
    public String saveTeaInfo(TeaRealNameInfo teaRealNameInfo, HttpSession session, Model model){
        User users = (User) session.getAttribute("users");
        model.addAttribute("user",users);
        teaRealNameInfo.setRtid((int) session.getAttribute("rtid"));
        teaRealNameInfo.setUid(users.getUid());
        session.setAttribute("tea", teaRealNameInfo);
        return "apply-1";
    }

    @PostMapping("/savePatriarchInfo")
    public String savePatriarchInfo(PatriarchRealNameInfo patriarchRealNameInfo, HttpSession session, Model model){
        User users = (User) session.getAttribute("users");
        model.addAttribute("user",users);
        patriarchRealNameInfo.setRtid((int) session.getAttribute("rtid"));
        patriarchRealNameInfo.setUid(users.getUid());
        session.setAttribute("pat", patriarchRealNameInfo);
        return "apply-1";
    }

    @PostMapping("/upload")
    public String uploadArticlePicture(@RequestParam("file") MultipartFile file, HttpSession session, Model model) {
        String filename = file.getOriginalFilename();
        String s = realNameService.uploadArticlePicture(file, filename);
        session.setAttribute("fileName",s + filename);
        User users = (User) session.getAttribute("users");
        model.addAttribute("user",users);
        return "apply-2";
    }

    @GetMapping("/getEmailVerification")
    @ResponseBody
    public boolean getEmailVerification(HttpServletRequest request,HttpSession session){
        String email = request.getParameter("email");
        int random = (int) (Math.random()*1000000);
        String s = String.valueOf(random);
        session.setAttribute("random", s);
        boolean emailVerification = realNameService.getEmailVerification(email, s);
        System.out.println(emailVerification);
        return emailVerification;
    }
    @PostMapping("/saveRealNameInfo")
    @ResponseBody
    public boolean saveRealNameInfo(HttpSession session, Model model, HttpServletRequest request){

        boolean b = false;
        String authcode = request.getParameter("authcode");
        String random = (String) session.getAttribute("random");
        String fileName = (String) session.getAttribute("fileName");
        int rtid = (int) session.getAttribute("rtid");
        User users = (User) session.getAttribute("users");
        model.addAttribute("user",users);
        if (authcode.equals(random)){
            if (rtid == 1){
                StuRealNameInfo stu = (StuRealNameInfo) session.getAttribute("stu");
                stu.setPhoto(fileName);
                b = realNameService.saveStuRealNameInfo(stu);
            } else if (rtid == 2){
                TeaRealNameInfo tea = (TeaRealNameInfo) session.getAttribute("tea");
                tea.setPhoto(fileName);
                b = realNameService.saveTeaRealNameInfo(tea);
            } else {
                PatriarchRealNameInfo pat = (PatriarchRealNameInfo) session.getAttribute("pat");
                pat.setPhoto(fileName);
                b = realNameService.savePatRealNameInfo(pat);
            }
            if (b){
                return true;
            } else
                return false;

        } else
        return false;
    }
}
