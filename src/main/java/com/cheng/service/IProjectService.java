package com.cheng.service;

import com.cheng.pojo.CrowdfundingType;
import com.cheng.pojo.MyAttentions;
import com.cheng.pojo.PayBack;
import com.cheng.pojo.Projects;

import java.util.List;

public interface IProjectService {

    List<Projects> getProjects();

    List<Projects> getAllProject();

    Projects getProjectByPid(int pid);

    List<PayBack> getPayBackByPid(int pid);

    Object getSponsorInfo(int pid);

    boolean saveAttention(int pid, int uid);

    MyAttentions getAttention(int uid, int pid);

    PayBack getPayBack(int pbid);

    boolean addMoney(int pid, int payNum, int uid);

    List<Projects> getProject();

    Projects getProByPid(int pid);

    boolean authProject(int pid);
}
