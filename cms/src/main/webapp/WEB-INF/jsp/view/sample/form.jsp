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
				<input type="hidden" name="fileId" id="fileId" value="<c:out value="${result.fileId}"/>"/>
				<input type="hidden" name="fileId2" id="fileId2" value="<c:out value="${result.fileId2}"/>"/>
				<input type="hidden" name="fileId3" id="fileId3" value="<c:out value="${result.fileId3}"/>"/>
				<input type="hidden" name="uploadedFile" id="uploadedFile" value="<c:out value="${uploadedFile.id}"/>"/>

				<div class="ibox">
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
							<label class="col-sm-3 col-form-label"><spring:message code="sample.list.category" text="카테고리" /><small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<select class="form-control" name="categoryCd" title="카테고리는 필수 선택 항목입니다." autocomplete="off" required="required">
									<option value=""><spring:message code="common.choice.category" text="선택하세요." /></option>
									<c:forEach varStatus="status" items="${code:findListByPathCd(codeSet, 'ROOT.workflow_group')}" var="category" >
										<option value="<c:out value="${category.id}"/>" <c:if test="${category.id eq result.categoryCd}"> selected="selected"</c:if>><c:out value="${category}"/></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.list.title" text="제목" /><small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="name" value="<c:out value="${result.name}"/>" title="<spring:message code="sample.list.title" text="제목" /><spring:message code="common.required" text="은(는) 필수 입력 항목입니다." />" maxlength="30" autocomplete="off" required="required"/>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.form.shortDesc" text="짧은 내용" /></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="shortDesc" value="<c:out value="${result.shortDesc}"/>" maxlength="50" autocomplete="off" />
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.form.longDesc" text="긴 내용" /><small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="longDesc" value="<c:out value="${result.longDesc}"/>" title="<spring:message code="sample.form.longDesc" text="긴 내용" /><spring:message code="common.required" text="은(는) 필수 입력 항목입니다." />" autocomplete="off" required="required"/>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label"><spring:message code="sample.list.testdate" text="테스트 일자" /><small class="text-danger"> *</small></label>
							<div class="col-sm-9">
								<div class="input-group date _flow-init-datepicker future">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input type="text" name="testDate" id="testDate" class="form-control" value="<javatime:format value="${result.testDate}" pattern="yyyy-MM-dd" />" title="<spring:message code="sample.list.testdate" text="테스트 일자" /><spring:message code="common.required" text="은(는) 필수 입력 항목입니다." />" autocomplete="off" required="required" readonly="readonly"/>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">첨부파일</label>
							<div class="col-sm-9" id="singleUploadZone">
								<input type="file" class="form-control" id="singleFileUploader" name="singleFileUploader"/>
								<div id="uploadedFileInfo">
									<c:if test="${!empty uploadedFile}">
										<div>
												<%--TODO:수정 시 아이콘 출력 필요 (jb.choi) --%>
											<c:out value="${uploadedFile.originalName}"/>
											<input type="button" value="삭제" onclick="setDeleteFile(this);"/>
										</div>
									</c:if>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">첨부파일</label>
							<div class="col-sm-9" id="multiUploadZone">
								<input type="file" class="form-control" id="multiFileUploader" multiple/>
								<c:forEach var="uploadedFileList" items="${uploadedFileList}">
									<div id="uploadedFileListInfo">
											<%--TODO:수정 시 아이콘 출력 필요 (jb.choi) --%>
										<c:out value="${uploadedFileList.originalName}"/>
										<input type="button" value="삭제" onclick="setDeleteFileList(this);"/>
										<input type="hidden" name="uploadedFileList" value="${uploadedFileList.id}"/>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">첨부파일</label>
							<div class="col-sm-9" id="multiUploadZone2">
								<input type="file" class="form-control" id="multiFileUploader2" multiple/>
								<c:forEach var="uploadedFileList2" items="${uploadedFileList2}">
									<div id="uploadedFileListInfo2">
											<%--TODO:수정 시 아이콘 출력 필요 (jb.choi) --%>
										<c:out value="${uploadedFileList2.originalName}"/>
										<input type="button" value="삭제" onclick="setDeleteFileList(this);"/>
										<input type="hidden" name="uploadedFileList2" value="${uploadedFileList2.id}"/>
									</div>
								</c:forEach>
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

			</div>
			<div class="btn-group pull-right">
				<button type="button" class="btn btn-white _flow-action-form" title="<spring:message code="button.modified" text="수정" />" data-id="${result.id}"><i class="fa fa-save"></i> <spring:message code="button.modified" text="수정" /></button>

			</div>



			<div class="btn-group pull-left">
				<c:if test="${!empty condition.id}">
					<button type="button" class="btn btn-white _flow-action-save" data-uri="remove.do" title="<spring:message code="button.delete" text="삭제" />" data-confirm="<spring:message code="button.delete.confirm" text="삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다." />"><i class="fa fa-trash"></i> <spring:message code="button.delete" text="삭제" /></button>
				</c:if>
			</div>
			<div class="btn-group pull-right">
				<button type="button" class="btn btn-white _flow-action-save" title="<spring:message code="button.create" text="등록" />" data-confirm="<spring:message code="button.create.confirm" text="입력 내용을 저장하시겠습니까?" />" data-validation="Y"><i class="fa fa-save"></i> <spring:message code="button.create" text="등록" /></button>
				<button type="button" class="btn btn-white _flow-action-list" title="<spring:message code="button.list" text="목록" />"><i class="fa fa-undo"></i> <spring:message code="button.list" text="목록" /></button>
			</div>
		</div>
	</div>

<!-- DeepFine Library -->
<script src="/static/js/api/deepfine.core.js" type="text/javascript"></script>
<script src="/static/js/api/deepfine.common.js" type="text/javascript"></script>
<script type="text/javaScript">
	$(document).ready(function () {
		flow.init(my);

        // 드래그&드랍 단일 업로드
        $("#singleUploadZone").on("dragover", function(e){
            e.stopPropagation(); //이벤트 버블링 차단
            e.preventDefault();	 //고유 이벤트 차단
        }).on("drop", function(e){
            e.stopPropagation(); //이벤트 버블링 차단
            e.preventDefault();	 //고유 이벤트 차단

            const selectedFile = e.originalEvent.dataTransfer.files["0"];
            sendSelectedFile(selectedFile);
        });

		// 버튼을 통한 단일 업로드
        $("#singleFileUploader").on("change", function(){
            const selectedFile = $("#singleFileUploader")["0"]["files"]["0"];

            if(selectedFile != undefined)
            	sendSelectedFile(selectedFile);
        });

        // 드래그&드랍 다중 업로드
        $("#multiUploadZone, #multiUploadZone2").on("dragover", function(e){
            e.stopPropagation(); //이벤트 버블링 차단
            e.preventDefault();	 //고유 이벤트 차단
        }).on("drop", function(e){
            e.stopPropagation(); //이벤트 버블링 차단
            e.preventDefault();	 //고유 이벤트 차단

            const selectedFiles = e.originalEvent.dataTransfer.files;
            sendSelectedFiles(selectedFiles, e.target.parentNode);
        });

        // 버튼을 통한 다중 업로드
        $("#multiFileUploader, #multiFileUploader2").on("change", function(e){
            const selectedFiles = e.target.files;

            if(selectedFiles.length != 0)
	            sendSelectedFiles(selectedFiles, e.target.parentNode);
        });

        /**
		 * 단일 업로드
         * @apiNote 사용자가 선택한 파일을 서버로 전송
         * @param selectedFile 사용자가 선택한 파일
         */
        function sendSelectedFile(selectedFile) {
            const uploadForm = new FormData(document.default);
            uploadForm.append("selectedFile", selectedFile);

			$.ajax({
				url: "<c:url value='/file/singleUpload.do'/>",
				type: "post",
				enctype: 'multipart/form-data',
				data: uploadForm,
				dataType: "json",
				contentType: false,
				processData: false,
				success: function(data) {
				    // 마스터 파일 아이디 저장
					$("#fileId").val(data["parentId"]);

                    // 파일명 출력
                    $("#uploadedFileInfo").text(data["originalName"]);

                    // MIME 타입 확인
                    if(data["mimetype"].split("/")[0] === "image") {
                        // 썸네일 생성
                        const uploadedFileThumbnail = createThumbnail(selectedFile);
                        $("#uploadedFileInfo").append(uploadedFileThumbnail);
                    }  else {
                        // 아이콘 생성
						const uploadedFileIcon = getFileIcon(selectedFile);
                        $("#uploadedFileInfo").append(uploadedFileIcon);
                    }

                    // 파일 제거 버튼 설정
                    const deleteFileBtn = $("<input type='button' value='삭제'/>");
                    deleteFileBtn.on("click", function() {
                        setDeleteFile(this);
                    });
                    $("#uploadedFileInfo").append(deleteFileBtn);

                    // 업로드된 파일 아이디 저장
                    $("#uploadedFile").val(data["id"]);
                },
				error: function() {
					alert("파일 업로드에 실패했습니다. 다시 시도해 주세요.");
				}
			});
        }

        /**
		 * 다중 업로드
		 * @apiNote 사용자가 선택한 파일 리스트를 서버로 전송
         * @param selectedFiles 사용자가 선택한 파일 리스트
         */
        function sendSelectedFiles(selectedFiles, uploadZone) {
            const uploadForm = new FormData(document.default);

            // TODO::재설정 필요 (jb.choi)
            if(uploadZone.id === "multiUploadZone")
                uploadForm.append("selectedFileId", document.getElementById("fileId2").value);
            else if(uploadZone.id === "multiUploadZone2")
                uploadForm.append("selectedFileId", document.getElementById("fileId3").value);

            for(let i = 0; i < selectedFiles.length; i++)
                uploadForm.append("selectedFiles", selectedFiles[i]);

            $.ajax({
                url: "<c:url value='/file/multiUpload.do'/>",
                type: "post",
                enctype: 'multipart/form-data',
                data: uploadForm,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function(data) {
					// 마스터 파일 아이디 저장
                    // TODO::재설정 필요 (jb.choi)
					if(uploadZone.id === "multiUploadZone")
                        $("#fileId2").val(data[0]["parentId"]);
					else if(uploadZone.id === "multiUploadZone2")
                        $("#fileId3").val(data[0]["parentId"]);

                    for(let i = 0; i < data.length; i++) {
                        // 파일명 출력
                        const uploadedFileListInfo = $("<div>").text(data[i]["originalName"]);

                        // MIME 타입 확인
                        if(data[i]["mimetype"].split("/")[0] === "image") {
                            // 썸네일 생성
                            const uploadedFileThumbnail = createThumbnail(selectedFiles[i]);
                            $(uploadedFileListInfo).append(uploadedFileThumbnail);
                        }  else {
                            // 파일 아이콘 가져오기
                            const uploadedFileIcon = getFileIcon(selectedFiles[i]);
                            $(uploadedFileListInfo).append(uploadedFileIcon);
                        }

                        // 파일 제거 버튼 설정
                        const deleteFileBtn = $("<input type='button' value='삭제'/>");
                        deleteFileBtn.on("click", function() {
                            setDeleteFileList(this);
                        });
                        $(uploadedFileListInfo).append(deleteFileBtn);

                        // 업로드된 파일 아이디 저장
                        // TODO::재설정 필요 (jb.choi)
                        if(uploadZone.id === "multiUploadZone") {
                        	const uploadedFileListId = $("<input type='hidden' name='uploadedFileList' value='" + data[i]["id"] + "'>")
                        	$(uploadedFileListInfo).append(uploadedFileListId);
						}
                        else if(uploadZone.id === "multiUploadZone2") {
                            const uploadedFileListId = $("<input type='hidden' name='uploadedFileList2' value='" + data[i]["id"] + "'>")
                            $(uploadedFileListInfo).append(uploadedFileListId);
                        }

                        // 다중 업로드 영역에 파일 정보 추가
                        $(uploadZone).append(uploadedFileListInfo);
                    }
                },
                error: function() {
                    alert("파일 업로드에 실패했습니다. 다시 시도해 주세요.");
                }
            });
		}
	});

	// TODO::재설정 필요 (jb.choi)
    function setDeleteFile(deleteFileBtn) {
        $(deleteFileBtn).parent().empty();
        $("#uploadedFile").val("");
    }

    // TODO::재설정 필요 (jb.choi)
    function setDeleteFileList(deleteFileBtn) {
        $(deleteFileBtn).parent().remove();
    }

    /**
	 * 썸네일 생성
     * @param uploadedFile 업로드된 파일
     * @return 썸네일
     */
    function createThumbnail(uploadedFile) {
        const img = document.createElement("img");
        const reader = new FileReader();

        reader.readAsDataURL(uploadedFile);
        reader.addEventListener("load", function () {
            img.src = reader.result;
            img.alt = uploadedFile.name;
            img.width = 50;
            img.height = 50;
        }, false);

        return img;
    }

    /**
	 * 파일 아이콘 가져오기
     * @param uploadedFile 업로드된 파일
     * @return 파일 아이콘
     */
    function getFileIcon(uploadedFile) {
        const img = document.createElement("img");
		let mimeTypeImg;

        switch (uploadedFile.type) {
            case "application/pdf": mimeTypeImg = "icon_pdf.png"; break;
            case "application/haansofthwp": mimeTypeImg = "icon_hwp.png"; break;
            case "application/msword":
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": mimeTypeImg = "icon_docx.png"; break;
            case "application/vnd.ms-powerpoint":
            case "application/vnd.openxmlformats-officedocument.presentationml.presentation": mimeTypeImg = "icon_pptx.png"; break;
			case "application/vnd.ms-excel":
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": mimeTypeImg = "icon_xlsx.png"; break;
			default: mimeTypeImg = "icon_file.png";
		}

		img.src = "/static/img/upload/" + mimeTypeImg;
        img.alt = uploadedFile.name;
		img.width = 50;
		img.height = 50;

        return img;
    }

	var my = {
		bind: {
			action: {
				preSaveDataHandler: function (data) {
					<%-- 날짜 데이터 포맷 변환 (String -> LocalDateTime) --%>
					data.testDate = data.testDate + 'T00:00:00';

                    <%-- 다중 파일 업로드에서 1개만 전달하는 경우 리스트로 형변환 --%>
                    if(typeof data.uploadedFileList === "string")
                        data.uploadedFileList = [data.uploadedFileList];

                    <%-- 다중 파일 업로드에서 1개만 전달하는 경우 리스트로 형변환 --%>
                    if(typeof data.uploadedFileList2 === "string")
                        data.uploadedFileList2 = [data.uploadedFileList2];

					return data;
				},
				validator: function (data) {
					var isValid = true;
					$("[required='required']").each(function (i, item) {
						if ($.trim($(item).val()) === '') {
							isValid = false;
							alert($(item).prop('title'));
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

