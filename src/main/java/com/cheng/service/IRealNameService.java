package com.cheng.service;

import com.cheng.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRealNameService {

    List<RealNameAuthenticationType> getRealNameType();

    String uploadArticlePicture(MultipartFile file, String filename);

    boolean saveStuRealNameInfo(StuRealNameInfo stuRealNameInfo);

    boolean saveTeaRealNameInfo(TeaRealNameInfo teaRealNameInfo);

    boolean savePatRealNameInfo(PatriarchRealNameInfo patriarchRealNameInfo);

    boolean getEmailVerification(String email, String s);

    List<Object> getAllRealNameInfo();

    boolean toAudit(RealNameInfo r);
}
