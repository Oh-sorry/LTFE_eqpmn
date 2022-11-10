package com.mtpms.dto;

import java.io.Serializable;

public class loginDto implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = -6563622194262791367L;
	/** 아이디 */
	private String userid;
	/** 이름 */
	private String username;
	/** 등급 */
	private String usergrade;
	/** 패스워드 */
	private String userpass;

	private String initpass;

	private String isMobile;


	/**
	 * @return the isMobile
	 */
	public String getIsMobile() {
		return isMobile;
	}
	/**
	 * @param isMobile the isMobile to set
	 */
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	/**
	 * @return the initpass
	 */
	public String getInitpass() {
		return initpass;
	}
	/**
	 * @param initpass the initpass to set
	 */
	public void setInitpass(String initpass) {
		this.initpass = initpass;
	}
	/**
	 * @return the userpass
	 */
	public String getUserpass() {
		return userpass;
	}
	/**
	 * @param userpass the userpass to set
	 */
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the usergrade
	 */
	public String getUsergrade() {
		return usergrade;
	}
	/**
	 * @param usergrade the usergrade to set
	 */
	public void setUsergrade(String usergrade) {
		this.usergrade = usergrade;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
