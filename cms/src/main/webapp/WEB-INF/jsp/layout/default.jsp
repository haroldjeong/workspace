<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles"		uri="http://tiles.apache.org/tags-tiles" %>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%--<meta name="_csrf" content="13a2a0d5-4657-4999-802f-2e5b1e816f8z"/>--%>
		<meta name="_csrf" content="${_csrf.token}"/>
		<%--<meta name="_csrf_header" content="${_csrf.headerName}"/>--%>
		<title>{관리자} 페이지 | Safety Management System</title>
		<tiles:insertAttribute name="default-css"/>
	</head>

	<body>
		<div id="wrapper">
			<tiles:insertAttribute name="default-gnb"/>
			<div id="page-wrapper" class="gray-bg">
				<div class="row border-bottom">
					<tiles:insertAttribute name="default-header"/>
				</div>

				<div class="row wrapper border-bottom white-bg page-heading">
					<tiles:insertAttribute name="default-navigator"/>
				</div>

				<tiles:insertAttribute name="default-pre-js"/>

				<div class="_flow-wrap tiles-body wrapper wrapper-content animated fadeInRight">
					<tiles:insertAttribute name="default-body"/>
				</div>

				<div class="footer">
					<tiles:insertAttribute name="default-footer"/>
				</div>
			</div>
		</div>

		<tiles:insertAttribute name="default-js"/>
	</body>
</html>
