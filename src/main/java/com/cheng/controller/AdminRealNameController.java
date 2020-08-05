package com.cheng.controller;

import com.cheng.pojo.RealNameInfo;
import com.cheng.service.IRealNameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminRealNameController {

    @Resource
    private IRealNameService realNameService;

    @GetMapping("/getAllRealNameInfo")
    @ResponseBody
    public List<Object> getAllRealNameInfo(){
        return realNameService.getAllRealNameInfo();
    }

    @PostMapping("/toAudit")
    @ResponseBody
    public boolean toAudit(@RequestBody RealNameInfo r){
        return realNameService.toAudit(r);
    }
}
