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

	public List<eqpmnManageDto> selectEqpmnCodeList() throws Exception {
		return selectList("eqpmnDaoSql.selectEqpmnCode2");
	}

	public List<eqpmnManageDto> selectCnmList() throws Exception {
		return selectList("eqpmnDaoSql.selectCnmList");
	}

	public int eqpmnUpdate(eqpmnManageDto eqpmnManageDto) {
		return update("eqpmnDaoSql.eqpmnUpdate", eqpmnManageDto);
	}

	public int eqpmnDelete(eqpmnManageDto eqpmnManageDto) {
		return delete("eqpmnDaoSql.eqpmnDelete", eqpmnManageDto);
	}

	public List<eqpmnManageDto> eqpmnManageList(eqpmnManageDto eqpmnManageDto) {
		return selectList("eqpmnDaoSql.eqpmnManageList", eqpmnManageDto);
	}

	public List<eqpmnManageDto> selectEqpmnListCode() {
		return selectList("eqpmnDaoSql.selectEqpmnListCode");
	}

	public List<eqpmnManageDto> userSelect(eqpmnManageDto eqpmnManageDto) {
		return selectList("eqpmnDaoSql.userSelect", eqpmnManageDto);
	}

	public List<eqpmnManageDto> eqpmnSelect(eqpmnManageDto eqpmnManageDto) {
		return selectList("eqpmnDaoSql.eqpmnSelect", eqpmnManageDto);
	}

	public List<eqpmnManageDto> selectEqpmnCode3() {
		return selectList("eqpmnDaoSql.selectEqpmnCode3");
	}

	public List<eqpmnManageDto> selectEqpmnCode4() {
		return selectList("eqpmnDaoSql.selectEqpmnCode4");
	}

	public int userManageUpdate(eqpmnManageDto eqpmnManageDto) {
		return update("eqpmnDaoSql.userManageUpdate", eqpmnManageDto);
	}

	public int userMappingUpdate(eqpmnManageDto eqpmnManageDto) {
		return update("eqpmnDaoSql.userMappingUpdate", eqpmnManageDto);
	}

	public int eqpmnMappingUpdate(eqpmnManageDto eqpmnManageDto) {
		return update("eqpmnDaoSql.eqpmnMappingUpdate", eqpmnManageDto);
	}

	public int userDelete(eqpmnManageDto eqpmnManageDto) {
		return delete("eqpmnDaoSql.userDelete", eqpmnManageDto);
	}

	public int userDelete2(eqpmnManageDto eqpmnManageDto) {
		return delete("eqpmnDaoSql.userDelete2", eqpmnManageDto);
	}
	
	public int userDelete3(eqpmnManageDto eqpmnManageDto) {
		return update("eqpmnDaoSql.userDelete3", eqpmnManageDto);
	}
}
