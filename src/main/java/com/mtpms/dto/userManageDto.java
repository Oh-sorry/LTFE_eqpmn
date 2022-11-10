package com.mtpms.dto;

import java.io.Serializable;

public class userManageDto implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = -3877997675488948169L;

	private String userid;
	private String username;
	private String userpass;
	private String usergrade;
	private String useyn;
	private String usergradename;
	private String projectid;
	private String projectname;

	/**
	 * @return the projectid
	 */
	public String getProjectid() {
		return projectid;
	}
	/**
	 * @param projectid the projectid to set
	 */
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	/**
	 * @return the projectname
	 */
	public String getProjectname() {
		return projectname;
	}
	/**
	 * @param projectname the projectname to set
	 */
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	/**
	 * @return the usergradename
	 */
	public String getUsergradename() {
		return usergradename;
	}
	/**
	 * @param usergradename the usergradename to set
	 */
	public void setUsergradename(String usergradename) {
		this.usergradename = usergradename;
	}
	/**
	 * @return the useyn
	 */
	public String getUseyn() {
		return useyn;
	}
	/**
	 * @param useyn the useyn to set
	 */
	public void setUseyn(String useyn) {
		this.useyn = useyn;
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
