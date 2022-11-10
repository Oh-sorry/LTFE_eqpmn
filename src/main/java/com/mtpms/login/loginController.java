package com.mtpms.login;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.mtpms.dto.loginDto;
import com.mtpms.login.service.loginService;

import egovframework.com.EgovMessageSource;
import egovframework.com.EgovUserDetailsHelper;

@Controller
public class loginController {

	@Resource(name = "loginService")
	private loginService loginService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    protected static Logger logger = Logger.getLogger(Main.class.getName());

    @RequestMapping(value="/login.do", method={RequestMethod.GET,RequestMethod.POST})
    public String loginView(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {
    	loginDto loginDto = (loginDto) EgovUserDetailsHelper.getAuthenticatedUser();

		if (loginDto.getUserid() != null) {
			return "redirect:/main/main.do";
		} else {
			return "login";
		}
    }

    @RequestMapping(value="/loginChk.do", method={RequestMethod.POST})
    public String loginDo(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, @ModelAttribute("loginDto") loginDto loginDto, ModelMap model) throws Exception {

		logger.info("isMobile => "+ loginDto.getIsMobile());
		logger.info("getUserid => "+ loginDto.getUserid());
		logger.info("passwd => "+ loginDto.getUserpass());

    	int rtn = loginService.selecLoginChk(loginDto);

    	logger.info("rtn => "+ rtn);

		if (rtn == 1) {
			loginDto loginInfoDto = loginService.selecLoginInfo(loginDto);

			logger.info("getUserid => "+ loginInfoDto.getUserid());
			logger.info("getUsername => "+ loginInfoDto.getUsername());
			logger.info("getUsergrade => "+ loginInfoDto.getUsergrade());

			loginInfoDto.setIsMobile(loginDto.getIsMobile());

			if ("init00!".equals(loginDto.getUserpass())) {
				loginInfoDto.setInitpass("Y");
			}

			session.setAttribute("loginDto", loginInfoDto);

           	return "redirect:/main/main.do";

		} else {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	    	return "login";
		}
    }

    @RequestMapping(value="/logOut.do", method={RequestMethod.GET,RequestMethod.POST})
    public String logOut(HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	//loginDto loginDto = null;
    	//session.setAttribute("loginDto", loginDto);
    	session.invalidate();

   		return "forward:/login.do";
    }
}
