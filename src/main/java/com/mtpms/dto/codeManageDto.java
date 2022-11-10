package com.mtpms.dto;

import java.io.Serializable;

public class codeManageDto implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 3979002045364199435L;

	private String code;
	private String codename;
	private String parentcode;
	private String useyn;
	private String path;
	private int level;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the codename
	 */
	public String getCodename() {
		return codename;
	}
	/**
	 * @param codename the codename to set
	 */
	public void setCodename(String codename) {
		this.codename = codename;
	}
	/**
	 * @return the parentcode
	 */
	public String getParentcode() {
		return parentcode;
	}
	/**
	 * @param parentcode the parentcode to set
	 */
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
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
