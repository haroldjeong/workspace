<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<c:set var="sort" value="${fn:replace(condition.sort, ' ', '')}" />

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
						<h5>기본정보</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">카테고리<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<select class="form-control" name="categoryCd" title="카테고리는 필수 선택 항목입니다." autocomplete="off" required="required">
									<option value="">선택하세요.</option>
									<c:forEach varStatus="status" items="${code:findListByPathCd(codeSet, 'ROOT.workflow_group')}" var="category" >
										<option value="<c:out value="${category.id}"/>" <c:if test="${category.id eq result.categoryCd}"> selected="selected"</c:if>><c:out value="${category}"/></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">제목<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="name" value="<c:out value="${result.name}"/>" title="제목은 필수 입력 항목입니다." maxlength="30" autocomplete="off" required="required"/>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">짧은 내용</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="shortDesc" value="<c:out value="${result.shortDesc}"/>" maxlength="50" autocomplete="off" />
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">긴 내용<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="longDesc" value="<c:out value="${result.longDesc}"/>" title="긴 내용은 필수 입력 항목입니다." autocomplete="off" required="required"/>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">테스트 일자<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<div class="input-group date _flow-init-datepicker future">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input type="text" name="testDate" id="testDate" class="form-control" value="<javatime:format value="${result.testDate}" pattern="yyyy-MM-dd" />" title="테스트 일자는 필수 입력 항목입니다." autocomplete="off" required="required" readonly="readonly"/>
								</div>
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
		flow.init(my);
	});

	var my = {
		bind: {
			action: {
				preSaveDataHandler: function (data) {
					<%-- 날짜 데이터 포맷 변환 (String -> LocalDateTime) --%>
					data.testDate = data.testDate + 'T00:00:00';
					return data;
				},
				validator: function (data) {
					var isValid = true;
					$("[required='required']").each(function (i, item) {
						if ($.trim($(item).val()) === '') {
							isValid = false;
							alert($(item).prop('title') || '필수 항목이 누락되었습니다.');
							$(item).focus();
							return isValid;
						}
					});

					return isValid;
				}
			}
		}
	};
</script>

