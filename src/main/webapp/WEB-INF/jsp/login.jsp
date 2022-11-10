<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mtech PMS</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript">
function loginDo() {
	if ($('#userid').val().trim() == "") {
		alert("사용자 아이디를 입력하세요");
		$('#userid').focus();
		return;
	}
	if ($('#userpass').val().trim() == "") {
		alert("사용자 비밀번호를 입력하세요");
		$('#userpass').focus();
		return;
	}
	$('#loginForm').submit();
}

$(document).ready(function() {
    var message = $('#message').val();
    if (message != "") {
        alert(message);
    }

    $('#isMobile').val(isMobile());

    $('#userid').focus();
});

function isMobile() {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}

</script>
</head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <h4> </h4>
    </div>

    <!-- Login Form -->
    <form name="loginForm" id="loginForm" action="<c:url value='/loginChk.do'/>" method="POST">
      <input type="text" id="userid" class="fadeIn second" name="userid" placeholder="아이디">
      <input type="password" id="userpass" class="fadeIn third" name="userpass" placeholder="비밀번호" onkeydown="javascript:if (event.keyCode==13) loginDo(); ">
      <input type="hidden" id="isMobile" name="isMobile">
      <input type="hidden" id="message" name="message" value="${message}">
      <input type="button" class="fadeIn fourth" value="로그인" onclick="javascript:loginDo()">
    </form>
  </div>
</div>
</body>
</html>