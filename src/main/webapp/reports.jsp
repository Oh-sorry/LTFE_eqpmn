<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>pdf 뷰어 테스트</title>
</head>
<body>
    <!-- src="./viewer.html?file=pdf파일경로" -->
    <!-- object data="http://localhost:8080/McstWork/reportsample.do" type="application/pdf" width=700px height=700px/ -->

    <iframe src="http://localhost:8080/McstWork/reportsample.do" type="application/pdf" width=700px height=700px />
	<!-- embed src="http://localhost:8080/McstWork/reportsample.do" type="application/pdf" width="700px" height="700px" / -->
</body>
</html>
