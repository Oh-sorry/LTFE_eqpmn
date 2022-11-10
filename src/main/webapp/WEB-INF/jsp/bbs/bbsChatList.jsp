<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link href="${pageContext.request.contextPath}/summernote/summernote-bs4.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/summernote/summernote-bs4.js"></script>
<script src="${pageContext.request.contextPath}/summernote/lang/summernote-ko-KR.js"></script>

<style>
.float{
	position:fixed;
	width:150px;
	height:100px;
	bottom:40px;
	right:40px;
	text-align:center;
	box-shadow: 1px 1px 1px 1px #999;
}
.my-float{
	margin-top:22px;
}
.btn-space {
    margin: 1px;
}
</style>
<script language="javascript">
	function goPage(page) {
		loadingOn();
		$('[id=searchForm] #pageno').val(page);
		$('#searchForm').submit();
	}

	function initData(menuid, menuname, programurl, projectid) {
		loadingOn();

		$('[id=searchForm] #leftMenuId').val(menuid);
		$('[id=searchForm] #leftMenuName').val(menuname);
		$('[id=searchForm] #searchprojectid').val(projectid);

		$('[id=searchForm] #bbsid').val(0);

		$('#searchForm').attr('action', "${pageContext.request.contextPath}"+programurl);
		$('#searchForm').submit();
	}

	function goView(menuid, menuname, programurl, projectid, bbsid) {
		loadingOn();

		$('[id=searchForm] #leftMenuId').val(menuid);
		$('[id=searchForm] #leftMenuName').val(menuname);
		$('[id=searchForm] #searchprojectid').val(projectid);

		$('[id=searchForm] #bbsid').val(bbsid);
		$('#searchForm').attr('action', "${pageContext.request.contextPath}"+programurl);
		$('#searchForm').submit();
	}

	function goDelete(bbsid) {
		if (confirm("삭제 하시겠습니까?")) {
			$('[id=searchForm] #bbsid').val(bbsid);

			$('#searchForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeDelete.do");
			$('#searchForm')[0].submit();
		}
	}

	$(document).ready(function(){
        var position = $('#searchposition').val();
        if (position != '') {
        	$(window).scrollTop(position);
        }

        if ("<c:out value='${msg}'/>" != "") {
            alert("<c:out value='${msg}'/>");
        }
    });

	function fileDownload(fileid){
		window.open("${pageContext.request.contextPath}/fileDownload.do?fileid="+fileid);
	}

	function goMenuSelect() {
		$('#menuSelect').attr("data-target","#modalMenuSelect");
	}

	$(window).scroll(function(event) {
		$('[id=searchForm] #searchposition').val($(this).scrollTop());
	});
</script>
<div class="float-left w-100">
	<form  class="form-inline" id="searchForm" name="searchForm" method="post" action="${pageContext.request.contextPath}/bbs/bbsChatList.do">
		<input type="hidden" id="leftMenuId" name="leftMenuId" value="${searchFormData.leftMenuId}">
		<input type="hidden" id="leftMenuName" name="leftMenuName" value="${searchFormData.leftMenuName}">
		<input type="hidden" id="pageno" name="pageno" value="${searchFormData.pageno}">
		<input type="hidden" id="bbsid" name="bbsid" value="${searchFormData.bbsid}">
		<input type="hidden" id="searchprojectid" name="searchprojectid" value="${searchFormData.searchprojectid}">
		<input type="hidden" id="searchtitle" name="searchtitle" value="${searchFormData.searchtitle}">
		<input type="hidden" id="searchreturnurl" name="searchreturnurl" value="/bbs/bbsChatList.do">
		<input type="hidden" id="searchposition" name="searchposition" value="${searchFormData.searchposition}">

	  <c:forEach var="bbsList" items="${bbsList}" varStatus="status">
		<div class="card w-100 border-warning" style="margin:2px" id="card_${status.index}">
               <div class="card-header" style="font-size:0.7rem">
                   <div class="float-left font-weight-bold">${bbsList.username}(<c:out value="${bbsList.gesidate}"></c:out>)</div>
                   <div class="float-right font-weight-bold">
                   	<c:out value="${bbsList.projectname}"  escapeXml="true" /> <c:out value="#${bbsList.menuname}"  escapeXml="true" />
                   </div>
               </div>
               <div class="card-body">
               	<c:if test="${bbsList.programurl == '/bbs/bbsMangeView.do'}">
               		<a href="javascript:goView('${bbsList.menuid}','${bbsList.menuname}','${bbsList.programurl}','${bbsList.projectid}','${bbsList.bbsid}');"><span class="btn btn-ssm btn btn-secondary"><i class="bi bi-search"></i></span></a>
               	</c:if>
               	<c:if test="${bbsList.userid == loginDto.userid}">
          			<a href="javascript:goDelete('${bbsList.bbsid}');"><span class="btn btn-ssm btn btn-secondary"><i class="bi bi-trash"></i></span></a>
          		</c:if>

               	<c:if test="${bbsList.programurl == '/bbs/bbsMangeView.do'}">
	                <div class="float-right">
						 <i class="bi bi-person-check"></i> ${bbsList.viewcnt} <i class="bi bi-person-plus"></i> ${bbsList.replycnt}
	                </div>
                </c:if>
                <p></p>
				<c:if test="${bbsList.title != null && bbsList.title != ''}">
					<div class="alert alert-info" role="alert">
					  ${bbsList.title}
					</div>
				</c:if>
				  ${bbsList.contents}
				<p></p>
                <ul  class="list-unstyled w-100">
				  <c:forEach var="pmsbbsfileDtoList" items="${bbsList.pmsbbsfileDtoList}" varStatus="status">
				  	<li>
				  	<div class="alert alert-secondary" role="alert">
						<i class="bi bi-file-earmark-check-fill"></i> <a href="javascript:fileDownload('${pmsbbsfileDtoList.fileid}')">${pmsbbsfileDtoList.localfile}</a>
					</div>
				    </li>
				   </c:forEach>
                </ul>
               </div>
           </div>
	  </c:forEach>
	  <div style="height:120px"></div>
	</form>

	<div  class="float">
	<c:if test="${paginationInfo.currentPageNo > 1}">
		 <button type="button" class="btn btn-sm btn-primary w-100  btn-space" onclick="javascript:goPage(${paginationInfo.currentPageNo - 1})">
	  		이전페이지 <span class="badge badge-light">${paginationInfo.currentPageNo -1} / ${paginationInfo.totalPageCount}</span>
		</button>
	</c:if>
	<c:if test="${paginationInfo.currentPageNo == 1}">
		 <button type="button" class="btn btn-sm btn-primary w-100  btn-space" onclick="javascript:goPage(${paginationInfo.currentPageNo - 1})" disabled>
	  		이전페이지 <span class="badge badge-light">${paginationInfo.currentPageNo} / ${paginationInfo.totalPageCount}</span>
		</button>
	</c:if>
	<c:if test="${paginationInfo.currentPageNo < paginationInfo.totalPageCount}">
		 <button type="button" class="btn btn-sm btn-primary w-100 btn-space" onclick="javascript:goPage(${paginationInfo.currentPageNo + 1})">
	  		다음페이지 <span class="badge badge-light">${paginationInfo.currentPageNo + 1} / ${paginationInfo.totalPageCount}</span>
		</button>
	</c:if>
	<c:if test="${paginationInfo.currentPageNo == paginationInfo.totalPageCount}">
		 <button type="button" class="btn btn-sm btn-primary w-100 btn-space" onclick="javascript:goPage(${paginationInfo.currentPageNo + 1})" disabled>
	  		다음페이지 <span class="badge badge-light">${paginationInfo.currentPageNo} / ${paginationInfo.totalPageCount}</span>
		</button>
	</c:if>
	<button type="button" id="menuSelect" data-toggle="modal" class="btn btn-sm btn-primary w-100 btn-space" onclick="javascript:goMenuSelect()">
  		새글 쓰기
	</button>
	</div>
</div>

<div class="modal fade" id="modalMenuSelect" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 메뉴선택</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="inputProjectForm" name="inputProjectForm">
			<div style="height: 400px;" class="overflow-auto">
				<div class="list-group">
        		<c:forEach var="menuSelectList" items="${menuSelectList}" varStatus="status">
  					<a href="javascript:initData('${menuSelectList.menuid}','${menuSelectList.menuname}','${menuSelectList.programurl}','${menuSelectList.projectid}')" class="list-group-item list-group-item-action">
						<label><c:out value="${menuSelectList.path}"  escapeXml="true" /></label>
  					</a>
      			</c:forEach>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 닫기</button>
      </div>
    </div>
  </div>
</div>
