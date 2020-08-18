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

<form method="post" class="form-horizontal" name="default" id="frmDefault">
<div class="row">
	<div class="col-lg-12">
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

	</div>
</div>


<div class="row">
	<div class="col-lg-4">
		<div class="ibox float-e-margins">
			<div class="ibox-content mailbox-content">
				<div class="btn-group">
					<button class="btn btn-white btn-sm m-n" type="button" onclick="flow.render.tree.openAll();"><spring:message code="menu.list.allopen" text="모두 열기"/></button>
					<button class="btn btn-white btn-sm m-n" type="button" onclick="flow.render.tree.closeAll();"><spring:message code="menu.list.allclose" text="모두 접기"/></button>
				</div>
				<input class="form-control _flow-input-search" placeholder="search code" />
				<div class="_flow-code-tree"></div>
			</div>
		</div>
	</div>

	<div class="col-lg-8 animated fadeInRight">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default m-t _flow-code-form">
					<input type="hidden" name="mode" />
					<div class="panel-body">
						<table class="table table-detail"><colgroup><col width="150"/><col/></colgroup><tbody>
						<tr>
							<th>권한</th>
							<td>
								읽기권한
								<input type="checkbox" class="i-checks" value="Y" name="readYn">
								쓰기권한
								<input type="checkbox" class="i-checks" value="Y" name="writeYn">
								다운로드권한
								<input type="checkbox" class="i-checks" value="Y" name="downYn">
								삭제권한
								<input type="checkbox" class="i-checks" value="Y" name="deleteYn">
							</td>
						</tr>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
<div class="row">
	<div class="col-lg-12">
		<div class="btn-group pull-left">
			<c:if test="${!empty condition.id}">
				<button type="button" class="btn btn-white _flow-action-save" title="<spring:message code="button.delete" text="삭제" />" onclick="flow.bind.action.remove();"><i class="fa fa-trash"></i> <spring:message code="button.delete" text="삭제" /></button>
			</c:if>
		</div>
		<div class="btn-group pull-right">
			<button type="button" class="btn btn-white _flow-action-save" title="<spring:message code="button.save" text="저장" />" onclick="flow.bind.action.save();"><i class="fa fa-save"></i> <spring:message code="button.save" text="저장" /></button>
			<button type="button" class="btn btn-white _flow-action-list" title="<spring:message code="button.list" text="목록으로" />" onclick="flow.bind.action.list();"><i class="fa fa-undo"></i> <spring:message code="button.list" text="목록으로" /></button>
		</div>
	</div>
</div>

<!-- DeepFine Library -->
<script src="/static/js/api/deepfine.core.js" type="text/javascript"></script>
<script src="/static/js/api/deepfine.common.js" type="text/javascript"></script>

<!-- Tree -->
<link href="/static/css/plugins/jsTree/style.min.css" rel="stylesheet">
<script src="/static/js/plugins/jsTree/jstree.min.js" type="text/javascript"></script>

<script type="text/javaScript">
    $(document).ready(function () {
        flow.init(my);
    });

    let _isFirst = true;
    let _this;
    let my = {
        init: function () {
            _this = this;
            _this.bind.action.findMenuAll();

        },
        selector: {
            tree: $("._flow-code-tree:first")
        },
        bind: {
            init: function () {
                /* Tree Node 선택 */
                $(_this.selector.tree).off('select_node.jstree').on('select_node.jstree', function(e, data) {
                    $(_this.selector.tree).jstree(true).open_node(data.node.id);
                });

                /* Tree Node 검색 */
                $('._flow-input-search:first').off('keyup').on('keyup', function () {
                    $(_this.selector.tree).jstree('search', $(this).val());
                });



            },
            action: {
                findMenuAll: function () {
                    $.core.ajax.post({
                        url: 'findMenuAll.do'
                        , data: JSON.stringify({'roleId': $("#id").val()})
                    }).done(function (result) {
                        if (result && result.length) {
                            _this.render.tree.draw(result);
                        }
                    });
                },
                list: function() {
                    $.core.loading.start();
                    try {
                        var uri = "list.do";
                        flow.$searchForm
                            .find('input[name="id"]').remove().end()
                            .attr('action', uri)
                            .submit();
                    } catch (e) {
                        $.core.loading.stop();
                    } finally {
                        $.core.loading.stop();
                    }
                },
                save: function() {
                    if (_this.bind.action.validator() && confirm('저장하시겠습니까?')) {
                        $.core.ajax.post({
                            url: 'save.do'
                            , data: JSON.stringify(flow.bind.action.getSaveData())
                        }).done(function (result) {
                            if (result) {
                                alert('저장되었습니다.');
                            }
                        });
                    }
                },
                remove: function () {
                    if (confirm('삭제하시겠습니까?')) {
                        $('input:hidden[name=mode]').val('REMOVE');

                        $.core.ajax.post({
                            url: 'remove.do'
                            , data: JSON.stringify({'id': $('input:hidden[name=id]').val()})
                        }).done(function (result) {
                            if (result) {
                                alert('삭제되었습니다.');
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
                    return isValid;
                },
                getSaveData: function() {
                    var arrData = [];

                    arrData.push({
                        channelCd: 'pc',
                        nationCd: 'kr',
                        langCd: 'ko',
                        map: {
                            //todo: 멀티 채널/국가/언어 별 컨텐츠 입력 필요 시, 여기에 구현 예정 (jm.lee)
                        }
                    });
                    var arrSelected = $(_this.selector.tree).jstree(true).get_selected('full',true);

                    var menuData = [];

                    $.each(arrSelected, function(i){
                        menuData.push(arrSelected[i].id);
                    });

                    var basicInfo = $("#frmDefault").serializeObject();
                    var param = $.extend(basicInfo, {
                        ml: arrData,
						menus: menuData
                    });
                    return flow.process(param);

                }
            }
        },
        render: {
            tree: {
                draw: function (menuList) {
                    let data = [];
                    $(menuList).each(function (idx, item) {
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
                            plugins: ['search', 'types', 'checkbox'],
                            types: {
                                default: {
                                    icon: 'fa fa-folder'
                                }
                            }
                        }).bind("loaded.jstree", function (event, data) {
                            $(this).jstree("open_all");
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
            }
        }
    };
</script>

