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
			<li class="nav-header">
				<div class="dropdown profile-element">
					<img alt="image" class="rounded-circle" src="/static/img/profile_small.jpg"/>
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<span class="block m-t-xs font-bold">이정민<small>&nbsp;선임연구원</small></span>
						<span class="text-muted text-xs block">주식회사 딥파인<b class="caret"></b></span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a class="dropdown-item" href="<c:url value="/"/>">프로필 수정</a></li>
						<li><a class="dropdown-item" href="<c:url value="/"/>">내 QR코드 보기</a></li>
						<li class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="<c:url value="/"/>">Log out</a></li>
					</ul>
				</div>
				<div class="logo-element">
					<!--img alt="Gyeonggi logo" src="/static/img/logo/gg_base.jpg" style="width:45px; height:45px;"/-->
				</div>
			</li>
			<li class="special_link">
				<a href="package.html"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Dashboards</span> <span class="label label-warning float-right">새창</span></a>
			</li>
			<li>
				<a href="#"><i class="fa fa-sitemap"></i> <span class="nav-label">1차 메뉴 (자식 포함)</span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li>
						<a href="#">2차 메뉴</a>
					</li>
					<li>
						<a href="#">2차 메뉴</a>
					</li>
					<li>
						<a href="#">2차 메뉴</a>
					</li>
					<li>
						<a href="#">2차 메뉴<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li>
								<a href="#">3차 메뉴</a>
							</li>
							<li>
								<a href="#">3차 메뉴</a>
							</li>
							<li>
								<a href="#">3차 메뉴</a>
							</li>

						</ul>
					</li>
				</ul>
			</li>
			<li>
				<a href="layouts.html"><i class="fa fa-diamond"></i> <span class="nav-label">1차 메뉴 (자식 미포함)</span></a>
			</li>
			<li>
				<a href="#"><i class="fa fa-database"></i> <span class="nav-label">일반 관리</span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li>
						<a href="#">기기 관리<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li>
								<a href="#">스마트글라스</a>
							</li>
							<li>
								<a href="#">IoT 센서</a>
							</li>
							<li>
								<a href="#">측량 장비</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">메뉴 관리<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li>
								<a href="#">관리자 사이트</a>
							</li>
							<li>
								<a href="#">도민 안전점검 청구</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">관리자 관리<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li>
								<a href="#">권한그룹</a>
							</li>
							<li>
								<a href="#">관리자 그룹</a>
							</li>
							<li>
								<a href="<c:url value="/user/list.do"/>">관리자</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">공통코드 관리</a>
					</li>
					<li>
						<a href="#">메시지 이력 관리<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li>
								<a href="#">APP Push</a>
							</li>
							<li>
								<a href="#">SMS/LMS</a>
							</li>
							<li>
								<a href="#">E-Mail</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">보안 이력 관리<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li>
								<a href="#">데이터 변경</a>
							</li>
							<li>
								<a href="#">개인정보 열람/변경</a>
							</li>
							<li>
								<a href="#">관리자 사이트 접속</a>
							</li>
							<li>
								<a href="#">오류 로그</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>
				<a href="#"><i class="fa fa-wheelchair"></i> <span class="nav-label">Sample Guide</span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li>
						<a href="<c:url value="/sample/list.do"/>">샘플 게시판</a>
					</li>
					<li>
						<a href="#">템플릿<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li>
								<a href="<c:url value="/user/template_form.do"/>">form</a>
							</li>
							<li>
								<a href="<c:url value="/user/template_table.do"/>">table</a>
							</li>
							<li>
								<a href="<c:url value="/user/template_modal.do"/>">modal</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li class="landing_link">
				<a target="_blank" href="landing.html"><i class="fa fa-external-link"></i> <span class="nav-label">도민 안전점검 청구</span> <span class="label label-warning float-right">새창</span></a>
			</li>
		</ul>
	</div>
</nav>
