package com.mtpms.dto;

import java.io.Serializable;

public class menuManageDto implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 2280341583402946720L;

	private long menuid;
	private String menuname;
	private long parentmenuid;
	private String programurl;
	private long sortno;
	private String systemgrade;
	private String useyn;
	private String path;
	private int level;
	private String systemgradename;
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
	 * @return the systemgradename
	 */
	public String getSystemgradename() {
		return systemgradename;
	}
	/**
	 * @param systemgradename the systemgradename to set
	 */
	public void setSystemgradename(String systemgradename) {
		this.systemgradename = systemgradename;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
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
	 * @return the programurl
	 */
	public String getProgramurl() {
		return programurl;
	}
	/**
	 * @param programurl the programurl to set
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
