<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	function goSearch() {
		loadingOn();
		$('#searchForm').submit();
	}

	function goPage(page) {
		loadingOn();
		$('[id=searchForm] #pageno').val(page);
		$('#searchForm').submit();
	}

	function initData() {
		loadingOn();
		$('[id=searchForm] #bbsid').val(0);
		$('#searchForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeInput.do");
		$('#searchForm').submit();
	}

	function goView(bbsid) {
		loadingOn();
		$('[id=searchForm] #bbsid').val(bbsid);
		$('#searchForm').attr('action', "${pageContext.request.contextPath}/bbs/bbsMangeView.do");
		$('#searchForm').submit();
	}

	$(document).ready(function(){
        if ("<c:out value='${msg}'/>" != "") {
            alert("<c:out value='${msg}'/>");
        }

        var position = $('#searchposition').val();
        if (position != '') {
        	$(window).scrollTop(position);
        }
	});

	$(window).scroll(function(event) {
		$('[id=searchForm] #searchposition').val($(this).scrollTop());
	});
</script>
<div class="float-left w-100">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> ${searchFormData.leftMenuName}</h4>
	</div>
	<p></p>
	<form  class="form-inline" id="searchForm" name="searchForm" method="post" action="${pageContext.request.contextPath}/bbs/bbsMangeList.do">
		<input type="hidden" id="leftMenuId" name="leftMenuId" value="${searchFormData.leftMenuId}">
		<input type="hidden" id="leftMenuName" name="leftMenuName" value="${searchFormData.leftMenuName}">
		<input type="hidden" id="pageno" name="pageno" value="${searchFormData.pageno}">
		<input type="hidden" id="bbsid" name="bbsid" value="${searchFormData.bbsid}">
		<input type="hidden" id="searchreturnurl" name="searchreturnurl" value="/bbs/bbsMangeList.do">
		<input type="hidden" id="searchposition" name="searchposition" value="${searchFormData.searchposition}">

   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">프로젝트</div>
     		</div>
     	    <select class="form-control" id="searchprojectid" name="searchprojectid" readonly>
     	   	  <option value="" selected></option>
     	   	  <c:forEach var="projectList" items="${projectList}" varStatus="status">
     	   	    <c:choose>
     	   	       <c:when test="${projectList.projectid==searchFormData.searchprojectid}">
     	   	          <option value="${projectList.projectid}" selected>${projectList.projectname}</option>
     	   	       </c:when>
                   <c:otherwise>
        		      <option value="${projectList.projectid}">${projectList.projectname}</option>
        	       </c:otherwise>
        	    </c:choose>
        	  </c:forEach>
      		</select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">제목/내용/사용자</div>
     		</div>
			<input type="text" class="form-control" id="searchtitle" name="searchtitle" value="${searchFormData.searchtitle}">
   		</div>
		<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
	</form>
	<p></p>

	<table class="table table-hover">
	  <thead class="thead-light">
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">작성일</th>
	      <th scope="col">사용자</th>
	      <th scope="col">제목</th>
	      <th scope="col">답글수</th>
	      <th scope="col">조회수</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="bbsList" items="${bbsList}" varStatus="status">
	  	<tr>
	      <th scope="row">${bbsList.rowid}</th>
	      <td>${bbsList.gesidate}</td>
	      <td>${bbsList.username}</td>
	      <td><a href="javascript:goView('${bbsList.bbsid}');"><span class="text-break">${bbsList.title}</span></a></td>
	      <td>${bbsList.replycnt}</td>
	      <td>${bbsList.viewcnt}</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<nav aria-label="Page navigation">
	  <ul class="pagination justify-content-center">
	    <li class="page-item">
	      <a class="page-link" href="javascript:goPage(${paginationInfo.firstPageNo})" aria-label="Previous">
	        <span aria-hidden="true">처음</span>
	      </a>
	    </li>
		<c:choose>
	  	  <c:when test="${paginationInfo.totalPageCount > paginationInfo.pageSize}">
	         <c:if test="${paginationInfo.firstPageNoOnPageList > paginationInfo.pageSize}">
			    <li class="page-item">
			      <a class="page-link" href="javascript:goPage(${paginationInfo.firstPageNoOnPageList - 1})" aria-label="Previous">
			        <span aria-hidden="true">이전</span>
			      </a>
			    </li>
	        </c:if>
		  </c:when>
        </c:choose>
		<c:forEach begin="${paginationInfo.firstPageNoOnPageList}" end="${paginationInfo.lastPageNoOnPageList}" step="1" varStatus="status">
			<c:if test="${paginationInfo.currentPageNo == status.current}">
				<li class="page-item disabled"><a class="page-link" href="#"><b>${status.current}</b></a></li>
			</c:if>
			<c:if test="${paginationInfo.currentPageNo != status.current}">
				<li class="page-item"><a class="page-link" href="javascript:goPage(${status.current})">${status.current}</a></li>
			</c:if>
		</c:forEach>
		<c:choose>
	  	  <c:when test="${paginationInfo.totalPageCount > paginationInfo.pageSize}">
	         <c:if test="${paginationInfo.lastPageNoOnPageList < paginationInfo.totalPageCount}">
			    <li class="page-item">
			      <a class="page-link" href="javascript:goPage(${paginationInfo.firstPageNoOnPageList + paginationInfo.pageSize})" aria-label="Previous">
			        <span aria-hidden="true">다음</span>
			      </a>
			    </li>
	        </c:if>
		  </c:when>
        </c:choose>
	    <li class="page-item">
	      <a class="page-link" href="javascript:goPage(${paginationInfo.lastPageNo})" aria-label="Previous">
	        <span aria-hidden="true">마지막</span>
	      </a>
	    </li>
	  </ul>
	</nav>
	<button type="button" class="btn btn-sm btn-primary float-right" id="btn_input" name="btn_input" data-toggle="modal" onclick="javascript:initData()"><i class="bi bi-pencil-fill"></i> 신규작성</button>
</div>

