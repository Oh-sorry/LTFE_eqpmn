package com.mtpms.eqpmn.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import com.mtpms.dto.eqpmnManageDto;

@Repository("eqpmnDao")
public class eqpmnDao extends EgovAbstractMapper{

	@SuppressWarnings("unchecked")
	public List<eqpmnManageDto> selectEqpmnList(eqpmnManageDto eqpmnManageDto) throws Exception {
		
		return selectList("eqpmnDaoSql.selectEqpmnList", eqpmnManageDto);
	}

	public List<eqpmnManageDto> selectEqpmnCode() throws Exception {
		return selectList("eqpmnDaoSql.selectEqpmnCode");
	}

	public List<eqpmnManageDto> selectEqpmnCode2() throws Exception {
		return selectList("eqpmnDaoSql.selectEqpmnCode2");
	}
}
