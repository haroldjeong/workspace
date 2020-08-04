<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<c:set var="sort" value="${fn:replace(condition.sort, ' ', '')}" />

<div class="_flow-wrap tiles-body wrapper wrapper-content animated fadeInRight">
	<form id="frmSearch" name="search" action="">
		<%--공통 파라미터--%>
		<input type="hidden" name="pageUnit" id="pageUnit" value="<c:out value="${condition.pageUnit}"/>"/>
		<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value="${condition.pageIndex}"/>"/>
		<input type="hidden" name="sort" id="sort" value="<c:out value="${sort}"/>" />

		<%--페이지별 검색 파라미터--%>
		<input type="hidden" name="searchGroup" id="searchGroup" value="<c:out value="${condition.searchGroup}"/>"/>
		<input type="hidden" name="searchStatus" id="searchStatus" value="<c:out value="${condition.searchStatus}"/>"/>
		<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value="${condition.searchCondition}"/>"/>
		<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value="${condition.searchKeyword}"/>"/>
	</form>

	<div class="row">
		<div class="col-lg-12">
			<form method="post" class="form-horizontal" name="default" id="frmDefault">
				<input type="hidden" name="id" id="id" value="<c:out value="${condition.id}"/> "/>

				<div class="ibox ">
					<div class="ibox-title">
						<h5>기본정보</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">이름</label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.name}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">이메일</label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.email}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">전화번호</label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.mobile}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">소속</label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.groupName}"/></p>
							</div>
						</div>
					</div>
				</div>

				<div class="ibox ">
					<div class="ibox-title">
						<h5>계정정보</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">로그인 아이디</label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.loginId}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">계정 상태</label>
							<div class="col-sm-9">
								<p class="form-control-static">
									<c:choose>
										<c:when test="${result.status eq 'ACTIVATED'}">
											<span class="btn btn-sm btn-primary">활성</span>
										</c:when>
										<c:when test="${result.status eq 'BLOCKED'}">
											<span class="btn btn-sm btn-danger">차단</span>
										</c:when>
										<c:when test="${result.status eq 'EXPIRED'}">
											<span class="btn btn-sm btn-warning">만료</span>
										</c:when>
										<c:when test="${result.status eq 'WITHDRAWN'}">
											<span class="btn btn-sm btn-primary">탈퇴</span>
										</c:when>
									</c:choose>
								</p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">로그인 실패횟수<br/>
								<small class="text-navy">5회 실패 시 로그인이 차단됩니다.</small>
							</label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.loginFail}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">접속 시스템</label>
							<div class="col-sm-9">
								<c:if test="${result.userType eq 'W' or result.userType eq 'A'}">
									<span class="btn btn-sm btn-primary">관리자사이트</span>
								</c:if>
								<c:if test="${result.userType eq 'G' or result.userType eq 'A'}">
									<span class="btn btn-sm btn-primary">스마트글라스</span>
								</c:if>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">생성일시</label>
							<div class="col-sm-9">
								<p class="form-control-static"><javatime:format value="${result.regDate}" style="LL" /></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">만료일자</label>
							<div class="col-sm-9">
								<p class="form-control-static"><javatime:format value="${result.expDate}" style="L-" /></p>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="btn-group pull-left">
				<button type="button" class="btn btn-white _flow-action-delete" title="삭제"><i class="fa fa-trash"></i> 삭제</button>
			</div>
			<div class="btn-group pull-right">
				<button type="button" class="btn btn-white _flow-action-form" title="수정" data-id="${result.id}"><i class="fa fa-save"></i> 수정</button>
				<button type="button" class="btn btn-white _flow-action-list" title="목록"><i class="fa fa-undo"></i> 목록으로</button>
			</div>
		</div>
	</div>
</div>

<!-- DeepFine Library -->
<script src="/static/js/api/deepfine.core.js" type="text/javascript"></script>
<script src="/static/js/api/deepfine.common.js" type="text/javascript"></script>
<script type="text/javaScript">
	$(document).ready(function () {
		flow.init();
	});
</script>

