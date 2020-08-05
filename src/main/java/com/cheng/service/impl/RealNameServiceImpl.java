package com.cheng.service.impl;

import com.cheng.dao.IRealNameDao;
import com.cheng.pojo.*;
import com.cheng.service.IRealNameService;
import com.cheng.utils.EmailUtils;
import com.cheng.utils.QiniuUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RealNameServiceImpl implements IRealNameService {

    @Resource
    private IRealNameDao realNameDao;

    @Override
    public List<RealNameAuthenticationType> getRealNameType() {
        return realNameDao.getRealNameType();
    }

    @Override
    public String uploadArticlePicture(MultipartFile file, String filename) {
        String filepath = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            QiniuUpload.updateFile(file, filepath + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filepath;
    }

    @Override
    public boolean saveStuRealNameInfo(StuRealNameInfo stuRealNameInfo) {
        return realNameDao.saveStuRealNameInfo(stuRealNameInfo) > 0;
    }

    @Override
    public boolean saveTeaRealNameInfo(TeaRealNameInfo teaRealNameInfo) {
        return realNameDao.saveTeaRealNameInfo(teaRealNameInfo) > 0;
    }

    @Override
    public boolean savePatRealNameInfo(PatriarchRealNameInfo patriarchRealNameInfo) {
        return realNameDao.savePatRealNameInfo(patriarchRealNameInfo) > 0;
    }

    @Override
    public boolean getEmailVerification(String email, String s) {

        return EmailUtils.sendMail(email, "验证码", s);

    }

    @Override
    public List<Object> getAllRealNameInfo() {
        List<Object> list = new ArrayList<>();
        List<StuRealNameInfo> stuRealNameInfos = realNameDao.getStuRealNameInfos();
        List<TeaRealNameInfo> teaRealNameInfos = realNameDao.getTeaRealNameInfos();
        List<PatriarchRealNameInfo> patriarchRealNameInfos = realNameDao.getPatriarchRealNameInfos();
        if (stuRealNameInfos.size() != 0) {
            list.add(stuRealNameInfos);
        }
        if (teaRealNameInfos.size() != 0) {
            list.add(teaRealNameInfos);
        }
        if (patriarchRealNameInfos.size() != 0) {
            list.add(patriarchRealNameInfos);
        }
        return list;
    }

    @Transactional
    @Override
    public boolean toAudit(RealNameInfo r) {
        /*
        rtid有三个值，1：表示是学生，2：表示是老师，3：表示是家长
         */
        System.out.println(r);
        RealNameInfo realNameInfo = realNameDao.selectRealNameInfo(r);
        if (realNameInfo != null) {

            if (r.getRtid() == 1) {
                return realNameDao.toAuditOfStu(r.getUdid()) > 0;
            } else if (r.getRtid() == 2) {
                return realNameDao.toAuditOfTea(r.getUdid()) > 0;
            } else if (r.getRtid() == 3) {
                return realNameDao.toAuditOfPat(r.getUdid()) > 0;
            } else
                return false;
        } else {
            if (r.getRtid() == 1) {
                return realNameDao.toAuditFailureOfStu(r.getUdid()) > 0;
            } else if (r.getRtid() == 2) {
                return realNameDao.toAuditFailureOfTea(r.getUdid()) > 0;
            } else if (r.getRtid() == 3) {
                return realNameDao.toAuditFailureOfPat(r.getUdid()) > 0;
            }
        }
        return false;
    }
}
