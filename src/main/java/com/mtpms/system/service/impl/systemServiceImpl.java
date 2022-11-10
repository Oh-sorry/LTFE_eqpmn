package com.mtpms.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import com.mtpms.dto.codeManageDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.userManageDto;
import com.mtpms.dto.db.pmscodeDto;
import com.mtpms.dto.db.pmsmenuDto;
import com.mtpms.dto.db.pmsprojectDto;
import com.mtpms.dto.db.pmsuserDto;
import com.mtpms.dto.db.pmsuserprojectDto;
import com.mtpms.system.dao.systemDao;
import com.mtpms.system.service.systemService;

@Service("systemService")
public class systemServiceImpl extends EgovAbstractServiceImpl implements systemService {

    @Resource(name = "systemDao")
    private systemDao systemDao;

    public Map<String, Object> selectUserManageList(userManageDto userManageDto) throws Exception {

    	List<userManageDto> list = systemDao.selectUserManageList(userManageDto);

    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", list);

    	return map;
    }

    public int pmsuserUpdate(pmsuserDto pmsuserDto) throws Exception {

    	int rtn = systemDao.pmsuserUpdate(pmsuserDto);

    	return rtn;
    }

    public int pmsuserpassUpdate(pmsuserDto pmsuserDto) throws Exception {
    	return systemDao.pmsuserpassUpdate(pmsuserDto);
    }

    public int selectUserDupChk(pmsuserDto pmsuserDto) throws Exception {
    	int rtn = systemDao.selectUserDupChk(pmsuserDto);

    	return rtn;
    }

    public int pmsuserDelete(pmsuserDto pmsuserDto) throws Exception {

    	int rtn = systemDao.pmsuserDelete(pmsuserDto);

    	return rtn;
    }

	public int pmsuserProjectDelete(String userId) throws Exception {
    	int rtn = systemDao.pmsuserProjectDelete(userId);

    	return rtn;
    }

	public int pmsuserProjectInsert(pmsuserprojectDto pmsuserprojectDto) throws Exception {

    	int rtn = systemDao.pmsuserProjectInsert(pmsuserprojectDto);

    	return rtn;
    }


	public Map<String, Object> selectMenuManageList(menuManageDto menuManageDto) throws Exception {

    	List<menuManageDto> list = systemDao.selectMenuManageList(menuManageDto);

    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", list);

    	return map;
    }

	public List<menuManageDto> selectParentMenuList() throws Exception {

		List<menuManageDto> list = systemDao.selectParentMenuList();

    	return list;
	}

    public int pmsmenuUpdate(pmsmenuDto pmsmenuDto) throws Exception {

    	int rtn = systemDao.pmsmenuUpdate(pmsmenuDto);

    	return rtn;
    }

    public int pmsmenuDelete(pmsmenuDto pmsmenuDto) throws Exception {

    	int rtn = systemDao.pmsmenuDelete(pmsmenuDto);

    	return rtn;
    }

    public Map<String, Object> selectCodeManageList(codeManageDto codeManageDto) throws Exception {

    	List<codeManageDto> list = systemDao.selectCodeManageList(codeManageDto);

    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", list);

    	return map;
    }

    public List<codeManageDto> selectParentCodeList() throws Exception {

		List<codeManageDto> list = systemDao.selectParentCodeList();

    	return list;
	}

    public int pmscodeUpdate(pmscodeDto pmscodeDto) throws Exception {

    	int rtn = systemDao.pmscodeUpdate(pmscodeDto);

    	return rtn;
    }

    public int pmscodeDelete(pmscodeDto pmscodeDto) throws Exception {

    	int rtn = systemDao.pmscodeDelete(pmscodeDto);

    	return rtn;
    }

    public Map<String, Object> selectProjectManageList(projectManageDto projectManageDto) throws Exception {

    	List<projectManageDto> list = systemDao.selectProjectManageList(projectManageDto);

    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", list);

    	return map;
    }

    public int pmsprojectUpdate(pmsprojectDto pmsprojectDto) throws Exception {

    	int rtn = systemDao.pmsprojectUpdate(pmsprojectDto);

    	return rtn;
    }

    public int pmsprojectDelete(pmsprojectDto pmsprojectDto) throws Exception {

    	int rtn = systemDao.pmsprojectDelete(pmsprojectDto);

    	return rtn;
    }

    public int pmsprojectInsert(pmsprojectDto pmsprojectDto) throws Exception {
    	return systemDao.pmsprojectInsert(pmsprojectDto);
    }

    public int pmsmenuInsert(pmsmenuDto pmsmenuDto) throws Exception {
    	return systemDao.pmsmenuInsert(pmsmenuDto);
    }

    public long selectmenuidseq() throws Exception {
    	return systemDao.selectmenuidseq();
    }

    public String selectProjectidSeq() throws Exception {
    	return systemDao.selectProjectidSeq();
    }

}
