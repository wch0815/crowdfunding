package com.cheng.pojo;

import lombok.Data;

import java.util.List;
@Data
public class EasyPay {

	private int epid;
	private int uid;
	private String epaccountnumber;// 账号
	private String epidcard;
	private double epbalance;
	private String eppassword;
	private double epinvestment;


}
