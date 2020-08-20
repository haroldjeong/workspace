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
		<input type="hidden" name="searchCategory" id="searchCategory" value="<c:out value="${condition.searchCategory}"/>"/>
		<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value="${condition.searchCondition}"/>"/>
		<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value="${condition.searchKeyword}"/>"/>
	</form>

	<div class="row">
		<div class="col-lg-12">
			<form method="post" class="form-horizontal" name="default" id="frmDefault">
				<input type="hidden" name="id" id="id" value="<c:out value="${condition.id}"/>"/>

				<div class="ibox ">
					<div class="ibox-title">
						<h5><spring:message code="sample.form.hedertitle" text="기본정보" /></h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.list.category" text="카테고리" /></label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${code:findById(codeSet, result.categoryCd)}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.list.title" text="제목" /></label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.name}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.form.shortDesc" text="짧은 내용" /></label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.shortDesc}"/></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.form.longDesc" text="긴 내용" /></label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.longDesc}"/></p>
							</div>
						</div>
					</div>
				</div>

				<div class="ibox ">
					<div class="ibox-title">
						<h5><spring:message code="sample.form.saveinfo" text="등록정보" /></h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.list.regdate" text="등록 일시" /></label>
							<div class="col-sm-9">
								<p class="form-control-static"><javatime:format value="${result.regDate}" style="LL" /></p>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.list.regid" text="등록자" /></label>
							<div class="col-sm-9">
								<p class="form-control-static"><c:out value="${result.regId}"/></p>
							</div>
						</div>
						<c:if test="${not empty result.modId}" >
							<div class="hr-line-dashed"></div>
							<div class="form-group row">
								<label class="col-sm-3 col-form-label"><spring:message code="sample.list.moddate" text="수정 일시" /></label>
								<div class="col-sm-9">
									<p class="form-control-static"><javatime:format value="${result.modDate}" style="LL" /></p>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group row">
								<label class="col-sm-3 col-form-label"><spring:message code="sample.list.modid" text="수정자" /></label>
								<div class="col-sm-9">
									<p class="form-control-static"><c:out value="${result.modId}"/></p>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="btn-group pull-left">
				<button type="button" class="btn btn-white _flow-action-save" data-uri="remove.do" title="<spring:message code="button.delete" text="삭제" />" data-confirm="<spring:message code="button.delete.confirm" text="삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다." />"><i class="fa fa-trash"></i> <spring:message code="button.delete" text="삭제" /></button>
			</div>
			<div class="btn-group pull-right">
				<button type="button" class="btn btn-white _flow-action-form" title="<spring:message code="button.modified" text="수정" />" data-id="${result.id}"><i class="fa fa-save"></i> <spring:message code="button.modified" text="수정" /></button>
				<button type="button" class="btn btn-white _flow-action-list" title="<spring:message code="button.list" text="목록" />"><i class="fa fa-undo"></i> <spring:message code="button.list" text="목록" /></button>
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

