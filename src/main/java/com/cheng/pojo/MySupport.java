package com.cheng.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class MySupport {

	private int msid;
	private int pid;
	private int uid;
	private double money;
	private int status;
	private Date ordertime;

	private Projects project;

}
