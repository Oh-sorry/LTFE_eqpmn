package com.mtpms.dto;

import java.io.Serializable;
import java.util.List;

import com.mtpms.dto.db.pmsbbsfileDto;

public class bbsManageDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7023928995168856635L;

	private long bbsid;
	private int menuid;
	private String menuname;
	private String projectid;
	private String projectname;
	private String userid;
	private String username;
	private String title;
	private String contents;
	private String gesidate;
	private long parentbbsid;
	private int replycnt;
	private String useyn;
	private long viewcnt;
	private int leftMenuId;
	private String leftMenuName;
	private long rowid;
	private int pageno;
	private int startno;
	private int endno;
	private String searchprojectid;
	private String searchtitle;
	private String searchusername;
	private String searchreturnurl;
	private String searchposition;
	private int childno;
	private String childsave;
	private String programurl;

	private List<pmsbbsfileDto> pmsbbsfileDtoList;
	private List<bbsManageDto> bbsChildInfo;



	/**
	 * @return the searchposition
	 */
	public String getSearchposition() {
		return searchposition;
	}
	/**
	 * @param searchposition the searchposition to set
	 */
	public void setSearchposition(String searchposition) {
		this.searchposition = searchposition;
	}
	/**
	 * @return the searchreturnurl
	 */
	public String getSearchreturnurl() {
		return searchreturnurl;
	}
	/**
	 * @param searchreturnurl the searchreturnurl to set
	 */
	public void setSearchreturnurl(String searchreturnurl) {
		this.searchreturnurl = searchreturnurl;
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
	 * @return the bbsChildInfo
	 */
	public List<bbsManageDto> getBbsChildInfo() {
		return bbsChildInfo;
	}
	/**
	 * @param bbsChildInfo the bbsChildInfo to set
	 */
	public void setBbsChildInfo(List<bbsManageDto> bbsChildInfo) {
		this.bbsChildInfo = bbsChildInfo;
	}
	/**
	 * @return the childsave
	 */
	public String getChildsave() {
		return childsave;
	}
	/**
	 * @param childsave the childsave to set
	 */
	public void setChildsave(String childsave) {
		this.childsave = childsave;
	}
	/**
	 * @return the childno
	 */
	public int getChildno() {
		return childno;
	}
	/**
	 * @param childno the childno to set
	 */
	public void setChildno(int childno) {
		this.childno = childno;
	}
	/**
	 * @return the pmsbbsfileDtoList
	 */
	public List<pmsbbsfileDto> getPmsbbsfileDtoList() {
		return pmsbbsfileDtoList;
	}
	/**
	 * @param pmsbbsfileDtoList the pmsbbsfileDtoList to set
	 */
	public void setPmsbbsfileDtoList(List<pmsbbsfileDto> pmsbbsfileDtoList) {
		this.pmsbbsfileDtoList = pmsbbsfileDtoList;
	}
	/**
	 * @return the searchprojectid
	 */
	public String getSearchprojectid() {
		return searchprojectid;
	}
	/**
	 * @param searchprojectid the searchprojectid to set
	 */
	public void setSearchprojectid(String searchprojectid) {
		this.searchprojectid = searchprojectid;
	}
	/**
	 * @return the searchtitle
	 */
	public String getSearchtitle() {
		return searchtitle;
	}
	/**
	 * @param searchtitle the searchtitle to set
	 */
	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}
	/**
	 * @return the searchusername
	 */
	public String getSearchusername() {
		return searchusername;
	}
	/**
	 * @param searchusername the searchusername to set
	 */
	public void setSearchusername(String searchusername) {
		this.searchusername = searchusername;
	}
	/**
	 * @return the rowid
	 */
	public long getRowid() {
		return rowid;
	}
	/**
	 * @param rowid the rowid to set
	 */
	public void setRowid(long rowid) {
		this.rowid = rowid;
	}
	/**
	 * @return the startno
	 */
	public int getStartno() {
		return startno;
	}
	/**
	 * @param startno the startno to set
	 */
	public void setStartno(int startno) {
		this.startno = startno;
	}
	/**
	 * @return the endno
	 */
	public int getEndno() {
		return endno;
	}
	/**
	 * @param endno the endno to set
	 */
	public void setEndno(int endno) {
		this.endno = endno;
	}
	/**
	 * @return the pageno
	 */
	public int getPageno() {
		return pageno;
	}
	/**
	 * @param pageno the pageno to set
	 */
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	/**
	 * @return the leftMenuId
	 */
	public int getLeftMenuId() {
		return leftMenuId;
	}
	/**
	 * @param leftMenuId the leftMenuId to set
	 */
	public void setLeftMenuId(int leftMenuId) {
		this.leftMenuId = leftMenuId;
	}
	/**
	 * @return the leftMenuName
	 */
	public String getLeftMenuName() {
		return leftMenuName;
	}
	/**
	 * @param leftMenuName the leftMenuName to set
	 */
	public void setLeftMenuName(String leftMenuName) {
		this.leftMenuName = leftMenuName;
	}
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
	 * @return the replycnt
	 */
	public int getReplycnt() {
		return replycnt;
	}
	/**
	 * @param replycnt the replycnt to set
	 */
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
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
