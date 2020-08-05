package com.cheng.service;

import com.cheng.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ICrowdFundingService {

    List<CrowdfundingType> getCrowdfundingType();

    List<CrowdfundingLabel> getCrowdfundingLabel();

    String uploadProjectTop(MultipartFile file, String filename);

    String uploadProjectDesc(MultipartFile file, String filename);

    boolean addCrowdFundingInfo(Projects projects);

    boolean addSponsorInformation(SponsorInformation sponsor);

    boolean addMyInitiate(MyInitiate myInitiate);

    boolean addCrowdFundingLabel(Map<String, Integer> map);

    boolean savePayBack(PayBack payBack);

    EasyPay selectEasyPay(EasyPay easyPay);

}
