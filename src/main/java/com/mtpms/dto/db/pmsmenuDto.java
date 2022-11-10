package com.mtpms.dto.db;

import java.io.Serializable;

public class pmsmenuDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2274233448448462646L;

	private long menuid;
	private String menuname;
	private long parentmenuid;
	private String programurl;
	private long sortno;
	private String systemgrade;
	private String useyn;
	private String projectid;


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
	 * @return the systemgrade
	 */
	public String getSystemgrade() {
		return systemgrade;
	}
	/**
	 * @param systemgrade the systemgrade to set
	 */
	public void setSystemgrade(String systemgrade) {
		this.systemgrade = systemgrade;
	}
	/**
	 * @return the menuid
	 */
	public long getMenuid() {
		return menuid;
	}
	/**
	 * @param menuid the menuid to set
	 */
	public void setMenuid(long menuid) {
		this.menuid = menuid;
	}
	/**
	 * @return the menuname
	 */
	public String getMenuname() {
		return menuname;
	}
	/**
	 * @param menuname the menuname to set
	 */
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	/**
	 * @return the parentmenuid
	 */
	public long getParentmenuid() {
		return parentmenuid;
	}
	/**
	 * @param parentmenuid the parentmenuid to set
	 */
	public void setParentmenuid(long parentmenuid) {
		this.parentmenuid = parentmenuid;
	}
	/**
	 * @return the programurlv
	 */
	public String getProgramurl() {
		return programurl;
	}
	/**
	 * @param programurlv the programurlv to set
	 */
	public void setProgramurl(String programurl) {
		this.programurl = programurl;
	}
	/**
	 * @return the sortno
	 */
	public long getSortno() {
		return sortno;
	}
	/**
	 * @param sortno the sortno to set
	 */
	public void setSortno(long sortno) {
		this.sortno = sortno;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
