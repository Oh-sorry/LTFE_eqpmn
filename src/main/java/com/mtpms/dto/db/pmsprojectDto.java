package com.mtpms.dto.db;

import java.io.Serializable;

public class pmsprojectDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 789335229707934990L;

	private String projectid;
	private String projectname;
	private String projectstartdt;
	private String projectenddt;
	private String useyn;
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
	 * @return the projectstartdt
	 */
	public String getProjectstartdt() {
		return projectstartdt;
	}
	/**
	 * @param projectstartdt the projectstartdt to set
	 */
	public void setProjectstartdt(String projectstartdt) {
		this.projectstartdt = projectstartdt;
	}
	/**
	 * @return the projectenddt
	 */
	public String getProjectenddt() {
		return projectenddt;
	}
	/**
	 * @param projectenddt the projectenddt to set
	 */
	public void setProjectenddt(String projectenddt) {
		this.projectenddt = projectenddt;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
