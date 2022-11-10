<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link href="${pageContext.request.contextPath}/summernote/summernote-bs4.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/summernote/summernote-bs4.js"></script>
<script src="${pageContext.request.contextPath}/summernote/lang/summernote-ko-KR.js"></script>

<script lang="javascript">
	$(document).ready(function(){
		$('#btn_close').click(function () {
			$('#inputForm').attr('action', "${pageContext.request.contextPath}${inputFormData.searchreturnurl}");
			$('#inputForm')[0].submit();
	    });
    });

	function goSave() {
		$('#inputForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeInput.do");
		$('#inputForm')[0].submit();
	}

	function goDelete() {
		if (confirm("삭제 하시겠습니까?")) {
			$('#inputForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeDelete.do");
			$('#inputForm')[0].submit();
		}
	}

	function fileDownload(fileid){
		window.open("${pageContext.request.contextPath}/fileDownload.do?fileid="+fileid);
	}

	function goChildInput(idx) {
		if (isMobile()) {
			$("#bbsChildInfoDiv_contents_"+idx).summernote(summernoteMobileSetting_h100);
		} else {
			$("#bbsChildInfoDiv_contents_"+idx).summernote(summernoteSetting_h100);
		}

		$("#btn_chile_save_"+idx).css("display","block");
	}

	function goChildSave(idx) {
		var markupStr = $("#bbsChildInfoDiv_contents_"+idx).summernote('code');

		if (markupStr == "<p><br></p>") {
			alert("내용을 입력 하세요");
			return;
		}

		$("[id=inputForm] #bbsChildInfo_contents_"+idx).val(markupStr);
		$("[id=inputForm] #childno").val(idx);
		$("[id=inputForm] #childsave").val("Y");

		$('#inputForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeSave.do");
		$('#inputForm')[0].submit();
	}

	function goChildDelte(idx) {
		if (confirm("삭제 하시겠습니까?")) {
			$("[id=inputForm] #childno").val(idx);
			$("[id=inputForm] #childsave").val("Y");

			$('#inputForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeDelete.do");
			$('#inputForm')[0].submit();
		}
	}

	$(document).ready(function(){
		if (isMobile()) {
			$("#bbsChildInfoDiv_contents_"+${fn:length(bbsChildInfoList)}).summernote(summernoteMobileSetting_h100);
		} else {
			$("#bbsChildInfoDiv_contents_"+${fn:length(bbsChildInfoList)}).summernote(summernoteSetting_h100);
		}

		setToDay("bbsChildInfo_gesidate_"+${fn:length(bbsChildInfoList)});
	});

	$(document).ready(function(){
        if ("<c:out value='${msg}'/>" != "") {
            alert("<c:out value='${msg}'/>");
        }
    });

	function isMobile() {
	    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	}
</script>

<form id="inputForm" name="inputForm" method="post" enctype="multipart/form-data">
<div class="container float-left">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> ${inputFormData.leftMenuName}</h4>
	</div>
	<p></p>
	  <input type="hidden" id="editObjectName" name="editObjectName">
	  <input type="hidden" id="leftMenuId" name="leftMenuId" value="${inputFormData.leftMenuId}">
	  <input type="hidden" id="leftMenuName" name="leftMenuName" value="${inputFormData.leftMenuName}">
	  <input type="hidden" id="pageno" name="pageno" value="${inputFormData.pageno}">
	  <input type="hidden" id="childno" name="childno" value="0">
	  <input type="hidden" id="childsave" name="childsave" value="N">
	  <c:forEach items="${inputFormData}" var="item">
	    <c:if test="${fn:indexOf(item.key,'search') >= 0}">
	  		<input type="hidden" id="${item.key}" name="${item.key}" value="${item.value}">
	  	</c:if>
	  </c:forEach>
	  <input type="hidden" id="bbsid" name="bbsid" value="${inputFormData.bbsid}">
	  <input type="hidden" id="menuid" name="menuid" value="${inputFormData.leftMenuId}">
	  <input type="hidden" id="inputtype" name="inputtype">
      <input type="hidden" class="form-control" id="userid" name="userid" readonly value="${inputFormData.userid}">

	  <input type="hidden" id="projectid" name="projectid" value="${bbsInfo.projectid}">
	  <input type="hidden" id="userid" name="userid" value="${bbsInfo.userid}">
      <input type="hidden" id="title" name="title" value="${bbsInfo.title}">
      <c:forEach var="pmsbbsfileDtoList" items="${pmsbbsfileDtoList}" varStatus="status">
		<input type="hidden" name="pmsbbsfileDtoList[${status.index}].fileid" id="pmsbbsfileDtoList_fileid_${status.index}" value="${pmsbbsfileDtoList.fileid}">
	  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].serverpath" id="pmsbbsfileDtoList_serverpath_${status.index}" value="${pmsbbsfileDtoList.serverpath}">
	  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].localfile" id="pmsbbsfileDtoList_localfile_${status.index}" value="${pmsbbsfileDtoList.localfile}">
	  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].serverfile" id="pmsbbsfileDtoList_serverfile_${status.index}" value="${pmsbbsfileDtoList.serverfile}">
	  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].useyn" id="pmsbbsfileDtoList_useyn_${status.index}" value="${pmsbbsfileDtoList.useyn}">
	  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].filesize" id="pmsbbsfileDtoList_filesize_${status.index}" value="${pmsbbsfileDtoList.filesize}">
	  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].ext" id="pmsbbsfileDtoList_ext_${status.index}" value="${pmsbbsfileDtoList.ext}">
	  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].bbsid" id="pmsbbsfileDtoList_bbsid_${status.index}" value="${pmsbbsfileDtoList.bbsid}">
	   </c:forEach>

	  <div class="form-row">
    	<div class="col">
   		  <div class="form-group control-label">
	        <label for="projectname"><i class="bi bi-check2-circle"></i> 프로젝트</label>
			<input type="text" class="form-control" id="projectname" name="projectname" readonly value="${bbsInfo.projectname}">
	      </div>
	    </div>
    	<div class="col">
   		  <div class="form-group control-label">
	        <label for="username"><i class="bi bi-check2-circle"></i> 작성자</label>
	        <input type="text" class="form-control" id="username" name="username" readonly value="${bbsInfo.username}">
	      </div>
	    </div>
	    <div class="col">
	   	  <div class="form-group control-label">
	        <label for="gesidate"><i class="bi bi-check2-circle"></i> 작성일</label>
	        <input type="text" class="form-control" id="gesidate" name="gesidate" readonly value="${bbsInfo.gesidate}">
	      </div>
	    </div>
	  </div>
	  <div class="form-row">
    	<div class="col">
	   	  <div class="form-group control-label">
	        <label for="title"><i class="bi bi-check2-circle"></i> 제목</label>
	        <pre class="border" style=" border-color: #6c757d;padding:10px;">${bbsInfo.title}</pre>
	      </div>
	    </div>
	  </div>
	  <div class="form-row">
    	<div class="col">
	   	  <div class="form-group required control-label">
	        <label for="contents"><i class="bi bi-check2-circle"></i> 내용</label>
   			<div class="border" style=" border-color: #6c757d;padding:10px;">${bbsInfo.contents}</div>
        	<textarea name="contents" id="contents" rows="10" cols="100" style="width:1050px; height:412px;display:none;">${bbsInfo.contents}</textarea>
	      </div>
	    </div>
	  </div>
	  <c:if test="${fn:length(pmsbbsfileDtoList) > 0}">
	  <div class="form-row">
    	<div class="col">
			<div class="card">
                <div class="card-header">
                    <div class="float-left"><i class="bi bi-check2-circle"></i> 첨부파일</div>
                    <div class="float-right"></div>
                </div>
                <div class="card-body">
                  <ul>
					  <c:forEach var="pmsbbsfileDtoList" items="${pmsbbsfileDtoList}" varStatus="status">
					  	<li>
				 		<i class="bi bi-file"></i> <i class="bi bi-file-${pmsbbsfileDtoList.ext}"></i> <i class="bi bi-filetype-${pmsbbsfileDtoList.ext}"></i> <a href="javascript:fileDownload('${pmsbbsfileDtoList.fileid}')">${pmsbbsfileDtoList.localfile}</a>
					    </li>
					   </c:forEach>
                   </ul>
                </div>
            </div>
		</div>
	  </div>
	  </c:if>
	 <p></p>
    <div class="form-row">
	 <div class="col">
		<p class="float-right">
		<c:if test="${bbsInfo.userid == sessionScope.loginDto.userid}">
			<button type="button" class="btn btn-sm btn-primary" id="btn_input" name="btn_input" onclick="javascript:goSave()"><i class="bi bi-pencil-fill"></i> 수정</button>
			<button type="button" class="btn btn-sm btn-primary" onclick="javascript:goDelete();"><i class="bi bi-trash"></i> 삭제</button>
		</c:if>
		<button type="button" class="btn btn-sm btn-secondary" id="btn_close" name="btn_close"><i class="bi bi-x"></i> 목록</button>
		</p>
	  </div>
	</div>
	<c:if test="${fn:length(bbsChildInfoList) > 0}">
		<c:forEach var="bbsChildInfo" items="${bbsChildInfoList}" varStatus="status">
			<input type="hidden" name="bbsChildInfo[${status.index}].bbsid" id="bbsChildInfo_bbsid_${status.index}" value="${bbsChildInfo.bbsid}">
			<input type="hidden" name="bbsChildInfo[${status.index}].menuid" id="bbsChildInfo_menuid_${status.index}" value="${bbsChildInfo.menuid}">
			<input type="hidden" name="bbsChildInfo[${status.index}].projectid" id="bbsChildInfo_projectid_${status.index}" value="${bbsChildInfo.projectid}">
			<div class="card">
                <div class="card-header">
                    <div class="float-left"><i class="bi bi-arrow-return-right"></i> <b>${bbsChildInfo.username}</b> [${bbsChildInfo.gesidate}]</div>
                    <div class="float-right">
                    	<c:if test="${bbsChildInfo.userid == sessionScope.loginDto.userid}">
                    		<button type="button" class="btn btn-ssm btn-secondary" id="btn_chile_input" name="btn_chile_input" onclick="javascript:goChildInput('${status.index}')">수정</button>
                    		<button type="button" class="btn btn-ssm btn-secondary" id="btn_chile_delete" name="btn_chile_delete" onclick="javascript:goChildDelte('${status.index}')">삭제</button>
                    	</c:if>
                    </div>
                </div>
                <div class="card-body_sm">
		       		<div style=" border-color: #6c757d;padding:10px;" name="bbsChildInfoDiv[${status.index}].contents" id="bbsChildInfoDiv_contents_${status.index}">${bbsChildInfo.contents}</div>
			        <textarea name="bbsChildInfo[${status.index}].contents" id="bbsChildInfo_contents_${status.index}" rows="10" cols="100" style="width:0px; height:0px;display:none;">${bbsChildInfo.contents}</textarea>
			        <div class="float-right" style="padding-top:5px;display:none;" id="btn_chile_save_${status.index}">
               			<button type="button" class="btn btn-sm btn-primary" id="btn_chile_save" name="btn_chile_save" onclick="javascript:goChildSave('${status.index}')">저장</button>
               		</div>
                </div>
            </div>
		    <p></p>
		 </c:forEach>
	  </c:if>

	  <input type="hidden" name="bbsChildInfo[${fn:length(bbsChildInfoList)}].bbsid" id="bbsChildInfo_bbsid_${fn:length(bbsChildInfoList)}" value="0">
	  <input type="hidden" name="bbsChildInfo[${fn:length(bbsChildInfoList)}].menuid" id="bbsChildInfo_menuid_${fn:length(bbsChildInfoList)}" value="${inputFormData.leftMenuId}">
	  <input type="hidden" name="bbsChildInfo[${fn:length(bbsChildInfoList)}].projectid" id="bbsChildInfo_projectid_${fn:length(bbsChildInfoList)}" value="${bbsInfo.projectid}">
	  <input type="hidden" name="bbsChildInfo[${fn:length(bbsChildInfoList)}].parentbbsid" id="bbsChildInfo_parentbbsid_${fn:length(bbsChildInfoList)}" value="${bbsInfo.bbsid}">
	  <input type="hidden" name="bbsChildInfo[${fn:length(bbsChildInfoList)}].userid" id="bbsChildInfo_userid_${fn:length(bbsChildInfoList)}" value="${sessionScope.loginDto.userid}">
	  <div class="card">
         <div class="card-header">
            <div class="float-left">
            	<input type="text" name="bbsChildInfo[${fn:length(bbsChildInfoList)}].username" id="bbsChildInfo_username_${fn:length(bbsChildInfoList)}" value="${sessionScope.loginDto.username}" readonly class="form-control-plaintext" style="width:70px;">
            </div>
            <div class="float-right">
            	<input type="text" name="bbsChildInfo[${fn:length(bbsChildInfoList)}].gesidate" id="bbsChildInfo_gesidate_${fn:length(bbsChildInfoList)}" readonly class="form-control-plaintext" style="width:70px;">
            </div>
         </div>
         <div class="card-body_sm">
      			<div style=" border-color: #6c757d;padding:10px;" name="bbsChildInfoDiv[${fn:length(bbsChildInfoList)}].contents" id="bbsChildInfoDiv_contents_${fn:length(bbsChildInfoList)}"></div>
	        	<textarea name="bbsChildInfo[${fn:length(bbsChildInfoList)}].contents" id="bbsChildInfo_contents_${fn:length(bbsChildInfoList)}" rows="10" cols="100" style="width:0px; height:0px;display:none;"></textarea>
	        <div class="float-right" style="padding-top:5px;">
       			<button type="button" class="btn btn-sm btn-primary" id="btn_chile_save" name="btn_chile_save" onclick="javascript:goChildSave('${fn:length(bbsChildInfoList)}')">저장</button>
            </div>
	     </div>
      </div>

</div>
</form>