package com.mtpms.common.service;

import java.util.List;

import com.mtpms.dto.comCodeDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.db.pmsbbsfileDto;

public interface commonService {

	public List<comCodeDto> selectComCodeList(String parentCode) throws Exception;

	public List<projectManageDto> selectProjectList() throws Exception;

	public List<projectManageDto> selectUserProjectList(String userid) throws Exception;

    public List<menuManageDto> selectMenuList(String userid) throws Exception;

    public pmsbbsfileDto selectpmsbbsFileInfo(long fileid) throws Exception ;
}
