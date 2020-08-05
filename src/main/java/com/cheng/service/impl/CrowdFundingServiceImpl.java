package com.cheng.service.impl;

import com.cheng.dao.ICrowdFundingDao;
import com.cheng.pojo.*;
import com.cheng.service.ICrowdFundingService;
import com.cheng.utils.QiniuUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CrowdFundingServiceImpl implements ICrowdFundingService {

    @Resource
    private ICrowdFundingDao crowdFundingDao;

    @Override
    public List<CrowdfundingType> getCrowdfundingType() {
        return crowdFundingDao.getCrowdfundingType();
    }

    @Override
    public List<CrowdfundingLabel> getCrowdfundingLabel() {
        return crowdFundingDao.getCrowdfundingLabel();
    }

    @Override
    public String uploadProjectTop(MultipartFile file, String filename) {
        String filepath = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            QiniuUpload.updateFile(file, filepath + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filepath;
    }

    @Override
    public String uploadProjectDesc(MultipartFile file, String filename) {
        String filepath = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            QiniuUpload.updateFile(file, filepath + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filepath;
    }

    @Override
    public boolean addCrowdFundingInfo(Projects projects) {
        return crowdFundingDao.addCrowdFundingInfo(projects) > 0;
    }

    @Override
    public boolean addSponsorInformation(SponsorInformation sponsor) {
        return crowdFundingDao.addSponsorInformation(sponsor) > 0;
    }

    @Override
    public boolean addMyInitiate(MyInitiate myInitiate) {
        return crowdFundingDao.addMyInitiate(myInitiate) > 0;
    }

    @Override
    public boolean addCrowdFundingLabel(Map<String, Integer> map) {
        return crowdFundingDao.addCrowdFundingLabel(map) > 0;
    }

    @Override
    public boolean savePayBack(PayBack payBack) {
        return crowdFundingDao.savePayBack(payBack) > 0;
    }

    @Override
    public EasyPay selectEasyPay(EasyPay easyPay) {
        return crowdFundingDao.selectEasyPay(easyPay);
    }
}
