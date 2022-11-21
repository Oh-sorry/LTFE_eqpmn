
package com.mtpms.eqpmn.service;

import java.util.List;
import java.util.Map;

import com.mtpms.dto.eqpmnManageDto;

public interface eqpmnService {

	public Map<String, Object> selectEqpmnList(eqpmnManageDto eqpmnManageDto) throws Exception;

	public List<eqpmnManageDto> selectEqpmnCode() throws Exception;

	public List<eqpmnManageDto> selectEqpmnCode2() throws Exception;

	public List<eqpmnManageDto> selectEqpmnCode3() throws Exception;
	
	public List<eqpmnManageDto> selectEqpmnCode4() throws Exception;
	
	public List<eqpmnManageDto> selectEqpmnCodeList() throws Exception;

	public List<eqpmnManageDto> selectCnmList() throws Exception;

	public int eqpmnUpdate(eqpmnManageDto eqpmnManageDto) throws Exception;

	public int eqpmnDelete(eqpmnManageDto eqpmnManageDto) throws Exception;

	public Map<String, Object> eqpmnManageList(eqpmnManageDto eqpmnManageDto) throws Exception;

	public List<eqpmnManageDto> selectEqpmnListCode() throws Exception;

	public Map<String, Object> userSelect(eqpmnManageDto eqpmnManageDto) throws Exception;

	public Map<String, Object> eqpmnSelect(eqpmnManageDto eqpmnManageDto) throws Exception;

	public int userManageUpdate(eqpmnManageDto eqpmnManageDto) throws Exception;

	public int userMappingUpdate(eqpmnManageDto eqpmnManageDto) throws Exception;

	public int eqpmnMappingUpdate(eqpmnManageDto eqpmnManageDto) throws Exception;

	public int userDelete(eqpmnManageDto eqpmnManageDto) throws Exception;

	public int userDelete2(eqpmnManageDto eqpmnManageDto) throws Exception;

	public int userDelete3(eqpmnManageDto eqpmnManageDto) throws Exception;
}
