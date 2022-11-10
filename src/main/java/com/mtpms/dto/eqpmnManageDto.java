package com.mtpms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class eqpmnManageDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1715063308317078975L;

	private String group_code;
	private String manage_no;
	private Date purchs_date;
	private long purchs_amount;
	private String name;
	private String pern_no;
	private boolean use_yn;
	private String use_place;
	private boolean disuse_yn;
	
	
	public String getGroup_code() {
		return group_code;
	}


	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}


	public String getManage_no() {
		return manage_no;
	}


	public void setManage_no(String manage_no) {
		this.manage_no = manage_no;
	}


	public Date getPurchs_date() {
		return purchs_date;
	}


	public void setPurchs_date(Date purchs_date) {
		this.purchs_date = purchs_date;
	}


	public long getPurchs_amount() {
		return purchs_amount;
	}


	public void setPurchs_amount(long purchs_amount) {
		this.purchs_amount = purchs_amount;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPern_no() {
		return pern_no;
	}


	public void setPern_no(String pern_no) {
		this.pern_no = pern_no;
	}


	public boolean isUse_yn() {
		return use_yn;
	}


	public void setUse_yn(boolean use_yn) {
		this.use_yn = use_yn;
	}


	public String getUse_place() {
		return use_place;
	}


	public void setUse_place(String use_place) {
		this.use_place = use_place;
	}


	public boolean isDisuse_yn() {
		return disuse_yn;
	}


	public void setDisuse_yn(boolean disuse_yn) {
		this.disuse_yn = disuse_yn;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
