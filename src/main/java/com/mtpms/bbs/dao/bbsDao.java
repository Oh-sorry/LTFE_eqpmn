package com.mtpms.bbs.dao;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import com.mtpms.dto.bbsManageDto;
import com.mtpms.dto.db.pmsbbsDto;
import com.mtpms.dto.db.pmsbbsfileDto;

@Repository("bbsDao")
public class bbsDao extends EgovAbstractMapper {

    @SuppressWarnings("unchecked")

    public List<bbsManageDto> selectBbsList(bbsManageDto bbsManageDto) throws Exception {
    	return selectList("bbsDaoSql.selectBbsList", bbsManageDto);
    }

    public long selectBbsListCnt(bbsManageDto bbsManageDto) throws Exception {
    	return selectOne("bbsDaoSql.selectBbsListCnt", bbsManageDto);
    }

    public long selectBbsSeq() throws Exception {
    	return selectOne("bbsDaoSql.selectBbsSeq");
    }

    public long selectBbsFileSeq() throws Exception {
    	return selectOne("bbsDaoSql.selectBbsFileSeq");
    }

    public int pmsbbsUpdate(pmsbbsDto pmsbbsDto) throws Exception {
    	return update("bbsDaoSql.pmsbbsUpdate", pmsbbsDto);
    }

    public int pmsbbsFileInsert(pmsbbsfileDto pmsbbsfileDto) throws Exception {
    	return insert("bbsDaoSql.pmsbbsFileInsert", pmsbbsfileDto);
    }

    public int pmsbbsFileDelete(pmsbbsfileDto pmsbbsfileDto) throws Exception {
    	return update("bbsDaoSql.pmsbbsFileDelete", pmsbbsfileDto);
    }

    public bbsManageDto selectBbsInfo(bbsManageDto bbsManageDto) throws Exception {
    	return selectOne("bbsDaoSql.selectBbsInfo", bbsManageDto);
    }

    public List<pmsbbsfileDto> selectBbsFileInfo(bbsManageDto bbsManageDto) throws Exception {
    	return selectList("bbsDaoSql.selectBbsFileInfo", bbsManageDto);
    }

    public int pmsbbsDelete(bbsManageDto bbsManageDto) throws Exception {
    	return update("bbsDaoSql.pmsbbsDelete", bbsManageDto);
    }

    public List<bbsManageDto> selectChildBbsInfo(bbsManageDto bbsManageDto) throws Exception {
    	return selectList("bbsDaoSql.selectChildBbsInfo", bbsManageDto);
    }

    public int pmsbbsviewcnt(bbsManageDto bbsManageDto) throws Exception {
    	return update("bbsDaoSql.pmsbbsviewcnt", bbsManageDto);
    }

}
