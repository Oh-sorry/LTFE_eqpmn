package com.mtpms.login.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import com.mtpms.dto.loginDto;
import com.mtpms.login.dao.loginDao;
import com.mtpms.login.service.loginService;

/**
 * 게시물 관리를 위한 서비스 구현 클래스
 * @author 공통 서비스 개발팀 한성곤
 * @since 2009.03.19
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.19  이삼섭          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Service("loginService")
public class loginServiceImpl extends EgovAbstractServiceImpl implements loginService {

    @Resource(name = "loginDao")
    private loginDao loginDao;

    public int selecLoginChk(loginDto loginDto)throws Exception {

    	return loginDao.selecLoginChk(loginDto);

    }

    public loginDto selecLoginInfo(loginDto loginDto)throws Exception {

    	return loginDao.selecLoginInfo(loginDto);

    }

}
