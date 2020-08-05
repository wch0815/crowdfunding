package com.cheng.service;

import com.cheng.pojo.*;

import java.util.List;

public interface IUserService {

    User login(User user);

    List<MySupport> getAllMySupportByUid(int uid);

    StuRealNameInfo lookStuRealNameState(int uid);

    PatriarchRealNameInfo lookPatRealNameState(int uid);

    TeaRealNameInfo lookTeaRealNameState(int uid);

    EasyPay getUserEasyPay(int uid);

    List<MySupport> getMySupper(int uid);

    List<MyAttentions> getMyAttention(int uid);

    List<MyInitiate> getMyInitiate(int uid);

    List<User> getAllUserInfo(int page);

    int getUserOfNum();

    User getUserByUid(int uid);

    boolean editUser(User user);

    boolean deleteUserByUid(int uid);

    boolean addUser(User user);

    List<User> selectInfo(String info);

    Admin adminLogin(Admin admin);

    boolean toReg(User user);

    boolean saveEasyPay(EasyPay easypay);
}
