package com.mtpms.common.dao;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import com.mtpms.dto.comCodeDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.db.pmsbbsfileDto;

@Repository("commonDao")
public class commonDao extends EgovAbstractMapper {


    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<comCodeDto> selectComCodeList(String parentCode) throws Exception {
    	return selectList("commonDaoSql.selectComCodeList", parentCode);
    }

    public List<projectManageDto> selectProjectList() throws Exception {
    	return selectList("commonDaoSql.selectProjectList");
    }

    public List<projectManageDto> selectUserProjectList(String userid) throws Exception {
    	return selectList("commonDaoSql.selectUserProjectList", userid);
    }

    public List<menuManageDto> selectMenuList(String userid) throws Exception {
    	return selectList("commonDaoSql.selectMenuList", userid);
    }

    public pmsbbsfileDto selectpmsbbsFileInfo(long fileid) throws Exception {
    	return selectOne("commonDaoSql.selectpmsbbsFileInfo", fileid);
    }

}
