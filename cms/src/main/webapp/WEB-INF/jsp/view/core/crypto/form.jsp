<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>


<div class="row">
	<div class="col-lg-12">
		<form method="post" class="form-horizontal" name="default" id="frmDefault">
			<input type="hidden" name="id" id="id" value="<c:out value="${condition.id}"/>"/>

			<div class="ibox ">
				<div class="ibox-title">
					<h5>AES 256</h5>
					<div class="ibox-tools">
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">
							암호화&nbsp;&nbsp;<button type="button" class="btn btn-danger _flow-aes-enc" ><i class="fa fa-lock"></i></button>
						</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="aesEnc" value="" maxlength="500" autocomplete="off" required="required" />
							<input type="text" class="form-control" id="aesEncResult" value="" maxlength="500" autocomplete="off" required="required" readonly="readonly" style="margin-top: 10px;" />
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">
							복호화&nbsp;&nbsp;<button type="button" class="btn btn-danger _flow-aes-dec" ><i class="fa fa-unlock"></i></button>
						</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="aesDec" value="" maxlength="500" autocomplete="off" required="required" />
							<input type="text" class="form-control" id="aesDecResult" value="" maxlength="500" autocomplete="off" required="required" readonly="readonly" style="margin-top: 10px;" />
						</div>
					</div>
				</div>
			</div>

			<div class="ibox ">
				<div class="ibox-title">
					<h5>SHA 512&nbsp;&nbsp;<button type="button" class="btn btn-danger _flow-sha-enc" ><i class="fa fa-lock"></i></button></h5>
					<div class="ibox-tools">
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">SHA512<br/>
							<small class="text-navy">Salt 값이 필요합니다.</small>
						</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<input type="text" class="form-control" id="shaEnc" value="" maxlength="500" autocomplete="off" required="required" />
							</p>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">Salt<br/>
						</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<input type="text" class="form-control" id="shaEncSalt" value="" maxlength="1024" autocomplete="off" required="required" />
							</p>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">결과<br/>
						</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<input type="text" class="form-control" id="shaEncResult" value="" maxlength="1024" autocomplete="off" readonly="readonly" required="required" />
							</p>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="/static/js/api/deepfine.core.js" type="text/javascript"></script>
<script src="/static/js/api/deepfine.common.js" type="text/javascript"></script>

<script>

	$(document).ready(function () {
		flow.init();

		$('._flow-aes-enc').click(function () {
			var str = $('#aesEnc').val();
			if (str === '') {
				alert('암호화 할 문자열을 입력해주세요.');
				$('#aesEnc').focus();
			} else {
				$.core.ajax.post({
					url: 'aes-enc.do'
					, dataType: 'text'
					, data: str
				}).done(function (result) {
					$('#aesEncResult').val(result);
				});
			}
		});

		$('._flow-aes-dec').click(function () {
			var str = $('#aesDec').val();
			if (str === '') {
				alert('복호화 할 문자열을 입력해주세요.');
				$('#aesDec').focus();
			} else {
				$.core.ajax.post({
					url: 'aes-dec.do'
					, dataType: 'text'
					, data: str
				}).done(function (result) {
					$('#aesDecResult').val(result);
				});
			}
		});

		$('._flow-sha-enc').click(function () {
			var str = $('#shaEnc').val();
			var salt = $('#shaEncSalt').val();

			if (str === '') {
				alert('암호화 할 문자열을 입력해주세요.');
				$('#shaEnc').focus();
			} else if (salt === '') {
				alert('Salt 값을 입력해주세요.');
				$('#shaEncSalt').focus();
			} else {
				$.core.ajax.post({
					url: 'sha-enc.do'
					, dataType: 'text'
					, data: str + "::" + salt
				}).done(function (result) {
					$('#shaEncResult').val(result);
				});
			}
		});
	});
</script>