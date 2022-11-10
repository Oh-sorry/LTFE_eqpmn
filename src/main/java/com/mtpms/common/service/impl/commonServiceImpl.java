package com.mtpms.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import com.mtpms.common.dao.commonDao;
import com.mtpms.common.service.commonService;
import com.mtpms.dto.comCodeDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.db.pmsbbsfileDto;

@Service("commonService")
public class commonServiceImpl extends EgovAbstractServiceImpl implements commonService {

    @Resource(name = "commonDao")
    private commonDao commonDao;

    public List<comCodeDto> selectComCodeList(String parentCode) throws Exception {

    	List<comCodeDto> list = commonDao.selectComCodeList(parentCode);

    	return list;
    }

    public List<projectManageDto> selectProjectList() throws Exception {
    	List<projectManageDto> list = commonDao.selectProjectList();

    	return list;
    }

    public List<projectManageDto> selectUserProjectList(String userid) throws Exception {
    	List<projectManageDto> list = commonDao.selectUserProjectList(userid);

    	return list;
    }

    public List<menuManageDto> selectMenuList(String userid) throws Exception {
    	return commonDao.selectMenuList(userid);
    }

    public pmsbbsfileDto selectpmsbbsFileInfo(long fileid) throws Exception {
    	return commonDao.selectpmsbbsFileInfo(fileid);
    }

}
