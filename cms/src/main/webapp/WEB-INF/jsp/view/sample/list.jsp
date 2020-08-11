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
						<h5><spring:message code="search.title" text="검색 조건" /></h5>
						<div class="ibox-tools">
							<a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div class="ibox-content pb-0">
						<div class="form-row">
							<div class="form-group col-lg-3">
								<label for="searchCategory"><strong><spring:message code="search.category" text="카테고리"/> </strong></label>
								<select class="form-control" name="searchCategory" id="searchCategory">
									<option value=""><spring:message code="search.all" text="전체"/></option>
									<c:forEach varStatus="status" items="${code:findListByPathCd(codeSet, 'ROOT.workflow_group')}" var="category" >
										<option value="<c:out value="${category.id}"/>" <c:if test="${category.id eq condition.searchCategory}"> selected="selected"</c:if>><c:out value="${category.name}"/></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-lg-3">
								<label for="searchCondition"><strong><spring:message code="search.condition" text="검색구분" /> </strong></label>
								<select class="form-control" name="searchCondition" id="searchCondition">
									<option value="name" <c:if test="${condition.searchCondition eq 'name'}"> selected="selected"</c:if> ><spring:message code="common.title" text="제목"/></option>
									<option value="long_desc" <c:if test="${condition.searchCondition eq 'long_desc'}"> selected="selected"</c:if> ><spring:message code="common.content" text="내용"/></option>
								</select>
							</div>
							<div class="form-group col-lg-6">
								<label for="searchKeyword"><strong><spring:message code="search.keword" text="검색 키워드"/> </strong></label>
								<div class="input-group input-group">
									<input class="form-control" placeholder="검색구분 선택 후, 검색어를 입력해주세요." name="searchKeyword" id="searchKeyword" value="<c:out value="${condition.searchKeyword}" />" autocomplete="off" />
									<div class="input-group-append">
										<button class="btn btn-primary _flow-action-search"><spring:message code="button.search" text="검색"/></button>
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
							<spring:message code="search.toralcount.none" text="총 <strong>n건의 데이터가 조회되었습니다." arguments="${paginationInfo.totalRecordCount}" htmlEscape="false" />
						</c:if>
						<c:if test="${not empty condition.searchKeyword}">
							<spring:message code="search.toralcount.search" text="<strong>n</strong> 검색결과 총 <strong>n건의 데이터가 조회되었습니다." arguments="${condition.searchKeyword},${paginationInfo.totalRecordCount}" htmlEscape="false" />
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
									<option value="reg_date:DESC" <c:if test="${sort eq 'reg_date:DESC' }">selected="selected"</c:if>><spring:message code="search.sort" text="등록 일자" /> ▼</option>
									<option value="reg_date:ASC" <c:if test="${sort eq 'reg_date:ASC' }">selected="selected"</c:if>><spring:message code="search.sort" text="등록 일자" /> ▲</option>
								</select>
							</div>
							<div class="col-sm-6 m-b-xs">
							</div>
							<div class="col-sm-3">
								<select class="form-control _flow-action-pageUnit">
									<option value="10" <c:if test="${condition.pageUnit eq '10' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="10" /></option>
									<option value="20" <c:if test="${condition.pageUnit eq '20' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="20" /></option>
									<option value="30" <c:if test="${condition.pageUnit eq '30' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="30" /></option>
									<option value="40" <c:if test="${condition.pageUnit eq '40' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="40" /></option>
									<option value="50" <c:if test="${condition.pageUnit eq '50' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="50" /></option>
									<option value="60" <c:if test="${condition.pageUnit eq '60' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="60" /></option>
									<option value="70" <c:if test="${condition.pageUnit eq '70' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="70" /></option>
									<option value="80" <c:if test="${condition.pageUnit eq '80' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="80" /></option>
									<option value="90" <c:if test="${condition.pageUnit eq '90' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="90" /></option>
									<option value="100" <c:if test="${condition.pageUnit eq '100' }">selected="selected"</c:if>><spring:message code="search.count.num" text="n개 보기" arguments="100" /></option>
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
									<col width="10%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="text-center">No.</th>
										<th><spring:message code="sample.list.category" text="카테고리"/></th>
										<th><spring:message code="sample.list.title" text="제목"/></th>
										<th class="text-center"><spring:message code="sample.list.testdate" text="테스트 일자"/></th>
										<th class="text-center"><spring:message code="sample.list.regdate" text="등록 일시"/></th>
										<th class="text-center"><spring:message code="sample.list.regid" text="등록자"/></th>
										<th class="text-center"><spring:message code="sample.list.moddate" text="수정 일시"/></th>
										<th class="text-center"><spring:message code="sample.list.modid" text="수정자"/></th>
										<th class="text-center">VIEW</th>
									</tr>
								</thead>
								<tbody>
								<c:if test="${fn:length(resultList) eq 0}">
									<tr>
										<td colspan="9" class="text-center"><spring:message code="search.content.none" text="조회 결과가 없습니다."/></td>
									</tr>
								</c:if>
								<c:forEach varStatus="status" items="${resultList}" var="result">
									<tr>
										<td class="text-center">
											<input type="checkbox" class="i-checks" name="input[]">&nbsp;&nbsp;
											<c:out value="${paginationInfo.totalRecordCount+1 - ((condition.pageIndex-1) * condition.pageSize + status.count)}"/>
										</td>
										<td><c:out value="${code:findById(codeSet, result.categoryCd)}"/></td>
										<td><c:out value="${result.name}"/></td>
										<td class="text-center"><javatime:format value="${result.testDate}" style="M-" /></td>
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
								<button type="button" class="btn btn-white btn-sm _flow-action-form" title="등록"><i class="fa fa-pencil-square"></i><spring:message code="button.create" text="등록"/></button>
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

