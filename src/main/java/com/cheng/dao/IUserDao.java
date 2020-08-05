package com.cheng.dao;

import com.cheng.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserDao {

    User login(User user);

    List<MySupport> getAllMySupportByUid(int uid);

    StuRealNameInfo lookStuRealNameState(int uid);

    PatriarchRealNameInfo lookPatRealNameState(int uid);

    TeaRealNameInfo lookTeaRealNameState(int uid);

    EasyPay getUserEasyPay(int uid);

    List<MyInitiate> getMyInitiate(int uid);

    List<MyAttentions> getMyAttention(int uid);

    List<MySupport> getMySupper(int uid);

    int updateBalance(Map<String, Integer> map);

    int addMySupport(Map<String, Integer> map);

    List<User> getAllUserInfo(int num);

    int getUserOfNum();

    User getUserByUid(int uid);

    int editUser(User user);

    int deleteUserByUid(int uid);

    int addUser(User user);

    List<User> selectInfo(String info);

    Admin adminLogin(Admin admin);

    int toReg(User user);

    int saveEasyPay(EasyPay easypay);
}
