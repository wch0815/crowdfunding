package com.cheng.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MyInitiate {

	private int iid;
	private int pid;
	private int uid;

	private List<Projects> project;

}
