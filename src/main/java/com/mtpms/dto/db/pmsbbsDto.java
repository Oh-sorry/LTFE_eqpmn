package com.mtpms.dto.db;

import java.io.Serializable;

public class pmsbbsDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3641736030990817044L;

	private long bbsid;
	private int menuid;
	private String projectid;
	private String userid;
	private String title;
	private String contents;
	private String gesidate;
	private long parentbbsid;
	private String useyn;
	private long viewcnt;


	/**
	 * @return the viewcnt
	 */
	public long getViewcnt() {
		return viewcnt;
	}
	/**
	 * @param viewcnt the viewcnt to set
	 */
	public void setViewcnt(long viewcnt) {
		this.viewcnt = viewcnt;
	}
	/**
	 * @return the bbsid
	 */
	public long getBbsid() {
		return bbsid;
	}
	/**
	 * @param bbsid the bbsid to set
	 */
	public void setBbsid(long bbsid) {
		this.bbsid = bbsid;
	}
	/**
	 * @return the menuid
	 */
	public int getMenuid() {
		return menuid;
	}
	/**
	 * @param menuid the menuid to set
	 */
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the gesidate
	 */
	public String getGesidate() {
		return gesidate;
	}
	/**
	 * @param gesidate the gesidate to set
	 */
	public void setGesidate(String gesidate) {
		this.gesidate = gesidate;
	}
	/**
	 * @return the parentbbsid
	 */
	public long getParentbbsid() {
		return parentbbsid;
	}
	/**
	 * @param parentbbsid the parentbbsid to set
	 */
	public void setParentbbsid(long parentbbsid) {
		this.parentbbsid = parentbbsid;
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
