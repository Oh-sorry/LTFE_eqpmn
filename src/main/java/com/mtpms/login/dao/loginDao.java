package com.mtpms.login.dao;
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import com.mtpms.dto.loginDto;

@Repository("loginDao")
public class loginDao extends EgovAbstractMapper {


    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public int selecLoginChk(loginDto loginDto) throws Exception {
    	return (int) selectOne("lgoinDaoSql.selecLoginChk", loginDto);
    }

    public loginDto selecLoginInfo(loginDto loginDto) throws Exception {
    	return selectOne("lgoinDaoSql.selecLoginInfo", loginDto);
    }

}
