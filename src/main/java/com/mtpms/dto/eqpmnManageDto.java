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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
