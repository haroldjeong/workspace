<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<c:set var="sort" value="${fn:replace(condition.sort, ' ', '')}" />

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
							<label class="col-sm-3 col-form-label">이름<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="name" value="<c:out value="${result.name}"/>" title="이름은 필수 입력 항목입니다." maxlength="50" autocomplete="off" required="required" />
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">이메일<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="email" value="<c:out value="${result.email}"/>" title="이메일은 필수 입력 항목입니다." maxlength="50" autocomplete="off" required="required"/>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">전화번호</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="mobile" value="<c:out value="${result.mobile}"/>" maxlength="50" autocomplete="off" />
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">소속<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<select class="form-control" name="groupId" title="소속은 필수 선택 항목입니다." autocomplete="off" required="required">
									<option value="">선택하세요.</option>
									<c:forEach varStatus="status" items="${groupList}" var="group">
									<option value="<c:out value="${group.groupId}"/>" <c:if test="${group.groupId eq result.groupId}"> selected="selected"</c:if>><c:out value="${group.groupName}"/></option>
									</c:forEach>
								</select>
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
							<label class="col-sm-3 col-form-label">로그인 아이디<br/>
								<small class="text-navy">로그인 아이디는 변경하실 수 없습니다.</small>
							</label>
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
											<button type="button" class="btn btn-sm btn-link _flow-action-unlock" title="차단 해제하기" data-uri="form"><i class="fa fa-unlock"></i> 차단 해제하기</button>
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

								<label class="checkbox-inline i-checks">
									<input type="checkbox" class="_flow-check-userType" name="userType" value="W" <c:if test="${result.userType eq 'W' or result.userType eq 'A'}">checked="checked"</c:if> >&nbsp;&nbsp;관리자사이트&nbsp;&nbsp;
								</label>
								<label class="i-checks">
									<input type="checkbox" class="_flow-check-userType" name="userType" value="G" <c:if test="${result.userType eq 'G' or result.userType eq 'A'}">checked="checked"</c:if> >&nbsp;&nbsp;스마트글라스&nbsp;&nbsp;
								</label>
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
							<label class="col-sm-3 col-form-label">만료일자<small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<div class="input-group date _flow-init-datepicker future">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input type="text" name="expDate" id="expDate" class="form-control" value="<javatime:format value="${result.expDate}" pattern="yyyy-MM-dd" />" title="만료일은 필수 입력 항목입니다." autocomplete="off" required="required" readonly="readonly"/>
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
				<button type="button" class="btn btn-white _flow-action-save" data-uri="remove.do" title="삭제" data-confirm="삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다."><i class="fa fa-trash"></i> 삭제</button>
			</div>
			<div class="btn-group pull-right">
				<button type="button" class="btn btn-white _flow-action-save" title="저장" data-confirm="입력 내용을 저장하시겠습니까?"><i class="fa fa-save"></i> 저장</button>
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
		flow.bind.myInit();
	});

	var my = {
		bind: {
			myInit: function () {
				$("._flow-action-unlock").each(function(i, item) {
					$(item).off("click").on("click", flow.bind.action.unlock);
				});
			},
			action: {
				preSaveDataHandler: function (data) {

					<%-- 날짜 데이터 포맷 변환 (String -> LocalDateTime) --%>
					data.expDate = data.expDate + 'T00:00:00';

					<%-- 접속 시스템 전체 체크 시, 'A'로 값 변경 --%>
					if ($.isArray(data.userType)) {
						if (data.userType.length === $('input:checkbox[name=userType]').length) {
							data.userType = 'A';
						}
					}

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
				},
				unlock: function () {
					if (confirm('차단 해제하시겠습니까?')) {
						alert('구현예정');
					}
				}
			}
		}
	};
</script>

