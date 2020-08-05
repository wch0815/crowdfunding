package com.cheng.dao;

import com.cheng.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRealNameDao {

    List<RealNameAuthenticationType> getRealNameType();

    int saveStuRealNameInfo(StuRealNameInfo stuRealNameInfo);

    int saveTeaRealNameInfo(TeaRealNameInfo teaRealNameInfo);

    int savePatRealNameInfo(PatriarchRealNameInfo patriarchRealNameInfo);

    List<StuRealNameInfo> getStuRealNameInfos();

    List<TeaRealNameInfo> getTeaRealNameInfos();

    List<PatriarchRealNameInfo> getPatriarchRealNameInfos();

    int toAuditOfStu(int rtid);

    int toAuditOfTea(int rtid);

    int toAuditOfPat(int rtid);

    RealNameInfo selectRealNameInfo(RealNameInfo r);

    int toAuditFailureOfStu(int udid);

    int toAuditFailureOfTea(int udid);

    int toAuditFailureOfPat(int udid);
}
