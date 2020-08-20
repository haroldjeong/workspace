<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>

<div class="row">
	<div class="col-lg-4">
		<div class="ibox float-e-margins">
			<div class="ibox-content mailbox-content">
				<div class="btn-group">
					<button class="btn btn-white btn-sm m-n" onclick="flow.render.tree.openAll();"><spring:message code="code.list.allopen" text="모두 열기"/></button>
					<button class="btn btn-white btn-sm m-n" onclick="flow.render.tree.closeAll();"><spring:message code="code.list.allclose" text="모두 접기"/></button>
				</div>
				<input class="form-control _flow-input-search" placeholder="search code" />
				<div class="_flow-code-tree"></div>
			</div>
		</div>
	</div>
	<div class="col-lg-8 animated fadeInRight">
		<div class="row">
			<div class="col-lg-12">
				<div id="_info" class="panel panel-default m-t">
					<div class="panel-body">
						<h3><spring:message code="code.list.tip_01" text="좌측 트리에서 코드를 선택하세요."/></h3>
						<p><spring:message code="code.list.tip_02" text="최상위 코드를 등록 하시려면 아래 버튼을 선택 하세요."/></p>
						<button type="button" class="btn btn-success btn-sm" title="<spring:message code="code.list.topsave" text="최상위 코드 등록"/>" onclick="flow.render.form('ADD-TOP');">
							<i class="fa fa-pencil-square"></i> <spring:message code="code.list.topsave" text="최상위 코드 등록"/>
						</button>
					</div>
				</div>
				<div class="panel panel-default m-t _flow-code-detail" style="display:none;">
					<div class="panel-heading">
						<i class="fa fa-home"></i>&nbsp;<strong></strong>
					</div>
					<div class="panel-body">
						<table class="table table-detail"><colgroup><col width="150"/><col/></colgroup><tbody>
						<tr>
							<th><spring:message code="code.list.code" text="코드"/> *</th>
							<td data-detail="cd"></td>
						</tr>
						<tr>
							<th><spring:message code="code.list.name" text="표기명"/> *</th>
							<td data-detail="name"></td>
							<!--
							<td>
								<table class="table table-detail m-n">
									<colgroup><col width="100"/><col/></colgroup>
									<tbody>
									<tr>
										<th>한국어</th>
										<td>
											<i class="fa fa-desktop"></i>&nbsp;name
										</td>
									</tr>
									<tr>
										<th>영어</th>
										<td>
											<i class="fa fa-desktop"></i>&nbsp;name
										</td>
									</tr>
									</tbody>
								</table>
							</td>
							-->
						</tr>
						<tr>
							<th><spring:message code="code.list.shrtDesc" text="짧은 표기명"/></th>
							<td data-detail="shrtDesc"></td>
							<!--
							<td>
								<table class="table table-detail m-n">
									<colgroup><col width="100"/><col/></colgroup>
									<tbody>
									<tr>
										<th>한국어</th>
										<td>
											<i class="fa fa-desktop"></i>&nbsp;name
										</td>
									</tr>
									<tr>
										<th>영어</th>
										<td>
											<i class="fa fa-desktop"></i>&nbsp;name
										</td>
									</tr>
									</tbody>
								</table>
							</td>
							-->
						</tr>
						<tr>
							<th><spring:message code="code.list.longDesc" text="전체 표기명"/></th>
							<td data-detail="longDesc"></td>
							<!--
							<td>
								<table class="table table-detail m-n">
									<colgroup><col width="100"/><col/></colgroup>
									<tbody>
									<tr>
										<th>한국어</th>
										<td>
											<i class="fa fa-desktop"></i>&nbsp;name
										</td>
									</tr>
									<tr>
										<th>영어</th>
										<td>
											<i class="fa fa-desktop"></i>&nbsp;name
										</td>
									</tr>
									</tbody>
								</table>
							</td>
							-->
						</tr>
						<tr>
							<th><spring:message code="code.list.customField1" text="사용자 정의 값1"/></th>
							<td data-detail="customField1"></td>
						</tr>
						<tr>
							<th><spring:message code="code.list.customField2" text="사용자 정의 값2"/></th>
							<td data-detail="customField2"></td>
						</tr>
						<tr>
							<th><spring:message code="code.list.customField3" text="사용자 정의 값3"/></th>
							<td data-detail="customField3"></td>
						</tr>
						<tr>
							<th><spring:message code="code.list.useYn" text="사용여부"/>*</th>
							<td data-detail="useYn"></td>
						</tr>
						</table>
						<div class="form-row">
							<div class="form-group col-lg-12">
								<div class="btn-group pull-right">
									<button type="button" class="btn btn-white btn-sm" title="<spring:message code="button.modified" text="수정" />" onclick="flow.render.form('MODIFY');"><i class="fa fa-pencil-square"></i> <spring:message code="button.modified" text="수정" /></button>
									<button type="button" class="btn btn-white btn-sm" title="<spring:message code="button.create.bottom" text="하위등록" />" onclick="flow.render.form('ADD');"><i class="fa fa-level-down"></i> <spring:message code="button.create.bottom" text="하위등록" /></button>
									<button type="button" class="btn btn-white btn-sm" title="<spring:message code="button.delete" text="삭제" />" onclick="flow.bind.action.remove();"><i class="fa fa-trash-o"></i> <spring:message code="button.delete" text="삭제" /></button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<form method="post" class="form-horizontal" name="default" id="frmDefault">
					<div class="panel panel-default m-t _flow-code-form" style="display:none;">
						<input type="hidden" name="mode" />
						<input type="hidden" name="id" value="" data-form="id"/>
						<input type="hidden" name="seq" value="" data-form="childCnt"/>

						<div class="panel-heading">
							<i class="fa fa-home"></i>&nbsp;<strong></strong>
						</div>
						<div class="panel-body">
							<table class="table table-detail"><colgroup><col width="150"/><col/></colgroup><tbody>
							<tr>
								<th><spring:message code="code.list.code" text="코드" /> *</th>
								<td>
									<input type="text" class="form-control" name="cd" value="" title="<spring:message code="code.list.code.title" text="코드는 필수 입력 항목입니다." />" maxlength="30" autocomplete="off" data-form="cd" required="required"/>
								</td>
							</tr>
							<tr>
								<th><spring:message code="code.list.name" text="표기명" /> *</th>
								<td>
									<input type="text" class="form-control" name="name" value="" title="<spring:message code="code.list.name.title" text="표기명은 필수 입력 항목입니다." />" maxlength="30" autocomplete="off" data-form="name" required="required"/>
								</td>
								<!--
								<td>
									<table class="table table-detail m-n">
										<colgroup><col width="100"/><col/></colgroup>
										<tbody>
										<tr>
											<th>한국어</th>
											<td>
												<i class="fa fa-desktop"></i>&nbsp;name
											</td>
										</tr>
										<tr>
											<th>영어</th>
											<td>
												<i class="fa fa-desktop"></i>&nbsp;name
											</td>
										</tr>
										</tbody>
									</table>
								</td>
								-->
							</tr>
							<tr>
								<th><spring:message code="code.list.shrtDesc" text="짧은 표기명" /></th>
								<td>
									<input type="text" class="form-control" name="shrtDesc" value="" title="<spring:message code="code.list.shrtDesc.title" text="짧은 표기명을 입력해주세요." />" maxlength="30" autocomplete="off" data-form="shrtDesc" />
								</td>
								<!--
								<td>
									<table class="table table-detail m-n">
										<colgroup><col width="100"/><col/></colgroup>
										<tbody>
										<tr>
											<th>한국어</th>
											<td>
												<i class="fa fa-desktop"></i>&nbsp;name
											</td>
										</tr>
										<tr>
											<th>영어</th>
											<td>
												<i class="fa fa-desktop"></i>&nbsp;name
											</td>
										</tr>
										</tbody>
									</table>
								</td>
								-->
							</tr>
							<tr>
								<th><spring:message code="code.list.longDesc" text="전체 표기명" /></th>
								<td>
									<input type="text" class="form-control" name="longDesc" value="" title="<spring:message code="code.list.longDesc.title" text="전체 표기명을 입력해주세요." />" maxlength="50" autocomplete="off" data-form="longDesc" />
								</td>
								<!--
								<td>
									<table class="table table-detail m-n">
										<colgroup><col width="100"/><col/></colgroup>
										<tbody>
										<tr>
											<th>한국어</th>
											<td>
												<i class="fa fa-desktop"></i>&nbsp;name
											</td>
										</tr>
										<tr>
											<th>영어</th>
											<td>
												<i class="fa fa-desktop"></i>&nbsp;name
											</td>
										</tr>
										</tbody>
									</table>
								</td>
								-->
							</tr>
							<tr>
								<th><spring:message code="code.list.customField1" text="사용자 정의 값1" /></th>
								<td>
									<input type="text" class="form-control" name="customField1" value="" title="<spring:message code="code.list.customField1.title" text="사용자 정의 값1을 입력해주세요." />" maxlength="30" autocomplete="off" data-form="customField1" />
								</td>
							</tr>
							<tr>
								<th><spring:message code="code.list.customField2" text="사용자 정의 값2" /></th>
								<td>
									<input type="text" class="form-control" name="customField2" value="" title="<spring:message code="code.list.customField2.title" text="사용자 정의 값2을 입력해주세요." />" maxlength="30" autocomplete="off" data-form="customField2" />
								</td>
							</tr>
							<tr>
								<th><spring:message code="code.list.customField3" text="사용자 정의 값3" /></th>
								<td>
									<input type="text" class="form-control" name="customField3" value="" title="<spring:message code="code.list.customField3.title" text="사용자 정의 값3을 입력해주세요." />" maxlength="30" autocomplete="off" data-form="customField3" />
								</td>
							</tr>
							<tr>
								<th><spring:message code="code.list.useYn" text="사용여부" /> *</th>
								<td>
									<select class="form-control" name="useYn" title="<spring:message code="code.list.useYn.title" text="사용 여부는 필수 선택 항목입니다." />" data-form="useYn" required="required">
										<option value="Y"><spring:message code="common.useY" text="사용" /></option>
										<option value="N"><spring:message code="common.useN" text="미사용" /></option>
									</select>
								</td>
							</tr>
							</table>
							<div class="form-row">
								<div class="form-group col-lg-12">
									<div class="btn-group pull-right">
										<button type="button" class="btn btn-white btn-sm" title="<spring:message code="button.save" text="저장" />" onclick="flow.bind.action.save();"><i class="fa fa-pencil-square"></i> <spring:message code="button.save" text="저장" /></button>
										<button type="button" class="btn btn-white btn-sm" title="<spring:message code="button.cancle" text="취소" />" onclick="flow.render.form('CANCEL');"><i class="fa fa-repeat"></i> <spring:message code="button.cancle" text="취소" /></button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>




<!-- DeepFine Library -->
<script src="/static/js/api/deepfine.core.js" type="text/javascript"></script>
<script src="/static/js/api/deepfine.common.js" type="text/javascript"></script>

<!-- Tree -->
<link href="/static/css/plugins/jsTree/style.min.css" rel="stylesheet">
<script src="/static/js/plugins/jsTree/jstree.min.js" type="text/javascript"></script>

<script type="text/javascript">

	$(document).ready(function () {
		flow.init(my);
	});

	let _isFirst = true;
	let _this;
	let my = {
		init: function () {
			_this = this;
			_this.bind.action.findAll();
		},
		selector: {
			tree: $("._flow-code-tree:first")
			, detail: $("._flow-code-detail:first")
			, form: $("._flow-code-form:first")
		},
		bind: {
			init: function () {
				/* Tree Node 선택 */
				$(_this.selector.tree).off('select_node.jstree').on('select_node.jstree', function(e, data) {
					$(_this.selector.tree).jstree(true).open_node(data.node.id);
					_this.bind.action.detail(data.node);
				});

				/* Tree Node 검색 */
				$('._flow-input-search:first').off('keyup').on('keyup', function () {
					$(_this.selector.tree).jstree('search', $(this).val());
				});

				$(document).on('dnd_stop.vakata', function (e, data, x, y) {
					let $tree = $(flow.selector.tree).jstree(true);
					let nodeId = data.data.nodes[0];
					let parentId = $tree.get_parent(nodeId);


					if (parentId === "#" && _isFirst) {
						_isFirst = false;

						alert('<spring:message code="code.list.nouptop" text="최상위 메뉴는 이동할 수 없습니다." javaScriptEscape="true" />');
						$(_this.selector.tree).jstree(true).refresh();
						return false;
					}

					if (_isFirst) {
						_isFirst = false;
						if (confirm('<spring:message code="code.list.move.confirm" text="변경 내용을 저장하시겠습니까? \n변경 내용에 따라 다소 시간이 소요될 수 있습니다." javaScriptEscape="true" />')) {
							let newData = $(_this.selector.tree).data().jstree.get_json(parentId, {flat: true});

							newData = newData.filter(function (data) {
								return data.id !== parentId;
							});

							let makeParam = function () {
								newData = $.map(newData, function (menu, idx) {
									return {
										id: menu.id,
										parentId: menu.parent,
										seq: idx + 1,
										depth: $tree.get_path(menu.id).length
									}
								});
								return newData.filter(function (menu, idx) {
									return menu.parentId !== '#';
								});
							}

							_this.bind.action.saveSeq(makeParam());
						} else {
							$(_this.selector.tree).jstree(true).refresh();
							return false;
						}
					}
				});
			},
			action: {
				findAll: function () {
					$.core.ajax.post({
						url: 'findAll.do'
					}).done(function (result) {
						if (result && result.length) {
							_this.render.tree.draw(result);
						}
					});
				},
				detail: function (node) {
					$.core.ajax.post({
						url: 'detail.do'
						, data: JSON.stringify({'id': node.id})
					}).done(function (result) {
						if (result) {
							_this.render.detail($.extend(true, node, result));
						}
					});
				},
				save: function () {
					if (_this.bind.action.validator() && confirm('<spring:message code="code.list.save.confirm" text="저장하시겠습니까?" javaScriptEscape="true" />')) {
						$.core.ajax.post({
							url: $('input:hidden[name=mode]').val().toLowerCase() + '.do'
							, data: JSON.stringify(flow.prototype.getSaveData())
						}).done(function (result) {
							if (result) {
								alert('<spring:message code="code.list.save.alert" text="저장되었습니다." javaScriptEscape="true" />');
								_this.bind.action.findAll();
							}
						});
					}
				},
				validator: function () {
					var isValid = true;
					$("[required='required']").each(function (i, item) {
						if ($.trim($(item).val()) === '') {
							isValid = false;
							alert($(item).prop('title'));
							$(item).focus();
							return isValid;
						}
					});

					$("input:text[name=cd]").each(function (i, item) {
						if($(item).val().indexOf(' ') > -1) {
							isValid = false;
							alert('<spring:message code="code.list.cd.alert_1" text="코드에는 공백을 포함할 수 없습니다." javaScriptEscape="true" />');
							$(item).focus();
							return isValid;
						}
					});

					$("input:text[name=cd], input:text[name=name]").each(function (i, item) {
						if($(item).val().indexOf('\.') > -1 || $(item).val().indexOf(',') > -1) {
							isValid = false;
							alert('<spring:message code="code.list.cd.alert_2" text="코드와 표기명에는 마침표(.)와 쉼표(,)를 사용할 수 없습니다." javaScriptEscape="true" />');
							$(item).focus();
							return isValid;
						}
					});
					return isValid;
				},
				saveSeq: function (data) {
					$.core.ajax.post({
						url: 'saveSeq.do'
						, data: JSON.stringify(data)
					}).done(function (result) {
						if (result) {
							alert('<spring:message code="code.list.save.alert" text="저장되었습니다." javaScriptEscape="true" />');
							_this.bind.action.findAll();
						}
					});
				},
				remove: function () {
					if (confirm('<spring:message code="code.list.delete.confirm" text="삭제하시겠습니까?" javaScriptEscape="true" />')) {
						$('input:hidden[name=mode]').val('REMOVE');

						$.core.ajax.post({
							url: 'remove.do'
							, data: JSON.stringify({'id': $('input:hidden[name=id]').val()})
						}).done(function (result) {
							if (result) {
								alert('<spring:message code="code.list.delete.alert" text="삭제되었습니다." javaScriptEscape="true" />');
								_this.bind.action.findAll();
							}
						});
					}
				}
			}
		},
		render: {
			tree: {
				draw: function (codeList) {
					let data = [];
					$(codeList).each(function (idx, item) {
						data.push({
							'id': item.id
							, 'parent': (Number(item.depth) === 2 ? '#' : item.parentId)
							, 'text': item.name + '<small>&nbsp;(' + item.cd + ')</small>'
							, 'li_attr': {
							}
							, 'data': {
								'pathName': item.pathName
							}
						});
					});

					if ($(_this.selector.tree).html() !== '') {
						/* Tree Refresh */
						$(_this.selector.tree).jstree(true).settings.core.data = data;
						$(_this.selector.tree).jstree(true).refresh();
						_isFirst = true;

						if ($('input:hidden[name=mode]').val() === 'REMOVE') {
							$(_this.selector.detail).hide();
							$(_this.selector.form).hide();
						}
					} else {
						/* Tree Initialize */
						$(_this.selector.tree).jstree({
							core: {
								data: data
								, check_callback: function (operation, node, node_parent, node_position, more) {
									if (operation === "move_node") {
										_isFirst = true;
										return true;
									} else {
										return false;
									}
								}
							},
							plugins: ['search', 'dnd', 'types'],
							types: {
								default: {
									icon: 'fa fa-folder'
								}
							}
						});
					}

					// rebind event
					_this.bind.init();
				},
				openAll: function () {
					$(_this.selector.tree).jstree(true).open_all();
				},
				closeAll: function () {
					$(_this.selector.tree).jstree(true).close_all();
				}
			},
			detail: function (code) {
				$('div.panel-heading').each(function (idx, item) {
					$(item).find('strong').html(code.data.pathName.replace(/\./g, ' &gt; '));
				});

				$('td[data-detail]').each(function (idx, item) {
					var fieldName = $.trim($(item).data('detail'));
					if (code.hasOwnProperty(fieldName)) {
						$(item).text(code[fieldName]);
					} else {
						$(item).text('');
					}
				});

				$('input[data-form], select[data-form]').each(function (idx, item) {
					var fieldName = $.trim($(item).data('form'));
					if (code.hasOwnProperty(fieldName)) {
						$(item).val(code[fieldName]);
					} else {
						$(item).val('');
					}
				});

				$('input:hidden[name=mode]').val('');
				$(_this.selector.detail).show();
				$(_this.selector.form).hide();
			},
			form: function (mode) {
				$('input:hidden[name=mode]').val(mode);

				if (mode === 'ADD-TOP') {
					$(_this.selector.tree).jstree('deselect_all', true);

					$('div.panel-heading').each(function (idx, item) {
						$(item).find('strong').html('ROOT');
					});

					$('input[data-form]').val('');
					$('select[data-form]').val('Y');
					$('input:hidden[name=seq]').val($('li[aria-level=1]').length);

					$(_this.selector.detail).hide();
					$(_this.selector.form).show();

				} else if (mode === 'CANCEL') {
					$(_this.selector.detail).show();
					$(_this.selector.form).hide();
				} else {
					if (mode === 'MODIFY') {
						$('input:text[name=cd]').prop('readonly', 'readonly');
					} else if (mode === 'ADD') {
						$('input:text[name=cd]').prop('readonly', '');
					}

					$(_this.selector.detail).hide();
					$(_this.selector.form).show();
				}
			}
		}
	};
</script>