
package com.mtpms.eqpmn.service;

import java.util.List;
import java.util.Map;

import com.mtpms.dto.eqpmnManageDto;

public interface eqpmnService {

	public Map<String, Object> selectEqpmnList(eqpmnManageDto eqpmnManageDto) throws Exception;


}
