package com.mtpms.bbs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtpms.bbs.service.bbsService;
import com.mtpms.common.fileUtil;
import com.mtpms.common.service.commonService;
import com.mtpms.dto.bbsManageDto;
import com.mtpms.dto.fileDto;
import com.mtpms.dto.loginDto;
import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.projectManageDto;
import com.mtpms.dto.db.pmsbbsDto;
import com.mtpms.dto.db.pmsbbsfileDto;

import egovframework.com.EgovMessageSource;

@Controller
public class bbsController {

	@Resource(name = "bbsService")
	private bbsService bbsService;

	@Resource(name = "commonService")
	private commonService commonService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "fileUtil")
    private fileUtil fileUtil;

    @Autowired
    private DefaultBeanValidator beanValidator;

    protected static Logger logger = Logger.getLogger(Main.class.getName());


    @RequestMapping(value="/bbs/bbsChatList.do", method={RequestMethod.GET, RequestMethod.POST})
    public String bbsChatList(@ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	loginDto loginDto = (loginDto) session.getAttribute("loginDto");
    	String userid = loginDto.getUserid();

    	List<projectManageDto> projectList = commonService.selectUserProjectList(userid);
    	List<menuManageDto> menuSelectList = commonService.selectMenuList(userid);

    	bbsManageDto.setUserid(userid);

    	if (bbsManageDto.getPageno() == 0) bbsManageDto.setPageno(1);

    	long totCnt = bbsService.selectBbsListCnt(bbsManageDto);

    	PaginationInfo paginationInfo = new PaginationInfo();
    	paginationInfo.setCurrentPageNo(bbsManageDto.getPageno());
    	paginationInfo.setRecordCountPerPage(propertyService.getInt("chatPageUnit"));
    	paginationInfo.setPageSize(propertyService.getInt("pageSize"));
    	paginationInfo.setTotalRecordCount((int)totCnt);

    	bbsManageDto.setStartno(paginationInfo.getFirstRecordIndex()+1);
    	bbsManageDto.setEndno(paginationInfo.getLastRecordIndex());

    	logger.info("totCnt =>"+totCnt);
    	logger.info("Startno =>"+paginationInfo.getFirstRecordIndex());
    	logger.info("Endno =>"+paginationInfo.getLastRecordIndex());

    	List<bbsManageDto> bbsList = bbsService.selectBbsList(bbsManageDto);

    	for (int i=0; i < bbsList.size(); i++) {
    		List<pmsbbsfileDto> pmsbbsfileDtoList = new ArrayList<pmsbbsfileDto>();
    		pmsbbsfileDtoList = bbsService.selectBbsFileInfo((bbsManageDto) bbsList.get(i));

    		bbsList.get(i).setPmsbbsfileDtoList(pmsbbsfileDtoList);
    	}

    	ObjectMapper objectMapper = new ObjectMapper();
    	Map result = objectMapper.convertValue(bbsManageDto, Map.class);

    	model.addAttribute("bbsList", bbsList);
    	model.addAttribute("searchFormData", result);
    	model.addAttribute("projectList", projectList);
    	model.addAttribute("menuSelectList", menuSelectList);
    	model.addAttribute("paginationInfo", paginationInfo);

   		model.addAttribute("pageUrl", "/bbs/bbsChatList.jsp");
   		return "main";
    }

    @RequestMapping(value="/bbs/bbsChatInput.do", method={RequestMethod.POST})
    public String bbsChatInput(@ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	loginDto loginDto = (loginDto) session.getAttribute("loginDto");
    	String userid = loginDto.getUserid();

    	List<projectManageDto> projectList = commonService.selectUserProjectList(userid);

    	bbsManageDto.setUserid(loginDto.getUserid());
    	bbsManageDto.setUsername(loginDto.getUsername());

    	if (bbsManageDto.getBbsid() == 0) {
	    	SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar calendar = Calendar.getInstance();

	        Date dateObj = calendar.getTime();
	        String today = dtf.format(dateObj);

	        bbsManageDto.setGesidate(today);
	        bbsManageDto.setProjectid(bbsManageDto.getSearchprojectid());
    	}

    	Map result = new ObjectMapper().convertValue(bbsManageDto, Map.class);

    	model.addAttribute("inputFormData", result);
    	model.addAttribute("projectList", projectList);
    	model.addAttribute("pmsbbsfileDtoList", bbsManageDto.getPmsbbsfileDtoList());

   		model.addAttribute("pageUrl", "/bbs/bbsChatInput.jsp");
   		return "main";
    }


    @RequestMapping(value="/bbs/bbsChatSave.do", method={RequestMethod.POST})
    public String bbsChatSave(final MultipartHttpServletRequest multiRequest, @ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	int rtn = 0;

    	List<pmsbbsfileDto> pmsbbsfileDtoList = bbsManageDto.getPmsbbsfileDtoList();
    	List<pmsbbsfileDto> pmsbbsfileDtoListnew = new ArrayList<pmsbbsfileDto>();

    	if (pmsbbsfileDtoList == null) pmsbbsfileDtoList = new ArrayList<pmsbbsfileDto>();

    	pmsbbsDto pmsbbsdto = new pmsbbsDto();

    	if ("Y".equals(bbsManageDto.getChildsave())) {
    		BeanUtils.copyProperties(pmsbbsdto, bbsManageDto.getBbsChildInfo().get(bbsManageDto.getChildno()));
    	} else {
    		BeanUtils.copyProperties(pmsbbsdto, bbsManageDto);
    	}

    	long bbsid = pmsbbsdto.getBbsid();

    	// 신규
	    if (bbsid == 0) {
	    	bbsid = bbsService.selectBbsSeq();
	    	pmsbbsdto.setBbsid(bbsid);
	    	if (!"Y".equals(bbsManageDto.getChildsave())) {
	    		bbsManageDto.setBbsid(bbsid);
	    	}
	    }

	    rtn = bbsService.pmsbbsUpdate(pmsbbsdto);

	    final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    for (int i = 0; i < pmsbbsfileDtoList.size(); i++) {

	    	if ("N".equals(pmsbbsfileDtoList.get(i).getUseyn())) {
	    		rtn = bbsService.pmsbbsFileDelete((pmsbbsfileDto)pmsbbsfileDtoList.get(i));

	    		if (rtn < 1) {
	    			pmsbbsfileDtoListnew.add((pmsbbsfileDto)pmsbbsfileDtoList.get(i));
	    		}
	    	}
	    }

	    if (rtn > 0) {
		    if (!files.isEmpty()) {
		    	List<fileDto> resultFileList = null;
		    	resultFileList = fileUtil.parseFileInf(files, "BBS_", 0, "", "");

			    for (int i =0; i < resultFileList.size(); i++) {
			    	fileDto filedto = (fileDto)resultFileList.get(i);
			    	pmsbbsfileDto newPmsbbsfile = new pmsbbsfileDto();

			    	newPmsbbsfile.setServerpath(filedto.getFileStreCours());
			    	newPmsbbsfile.setLocalfile(filedto.getOrignlFileNm());
			    	newPmsbbsfile.setServerfile(filedto.getStreFileNm());
			    	newPmsbbsfile.setFilesize(Long.parseLong(filedto.getFileMg()));
			    	newPmsbbsfile.setExt(filedto.getFileExtsn());
			    	newPmsbbsfile.setUseyn("Y");
			    	long bbsfileid = bbsService.selectBbsFileSeq();
			    	newPmsbbsfile.setFileid(bbsfileid);
			    	newPmsbbsfile.setBbsid(bbsid);

			    	pmsbbsfileDtoListnew.add(newPmsbbsfile);

			    	rtn = bbsService.pmsbbsFileInsert(newPmsbbsfile);
			    	if (rtn < 1) break;
			    }
		    }
	    }

    	Map inputFormDataMap = new ObjectMapper().convertValue(bbsManageDto, Map.class);

	    if (rtn < 1) {
	    	List<projectManageDto> projectList = commonService.selectUserProjectList(bbsManageDto.getUserid());

	    	model.addAttribute("msg", "저장시 오류가 발생 되었습니다.");
	    	bbsManageDto.setPmsbbsfileDtoList(pmsbbsfileDtoListnew);
	    	return bbsChatInput(bbsManageDto, request, reponse, session, model);
	    } else {
	    	model.addAttribute("msg", "정상적으로 저장 되었습니다.");
	    	return bbsChatInput(bbsManageDto, request, reponse, session, model);
	    }
    }

    @RequestMapping(value="/bbs/bbsMangeList.do", method={RequestMethod.GET, RequestMethod.POST})
    public String bbsMangeList(@ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	loginDto loginDto = (loginDto) session.getAttribute("loginDto");
    	String userid = loginDto.getUserid();

    	if (bbsManageDto == null || bbsManageDto.getLeftMenuId() == 0) {
    		return "main";
    	} else {

	    	List<projectManageDto> projectList = commonService.selectUserProjectList(userid);

	    	bbsManageDto.setUserid(userid);

	    	if (bbsManageDto.getPageno() == 0) bbsManageDto.setPageno(1);

	    	long totCnt = bbsService.selectBbsListCnt(bbsManageDto);

	    	PaginationInfo paginationInfo = new PaginationInfo();
	    	paginationInfo.setCurrentPageNo(bbsManageDto.getPageno());
	    	paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));
	    	paginationInfo.setPageSize(propertyService.getInt("pageSize"));
	    	paginationInfo.setTotalRecordCount((int)totCnt);

	    	bbsManageDto.setStartno(paginationInfo.getFirstRecordIndex()+1);
	    	bbsManageDto.setEndno(paginationInfo.getLastRecordIndex());

	    	logger.info("totCnt =>"+totCnt);
	    	logger.info("Startno =>"+paginationInfo.getFirstRecordIndex());
	    	logger.info("Endno =>"+paginationInfo.getLastRecordIndex());

	    	List<bbsManageDto> bbsList = bbsService.selectBbsList(bbsManageDto);

	    	ObjectMapper objectMapper = new ObjectMapper();
	    	Map result = objectMapper.convertValue(bbsManageDto, Map.class);

	    	model.addAttribute("bbsList", bbsList);
	    	model.addAttribute("searchFormData", result);
	    	model.addAttribute("projectList", projectList);
	    	model.addAttribute("paginationInfo", paginationInfo);

	   		model.addAttribute("pageUrl", "/bbs/bbsMangeList.jsp");
	   		return "main";
    	}
    }

    @RequestMapping(value="/bbs/bbsMangeInput.do", method={RequestMethod.POST})
    public String bbsMangeInput(@ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	loginDto loginDto = (loginDto) session.getAttribute("loginDto");
    	String userid = loginDto.getUserid();

    	if (bbsManageDto == null || bbsManageDto.getLeftMenuId() == 0) {
    		return "main";
    	} else {

	    	List<projectManageDto> projectList = commonService.selectUserProjectList(userid);

	    	bbsManageDto.setUserid(loginDto.getUserid());
	    	bbsManageDto.setUsername(loginDto.getUsername());

	    	if (bbsManageDto.getBbsid() == 0) {
		    	SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
		        Calendar calendar = Calendar.getInstance();

		        Date dateObj = calendar.getTime();
		        String today = dtf.format(dateObj);

		        bbsManageDto.setGesidate(today);
		        bbsManageDto.setProjectid(bbsManageDto.getSearchprojectid());
	    	}

	    	Map result = new ObjectMapper().convertValue(bbsManageDto, Map.class);

	    	model.addAttribute("inputFormData", result);
	    	model.addAttribute("projectList", projectList);
	    	model.addAttribute("pmsbbsfileDtoList", bbsManageDto.getPmsbbsfileDtoList());

	   		model.addAttribute("pageUrl", "/bbs/bbsMangeInput.jsp");
	   		return "main";

    	}
    }


    @RequestMapping(value="/bbs/bbsMangeSave.do", method={RequestMethod.POST})
    public String bbsMangeSave(final MultipartHttpServletRequest multiRequest, @ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	int rtn = 0;

    	List<pmsbbsfileDto> pmsbbsfileDtoList = bbsManageDto.getPmsbbsfileDtoList();
    	List<pmsbbsfileDto> pmsbbsfileDtoListnew = new ArrayList<pmsbbsfileDto>();

    	if (pmsbbsfileDtoList == null) pmsbbsfileDtoList = new ArrayList<pmsbbsfileDto>();

    	pmsbbsDto pmsbbsdto = new pmsbbsDto();

    	if ("Y".equals(bbsManageDto.getChildsave())) {
    		BeanUtils.copyProperties(pmsbbsdto, bbsManageDto.getBbsChildInfo().get(bbsManageDto.getChildno()));
    	} else {
    		BeanUtils.copyProperties(pmsbbsdto, bbsManageDto);
    	}

    	long bbsid = pmsbbsdto.getBbsid();

    	// 신규
	    if (bbsid == 0) {
	    	bbsid = bbsService.selectBbsSeq();
	    	pmsbbsdto.setBbsid(bbsid);
	    	if (!"Y".equals(bbsManageDto.getChildsave())) {
	    		bbsManageDto.setBbsid(bbsid);
	    	}
	    }

	    rtn = bbsService.pmsbbsUpdate(pmsbbsdto);

	    final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    for (int i = 0; i < pmsbbsfileDtoList.size(); i++) {

	    	if ("N".equals(pmsbbsfileDtoList.get(i).getUseyn())) {
	    		rtn = bbsService.pmsbbsFileDelete((pmsbbsfileDto)pmsbbsfileDtoList.get(i));

	    		if (rtn < 1) {
	    			pmsbbsfileDtoListnew.add((pmsbbsfileDto)pmsbbsfileDtoList.get(i));
	    		}
	    	}
	    }

	    if (rtn > 0) {
		    if (!files.isEmpty()) {
		    	List<fileDto> resultFileList = null;
		    	resultFileList = fileUtil.parseFileInf(files, "BBS_", 0, "", "");

			    for (int i =0; i < resultFileList.size(); i++) {
			    	fileDto filedto = (fileDto)resultFileList.get(i);
			    	pmsbbsfileDto newPmsbbsfile = new pmsbbsfileDto();

			    	newPmsbbsfile.setServerpath(filedto.getFileStreCours());
			    	newPmsbbsfile.setLocalfile(filedto.getOrignlFileNm());
			    	newPmsbbsfile.setServerfile(filedto.getStreFileNm());
			    	newPmsbbsfile.setFilesize(Long.parseLong(filedto.getFileMg()));
			    	newPmsbbsfile.setExt(filedto.getFileExtsn());
			    	newPmsbbsfile.setUseyn("Y");
			    	long bbsfileid = bbsService.selectBbsFileSeq();
			    	newPmsbbsfile.setFileid(bbsfileid);
			    	newPmsbbsfile.setBbsid(bbsid);

			    	pmsbbsfileDtoListnew.add(newPmsbbsfile);

			    	rtn = bbsService.pmsbbsFileInsert(newPmsbbsfile);
			    	if (rtn < 1) break;
			    }
		    }
	    }

    	Map inputFormDataMap = new ObjectMapper().convertValue(bbsManageDto, Map.class);

	    if (rtn < 1) {
	    	List<projectManageDto> projectList = commonService.selectUserProjectList(bbsManageDto.getUserid());

	    	model.addAttribute("msg", "저장시 오류가 발생 되었습니다.");
	    	bbsManageDto.setPmsbbsfileDtoList(pmsbbsfileDtoListnew);
	    	return bbsMangeInput(bbsManageDto, request, reponse, session, model);
	    } else {
	    	model.addAttribute("msg", "정상적으로 저장 되었습니다.");
	    	return bbsMangeView(bbsManageDto, request, reponse, session, model);
	    }
    }

    @RequestMapping(value="/bbs/bbsMangeView.do", method={RequestMethod.POST})
    public String bbsMangeView(@ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletRequest request, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	logger.info("bbsid =>"+bbsManageDto.getBbsid());

    	loginDto loginDto = (loginDto) session.getAttribute("loginDto");

	    List<projectManageDto> projectList = commonService.selectUserProjectList(bbsManageDto.getUserid());

	    bbsManageDto bbsInfo = bbsService.selectBbsInfo(bbsManageDto);

		logger.info("loginDto.getUserid() = >" + loginDto.getUserid());
		logger.info("bbsInfo.getUserid() = >" + bbsInfo.getUserid());
		logger.info("bbsInfo.getBbsid() = >" + bbsInfo.getBbsid());

    	if (!loginDto.getUserid().equals(bbsInfo.getUserid())) {
    		logger.info("pmsbbsviewcnt = > ");
    		bbsService.pmsbbsviewcnt(bbsInfo);
    	}

	    List<pmsbbsfileDto> pmsbbsfileDtoList = new ArrayList<pmsbbsfileDto>();
	    pmsbbsfileDtoList = bbsService.selectBbsFileInfo(bbsManageDto);

    	List<bbsManageDto> bbsChildInfoList = bbsService.selectChildBbsInfo(bbsManageDto);

    	for (int i =0; i < bbsChildInfoList.size(); i++) {
    		List<pmsbbsfileDto> pmsbbsChilefileDtoList = new ArrayList<pmsbbsfileDto>();
    		pmsbbsChilefileDtoList = bbsService.selectBbsFileInfo(bbsChildInfoList.get(i));

    		bbsChildInfoList.get(i).setPmsbbsfileDtoList(pmsbbsChilefileDtoList);
    	}

    	Map inputFormDataMap = new ObjectMapper().convertValue(bbsManageDto, Map.class);
    	model.addAttribute("inputFormData", inputFormDataMap);
    	model.addAttribute("bbsInfo", bbsInfo);
    	model.addAttribute("pmsbbsfileDtoList", pmsbbsfileDtoList);
    	model.addAttribute("bbsChildInfoList", bbsChildInfoList);
    	model.addAttribute("projectList", projectList);

    	logger.info("bbsid12345 =>"+bbsManageDto.getBbsid());

   		model.addAttribute("pageUrl", "/bbs/bbsMangeView.jsp");
	    return "main";
    }


    @RequestMapping(value="/bbs/bbsMangeDelete.do", method={RequestMethod.POST})
    public String bbsMangeDelete(@ModelAttribute("bbsManageDto") bbsManageDto bbsManageDto, HttpServletResponse reponse, HttpSession session, ModelMap model) throws Exception {

    	int rtn = 0;

    	if ("Y".equals(bbsManageDto.getChildsave())) {
    		rtn = bbsService.pmsbbsDelete(bbsManageDto.getBbsChildInfo().get(bbsManageDto.getChildno()));

    		if (rtn < 1) {
    	    	model.addAttribute("msg", "삭제시 오류가 발생 되었습니다.");
    	    } else {
    	    	model.addAttribute("msg", "정상적으로 삭제 되었습니다.");
    	    }

	   		return "forward:/bbs/bbsMangeView.do";

    	} else {
    		rtn = bbsService.pmsbbsDelete(bbsManageDto);

    		if (rtn < 1) {
    	    	model.addAttribute("msg", "삭제시 오류가 발생 되었습니다.");
    	   		return "forward:"+bbsManageDto.getSearchreturnurl();

    	    } else {
    	    	model.addAttribute("msg", "정상적으로 삭제 되었습니다.");
    	    	return "forward:"+bbsManageDto.getSearchreturnurl();
    	    }
    	}

    }
}
