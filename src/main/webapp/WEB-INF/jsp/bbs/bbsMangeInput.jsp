<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link href="${pageContext.request.contextPath}/summernote/summernote-bs4.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/summernote/summernote-bs4.js"></script>
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

/*
//이미지(png)로 다운로드
//이미지(png)로 다운로드
function PrintDiv(){
	  //document.querySelector("#tx_canvas")
	//html2canvas(document.querySelector("#cap2")).then(function(canvas) {
html2canvas(document.getElementById("capture")).then(function(canvas) {
		 if (canvas.msToBlob) { //for IE 10, 11
		  var blob = canvas.msToBlob();
		  window.navigator.msSaveBlob(blob, "capture.png");
		 } else {
		  saveAs(canvas.toDataURL(), "capture.png");
		 }
	});
}

function saveAs(uri, filename){
	var link = document.createElement('a');
	  if (typeof link.download === 'string') {
	   link.href = uri;
	   link.download = filename;
	   //Firefox requires the link to be in the body
	   document.body.appendChild(link);
	   //simulate click
	   link.click();
	   //remove the link when done
	   document.body.removeChild(link);
	  } else {
	   window.open(uri);
	  }
}
*/

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
		if ($('[id=inputForm] #title').val().trim() == '') {
			alert("제목을 입력 하세요");
			$('[id=inputForm] #title').focus();
			return;
		}

		var markupStr = $('#contents').summernote('code');

		if (markupStr == "<p><br></p>") {
			alert("내용을 입력 하세요");
			return;
		}

		$('#inputForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeSave.do");
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
		<h4><i class="bi bi-info-square-fill"></i> ${inputFormData.leftMenuName}</h4>
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
      <input type="hidden" class="form-control" id="userid" name="userid" readonly value="${inputFormData.userid}">

	  <div class="form-row">
    	<div class="col">
   		  <div class="form-group required control-label">
	        <label for="projectid"><i class="bi bi-check2-circle"></i> 프로젝트</label>
     	    <select class="form-control" id="projectid" name="projectid" readonly>
     	   	  <c:forEach var="projectList" items="${projectList}" varStatus="status">
     	   	    <c:choose>
     	   	       <c:when test="${projectList.projectid==inputFormData.projectid}">
     	   	          <option value="${projectList.projectid}" selected>${projectList.projectname}</option>
     	   	       </c:when>
                   <c:otherwise>
        		      <option value="${projectList.projectid}">${projectList.projectname}</option>
        	       </c:otherwise>
        	    </c:choose>
        	  </c:forEach>
      		</select>
	      </div>
	    </div>
    	<div class="col">
   		  <div class="form-group required control-label">
	        <label for="username"><i class="bi bi-check2-circle"></i> 작성자</label>
	        <input type="text" class="form-control" id="username" name="username" readonly value="${inputFormData.username}">
	      </div>
	    </div>
	    <div class="col">
	   	  <div class="form-group required control-label">
	        <label for="gesidate"><i class="bi bi-check2-circle"></i> 작성일</label>
	        <input type="text" class="form-control" id="gesidate" name="gesidate" readonly value="${inputFormData.gesidate}">
	      </div>
	    </div>
	  </div>
	  <div class="form-row">
    	<div class="col">
	   	  <div class="form-group required control-label">
	        <label for="title"><i class="bi bi-check2-circle"></i> 제목</label>
	        <input type="text" class="form-control" id="title" name="title" maxlength="300" value="${inputFormData.title}">
	      </div>
	    </div>
	  </div>
	  <div class="form-row">
    	<div class="col">
	   	  <div class="form-group required control-label">
	        <label for="contents"><i class="bi bi-check2-circle"></i> 내용</label>
	          <textarea name="contents" id="contents" rows="10" cols="100" style="width:1050px; height:412px;display:none;">${inputFormData.contents}</textarea>
	      </div>
	    </div>
	  </div>
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
	<button type="button" class="btn btn-sm btn-primary" id="btn_input" name="btn_input" onclick="javascript:goSave()"><i class="bi bi-save"></i> 저장</button>
	<button type="button" class="btn btn-sm btn-secondary" id="btn_close" name="btn_close"><i class="bi bi-x"></i> 목록</button>
	</p>
</div>
