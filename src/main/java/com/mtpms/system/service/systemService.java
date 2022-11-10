package com.mtpms.system.service;

import java.util.List;
import java.util.Map;

import com.mtpms.dto.codeManageDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.userManageDto;
import com.mtpms.dto.db.pmscodeDto;
import com.mtpms.dto.db.pmsmenuDto;
import com.mtpms.dto.db.pmsprojectDto;
import com.mtpms.dto.db.pmsuserDto;
import com.mtpms.dto.db.pmsuserprojectDto;

public interface systemService {

	public Map<String, Object> selectUserManageList(userManageDto userManageDto) throws Exception;

	public int pmsuserUpdate(pmsuserDto pmsuserDto) throws Exception;

    public int pmsuserpassUpdate(pmsuserDto pmsuserDto) throws Exception;

	public int selectUserDupChk(pmsuserDto pmsuserDto) throws Exception;

	public int pmsuserDelete(pmsuserDto pmsuserDto) throws Exception;

	public int pmsuserProjectDelete(String userId) throws Exception;

	public int pmsuserProjectInsert(pmsuserprojectDto pmsuserprojectDto) throws Exception;

	public Map<String, Object> selectMenuManageList(menuManageDto menuManageDto) throws Exception;

	public List<menuManageDto> selectParentMenuList() throws Exception;

	public int pmsmenuUpdate(pmsmenuDto pmsmenuDto) throws Exception;

	public int pmsmenuDelete(pmsmenuDto pmsmenuDto) throws Exception;

	public Map<String, Object> selectCodeManageList(codeManageDto codeManageDto) throws Exception;

	public List<codeManageDto> selectParentCodeList() throws Exception;

	public int pmscodeUpdate(pmscodeDto pmscodeDto) throws Exception;

	public int pmscodeDelete(pmscodeDto pmscodeDto) throws Exception;

	public Map<String, Object> selectProjectManageList(projectManageDto projectManageDto) throws Exception;

	public int pmsprojectUpdate(pmsprojectDto pmsprojectDto) throws Exception;

	public int pmsprojectDelete(pmsprojectDto pmsprojectDto) throws Exception;

    public int pmsprojectInsert(pmsprojectDto pmsprojectDto) throws Exception;

    public int pmsmenuInsert(pmsmenuDto pmsmenuDto) throws Exception;

    public long selectmenuidseq() throws Exception;

    public String selectProjectidSeq() throws Exception;
}