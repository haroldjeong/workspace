<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>

<div class="col-lg-10">
	<h2>${currMenu.name}</h2>
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<c:url value="/"/>">Home</a></li>
		<c:forEach varStatus="status" items="${menuNavigator }" var="result">
			<c:if test="${result.depth > 2}">
			<c:choose>
				<c:when test="${status.last}">
					<li class="breadcrumb-item active"><strong>${result.name}</strong></li>
				</c:when>
				<c:otherwise>
					<li class="breadcrumb-item">${result.name}</li>
				</c:otherwise>
			</c:choose>
			</c:if>
		</c:forEach>
	</ol>
</div>