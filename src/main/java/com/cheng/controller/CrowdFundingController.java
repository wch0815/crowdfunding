package com.cheng.controller;

import com.cheng.pojo.*;
import com.cheng.service.ICrowdFundingService;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CrowdFundingController {

    @Resource
    private ICrowdFundingService crowdFundingService;

    @GetMapping("/getCrowdFundingType")
    @ResponseBody
    public List<CrowdfundingType> getCrowdfundingType() {
        return crowdFundingService.getCrowdfundingType();
    }

    @GetMapping("/getCrowdfundingLabel")
    @ResponseBody
    public List<CrowdfundingLabel> getCrowdfundingLabel() {
        return crowdFundingService.getCrowdfundingLabel();
    }

    @PostMapping("/uploadTop")
    @ResponseBody
    public void uploadTop(MultipartFile file, HttpSession session) {
        String filename = file.getOriginalFilename();
        System.out.println(file);
        String s = crowdFundingService.uploadProjectTop(file, filename);

        session.setAttribute("ipimag", s + filename);
    }

    @PostMapping("/uploadDesc")
    @ResponseBody
    public void uploadDesc(@RequestParam("file") MultipartFile file, HttpSession session) {
        String filename = file.getOriginalFilename();
        String s = crowdFundingService.uploadProjectDesc(file, filename);
        session.setAttribute("ipimagdesc", s + filename);
    }

    @PostMapping("/addCrowdfundingProject")
    @ResponseBody
    public boolean addCrowdfundingProject(@RequestBody JavaBean data, HttpSession session) {
        System.out.println(data);
        session.setAttribute("data", data);
        return true;
    }

    @PostMapping("/savePayBack")
    @ResponseBody
    public boolean savePayBack(@RequestBody PayBack payBack, HttpSession session) {
        String ipimagdesc = (String) session.getAttribute("ipimagdesc");
        payBack.setPbimag(ipimagdesc);
        session.setAttribute("payBack", payBack);
        return true;
    }

    @PostMapping("/submitEasyPay")
    @ResponseBody
    public boolean submitEasyPay(@RequestBody EasyPay easyPay, HttpSession session) {
        PayBack payBack = (PayBack) session.getAttribute("payBack");
        JavaBean data = (JavaBean) session.getAttribute("data");
        String ipimag = (String) session.getAttribute("ipimag");
        String ipimagdesc = (String) session.getAttribute("ipimagdesc");
        Projects project = data.getProject();
        project.setIpimag(ipimag);
        project.setIpdescimag(ipimagdesc);
        project.setReminders("sda");
        SponsorInformation sponsor = data.getSponsor();
        Integer[] label = data.getLabel();
        boolean b = crowdFundingService.addCrowdFundingInfo(project);
        if (b) {
            int pid = project.getPid();
            System.out.println(pid);
            sponsor.setPid(pid);
            crowdFundingService.addSponsorInformation(sponsor);
            User user = (User) session.getAttribute("users");
            MyInitiate myInitiate = new MyInitiate();
            myInitiate.setPid(pid);
            myInitiate.setUid(user.getUid());
            crowdFundingService.addMyInitiate(myInitiate);
            payBack.setPid(pid);
            boolean flag = crowdFundingService.savePayBack(payBack);
            for (Integer clid : label) {
                Map<String, Integer> map = new HashMap<>();
                map.put("pid", pid);
                map.put("clid", clid);
                crowdFundingService.addCrowdFundingLabel(map);
            }
            EasyPay easyPay1 = crowdFundingService.selectEasyPay(easyPay);
            if (easyPay1 != null) {
                return true;
            } else
                return false;
        }
        return false;
    }
}
