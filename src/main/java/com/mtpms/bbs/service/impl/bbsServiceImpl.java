package com.mtpms.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import com.mtpms.bbs.dao.bbsDao;
import com.mtpms.bbs.service.bbsService;
import com.mtpms.dto.bbsManageDto;
import com.mtpms.dto.db.pmsbbsDto;
import com.mtpms.dto.db.pmsbbsfileDto;

@Service("bbsService")
public class bbsServiceImpl extends EgovAbstractServiceImpl implements bbsService {

    @Resource(name = "bbsDao")
    private bbsDao bbsDao;

    public List<bbsManageDto> selectBbsList(bbsManageDto bbsManageDto) throws Exception {

    	List<bbsManageDto> list = bbsDao.selectBbsList(bbsManageDto);

    	return list;
    }

    public long selectBbsListCnt(bbsManageDto bbsManageDto) throws Exception {

    	return bbsDao.selectBbsListCnt(bbsManageDto);

    }

    public long selectBbsSeq() throws Exception {
    	return bbsDao.selectBbsSeq();
    }

    public long selectBbsFileSeq() throws Exception {
    	return bbsDao.selectBbsFileSeq();
    }

    public int pmsbbsUpdate(pmsbbsDto pmsbbsDto) throws Exception {
    	return bbsDao.pmsbbsUpdate(pmsbbsDto);
    }

    public int pmsbbsFileInsert(pmsbbsfileDto pmsbbsfileDto) throws Exception {
    	return bbsDao.pmsbbsFileInsert(pmsbbsfileDto);
    }

    public int pmsbbsFileDelete(pmsbbsfileDto pmsbbsfileDto) throws Exception {
    	return bbsDao.pmsbbsFileDelete(pmsbbsfileDto);
    }


    public bbsManageDto selectBbsInfo(bbsManageDto bbsManageDto) throws Exception {
    	return bbsDao.selectBbsInfo(bbsManageDto);
    }

    public List<pmsbbsfileDto> selectBbsFileInfo(bbsManageDto bbsManageDto) throws Exception {
    	List<pmsbbsfileDto> list = bbsDao.selectBbsFileInfo(bbsManageDto);
    	return list;
    }

    public int pmsbbsDelete(bbsManageDto bbsManageDto) throws Exception {
    	return bbsDao.pmsbbsDelete(bbsManageDto);
    }

    public List<bbsManageDto> selectChildBbsInfo(bbsManageDto bbsManageDto) throws Exception {
    	List<bbsManageDto> list = bbsDao.selectChildBbsInfo(bbsManageDto);
    	return list;
    }

    public int pmsbbsviewcnt(bbsManageDto bbsManageDto) throws Exception {
    	return bbsDao.pmsbbsviewcnt(bbsManageDto);
    }
}
