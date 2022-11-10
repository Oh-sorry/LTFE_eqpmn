package com.mtpms.dto.db;

import java.io.Serializable;

public class pmsbbsfileDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -243253135369356709L;

	private long fileid;
	private String serverpath;
	private String serverfile;
	private String localfile;
	private String useyn;
	private long filesize;
	private long bbsid;
	private String ext;


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
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}
	/**
	 * @param ext the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
	/**
	 * @return the filesize
	 */
	public long getFilesize() {
		return filesize;
	}
	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	/**
	 * @return the fileid
	 */
	public long getFileid() {
		return fileid;
	}
	/**
	 * @param fileid the fileid to set
	 */
	public void setFileid(long fileid) {
		this.fileid = fileid;
	}
	/**
	 * @return the serverpath
	 */
	public String getServerpath() {
		return serverpath;
	}
	/**
	 * @param serverpath the serverpath to set
	 */
	public void setServerpath(String serverpath) {
		this.serverpath = serverpath;
	}
	/**
	 * @return the serverfile
	 */
	public String getServerfile() {
		return serverfile;
	}
	/**
	 * @param serverfile the serverfile to set
	 */
	public void setServerfile(String serverfile) {
		this.serverfile = serverfile;
	}
	/**
	 * @return the localfile
	 */
	public String getLocalfile() {
		return localfile;
	}
	/**
	 * @param localfile the localfile to set
	 */
	public void setLocalfile(String localfile) {
		this.localfile = localfile;
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
