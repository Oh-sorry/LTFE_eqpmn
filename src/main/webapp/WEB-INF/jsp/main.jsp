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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PMS</title>

<link href="${pageContext.request.contextPath}/css/NotoSansFont.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
<link href="${pageContext.request.contextPath}/css/bootstrap-grid.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-reboot.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
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
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
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
function loadProgram(parentMenuId,menuId,menuname,progrmaUrl,projectid) {
	sessionStorage.setItem("menuParentIdSelect",parentMenuId);
	sessionStorage.setItem("menuIdSelect",menuId);
	$('#topMenuId').val(parentMenuId);
	$('#leftMenuId').val(menuId);
	$('#leftMenuName').val(menuname);
	$('#searchprojectid').val(projectid);
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

$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });

    $(function () {
  	  $('[data-toggle="tooltip"]').tooltip()
  })

  menuParentIdSelect= sessionStorage.getItem("menuParentIdSelect");
  menuIdSelect = sessionStorage.getItem("menuIdSelect");

  if (menuParentIdSelect != null && menuParentIdSelect != '') {
	$('#homeSubmenu_' + menuParentIdSelect).addClass("show");
	$('#ahomeSubmenu_' + menuParentIdSelect).attr("aria-expanded",true);
  }

});
</script>

<c:if test="${loginDto.isMobile == 'true'}">
	<style>
	    #sidebar {
			margin-left: -250px;
	    }
	    #sidebar.active {
			margin-left: 0;
	    }
	</style>
</c:if>
<c:if test="${loginDto.isMobile == 'false'}">
	<style>
	    #sidebar {
			margin-left:0;
	    }
	    #sidebar.active {
			margin-left: -250px;
	    }
	</style>
</c:if>

</head>
<body>
<form id="topMenuFrom" name="topMenuFrom" method="POST" action="<c:url value='/main/main.do'/>">
<input type="hidden" name="topMenuId" id="topMenuId"/>
<input type="hidden" name="topMenuName" id="topMenuName"/>
<input type="hidden" name="leftMenuId" id="leftMenuId"/>
<input type="hidden" name="leftMenuName" id="leftMenuName"/>
<input type="hidden" name="searchprojectid" id="searchprojectid"/>
</form>
<form id="topLoginfm" name="topLoginfm"></form>

<div class="wrapper">
        <!-- Sidebar  -->
        <nav id="sidebar">
            <div class="sidebar-header text-center">
                <h5><a href="<c:url value='/main/main.do'/>"><img src="${pageContext.request.contextPath}/images/mtech_log.png" style="width:200px"></a></h5>
            </div>

            <ul class="list-unstyled components">
            	<h6>
                <p class="text-center">
                	<i class="bi bi-person-fill" style="font-size: 1rem; color: white;"></i> ${loginDto.username}
					    <!--
					    <button type="button" class="btn btn-sm btn-info" type="submit" id="btn_passwdChange" name="btn_passwdChange" data-toggle="modal" onClick="javascript:goPasswdChange();">비밀번호변경</button>&nbsp;
					  	<button type="button" class="btn btn-sm btn-info" type="submit" onClick="javascript:goLogOut();">log-Out</button>
					  	-->
                </p>
				</h6>

           	    <c:forEach var="leftMenulist" items="${leftMenulist}" varStatus="status">
           	    	<c:if test="${status.index == 0}">
           	    		<li>
           	    		<a id="ahomeSubmenu_${leftMenulist.menuid}" href="#homeSubmenu_${leftMenulist.menuid}" data-toggle="collapse" data-target="#homeSubmenu_${leftMenulist.menuid}" aria-expanded="false" class="dropdown-toggle"><c:out value="${leftMenulist.menuname}" escapeXml="true"></c:out></a>
           	    		<ul class="collapse list-unstyled" id="homeSubmenu_${leftMenulist.menuid}">
           	    	</c:if>
           	     	<c:if test="${status.index > 0 && leftMenulist.level==1}">
           	    			</ul>
           	    		</li>
           	    		<li>
           	    		  <a id="ahomeSubmenu_${leftMenulist.menuid}" href="#homeSubmenu_${leftMenulist.menuid}" data-toggle="collapse" data-target="#homeSubmenu_${leftMenulist.menuid}" aria-expanded="false" class="dropdown-toggle"><c:out value="${leftMenulist.menuname}" escapeXml="true"></c:out></a>
                          <ul class="collapse list-unstyled" id="homeSubmenu_${leftMenulist.menuid}">
           	    	</c:if>
           	    	<c:if test="${status.index > 0 && leftMenulist.level==2}">
           	    		<li><a href="javascript:loadProgram('${leftMenulist.parentmenuid}','${leftMenulist.menuid}','${leftMenulist.menuname}','${leftMenulist.programurl}','${leftMenulist.projectid}')"><c:out value="${leftMenulist.menuname}" escapeXml="true"></c:out></a></li>
           	    	</c:if>
    			</c:forEach>

        		</ul>
        		</li>
			</ul>
            <ul class="list-unstyled CTAs">
                <li>
                    <!-- a href="https://bootstrapious.com/p/bootstrap-sidebar" class="article">글쓰기</a -->
                </li>
            </ul>
        </nav>

        <!-- Page Content  -->
        <div id="content">
		    <form name="topSearch" id="topSearch" method="post" action="${pageContext.request.contextPath}/bbs/bbsChatList.do">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <button type="button" id="sidebarCollapse" class="btn btn-sm btn-info mr-1">
                        <i class="bi bi-list"></i>
                    </button>
                   	<a href="<c:url value='/main/main.do'/>" data-toggle="tooltip" data-placement="top" title="메인화면"><span class="btn btn-sm btn-info mr-1"><i class="bi bi-house-door"></i></span></a>
                   	<a href="javascript:goPasswdChange();" data-toggle="tooltip" data-placement="top" title="비밀번호변경"><span class="btn btn-sm btn-info mr-1" data-toggle="modal" id="btn_passwdChange" name="btn_passwdChange"><i class="bi bi-key-fill"></i></span></a>
                	<a href="javascript:goLogOut();" data-toggle="tooltip" data-placement="top" title="로그아웃"><span class="btn btn-sm btn-info mr-1"><i class="bi bi-box-arrow-right"></i></span></a>


                    <button class="btn btn-sm btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="bi bi-list"></i>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                     	<ul class="navbar-nav mr-auto">
      						<li class="nav-item active"></li>
      					</ul>
	                         <span class="btn btn-sm btn-outline-success my-2 my-sm-0"><i class="bi bi-search"></i></span><input class="form-control mr-sm-2 w-25" type="search" name="searchtitle" id="searchtitle" placeholder="Search" aria-label="Search">
                    </div>
                </div>
            </nav>
			</form>

             <!-- 본문 -->
		  	<c:if test="${pageUrl != null && pageUrl != '' }">
		    	<jsp:include page="/WEB-INF/jsp/${pageUrl}" flush="true"/>
		    </c:if>
		  <!-- /본문 -->
     </div>
</div>

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
		<form id="passwdInputForm" name="passwdInputForm">
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
</body>
</html>