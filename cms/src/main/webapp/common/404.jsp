<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<%
	/**
	 * @description : 공통 > 오류 페이지 (404 Error)
	 * @author jm.lee (DEEP.FINE)
	 * @since 2020.07.16
	 */
%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>CMS | 404 Error</title>

	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/font-awesome/css/font-awesome.css" rel="stylesheet">

	<link href="/static/css/animate.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="middle-box text-center animated fadeInDown">
		<h1>404</h1>
		<h3 class="font-bold">헐! 페이지를 찾을 수 없습니다.</h3>
		<div class="error-desc">
			요청하신 URL은 변경되었거나 없어졌을 수 있습니다!<br/>(to-do: 디자인 및 메시지 정의 필요)
			<br/><a href="<c:url value="/"/> " class="btn btn-primary m-t">메인으로</a>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="/static/js/jquery-3.1.1.min.js"></script>
	<script src="/static/js/popper.min.js"></script>
	<script src="/static/js/bootstrap.js"></script>
</body>

</html>
