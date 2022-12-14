<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="${pageContext.request.contextPath}/summernote/summernote-bs4.js"></script>
<link href="${pageContext.request.contextPath}/summernote/summernote-bs4.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/summernote/lang/summernote-ko-KR.js"></script>

<style>
	// File Uploader Starts here
	.card {
	  margin-top: 10px;
	}
	.btn-upload {
	    padding: 5px 10px;
	    margin-left: 10px;
	}
	.upload-input-group {
	    margin-bottom: 5px;
	}

	.input-group>.custom-select:not(:last-child), .input-group>.form-control:not(:last-child) {
	  height: 40px;
	}
</style>

<script lang="javascript">
	$(function () {
	    $(document).on('click', '.btn-fileadd', function (e) {
	        e.preventDefault();

	        var controlForm = $('.controls:first'),
	            currentEntry = $(this).parents('.entry:first'),
	            newEntry = $(currentEntry.clone()).appendTo(controlForm);

	        newEntry.find('input').val('');
	        controlForm.find('.entry:not(:last) .btn-fileadd')
	            .removeClass('btn-fileadd').addClass('btn-remove')
	            .removeClass('btn-success').addClass('btn-danger')
	            .html('<i class="bi bi-file-earmark-minus"></i>');
	    }).on('click', '.btn-remove', function (e) {
	        $(this).parents('.entry:first').remove();

	        e.preventDefault();
	        return false;
	    });

	});

	$(document).ready(function(){
		$('#btn_close').click(function () {
			$('#inputForm').attr('action', "${pageContext.request.contextPath}${inputFormData.searchreturnurl}");
			$('#inputForm')[0].submit();
	    });
    });

	function goSave() {
		var markupStr = $('#contents').summernote('code');

		if (markupStr == "<p><br></p>") {
			alert("????????? ?????? ?????????");
			return;
		}

		$('#inputForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsChatSave.do");
		$('#inputForm')[0].submit();
	}

	function checkUseYn(index) {
		if ($('[id=inputForm] #useyn'+index).is(":checked")) {
			$('[id=inputForm] #pmsbbsfileDtoList_useyn_'+index).val('Y');
		} else {
			$('[id=inputForm] #pmsbbsfileDtoList_useyn_'+index).val('N');
		}
	}

	function fileDownload(fileid){
		window.open("${pageContext.request.contextPath}/fileDownload.do?fileid="+fileid);
	}

	$(document).ready(function(){
		if (isMobile()) {
			 $('#contents').summernote(summernoteMobileSetting);
		} else {
			 $('#contents').summernote(summernoteSetting);
		}
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
<div class="float-left w-100">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i>
     	   	  <c:forEach var="projectList" items="${projectList}" varStatus="status">
     	   	    <c:choose>
     	   	       <c:when test="${projectList.projectid==inputFormData.projectid}">
     	   	          ${projectList.projectname}
     	   	       </c:when>
        	    </c:choose>
        	  </c:forEach>
			#${inputFormData.leftMenuName}
		</h4>
	</div>
	<p></p>
	<form id="inputForm" name="inputForm" method="post" enctype="multipart/form-data">
	  <input type="hidden" id="leftMenuId" name="leftMenuId" value="${inputFormData.leftMenuId}">
	  <input type="hidden" id="leftMenuName" name="leftMenuName" value="${inputFormData.leftMenuName}">
	  <input type="hidden" id="pageno" name="pageno" value="${inputFormData.pageno}">
	  <c:forEach items="${inputFormData}" var="item">
	    <c:if test="${fn:indexOf(item.key,'search') >= 0}">
	  		<input type="hidden" id="${item.key}" name="${item.key}" value="${item.value}">
	  	</c:if>
	  </c:forEach>
	  <input type="hidden" id="bbsid" name="bbsid" value="${inputFormData.bbsid}">
	  <input type="hidden" id="menuid" name="menuid" value="${inputFormData.leftMenuId}">
	  <input type="hidden" id="inputtype" name="inputtype">
	  <input type="hidden" id="projectid" name="projectid" value="${inputFormData.projectid}">
      <input type="hidden" id="userid" name="userid" value="${inputFormData.userid}">
      <input type="hidden" id="username" name="username" value="${inputFormData.username}">
      <input type="hidden" id="gesidate" name="gesidate" value="${inputFormData.gesidate}">

	  <div class="form-row">
    	<div class="col">
	   	  <div class="form-group required control-label">
	        <label for="contents"><i class="bi bi-check2-circle"></i> ??????</label>
	          <textarea name="contents" id="contents" rows="10" cols="100" style="width:1050px; height:412px;display:none;">${inputFormData.contents}</textarea>
	      </div>
	    </div>
	  </div>
	  <div class="form-row">
    	<div class="col">
			<div class="card">
                <div class="card-header">
                    <div class="float-left"><i class="bi bi-check2-circle"></i> ????????????</div>
                    <div class="float-right"></div>
                </div>
                <div class="card-body">
                  <ul>
				  <c:forEach var="pmsbbsfileDtoList" items="${pmsbbsfileDtoList}" varStatus="status">
					<input type="hidden" name="pmsbbsfileDtoList[${status.index}].fileid" id="pmsbbsfileDtoList_fileid_${status.index}" value="${pmsbbsfileDtoList.fileid}">
				  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].serverpath" id="pmsbbsfileDtoList_serverpath_${status.index}" value="${pmsbbsfileDtoList.serverpath}">
				  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].localfile" id="pmsbbsfileDtoList_localfile_${status.index}" value="${pmsbbsfileDtoList.localfile}">
				  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].serverfile" id="pmsbbsfileDtoList_serverfile_${status.index}" value="${pmsbbsfileDtoList.serverfile}">
				  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].useyn" id="pmsbbsfileDtoList_useyn_${status.index}" value="${pmsbbsfileDtoList.useyn}">
				  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].filesize" id="pmsbbsfileDtoList_filesize_${status.index}" value="${pmsbbsfileDtoList.filesize}">
				  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].ext" id="pmsbbsfileDtoList_ext_${status.index}" value="${pmsbbsfileDtoList.ext}">
				  	<input type="hidden" name="pmsbbsfileDtoList[${status.index}].bbsid" id="pmsbbsfileDtoList_bbsid_${status.index}" value="${pmsbbsfileDtoList.bbsid}">
				  	<li>
				  		 <div class="custom-control custom-switch">
  								<input type="checkbox" class="custom-control-input" id="useyn${status.index}" name="useyn${status.index}" onclick="javascript:checkUseYn('${status.index}');" checked>
  								<label class="custom-control-label" for="useyn${status.index}"><i class="bi bi-trash"></i></label>
						 		<i class="bi bi-file"></i> <i class="bi bi-file-${pmsbbsfileDtoList.ext}"></i> <i class="bi bi-filetype-${pmsbbsfileDtoList.ext}"></i> <a href="javascript:fileDownload('${pmsbbsfileDtoList.fileid}')">${pmsbbsfileDtoList.localfile}</a>
						 </div>

				    </li>
				   </c:forEach>
                   </ul>
                   <div class="controls">
                       <div class="entry input-group upload-input-group">
                           <input class="form-control" id="fileInput" name="fileInput[]" type="file" onchange="javascript:fileChk(this)">
                           <button class="btn btn-upload btn-sm btn-success btn-fileadd" type="button">
                               <i class="bi bi-file-earmark-plus"></i>
                           </button>
                       </div>
                   </div>
                </div>
            </div>
		</div>
	  </div>
	</form>
	<p></p>
	<p class="float-right">
	<button type="button" class="btn btn-sm btn-primary" id="btn_input" name="btn_input" onclick="javascript:goSave()"><i class="bi bi-save"></i> ??????</button>
	<button type="button" class="btn btn-sm btn-secondary" id="btn_close" name="btn_close"><i class="bi bi-x"></i> ??????</button>
	</p>
</div>
