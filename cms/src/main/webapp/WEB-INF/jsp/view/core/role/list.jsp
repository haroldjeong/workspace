<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<c:set var="sort" value="${fn:replace(condition.sort, ' ', '')}" />

	<div class="row">
		<div class="col-lg-12">
			<form id="frmSearch" name="frmSearch" action="">
				<input type="hidden" name="id" id="id"/>
				<input type="hidden" name="pageUnit" id="pageUnit" value="<c:out value="${condition.pageUnit}"/>"/>
				<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value="${condition.pageIndex}"/>"/>
				<input type="hidden" name="sort" id="sort" value="<c:out value="${sort}"/>"/>

				<div class="ibox ibox-search">
					<div class="ibox-title">
						<h5>검색조건 </h5>
						<div class="ibox-tools">
							<a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div class="ibox-content pb-0">
						<div class="form-row">
							<div class="form-group col-lg-3">
								<label for="searchCondition"><strong>검색구분</strong></label>
								<select class="form-control" name="searchCondition" id="searchCondition">
									<option value="name" <c:if test="${condition.searchCondition eq 'name'}"> selected="selected"</c:if> >제목</option>
									<option value="long_desc" <c:if test="${condition.searchCondition eq 'long_desc'}"> selected="selected"</c:if> >상세내용</option>
								</select>
							</div>
							<div class="form-group col-lg-6">
								<label for="searchKeyword"><strong>검색어</strong></label>
								<div class="input-group input-group">
									<input class="form-control" placeholder="검색구분 선택 후, 검색어를 입력해주세요." name="searchKeyword" id="searchKeyword" value="<c:out value="${condition.searchKeyword}" />" autocomplete="off" />
									<div class="input-group-append">
										<button class="btn btn-primary _flow-action-search">Search</button>
										<button class="btn btn-white _flow-action-reset"><i class="fa fa-repeat"></i></button>
									</div>
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
			<form method="post" class="form-horizontal" name="default" id="frmDefault">
				<div class="ibox ">
					<div class="ibox-title">
						<c:if test="${empty condition.searchKeyword}">
							총 <strong><c:out value="${paginationInfo.totalRecordCount}" />건</strong>의 데이터가 조회되었습니다.
						</c:if>
						<c:if test="${not empty condition.searchKeyword}">
							<strong>"<c:out value="${condition.searchKeyword}"/>"</strong> 검색결과 <strong><c:out value="${paginationInfo.totalRecordCount}" />건</strong>의 데이터가 조회되었습니다.
						</c:if>
						<div class="ibox-tools">
							<span>1 / 1 pages</span>
							<a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-3 m-b-xs">
								<select class="form-control _flow-action-sort">
									<option value="reg_date:DESC" <c:if test="${sort eq 'reg_date:DESC' }">selected="selected"</c:if>>등록 일자 ▼</option>
									<option value="reg_date:ASC" <c:if test="${sort eq 'reg_date:ASC' }">selected="selected"</c:if>>등록 일자 ▲</option>
								</select>
							</div>
							<div class="col-sm-6 m-b-xs">
							</div>
							<div class="col-sm-3">
								<select class="form-control _flow-action-pageUnit">
									<option value="10" <c:if test="${condition.pageUnit eq '10' }">selected="selected"</c:if>>10개 보기</option>
									<option value="20" <c:if test="${condition.pageUnit eq '20' }">selected="selected"</c:if>>20개 보기</option>
									<option value="30" <c:if test="${condition.pageUnit eq '30' }">selected="selected"</c:if>>30개 보기</option>
									<option value="40" <c:if test="${condition.pageUnit eq '40' }">selected="selected"</c:if>>40개 보기</option>
									<option value="50" <c:if test="${condition.pageUnit eq '50' }">selected="selected"</c:if>>50개 보기</option>
									<option value="60" <c:if test="${condition.pageUnit eq '60' }">selected="selected"</c:if>>60개 보기</option>
									<option value="70" <c:if test="${condition.pageUnit eq '70' }">selected="selected"</c:if>>70개 보기</option>
									<option value="80" <c:if test="${condition.pageUnit eq '80' }">selected="selected"</c:if>>80개 보기</option>
									<option value="90" <c:if test="${condition.pageUnit eq '90' }">selected="selected"</c:if>>90개 보기</option>
									<option value="100" <c:if test="${condition.pageUnit eq '100' }">selected="selected"</c:if>>100개 보기</option>
								</select>
							</div>
						</div>
						<div class="table-responsive">
							<table class="table table-striped table-hover">
								<colgroup>
									<col width="8%"/>
									<col width="20%"/>
									<col width="*"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="text-center">No.</th>
										<th>Role Cd</th>
										<th>Role 이름</th>
										<th class="text-center">등록 일시</th>
										<th class="text-center">등록자</th>
										<th class="text-center">수정 일시</th>
										<th class="text-center">수정자</th>
										<th class="text-center">VIEW</th>
									</tr>
								</thead>
								<tbody>
								<c:if test="${fn:length(resultList) eq 0}">
									<tr>
										<td colspan="9" class="text-center">조회 결과가 없습니다.</td>
									</tr>
								</c:if>
								<c:forEach varStatus="status" items="${resultList}" var="result">
									<tr>
										<td class="text-center">
											<input type="checkbox" class="i-checks" name="input[]">&nbsp;&nbsp;
											<c:out value="${paginationInfo.totalRecordCount+1 - ((condition.pageIndex-1) * condition.pageSize + status.count)}"/>
										</td>
										<td><c:out value="${result.roleCd}"/></td>
										<td><c:out value="${result.name}"/></td>
										<td class="text-center"><javatime:format value="${result.regDate}" style="MS" /></td>
										<td><c:out value="${result.regId}"/></td>
										<td class="text-center"><javatime:format value="${result.modDate}" style="MS" /></td>
										<td><c:out value="${result.modId}"/></td>
										<td class="text-center">
											<button type="button" class="_flow-action-detail btn btn-white btn-sm" data-id="${result.id}" title="보기"><i class="fa fa-search"></i> </button>
											<button type="button" class="_flow-action-form btn btn-white btn-sm" data-id="${result.id}" title="수정"><i class="fa fa-wrench"></i></button>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row" style="margin-top:5px;">
						<div class="col-lg-4">
							<div class="btn-group">
								<button type="button" class="btn btn-white btn-sm _flow-action-form" title="등록"><i class="fa fa-pencil-square"></i> 등록</button>
							</div>
						</div>
						<div class="col-lg-8">
							<ul class="pagination float-right" id="paging">
								<ui:pagination paginationInfo = "${paginationInfo}" type="cmsPage" jsFunction="flow.bind.action.page" />
							</ul>
						</div>
					</div>
				</div>
			</form>
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

