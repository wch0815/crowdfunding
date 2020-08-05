package com.cheng.pojo;

import lombok.Data;

@Data
public class PatriarchRealNameInfo {

    private int udid;
    private int uid;
    private int rtid;
    private String realname;
    private String cardnum;
    private String tel;
    private String stuname;  //学生姓名
    private String classname;  //学生所在班级
    private String photo;
    private int state;
}
