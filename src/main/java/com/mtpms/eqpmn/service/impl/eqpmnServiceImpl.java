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
	
	public List<eqpmnManageDto> selectEqpmnCode() throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.selectEqpmnCode();
		
		return list;
	}
	
	public List<eqpmnManageDto> selectEqpmnCode2() throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.selectEqpmnCode2();
		
		return list;
	}
	
	public List<eqpmnManageDto> selectEqpmnCode3() throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.selectEqpmnCode3();
		
		return list;
	}
	
	public List<eqpmnManageDto> selectEqpmnCode4() throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.selectEqpmnCode4();
		
		return list;
	}
	
	public List<eqpmnManageDto> selectEqpmnCodeList() throws Exception {
		List<eqpmnManageDto> list = eqpmnDao.selectEqpmnCodeList();
		
		return list;
	}
	
	public List<eqpmnManageDto> selectCnmList() throws Exception {
		List<eqpmnManageDto> list = eqpmnDao.selectCnmList();
	
		return list;
	}
	
	public int eqpmnUpdate(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn = eqpmnDao.eqpmnUpdate(eqpmnManageDto);
		
		return rtn;
	}
	
	public int eqpmnDelete(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn = eqpmnDao.eqpmnDelete(eqpmnManageDto);
		
		return rtn;
	}
	
	public Map<String, Object> eqpmnManageList(eqpmnManageDto eqpmnManageDto) throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.eqpmnManageList(eqpmnManageDto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", list);
		
		return map;
	}
	
	public List<eqpmnManageDto> selectEqpmnListCode() throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.selectEqpmnListCode();
		
		return list;
	}
	
	public Map<String, Object> userSelect(eqpmnManageDto eqpmnManageDto) throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.userSelect(eqpmnManageDto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", list);
		
		return map;
	}
	
	public Map<String, Object> eqpmnSelect(eqpmnManageDto eqpmnManageDto) throws Exception {
		
		List<eqpmnManageDto> list = eqpmnDao.eqpmnSelect(eqpmnManageDto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", list);
		
		return map;
	}
	
	public int userManageUpdate(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn = eqpmnDao.userManageUpdate(eqpmnManageDto);
		
		return rtn;
	}
	
	public int userMappingUpdate(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn2 = eqpmnDao.userMappingUpdate(eqpmnManageDto);
		
		return rtn2;
	}
	
	public int eqpmnMappingUpdate(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn3 = eqpmnDao.eqpmnMappingUpdate(eqpmnManageDto);
		
		return rtn3;
	}
	
	public int userDelete(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn = eqpmnDao.userDelete(eqpmnManageDto);
		
		return rtn;
	}
	
	public int userDelete2(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn = eqpmnDao.userDelete2(eqpmnManageDto);
		
		return rtn;
	}
	
	public int userDelete3(eqpmnManageDto eqpmnManageDto) throws Exception {
		int rtn = eqpmnDao.userDelete3(eqpmnManageDto);
		
		return rtn;
	}
}
