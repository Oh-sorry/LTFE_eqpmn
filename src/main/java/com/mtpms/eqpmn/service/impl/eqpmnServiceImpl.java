package com.mtpms.eqpmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import com.mtpms.eqpmn.dao.eqpmnDao;
import com.mtpms.dto.eqpmnManageDto;
import com.mtpms.eqpmn.service.eqpmnService;

@Service("eqpmnService")
public class eqpmnServiceImpl extends EgovAbstractServiceImpl implements eqpmnService {

	@Resource(name= "eqpmnDao")
	private eqpmnDao eqpmnDao;
	
	public Map<String, Object> selectEqpmnList(eqpmnManageDto eqpmnManageDto) throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.selectEqpmnList(eqpmnManageDto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", list);
		
		return map;
	};

}
