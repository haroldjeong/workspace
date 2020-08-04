<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<%
	/**
	 * @description : 공통 > 오류 페이지 (500 Error)
	 * @author jm.lee (DEEP.FINE)
	 * @since 2020.07.16
	 */
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>WEB | 500 Error</title>
	<link href="/static/css/style.css" rel="stylesheet">
</head>

<body>
	<div>
		<h1>500</h1>
		<h3 class="font-bold">헐! 페이지에 오류가 있습니다.</h3>
		<div class="error-desc">
			The server encountered something unexpected that didn't allow it to complete the request. We apologize.<br/>
			You can go back to main page: <br/><a href="index.html" class="btn btn-primary m-t">메인으로</a>
			<br/>(to-do: 디자인 및 메시지 정의 필요)
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="/static/js/jquery-3.1.1.min.js"></script>
</body>

</html>
