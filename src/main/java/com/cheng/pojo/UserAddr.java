package com.cheng.pojo;

import lombok.Data;

@Data
public class UserAddr {

    private int said;
    private int uid;
    private String saaddr;
    private String saconsignee;  //收件人
    private String satel;           //收货人联系电话
}
