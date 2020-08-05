package com.cheng.pojo;

import lombok.Data;

@Data
public class SponsorInformation {

	private int siid;
	private int pid;
	private String siintroduction; //自我介绍
	private String siintroductiondesc; //详细自我介绍
	private String siphone;  //联系电话
	private String siservicetel; //客服电话

}
