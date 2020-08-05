package com.cheng.service.impl;

import com.cheng.dao.IProjectDao;
import com.cheng.dao.IUserDao;
import com.cheng.pojo.*;
import com.cheng.service.IProjectService;
import com.cheng.utils.QiniuUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private IProjectDao projectDao;

    @Resource
    private IUserDao userDao;

    @Override
    public List<Projects> getProjects() {
        List<Projects> projects = projectDao.getProjects();
        for (Projects project : projects) {
            String ipimag = QiniuUtils.domain + project.getIpimag();
            String ipdescimag = QiniuUtils.domain + project.getIpdescimag();
            project.setIpimag(ipimag);
            project.setIpdescimag(ipdescimag);
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            long format = new Date().getDay();
            System.out.println("num+" + format);// new Date()为获取当前系统时间
//            System.out.println("time="+millis);
            Date ipdate = project.getIpdate();
            int ipday = project.getIpday();
            System.out.println(ipday);
            long time = ipdate.getDay();
            System.out.println("numm2=" + time);
            long day2 = format - time;
            System.out.println(day2);
            if (day2 > 0) {
                project.setDay((int) day2);
            } else {
                project.setDay(0);
            }
//            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            double ipmoney = project.getIpmoney();
            double money = project.getMoney();
            int rate = (int) (money / ipmoney * 100);
            project.setRate(rate);
            System.out.println("rate = " + rate);
        }
        System.out.println("-=-=-=-=-=-=");
        System.out.println(projects);
        return projects;
    }

    @Override
    public List<Projects> getAllProject() {
        List<Projects> allProject = projectDao.getAllProject();
        for (Projects projects : allProject) {
            String ipimag = QiniuUtils.domain + projects.getIpimag();
            String ipdescimag = QiniuUtils.domain + projects.getIpdescimag();
            projects.setIpimag(ipimag);
            projects.setIpdescimag(ipdescimag);
            long format = new Date().getDay();
            System.out.println("num+" + format);// new Date()为获取当前系统时间
//            System.out.println("time="+millis);
            Date ipdate = projects.getIpdate();
            int ipday = projects.getIpday();
            System.out.println(ipday);
            long time = ipdate.getDay();
            System.out.println("numm2=" + time);
            long day2 = (time + ipday) - format;
            System.out.println(day2);
            if (day2 > 0) {
                projects.setDay((int) day2);
            } else {
                projects.setDay(0);
            }
//            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            double ipmoney = projects.getIpmoney();
            double money = projects.getMoney();
            int rate = (int) (money / ipmoney * 100);
            projects.setRate(rate);
            System.out.println("rate = " + rate);
        }
        System.out.println("/*/*/*/*/*/*/*/");
        System.out.println(allProject);
        return allProject;
    }

    @Override
    public Projects getProjectByPid(int pid) {
        Projects projectByPid = projectDao.getProjectByPid(pid);
        String ipimag = QiniuUtils.domain + projectByPid.getIpimag();
        String ipdescimag = QiniuUtils.domain + projectByPid.getIpdescimag();
        projectByPid.setIpimag(ipimag);
        projectByPid.setIpdescimag(ipdescimag);
        long format = new Date().getDay();
        System.out.println("num+" + format);// new Date()为获取当前系统时间
//            System.out.println("time="+millis);
        Date ipdate = projectByPid.getIpdate();
        int ipday = projectByPid.getIpday();
        System.out.println(ipday);
        long time = ipdate.getDay();
        System.out.println("numm2=" + time);
        long day2 = (time + ipday) - format;
        System.out.println(day2);
        if (day2 > 0) {
            projectByPid.setDay((int) day2);
        } else {
            projectByPid.setDay(0);
        }
//            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        double ipmoney = projectByPid.getIpmoney();
        double money = projectByPid.getMoney();
        int rate = (int) (money / ipmoney * 100);
        projectByPid.setRate(rate);
        System.out.println("rate = " + rate);
        return projectByPid;
    }

    @Override
    public List<PayBack> getPayBackByPid(int pid) {
        List<PayBack> payBackByPid = projectDao.getPayBackByPid(pid);
        for (PayBack payBack : payBackByPid) {
            String pbimag = QiniuUtils.domain + payBack.getPbimag();
            payBack.setPbimag(pbimag);
        }
        return payBackByPid;
    }

    @Override
    public Object getSponsorInfo(int pid) {
        StuRealNameInfo stuRealNameInfo = projectDao.getStuRealNameInfo(pid);
        TeaRealNameInfo teaRealNameInfo = projectDao.getTeaRealNameInfo(pid);
        PatriarchRealNameInfo patriarchRealNameInfo = projectDao.getPatriarchRealNameInfo(pid);
        if (stuRealNameInfo != null)
            return stuRealNameInfo;
        if (teaRealNameInfo != null)
            return teaRealNameInfo;
        if (patriarchRealNameInfo != null)
            return patriarchRealNameInfo;
        return null;
    }

    @Override
    @Transactional
    public boolean saveAttention(int pid, int uid) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pid", pid);
        map.put("uid", uid);
        MyAttentions myAttentions = projectDao.getMyAttentions(map);
        if (myAttentions == null) {
            boolean b = projectDao.addProjectAttention(pid) > 0;
            boolean f = projectDao.saveAttention(map) > 0;
            if (b && f)
                return true;
        } else {
            boolean b = projectDao.subtractionProjectAttention(pid) > 0;
            boolean f = projectDao.deleteAttention(map) > 0;
            if (b && f)
                return false;
        }
        return false;
    }

    @Override
    public MyAttentions getAttention(int uid, int pid) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pid", pid);
        map.put("uid", uid);
        return projectDao.getMyAttentions(map);
    }

    @Override
    public PayBack getPayBack(int pbid) {
        return projectDao.getPayBack(pbid);
    }

    @Override
    @Transactional
    public boolean addMoney(int pid, int payNum, int uid) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pid", pid);
        map.put("paynum", payNum);
        map.put("uid",uid);
        boolean b = projectDao.addMoney(map) > 0;
        boolean b1 =  userDao.updateBalance(map) > 0;
        boolean b2 = userDao.addMySupport(map) > 0;
        if (b && b1)
            return true;
        else
            return false;
    }

    @Override
    public List<Projects> getProject() {
        List<Projects> project = projectDao.getProject();
        for (Projects projects : project) {
            String ipimag = QiniuUtils.domain + projects.getIpimag();
            String ipdescimag = QiniuUtils.domain + projects.getIpdescimag();
            projects.setIpimag(ipimag);
            projects.setIpdescimag(ipdescimag);
            long format = new Date().getDay();
            Date ipdate = projects.getIpdate();
            int ipday = projects.getIpday();
            long time = ipdate.getDay();
            long day2 = (time + ipday) - format;
            if (day2 > 0) {
                projects.setDay((int) day2);
            } else {
                projects.setDay(0);
            }
            double ipmoney = projects.getIpmoney();
            double money = projects.getMoney();
            int rate = (int) (money / ipmoney * 100);
            projects.setRate(rate);
        }
        return project;
    }

    @Override
    public Projects getProByPid(int pid) {
        Projects projectByPid = projectDao.getProByPid(pid);
        String ipimag = QiniuUtils.domain + projectByPid.getIpimag();
        String ipdescimag = QiniuUtils.domain + projectByPid.getIpdescimag();
        List<PayBack> payBack = projectByPid.getPayBack();
        for (PayBack back : payBack) {
            String pbimag = QiniuUtils.domain + back.getPbimag();
            back.setPbimag(pbimag);
        }
        projectByPid.setIpimag(ipimag);
        projectByPid.setIpdescimag(ipdescimag);
        long format = new Date().getDay();
        Date ipdate = projectByPid.getIpdate();
        int ipday = projectByPid.getIpday();
        long time = ipdate.getDay();
        long day2 = (time + ipday) - format;
        if (day2 > 0) {
            projectByPid.setDay((int) day2);
        } else {
            projectByPid.setDay(0);
        }
        double ipmoney = projectByPid.getIpmoney();
        double money = projectByPid.getMoney();
        int rate = (int) (money / ipmoney * 100);
        projectByPid.setRate(rate);
        return projectByPid;
    }

    @Override
    public boolean authProject(int pid) {
        return projectDao.authProject(pid) > 0;
    }
}
