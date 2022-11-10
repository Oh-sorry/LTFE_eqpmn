package com.mtpms.main.dao;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.db.pmsmenuDto;

@Repository("mainDao")
public class mainDao extends EgovAbstractMapper {


    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<pmsmenuDto> selectTopMenu() throws Exception {
    	return selectList("mainDaoSql.selectTopMenu");
    }

    public List<menuManageDto> selectLeftMenu(String suerid) throws Exception {
    	return selectList("mainDaoSql.selectLeftMenu", suerid);
    }

}
