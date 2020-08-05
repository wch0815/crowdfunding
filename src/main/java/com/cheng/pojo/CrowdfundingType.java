package com.cheng.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CrowdfundingType {

	private int ctid;
	private String ctype;

	private List<Projects> projects;


}
