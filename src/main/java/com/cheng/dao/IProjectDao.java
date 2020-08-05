package com.cheng.dao;

import com.cheng.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IProjectDao {

    List<Projects> getProjects();

    List<Projects> getAllProject();

    Projects getProjectByPid(int pid);

    List<PayBack> getPayBackByPid(int pid);

    StuRealNameInfo getStuRealNameInfo(int pid);

    TeaRealNameInfo getTeaRealNameInfo(int pid);

    PatriarchRealNameInfo getPatriarchRealNameInfo(int pid);

    int saveAttention(Map<String, Integer> map);

    int addProjectAttention(int pid);

    MyAttentions getMyAttentions(Map<String,Integer> map);

    int subtractionProjectAttention(int pid);

    int deleteAttention(Map<String, Integer> map);

    PayBack getPayBack(int pbid);

    int addMoney(Map<String, Integer> map);

    List<Projects> getProject();

    Projects getProByPid(int pid);

    int authProject(int pid);
}
