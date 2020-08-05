package com.cheng.dao;

import com.cheng.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ICrowdFundingDao {

    List<CrowdfundingType> getCrowdfundingType();

    List<CrowdfundingLabel> getCrowdfundingLabel();

    int addCrowdFundingInfo(Projects projects);

    int addSponsorInformation(SponsorInformation sponsor);

    int addMyInitiate(MyInitiate myInitiate);

    int addCrowdFundingLabel(Map<String, Integer> map);

    int savePayBack(PayBack payBack);

    EasyPay selectEasyPay(EasyPay easyPay);
}
