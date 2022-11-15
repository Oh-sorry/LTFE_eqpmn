package com.mtpms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class eqpmnManageDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1715063308317078975L;

	private String codenm;
	private String manageno;
	private String purchsdate;
	private int purchsamount;
	private String name;
	private String pernno;
	private String useyn;
	private String useplace;
	private String disuseyn;
	private String eqpmncode;
	private String remark;
	
	private String momanageno1;
	private String momanageno2;
	private String pcmanageno1;
	private String pcmanageno2;
	private String phmanageno;
	
	private String type;
	private String keyword;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEqpmncode() {
		return eqpmncode;
	}

	public void setEqpmncode(String eqpmncode) {
		this.eqpmncode = eqpmncode;
	}

	public String getCodenm() {
		return codenm;
	}

	public void setCodenm(String codenm) {
		this.codenm = codenm;
	}

	public String getManageno() {
		return manageno;
	}

	public void setManageno(String manageno) {
		this.manageno = manageno;
	}

	public String getPurchsdate() {
		return purchsdate;
	}

	public void setPurchsdate(String purchsdate) {
		this.purchsdate = purchsdate;
	}

	public int getPurchsamount() {
		return purchsamount;
	}

	public void setPurchsamount(int purchsamount) {
		this.purchsamount = purchsamount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPernno() {
		return pernno;
	}

	public void setPernno(String pernno) {
		this.pernno = pernno;
	}

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	public String getUseplace() {
		return useplace;
	}

	public void setUseplace(String useplace) {
		this.useplace = useplace;
	}

	public String getDisuseyn() {
		return disuseyn;
	}

	public void setDisuseyn(String disuseyn) {
		this.disuseyn = disuseyn;
	}
	
	public String getMomanageno1() {
		return momanageno1;
	}

	public void setMomanageno1(String momanageno1) {
		this.momanageno1 = momanageno1;
	}

	public String getMomanageno2() {
		return momanageno2;
	}

	public void setMomanageno2(String momanageno2) {
		this.momanageno2 = momanageno2;
	}

	public String getPcmanageno1() {
		return pcmanageno1;
	}

	public void setPcmanageno1(String pcmanageno1) {
		this.pcmanageno1 = pcmanageno1;
	}

	public String getPcmanageno2() {
		return pcmanageno2;
	}

	public void setPcmanageno2(String pcmanageno2) {
		this.pcmanageno2 = pcmanageno2;
	}

	public String getPhmanageno() {
		return phmanageno;
	}

	public void setPhmanageno(String phmanageno) {
		this.phmanageno = phmanageno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
