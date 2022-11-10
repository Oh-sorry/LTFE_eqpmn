package com.mtpms.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.db.pmsmenuDto;
import com.mtpms.main.dao.mainDao;
import com.mtpms.main.service.mainService;

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
@Service("mainService")
public class mainServiceImpl extends EgovAbstractServiceImpl implements mainService {

    @Resource(name = "mainDao")
    private mainDao mainDao;

    public List<pmsmenuDto> selectTopMenu() throws Exception {

    	return mainDao.selectTopMenu();

    }

    public List<menuManageDto> selectLeftMenu(String userid) throws Exception {

    	return mainDao.selectLeftMenu(userid);

    }

}
