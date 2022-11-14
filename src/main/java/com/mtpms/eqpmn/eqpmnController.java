package com.mtpms.eqpmn;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtpms.common.service.commonService;
import com.mtpms.dto.comCodeDto;
import com.mtpms.dto.eqpmnManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.db.pmsmenuDto;
import com.mtpms.eqpmn.service.eqpmnService;
import com.mtpms.system.service.systemService;

import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.EgovMessageSource;
@Controller
public class eqpmnController {
	
	@Resource(name = "commonService")
	private commonService commonService;
	
	@Resource(name = "eqpmnService")
	private eqpmnService eqpmnService;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Autowired
    private DefaultBeanValidator beanValidator;

    protected static Logger logger = Logger.getLogger(Main.class.getName());
    
	@RequestMapping(value = "/eqpmn/eqpmnManage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String eqpmnManage(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

		List<eqpmnManageDto> list = eqpmnService.selectEqpmnCodeList();
		List<eqpmnManageDto> cnmList = eqpmnService.selectCnmList();

		model.addAttribute("cnmList", cnmList);
		model.addAttribute("list", list);
		model.addAttribute("pageUrl", "/eqpmn/eqpmnManage.jsp");
		return "main";
	}
	
	@RequestMapping(value = "/eqpmn/eqpmnManage.ajax", method= { RequestMethod.POST })
	public ModelAndView eqpmnManageAjax(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto, HttpSession session, ModelMap model) throws Exception {
		Map<String, Object> map = eqpmnService.selectEqpmnList(eqpmnManageDto);
		ModelAndView modelAndView = new ModelAndView("jsonView", map);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/eqpmn/selectEqpmnCode.ajax", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<eqpmnManageDto> selectEqpmnCode(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto,HttpSession session, ModelMap model) throws Exception {
		
		List<eqpmnManageDto> list = eqpmnService.selectEqpmnCode();
		return list;
	}
	
	@RequestMapping(value="/eqpmn/selectEqpmnCode2.ajax", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<eqpmnManageDto> selectEqpmnCode2(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto,HttpSession session, ModelMap model) throws Exception {
		
		List<eqpmnManageDto> list = eqpmnService.selectEqpmnCode2();
		return list;
	}
	
	
	@RequestMapping(value="/eqpmn/eqpmnManageUpdate.ajax",  method={RequestMethod.POST})
	public ResponseEntity<String> eqpmnManageUpdate(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto, HttpSession session, Model model) throws Exception {
		int rtn = eqpmnService.eqpmnUpdate(eqpmnManageDto);
		
		logger.info("rtn =>" + rtn);
		
		ResponseEntity<String> resRtn = null;
		if(rtn ==1 ) {
			resRtn = new ResponseEntity<>(HttpStatus.OK);
		} else {
			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return resRtn;
	}
	
	@RequestMapping(value = "/eqpmn/eqpmnManageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String eqpmnManageList(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

		model.addAttribute("pageUrl", "/eqpmn/eqpmnManageList.jsp");
		return "main";
	}
	
	@RequestMapping(value = "/eqpmn/eqpmnManageList.ajax", method= { RequestMethod.POST })
	public ModelAndView eqpmnManageList(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto, HttpSession session, ModelMap model) throws Exception {
		Map<String, Object> map = eqpmnService.eqpmnManageList(eqpmnManageDto);
		ModelAndView modelAndView = new ModelAndView("jsonView", map);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/eqpmn/selectEqpmnListCode.ajax", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<eqpmnManageDto> selectEqpmnListCode(@ModelAttribute("eqpmnManageDto") eqpmnManageDto eqpmnManageDto,HttpSession session, ModelMap model) throws Exception {
		
		List<eqpmnManageDto> list = eqpmnService.selectEqpmnListCode();
		return list;
	}
}
