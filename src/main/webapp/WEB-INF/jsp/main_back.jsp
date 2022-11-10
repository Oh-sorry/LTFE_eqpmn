<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PMS</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
<link href="${pageContext.request.contextPath}/css/bootstrap-grid.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-reboot.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/jquery-ui.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/ui.jqgrid.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/icons.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/images/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.migrate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jqGrid.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/js/loadingoverlay.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>

<script language=javascript>
function goLogIn() {
	topLoginfm.action = "<c:url value='/login.do'/>";
	topLoginfm.submit();
}
function goLogOut() {
	topLoginfm.action = "<c:url value='/logOut.do'/>";
	topLoginfm.submit();
}
function goTopMenu(menuId,menuname) {
	$('#topMenuId').val(menuId);
	$('#topMenuName').val(menuname);
	$('#topMenuFrom').submit();
}
function loadProgram(menuId,menuname,progrmaUrl) {
	$('#leftMenuId').val(menuId);
	$('#leftMenuName').val(menuname);
	$('#topMenuFrom').attr("action","${pageContext.request.contextPath}"+progrmaUrl);
	$('#topMenuFrom').submit();
}
function loadingOn() {
	$.LoadingOverlay("show", {
		background       : "rgba(0, 0, 0, 0.5)",
		image            : "",
		maxSize          : 60,
		fontawesome      : "fa fa-spinner fa-pulse fa-fw",
		fontawesomeColor : "#FFFFFF",
	});
}
function loadingOff() {
	$.LoadingOverlay("hide");
}

function goPasswdChange() {
	$('#btn_passwdChange').attr("data-target","#passwdChangeModalpopup");
}

function goPasswdSave() {
	var usr_pwd = $("#user_password").val();
	var usr_rep_pwd = $("#user_repassword").val();
	var blank_pattern = /[\s]/g;
	var usrPwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=()-])(?=.*[0-9]).{6,20}$/;

	if(usr_pwd ==""){
		alert("비밀번호 입력 해주세요");
		$("#user_password").focus();
		return;
	}

	if(usr_pwd != usr_rep_pwd ){
		alert("비밀번호가 일치 하지 않습니다.");
		$("#user_repassword").focus();
		return;
	}

	if( blank_pattern.test(usr_pwd) == true){
	   alert('공백은 사용할 수 없습니다.');
	   $("#user_repassword").focus();
	   return;
	}

	if(!usrPwd.test(usr_pwd)){
		alert("비밀번호는 6~20자 이내의 영문/숫자/특수문자 조합 입니다.");
		$("#user_password").focus();
		return;
	}

    loadingOn();

    $.ajax({
        type : "POST",
        url : "${pageContext.request.contextPath}/system/userPasswdUpdate.ajax",
        data : "userpass="+usr_pwd,
        async: false,
        success : function(data){
       	 if(data != null) {
       	 	alert("정상적으로 처리되었습니다.")
            	goSearch();
       	 }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("작업 중 오류가 발생하였습니다.")
        }
    });

	 loadingOff();

    $('#passwdChangeModalpopup').modal('hide');
}

$(document).ready(function(){
	if ("${loginDto.initpass}" == "Y")	{
		alert("초기비밀번호 입니다. 비밀번호를 변경하세요");
	}
});

</script>
</head>
<body>
<form id="topMenuFrom" name="topMenuFrom" method="POST" action="<c:url value='/main/main.do'/>">
<input type="hidden" name="topMenuId" id="topMenuId"/>
<input type="hidden" name="topMenuName" id="topMenuName"/>
<input type="hidden" name="leftMenuId" id="leftMenuId"/>
<input type="hidden" name="leftMenuName" id="leftMenuName"/>
</form>
<!-- 탑메뉴 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <img src="${pageContext.request.contextPath}/images/bootstrap-icons/bootstrap.svg" alt="Bootstrap" width="20" height="20">
    <a class="navbar-brand" href="<c:url value='/main/main.do'/>"> 엠텍 PMS</a>
     <div class="navbar-loginName"><i class="bi bi-person-fill" style="font-size: 1rem; color: white;"></i> ${loginDto.username}</div>
  	 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto">
          <li class="nav-item"> </li>
          <c:forEach var="topMenulist" items="${topMenulist}" varStatus="status">
		    <c:choose>
		  	  <c:when test="${topMenulist.systemgrade=='00004'}">
	            <c:if test="${loginDto.usergrade == '00004'}">
	        	  <li class="nav-item">
	          		<a class="nav-link active" aria-current="page" href="javascript:goTopMenu('${topMenulist.menuid}','${topMenulist.menuname}')"><i class="bi bi-gear"></i> ${topMenulist.menuname}</a>
	        	  </li>
	            </c:if>
			  </c:when>
              <c:otherwise>
	        	  <li class="nav-item">
	          		<a class="nav-link active" aria-current="page" href="javascript:goTopMenu('${topMenulist.menuid}','${topMenulist.menuname}')"><i class="bi bi-arrow-right-circle"></i> ${topMenulist.menuname}</a>
	        	  </li>
              </c:otherwise>
             </c:choose>
	       </c:forEach>
	       <!-- li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
              <i class="bi bi-card-list"></i> 프로젝트
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
          </li -->
        </ul>
      </div>
      <ul class="nav navbar-nav navbar-right">
      	<li class="nav-item">
			<form class="d-flex" id="topLoginfm">
			  <c:if test="${loginDto.usergrade == ''}"><button class="btn btn-outline-success" type="submit" onClick="javascript:goLogIn();">Log-In</button></c:if>
			  <c:if test="${loginDto.usergrade != ''}">
			    <button type="button" class="btn btn-outline-success" type="submit" id="btn_passwdChange" name="btn_passwdChange" data-toggle="modal" onClick="javascript:goPasswdChange();">비밀번호변경</button>&nbsp;
			  	<button type="button" class="btn btn-outline-success" type="submit" onClick="javascript:goLogOut();">Log-Out</button>
			  </c:if>
			</form>
	     </li>
	  </ul>
  </div>
</nav>
<!-- /탑메뉴 -->

<div id="page-wrapper">
<!-- 최측메뉴 -->
  <div id="sidebar-wrapper">
    <ul class="sidebar-nav">
	  <c:forEach var="leftMenulist" items="${leftMenulist}" varStatus="status">
        <li><a href="javascript:loadProgram('${leftMenulist.menuid}','${leftMenulist.menuname}','${leftMenulist.programurl}')"><i class="bi bi-tags-fill"></i> <c:out value="${leftMenulist.menuname}" escapeXml="true"></c:out></a></li>
      </c:forEach>
    </ul>
  </div>

  <!-- 본문 -->
  <div id="page-content-wrapper">
  	<c:if test="${pageUrl != null && pageUrl != '' }">
    	<jsp:include page="/WEB-INF/jsp/${pageUrl}" flush="true"/>
    </c:if>
  	<c:if test="${pageUrl == null || pageUrl == '' }">
    	<img src="${pageContext.request.contextPath}/images/coding-g649521df6_1280.jpg" class="rounded"/>
    </c:if>

	    <div class="modal fade" id="passwdChangeModalpopup" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 비밀번호 변경</b></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
				<form id="inputForm" name="inputForm">
				  <input type="hidden" id="inputtype" name="inputtype">
				  <div class="form-group required control-label">
				    <label for="userid"><i class="bi bi-check2-circle"></i> 신규 비밀번호</label>
				    <input type=password class="form-control" id="user_password" name="user_password" maxlength="20">
				  </div>
				  <div class="form-group required control-label">
				    <label for="username"><i class="bi bi-check2-circle"></i> 확인 비밀번호</label>
				    <input type="password" class="form-control" id="user_repassword" name="user_repassword" maxlength="20">
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:goPasswdSave()"><i class="bi bi-save"></i> 저장</button>
		        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 닫기</button>
		      </div>
		    </div>
		  </div>
		</div>
  </div>
  <!-- /본문 -->
</div>


</body>
</html>