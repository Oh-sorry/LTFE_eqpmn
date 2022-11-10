package com.mtpms.bbs.service;

import java.util.List;

import com.mtpms.dto.bbsManageDto;
import com.mtpms.dto.db.pmsbbsDto;
import com.mtpms.dto.db.pmsbbsfileDto;

public interface bbsService {

	public List<bbsManageDto> selectBbsList(bbsManageDto bbsManageDto) throws Exception;

	public long selectBbsListCnt(bbsManageDto bbsManageDto) throws Exception;

    public long selectBbsSeq() throws Exception;

    public long selectBbsFileSeq() throws Exception;

    public int pmsbbsUpdate(pmsbbsDto pmsbbsDto) throws Exception;

    public int pmsbbsFileInsert(pmsbbsfileDto pmsbbsfileDto) throws Exception;

    public int pmsbbsFileDelete(pmsbbsfileDto pmsbbsfileDto) throws Exception;

    public bbsManageDto selectBbsInfo(bbsManageDto bbsManageDto) throws Exception;

    public List<pmsbbsfileDto> selectBbsFileInfo(bbsManageDto bbsManageDto) throws Exception;

    public int pmsbbsDelete(bbsManageDto bbsManageDto) throws Exception;

    public List<bbsManageDto> selectChildBbsInfo(bbsManageDto bbsManageDto) throws Exception ;

    public int pmsbbsviewcnt(bbsManageDto bbsManageDto) throws Exception;
}