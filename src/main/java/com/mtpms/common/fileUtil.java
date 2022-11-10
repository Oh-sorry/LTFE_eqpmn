package com.mtpms.common;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtpms.common.service.commonService;
import com.mtpms.dto.fileDto;
import com.mtpms.dto.db.pmsbbsfileDto;

@Component("fileUtil")
public class fileUtil {

    public static final int BUFF_SIZE = 2048;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name = "commonService")
    protected commonService commonService;

    protected static Logger logger = Logger.getLogger(fileUtil.class.getName());

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<fileDto> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
    	int fileKey = fileKeyParam;

    	String storePathString = "";
    	String atchFileIdString = "";

		if ("".equals(storePath) || storePath == null) {
		    storePathString = propertyService.getString("Globals.fileStorePath");
		} else {
		    storePathString = propertyService.getString(storePath);
		}

		if (!"".equals(atchFileId) && atchFileId != null) {
		    atchFileIdString = atchFileId;
		}

		File saveFolder = new File(storePathString);

		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<fileDto> result  = new ArrayList<fileDto>();
		fileDto fvo;

		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();

		    file = entry.getValue();
		    String orginFileName = file.getOriginalFilename();

		    //--------------------------------------
		    // 원 파일명이 없는 경우 처리
		    // (첨부가 되지 않은 input file type)
		    //--------------------------------------
		    if ("".equals(orginFileName)) {
		    	continue;
		    }
		    ////------------------------------------

		    int index = orginFileName.lastIndexOf(".");
		    String fileExt = orginFileName.substring(index + 1);
		    String newName = KeyStr + stringUtil.getTimeStamp() + fileKey;
		    long _size = file.getSize();

		    if (!"".equals(orginFileName)) {
		    	filePath = storePathString + File.separator + newName;
		    	file.transferTo(new File(filePath));
		    }

		    fvo = new fileDto();
		    fvo.setFileExtsn(fileExt);
		    fvo.setFileStreCours(storePathString);
		    fvo.setFileMg(Long.toString(_size));
		    fvo.setOrignlFileNm(orginFileName);
		    fvo.setStreFileNm(newName);
		    fvo.setAtchFileId(atchFileIdString);
		    fvo.setFileSn(String.valueOf(fileKey));

		    writeFile(file, newName, storePathString);

		    result.add(fvo);

		    fileKey++;
		}

		return result;
    }

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
    	InputStream stream = null;
    	OutputStream bos = null;
    	newName = stringUtil.isNullToString(newName).replaceAll("..", "");
    	stordFilePath = stringUtil.isNullToString(stordFilePath).replaceAll("..", "");
    	try {
    		stream = file.getInputStream();
    		File cFile = new File(stordFilePath);

    		if (!cFile.isDirectory())
    			cFile.mkdir();

    		bos = new FileOutputStream(stordFilePath + File.separator + newName);

    		int bytesRead = 0;
    		byte[] buffer = new byte[BUFF_SIZE];

    		while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
    			bos.write(buffer, 0, bytesRead);
    		}
    	} catch (FileNotFoundException fnfe) {
    		logger.debug("fnfe: {}", fnfe);
    	} catch (IOException ioe) {
    		logger.debug("ioe: {}", ioe);
    	} catch (Exception e) {
    		logger.debug("e: {}", e);
    	} finally {
    		if (bos != null) {
    			try {
    				bos.close();
    			} catch (Exception ignore) {
    				logger.debug("IGNORED: "+ ignore.getMessage());
    			}
    		}
    		if (stream != null) {
    			try {
    				stream.close();
    			} catch (Exception ignore) {
    				logger.debug("IGNORED: "+ ignore.getMessage());
    			}
    		}
    	}
    }

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			//throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/fileDownload.do", method={RequestMethod.GET, RequestMethod.POST})
	public void fileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		long fileid = (long) commandMap.get("fileid");

		pmsbbsfileDto pmsbbsfileDto = commonService.selectpmsbbsFileInfo(fileid);

		File uFile = new File(pmsbbsfileDto.getServerpath(), pmsbbsfileDto.getServerfile());
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			//response.setBufferSize(fSize);	// OutOfMemeory 발생
			response.setContentType(mimetype);
			//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
			setDisposition(pmsbbsfileDto.getLocalfile(), request, response);
			//response.setContentLength(fSize);

			/*
			 * FileCopyUtils.copy(in, response.getOutputStream());
			 * in.close();
			 * response.getOutputStream().flush();
			 * response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				logger.debug("IGNORED: "+ ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						logger.debug("IGNORED: "+ ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						logger.debug("IGNORED: "+ ignore.getMessage());
					}
				}
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + pmsbbsfileDto.getLocalfile() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

}
