package com.mtpms.system.dao;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import com.mtpms.dto.codeManageDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.userManageDto;
import com.mtpms.dto.db.pmscodeDto;
import com.mtpms.dto.db.pmsmenuDto;
import com.mtpms.dto.db.pmsprojectDto;
import com.mtpms.dto.db.pmsuserDto;
import com.mtpms.dto.db.pmsuserprojectDto;

@Repository("systemDao")
public class systemDao extends EgovAbstractMapper {


    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<userManageDto> selectUserManageList(userManageDto userManageDto) throws Exception {
    	return selectList("systemDaoSql.selectUserManageList",userManageDto);
    }

    public int pmsuserUpdate(pmsuserDto pmsuserDto) throws Exception {
    	return update("systemDaoSql.pmsuserUpdate",pmsuserDto);
    }

    public int pmsuserpassUpdate(pmsuserDto pmsuserDto) throws Exception {
    	return update("systemDaoSql.pmsuserpassUpdate",pmsuserDto);
    }

    public int selectUserDupChk(pmsuserDto pmsuserDto) throws Exception {
    	return selectOne("systemDaoSql.selectUserDupChk",pmsuserDto);
    }

    public int pmsuserDelete(pmsuserDto pmsuserDto) throws Exception {
    	return delete("systemDaoSql.pmsuserDelete",pmsuserDto);
    }

	public int pmsuserProjectDelete(String userId) throws Exception {
		return delete("systemDaoSql.pmsuserProjectDelete",userId);
    }

	public int pmsuserProjectInsert(pmsuserprojectDto pmsuserprojectDto) throws Exception {
		return delete("systemDaoSql.pmsuserProjectInsert",pmsuserprojectDto);
    }

    public List<menuManageDto> selectMenuManageList(menuManageDto menuManageDto) throws Exception {
    	return selectList("systemDaoSql.selectMenuManageList",menuManageDto);
    }

    public List<menuManageDto> selectParentMenuList() throws Exception {
    	return selectList("systemDaoSql.selectParentMenuList");
    }

    public int pmsmenuUpdate(pmsmenuDto pmsmenuDto) throws Exception {
    	return update("systemDaoSql.pmsmenuUpdate",pmsmenuDto);
    }

    public int pmsmenuDelete(pmsmenuDto pmsmenuDto) throws Exception {
    	return delete("systemDaoSql.pmsmenuDelete",pmsmenuDto);
    }

    public List<codeManageDto> selectCodeManageList(codeManageDto codeManageDto) throws Exception {
    	return selectList("systemDaoSql.selectCodeManageList",codeManageDto);
    }

    public List<codeManageDto> selectParentCodeList() throws Exception {
    	return selectList("systemDaoSql.selectParentCodeList");
    }

    public int pmscodeUpdate(pmscodeDto pmscodeDto) throws Exception {
    	return update("systemDaoSql.pmscodeUpdate",pmscodeDto);
    }

    public int pmscodeDelete(pmscodeDto pmscodeDto) throws Exception {
    	return delete("systemDaoSql.pmscodeDelete",pmscodeDto);
    }

    public List<projectManageDto> selectProjectManageList(projectManageDto projectManageDto) throws Exception {
    	return selectList("systemDaoSql.selectProjectManageList",projectManageDto);
    }

    public int pmsprojectUpdate(pmsprojectDto pmsprojectDto) throws Exception {
    	return update("systemDaoSql.pmsprojectUpdate",pmsprojectDto);
    }

    public int pmsprojectDelete(pmsprojectDto pmsprojectDto) throws Exception {
    	return delete("systemDaoSql.pmsprojectDelete",pmsprojectDto);
    }

    public int pmsprojectInsert(pmsprojectDto pmsprojectDto) throws Exception {
    	return insert("systemDaoSql.pmsprojectInsert",pmsprojectDto);
    }

    public int pmsmenuInsert(pmsmenuDto pmsmenuDto) throws Exception {
    	return insert("systemDaoSql.pmsmenuInsert",pmsmenuDto);
    }

    public long selectmenuidseq() throws Exception {
    	return selectOne("systemDaoSql.selectmenuidseq");
    }

    public String selectProjectidSeq() throws Exception {
    	return selectOne("systemDaoSql.selectProjectidSeq");
    }

}
