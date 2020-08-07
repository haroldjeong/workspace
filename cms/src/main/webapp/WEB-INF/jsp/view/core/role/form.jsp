<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<c:set var="sort" value="${fn:replace(condition.sort, ' ', '')}" />

	<form id="frmSearch" name="search" action="">
		<%--공통 파라미터--%>
		<input type="hidden" name="pageUnit" id="pageUnit" value="<c:out value="${condition.pageUnit}"/>"/>
		<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value="${condition.pageIndex}"/>"/>
		<input type="hidden" name="sort" id="sort" value="<c:out value="${sort}"/>" />
		<%--페이지별 검색 파라미터--%>
		<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value="${condition.searchCondition}"/>"/>
		<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value="${condition.searchKeyword}"/>"/>
	</form>

	<div class="row">
		<div class="col-lg-12">
			<form method="post" class="form-horizontal" name="default" id="frmDefault">
				<input type="hidden" name="id" id="id" value="<c:out value="${condition.id}"/>"/>
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
							<label class="col-sm-3 col-form-label">Role 코드<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="roleCd" value="<c:out value="${result.roleCd}"/>" title="Role 코드는 필수 입력 항목입니다." maxlength="30" autocomplete="off" required="required"/>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">Role 이름<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="name" value="<c:out value="${result.name}"/>" title="Role 이름은 필수 입력 항목입니다." maxlength="30" autocomplete="off" required="required"/>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">Role 설명</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="roleDesc" value="<c:out value="${result.roleDesc}"/>" maxlength="50" autocomplete="off" />
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">사용 여부<br/></label>
							<div class="col-sm-9">
								<label><input type="radio" checked="checked" value="Y" name="useYn">사용</label>
								<label><input type="radio" <c:if test="${result.useYn eq 'N'}">checked="checked"</c:if> value="N" name="useYn">미사용</label>
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
				<c:if test="${!empty condition.id}">
					<button type="button" class="btn btn-white _flow-action-save" data-uri="remove.do" title="삭제" data-confirm="삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다."><i class="fa fa-trash"></i> 삭제</button>
				</c:if>
			</div>
			<div class="btn-group pull-right">
				<button type="button" class="btn btn-white _flow-action-save" title="저장" data-confirm="입력 내용을 저장하시겠습니까?" data-validation="Y"><i class="fa fa-save"></i> 저장</button>
				<button type="button" class="btn btn-white _flow-action-list" title="목록"><i class="fa fa-undo"></i> 목록으로</button>
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

