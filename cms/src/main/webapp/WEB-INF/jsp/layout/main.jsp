<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles"		uri="http://tiles.apache.org/tags-tiles" %>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="_csrf" data-th-content="${_csrf.token}"/>
		<meta name="_csrf_header" data-th-content="${_csrf.headerName}"/>
		<title>메인 페이지 | Safety Management System</title>
		<tiles:insertAttribute name="main-css"/>
	</head>

	<body>
		<div id="wrapper">
			<tiles:insertAttribute name="main-gnb"/>
			<div id="page-wrapper" class="gray-bg">
				<div class="row border-bottom">
					<tiles:insertAttribute name="main-header"/>
				</div>

				<tiles:insertAttribute name="main-body"/>

				<div class="footer">
					<tiles:insertAttribute name="main-footer"/>
				</div>
			</div>
		</div>

		<tiles:insertAttribute name="main-js"/>
	</body>
</html>
