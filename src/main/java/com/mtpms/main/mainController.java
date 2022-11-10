package com.mtpms.main;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.mtpms.dto.loginDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.main.service.mainService;

import egovframework.com.EgovMessageSource;

@Controller
public class mainController {

	@Resource(name = "mainService")
	private mainService mainService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    protected static Logger logger = Logger.getLogger(Main.class.getName());

    @RequestMapping(value="/main/main.do", method={RequestMethod.GET,RequestMethod.POST})
    public String mainPage(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	loginDto loginDto = (loginDto)session.getAttribute("loginDto");

    	String userid = loginDto.getUserid();
    	//List<pmsmenuDto> topMenulist = mainService.selectTopMenu();
    	//session.setAttribute("topMenulist", topMenulist);

    	//String topMenuId = request.getParameter("topMenuId");
    	//String topMenuName = request.getParameter("topMenuName");

    	//logger.info("topMenuId =>"+topMenuId);
    	//logger.info("topMenuName =>"+topMenuName);

    	//if (topMenuId != null && !"".equals(topMenuId)) {
    	List<menuManageDto> leftMenulist = mainService.selectLeftMenu(userid);

    	logger.info("leftMenulist =>"+leftMenulist);

        session.setAttribute("leftMenulist", leftMenulist);

    	//return "main";
        return "forward:/bbs/bbsChatList.do";
    }

}
