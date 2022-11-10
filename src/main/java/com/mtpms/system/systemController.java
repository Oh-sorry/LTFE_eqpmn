package com.mtpms.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.mtpms.common.service.commonService;
import com.mtpms.dto.codeManageDto;
import com.mtpms.dto.comCodeDto;
import com.mtpms.dto.loginDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.userManageDto;
import com.mtpms.dto.db.pmscodeDto;
import com.mtpms.dto.db.pmsmenuDto;
import com.mtpms.dto.db.pmsprojectDto;
import com.mtpms.dto.db.pmsuserDto;
import com.mtpms.dto.db.pmsuserprojectDto;
import com.mtpms.system.service.systemService;

import egovframework.com.EgovMessageSource;

@Controller
public class systemController {

	@Resource(name = "systemService")
	private systemService systemService;

	@Resource(name = "commonService")
	private commonService commonService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    protected static Logger logger = Logger.getLogger(Main.class.getName());

    @RequestMapping(value="/system/userMangeList.do", method={RequestMethod.GET, RequestMethod.POST})
    public String userManger(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	List<comCodeDto> systemGrade = commonService.selectComCodeList("00003");
    	List<projectManageDto> projectList = commonService.selectProjectList();

    	model.addAttribute("systemGrade", systemGrade);
    	model.addAttribute("projectList", projectList);

   		model.addAttribute("pageUrl", "/system/userManageList.jsp");
   		return "main";
    }

    @RequestMapping(value="/system/userMangeList.ajax", method={RequestMethod.POST})
    public ModelAndView selectListAjax(@ModelAttribute("userManageDto") userManageDto userManageDto, HttpSession session, Model model) throws Exception {

		Map<String, Object> map = systemService.selectUserManageList(userManageDto);
		ModelAndView modelAndView = new ModelAndView("jsonView",map);

		return modelAndView;
    }

    @RequestMapping(value="/system/userMangeUpdate.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> userMangeUpdate(@ModelAttribute("pmsuserDto") pmsuserDto pmsuserDto, HttpSession session, Model model) throws Exception {

		int rtn = systemService.pmsuserUpdate(pmsuserDto);

		logger.info("rtn =>" + rtn);
		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/userPasswdUpdate.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> userPasswdUpdate(@ModelAttribute("pmsuserDto") pmsuserDto pmsuserDto, HttpSession session, Model model) throws Exception {

    	loginDto loginDto = (loginDto) session.getAttribute("loginDto");
    	String userid = loginDto.getUserid();

    	pmsuserDto.setUserid(userid);

		int rtn = systemService.pmsuserpassUpdate(pmsuserDto);

		logger.info("rtn =>" + rtn);
		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/selectUserDupChk.ajax", method={RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody int selectUserDupChk(@ModelAttribute("pmsuserDto") pmsuserDto pmsuserDto, HttpSession session, Model model) throws Exception {

    	int rtn = systemService.selectUserDupChk(pmsuserDto);
    	return rtn;
    }

    @RequestMapping(value="/system/userMangeDelete.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> userMangeDelete(@ModelAttribute("pmsuserDto") pmsuserDto pmsuserDto, HttpSession session, Model model) throws Exception {

		int rtn = systemService.pmsuserDelete(pmsuserDto);

		logger.info("rtn =>" + rtn);
		if (rtn > 0) {
			rtn = systemService.pmsuserProjectDelete(pmsuserDto.getUserid());
			logger.info("rtn =>" + rtn);
		}

		ResponseEntity<String> resRtn = null;
   		if (rtn >= 0) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/userProjectUpdate.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> userProjectUpdate(@ModelAttribute("pmsuserprojectDto") pmsuserprojectDto pmsuserprojectDto, HttpSession session, Model model) throws Exception {

    	logger.info("userid = > "+ pmsuserprojectDto.getUserid());
    	logger.info("projectid = > "+ pmsuserprojectDto.getProjectid());

    	String[] projectId = pmsuserprojectDto.getProjectid().split(",");

		int rtn = systemService.pmsuserProjectDelete(pmsuserprojectDto.getUserid());

		if (rtn >= 0) {
	    	for (int i=0; i < projectId.length; i++) {
	    		if (!"".equals(projectId[i])) {
	    			pmsuserprojectDto insertDto = new pmsuserprojectDto();

	    			insertDto.setUserid(pmsuserprojectDto.getUserid());
	    			insertDto.setProjectid(projectId[i]);

	    			rtn = systemService.pmsuserProjectInsert(insertDto);

	    			if (rtn == 0) break;
	    		}
	    	}
		}
		//int rtn = systemService.pmsuserProjectDelete(pmsuserprojectDto);

		logger.info("rtn =>" + rtn);

		ResponseEntity<String> resRtn = null;
   		if (rtn >= 0) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/menuMangeList.do", method={RequestMethod.GET, RequestMethod.POST})
    public String menuMangeList(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	List<comCodeDto> systemGrade = commonService.selectComCodeList("00003");

    	model.addAttribute("systemGrade", systemGrade);

    	model.addAttribute("pageUrl", "/system/menuManageList.jsp");
   		return "main";
    }

    @RequestMapping(value="/system/menuMangeList.ajax", method={RequestMethod.POST})
    public ModelAndView menuMangeList(@ModelAttribute("menuManageDto") menuManageDto menuManageDto, HttpSession session, Model model) throws Exception {

		Map<String, Object> map = systemService.selectMenuManageList(menuManageDto);
		ModelAndView modelAndView = new ModelAndView("jsonView",map);

		return modelAndView;
    }

    @RequestMapping(value="/system/selectParentMenuList.ajax", method={RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody List<menuManageDto> selectParentMenuList(@ModelAttribute("menuManageDto") menuManageDto menuManageDto, HttpSession session, Model model) throws Exception {

    	List<menuManageDto> list = systemService.selectParentMenuList();
    	return list;
    }

    @RequestMapping(value="/system/menuMangeUpdate.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> menuMangeUpdate(@ModelAttribute("pmsmenuDto") pmsmenuDto pmsmenuDto, HttpSession session, Model model) throws Exception {

		int rtn = systemService.pmsmenuUpdate(pmsmenuDto);

		logger.info("rtn =>" + rtn);

		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/menuMangeDelete.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> menuMangeDelete(@ModelAttribute("pmsmenuDto") pmsmenuDto pmsmenuDto, HttpSession session, Model model) throws Exception {

		int rtn = systemService.pmsmenuDelete(pmsmenuDto);

		logger.info("rtn =>" + rtn);

		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/codeMangeList.do", method={RequestMethod.GET, RequestMethod.POST})
    public String codeMangeList(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

   		model.addAttribute("pageUrl", "/system/codeManageList.jsp");
   		return "main";
    }

    @RequestMapping(value="/system/codeMangeList.ajax", method={RequestMethod.POST})
    public ModelAndView codeMangeList(@ModelAttribute("codeManageDto") codeManageDto codeManageDto, HttpSession session, Model model) throws Exception {

		Map<String, Object> map = systemService.selectCodeManageList(codeManageDto);
		ModelAndView modelAndView = new ModelAndView("jsonView",map);

		return modelAndView;
    }

    @RequestMapping(value="/system/selectParentCodeList.ajax", method={RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody List<codeManageDto> selectParentCodeList(@ModelAttribute("codeManageDto") codeManageDto codeManageDto, HttpSession session, Model model) throws Exception {

    	List<codeManageDto> list = systemService.selectParentCodeList();
    	return list;
    }

    @RequestMapping(value="/system/codeMangeUpdate.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> codeMangeUpdate(@ModelAttribute("pmscodeDto") pmscodeDto pmscodeDto, HttpSession session, Model model) throws Exception {

		int rtn = systemService.pmscodeUpdate(pmscodeDto);

		logger.info("rtn =>" + rtn);

		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/codeMangeDelete.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> codeMangeDelete(@ModelAttribute("pmscodeDto") pmscodeDto pmscodeDto, HttpSession session, Model model) throws Exception {

		int rtn = systemService.pmscodeDelete(pmscodeDto);

		logger.info("rtn =>" + rtn);

		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/projectMangeList.do", method={RequestMethod.GET, RequestMethod.POST})
    public String projectMangeList(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

   		model.addAttribute("pageUrl", "/system/projectManageList.jsp");
   		return "main";
    }

    @RequestMapping(value="/system/projectMangeList.ajax", method={RequestMethod.POST})
    public ModelAndView projectMangeList(@ModelAttribute("projectManageDto") projectManageDto projectManageDto, HttpSession session, Model model) throws Exception {

		Map<String, Object> map = systemService.selectProjectManageList(projectManageDto);
		ModelAndView modelAndView = new ModelAndView("jsonView",map);
		return modelAndView;
    }

    @RequestMapping(value="/system/projectMangeUpdate.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> projectMangeUpdate(@ModelAttribute("pmsprojectDto") pmsprojectDto pmsprojectDto, HttpSession session, Model model) throws Exception {

    	pmsmenuDto pmsmenuDto = new pmsmenuDto();

    	int rtn = 0;
    	if ("0".equals(pmsprojectDto.getProjectid())) {
    		String projectid = systemService.selectProjectidSeq();

    		pmsprojectDto.setProjectid(projectid);
    		rtn = systemService.pmsprojectInsert(pmsprojectDto);

    		if (rtn == 1) {
    			long menuid = systemService.selectmenuidseq();
    			pmsmenuDto.setMenuid(menuid);
    			pmsmenuDto.setMenuname(pmsprojectDto.getProjectname());
    			pmsmenuDto.setParentmenuid(0);
    			pmsmenuDto.setSortno(menuid);
    			pmsmenuDto.setSystemgrade("00005");
    			pmsmenuDto.setProjectid(projectid);
    			pmsmenuDto.setProgramurl("/bbs/bbsMangeList.do");
    			pmsmenuDto.setUseyn("Y");

    			rtn = systemService.pmsmenuInsert(pmsmenuDto);

    			if (rtn == 1) {
    				long childMenuid = systemService.selectmenuidseq();
        			pmsmenuDto.setMenuid(childMenuid);
        			pmsmenuDto.setMenuname("공지사항");
        			pmsmenuDto.setParentmenuid(menuid);
        			pmsmenuDto.setSortno(1);
        			pmsmenuDto.setSystemgrade("00005");
        			pmsmenuDto.setProjectid(projectid);
        			pmsmenuDto.setProgramurl("/bbs/bbsMangeList.do");
        			pmsmenuDto.setUseyn("Y");

        			rtn = systemService.pmsmenuInsert(pmsmenuDto);
    			}
    		}

    	} else {
    		rtn = systemService.pmsprojectUpdate(pmsprojectDto);
    	}


		logger.info("rtn =>" + rtn);

		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }

    @RequestMapping(value="/system/projectMangeDelete.ajax", method={RequestMethod.POST})
    public ResponseEntity<String> projectMangeDelete(@ModelAttribute("pmsprojectDto") pmsprojectDto pmsprojectDto, HttpSession session, Model model) throws Exception {

		int rtn = systemService.pmsprojectDelete(pmsprojectDto);

		logger.info("rtn =>" + rtn);

		ResponseEntity<String> resRtn = null;
   		if (rtn == 1) {
   			resRtn = new ResponseEntity<>(HttpStatus.OK);
   		} else {
   			resRtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   		}

   		return resRtn;
    }
}
