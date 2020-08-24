<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<%
	/**
	 * 기본 레이아웃
	 * @Description GNB 영역
	 * @author jm.lee (DEEP.FINE)
	 * @since 2020.07.24
	 *
	 * @Modification
	 * - OOO 기능 추가 (yyyy.mm.dd / 홍길동)
	 */
%>
<nav class="navbar-default navbar-static-side">
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<input type="hidden" id="currMenu" value="<c:out value="${currMenu.id}" />" />
			<li class="nav-header">
				<div class="dropdown profile-element">
					<img alt="image" class="rounded-circle" src="/static/img/profile_small.jpg"/>
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<span class="block m-t-xs font-bold"><c:out value="${user:getUserInfo().getName()}"/><small>&nbsp;선임연구원</small></span>
						<span class="text-muted text-xs block"><c:out value="${user:getUserInfo().getGroupName()}"/><b class="caret"></b></span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a class="dropdown-item" href="<c:url value="/"/>">프로필 수정</a></li>
						<li><a class="dropdown-item" href="<c:url value="/"/>">내 QR코드 보기</a></li>
						<li class="dropdown-divider"></li>
						<li><a class="dropdown-item _flow-action-logout" href="#">Log out</a></li>
					</ul>
				</div>
				<div class="logo-element">
					<!--img alt="Gyeonggi logo" src="/static/img/logo/gg_base.jpg" style="width:45px; height:45px;"/-->
				</div>
			</li>
			<li class="special_link">
				<a href="package.html"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Dashboards</span> <span class="label label-warning float-right">새창</span></a>
			</li>
			<c:forEach varStatus="status" items="${menuSet }" var="result">
				<c:if test="${result.depth > 2}">
					<c:set var="hasParent" value="${result.depth < menuSet[status.count].depth}"/>
					<c:set var="isEnd" value="${result.depth > menuSet[status.count].depth}"/>
					<c:set var="isSelected" value="${fn:indexOf(currMenu.pathId, result.id) > -1}"/>
					<c:choose>
						<c:when test="${hasParent}">
							<li<c:if test="${isSelected}"> class="active"</c:if>>
							<a href="${result.linkUrl}" value="${result.id}"<c:if test="${isSelected}"> aria-expanded="true"</c:if>><i class="fa fa-sitemap"></i> <span class="nav-label">${menuSet[status.index].name }</span><span class="fa arrow"></span></a>
						</c:when>
						<c:otherwise>
							<li<c:if test="${isSelected}"> class="active"</c:if>>
							<a href="${result.linkUrl}" value="${result.id}">${menuSet[status.index].name }</a>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${hasParent }">
							<c:choose>
								<c:when test="${result.depth < 4}">
									<ul class="nav nav-second-level collapse">
								</c:when>
								<c:otherwise>
									<ul class="nav nav-third-level">
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							</li>
						</c:otherwise>
					</c:choose>
					<c:if test="${isEnd }">
						</ul>
						</li>
					</c:if>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</nav>
