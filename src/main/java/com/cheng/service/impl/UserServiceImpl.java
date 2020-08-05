package com.cheng.service.impl;

import com.cheng.dao.IUserDao;
import com.cheng.pojo.*;
import com.cheng.service.IUserService;
import com.cheng.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<MySupport> getAllMySupportByUid(int uid) {
        List<MySupport> allMySupportByUid = userDao.getAllMySupportByUid(uid);
        for (MySupport projects : allMySupportByUid) {
            String ipimag = QiniuUtils.domain + projects.getProject().getIpimag();
            String ipdescimag = QiniuUtils.domain + projects.getProject().getIpdescimag();
            projects.getProject().setIpimag(ipimag);
            projects.getProject().setIpdescimag(ipdescimag);
            long format = new Date().getDay();
            System.out.println("num+" + format);// new Date()为获取当前系统时间
//            System.out.println("time="+millis);
            Date ipdate = projects.getProject().getIpdate();
            int ipday = projects.getProject().getIpday();
            System.out.println(ipday);
            long time = ipdate.getDay();
            System.out.println("numm2=" + time);
            long day2 = (time + ipday) - format;
            System.out.println(day2);
            if (day2 > 0) {
                projects.getProject().setDay((int) day2);
            } else {
                projects.getProject().setDay(0);
            }
//            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            double ipmoney = projects.getProject().getIpmoney();
            double money = projects.getMoney();
            int rate = (int) (money / ipmoney * 100);
            projects.getProject().setRate(rate);
            System.out.println("rate = " + rate);
        }
        return allMySupportByUid;
    }

    @Override
    public StuRealNameInfo lookStuRealNameState(int uid) {
        return userDao.lookStuRealNameState(uid);
    }

    @Override
    public PatriarchRealNameInfo lookPatRealNameState(int uid) {
        return userDao.lookPatRealNameState(uid);
    }

    @Override
    public TeaRealNameInfo lookTeaRealNameState(int uid) {
        return userDao.lookTeaRealNameState(uid);
    }

    @Override
    public EasyPay getUserEasyPay(int uid) {
        return userDao.getUserEasyPay(uid);
    }

    @Override
    public List<MySupport> getMySupper(int uid) {
        List<MySupport> mySupper = userDao.getMySupper(uid);
        for (MySupport mySupport : mySupper) {
            String ipimag = QiniuUtils.domain + mySupport.getProject().getIpimag();
            String ipdescimag = QiniuUtils.domain + mySupport.getProject().getIpdescimag();
            mySupport.getProject().setIpimag(ipimag);
            mySupport.getProject().setIpdescimag(ipdescimag);
            long format = new Date().getDay();
            System.out.println("num+" + format);// new Date()为获取当前系统时间
//            System.out.println("time="+millis);
            Date ipdate = mySupport.getProject().getIpdate();
            int ipday = mySupport.getProject().getIpday();
            System.out.println(ipday);
            long time = ipdate.getDay();
            System.out.println("numm2=" + time);
            long day2 = (time + ipday) - format;
            System.out.println(day2);
            if (day2 > 0) {
                mySupport.getProject().setDay((int) day2);
            } else {
                mySupport.getProject().setDay(0);
            }
//            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            double ipmoney = mySupport.getProject().getIpmoney();
            double money = mySupport.getMoney();
            int rate = (int) (money / ipmoney * 100);
            mySupport.getProject().setRate(rate);
            System.out.println("rate = " + rate);
        }
        return mySupper;
    }

    @Override
    public List<MyAttentions> getMyAttention(int uid) {
        return userDao.getMyAttention(uid);
    }

    @Override
    public List<MyInitiate> getMyInitiate(int uid) {
        return userDao.getMyInitiate(uid);
    }

    @Override
    public List<User> getAllUserInfo(int page) {
        int num;
        if (page == 1 || page <= 0){
            num = 0;
        } else {
            num = 10 * (page- 1);
        }

        return userDao.getAllUserInfo(num);
    }

    @Override
    public int getUserOfNum() {
        int userOfNum = userDao.getUserOfNum();
        int num = (userDao.getUserOfNum() % 10 == 0) ? userOfNum / 10: userOfNum / 10 + 1;
        return num;
    }

    @Override
    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    @Override
    public boolean editUser(User user) {
        return userDao.editUser(user) > 0;
    }

    @Override
    public boolean deleteUserByUid(int uid) {
        return userDao.deleteUserByUid(uid) > 0;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user) > 0;
    }

    @Override
    public List<User> selectInfo(String info) {
        return userDao.selectInfo(info);
    }

    @Override
    public Admin adminLogin(Admin admin) {
        return userDao.adminLogin(admin);
    }

    @Override
    public boolean toReg(User user) {
        return userDao.toReg(user) > 0;
    }

    @Override
    public boolean saveEasyPay(EasyPay easypay) {
        return userDao.saveEasyPay(easypay) > 0;
    }
}
