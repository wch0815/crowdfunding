package com.cheng.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Projects {

	private int pid;
	private int clid;
	private int ctid;
	private String ipname;
	private String ipdesc;
	private double ipmoney;
    private int ipday;
    private String ipimag;
    private String ipdescimag;
    private Date ipdate;
    private String reminders;
    private double money;
	private int attentions;
	private int state;
	private int day;
	private int rate;
    private CrowdfundingType type;
    private User user;
    private List<PayBack> payBack;

}
