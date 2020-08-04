/**
 * Deep.Fine Common Script
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.22
 * @version 1.0.0
 */

var _CONST = {
	MY_HOST: (!window.location.hostname ? 'localhost' : window.location.hostname)
	, USER_AGENT: navigator.userAgent
};

var _var = {
};

var _commonUi = {
	init: function () {

		/* init variable */
		$.extend(_var, {
		});

		/* init ajax config */
		$.ajaxSetup(this.config.ajax);

		this.bind.init();
	},
	config: {
		ajax: {
			type: "POST"
			, dataType: "json"
			, global: false
			, timeout: 3000
			, async: true
		}
	},
	bind: {
		init: function () {

			// Moment Locale initialize
			if (moment) {
				moment.locale('ko');
			}

			// sort
			$("._sortable").each(function(i, item) {
				if ($(item).data("uiSortable")) {
					return true;
				}
				$(item).sortable({
					stop: function(e, ui) {
						$.core.log($(ui.item).index());
					}
				});
			});

			// drag
			$("._draggable").each(function(i, item) {
				$(item).draggable({
					stop: function(e, ui) {
						$.core.log($(ui.item).index());
					}
				});
			});
		}
	},
	action: {

	}
};

$(document).ready(function() {
	_commonUi.init();

	var flow = {
		init: function(o) {
			console.log('Flow Initializing..');

			var _this = this;
			o = o || {};

			$.extend(true, _this, o);

			_this.$wrapList = $(_this.options.selector.wrap);
			_this.$searchForm = $(_this.options.selector.searchForm);
			_this.$dataForm = $(_this.options.selector.dataForm);

			if (o && o.hasOwnProperty("init")) {
				o.init();
			}

			flow.bind.init();
		},
		bind: {
			init: function() {
				flow.$wrapList.find("._flow-action-sort:first").off("change").on("change", function () {
					$("#sort").val($(this).find("option:selected").val());
					$("#pageIndex").val(1);
					flow.bind.action.list();
				});

				flow.$wrapList.find("._flow-action-pageUnit:first").off("change").on("change", function () {
					$("#pageUnit").val($(this).find("option:selected").val());
					$("#pageIndex").val(1);
					flow.bind.action.list();
				});

				flow.$wrapList.find("._flow-action-search").each(function(i, item) {
					$(item).off("click").on("click", function () {
						$("#pageIndex").val(1);
						flow.bind.action.list();
					});
				});

				flow.$wrapList.find("._flow-action-list").each(function(i, item) {
					$(item).off("click").on("click", flow.bind.action.list);
				});

				flow.$wrapList.find("._flow-action-detail").each(function(i, item) {
					$(item).off("click").on("click", flow.bind.action.detail);
				});

				flow.$wrapList.find("._flow-action-form").each(function(i, item) {
					$(item).off("click").on("click", flow.bind.action.form);
				});

				flow.$wrapList.find("._flow-action-save").each(function(i, item) {
					$(item).off("click").on("click", function() {
						flow.bind.action.save(item, $(this).data("confirm") || "", $(this).data("validation") || "");
					});
				});

				flow.$wrapList.find("._flow-action-reset").each(function(i, item) {
					$(item).off("click").on("click", function() {
						$("#pageIndex").val(1);
						flow.$searchForm
							.find(":input").not(":button, :submit, :reset, :hidden")
							.val('')
							.removeAttr('checked')
							.removeAttr('selected');

						flow.$searchForm.submit();
					});
				});

			},
			action: {
				list: function() {
					$.core.loading.start();
					try {
						var uri = $(this).data("uri") || "list.do";

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
				page: function (idx) {
					$("#pageIndex").val(idx);
					flow.bind.action.list();
				},
				detail: function() {
					try {
						var id = $(this).data("id") || "";
						var uri = $(this).data("uri") || "detail.do";

						if (id) {
							$.core.loading.start();

							if (flow.$searchForm.find('input[name="id"]').length === 0) {
								flow.$searchForm.prepend($('<input type="hidden" name="id" value="' + id + '"/>'));
							}
							flow.$searchForm
								.find('input[name="id"]').val(id).end()
								.attr('action', uri)
								.submit();
						}
					} catch (e) {
						$.core.loading.stop();
					} finally {
						$.core.loading.stop();
					}
				},
				form: function() {
					try {
						var id = $(this).data("id") || "";
						var uri = $(this).data("uri") || "form.do";

						$.core.loading.start();

						if (flow.$searchForm.find('input[name="id"]').length === 0) {
							flow.$searchForm.prepend($('<input type="hidden" name="id" value="' + id + '"/>'));
						}
						flow.$searchForm
							.find('input[name="id"]').val(id).end()
							.attr('action', uri)
							.submit();
					} catch (e) {
						$.core.loading.stop();
					} finally {
						$.core.loading.stop();
					}
				},
				save: function(item, confirmMsg, isValidation) {
					$.core.loading.start();

					try {
						var uri = $(item).data("uri") || "save.do";
						var dataType = $(item).data("result-type") || "json";

						var preProcessedResult = flow.prototype.getSaveData();

						// 유효성 검증 할 때 param으로 검증하여 validator 함수에서 반환하는 값에 따라 처리 중단 가능
						if (isValidation === "Y" &&
							flow.bind.action.validator &&
							$.isFunction(flow.bind.action.validator)) {

							if (!flow.bind.action.validator(preProcessedResult)) {
								$.core.loading.stop();
								return false;
							}
						}

						// console.log("flow.bind.action.save.start");
						// console.log(JSON.stringify(preProcessedResult));
						// console.log("flow.bind.action.save.end");
						if (!confirmMsg || confirm(confirmMsg)) {

							$.core.ajax.post({
								dataType: dataType,
								contentType: 'application/json',
								url: uri,
								data: JSON.stringify(preProcessedResult),
								success: function (result) {
									var postProcessedResult = result;

									if (typeof flow.bind.action.success === "function" ||
										typeof flow.bind.action.success === "object") {
										if (flow.bind.action.postSaveDataHandler &&
											$.isFunction(flow.bind.action.postSaveDataHandler)) {
											postProcessedResult = flow.bind.action.postSaveDataHandler(result);
										}
										// 데이터 후처리
										flow.bind.action.success(postProcessedResult);
									} else {
										flow.bind.action.list();
									}
								},
								error: function (xhr, ajaxOptions, thrownError) {
									if (flow.bind.action.saveErrorHandler && $.isFunction(flow.bind.action.saveErrorHandler)) {
										flow.bind.action.saveErrorHandler(xhr);
									} else {
										if (xhr && xhr.message) {
											$.core.alert(e.message);
										} else if (xhr.responseJSON && xhr.responseJSON.message) {
											$.core.alert(xhr.responseJSON.message);
										} else {
											$.core.alert("[" + xhr.status + "] 처리 중 오류가 발생하였습니다. 다시 시도하거나 관리자에게 문의바랍니다.");
										}
									}

									// todo: ajax exception handling 추가 필요 (jm.lee)
								}
							});
						}

					} catch (e) {
						$.core.alert(e);
						$.core.loading.stop();
					} finally {
						$.core.loading.stop();
					}
				},
				validator: function () {
					return true;
				},
				preSaveDataHandler: function(param) {
					return param;
				},
				postSaveDataHandler: function(param) {
					return param;
				},
			}
		},
		render: {},
		options: {
			selector: {
				wrap: "._flow-wrap",
				searchForm: "form#frmSearch",
				dataForm: "form#frmDefault"
			}
		}, process: function (param) {
			// 데이터 전처리
			var preProcessedResult = param;

			if (flow.bind.action.preSaveDataHandler &&
				$.isFunction(flow.bind.action.preSaveDataHandler)) {

				preProcessedResult = flow.bind.action.preSaveDataHandler(param);
			}
			return preProcessedResult;
		}, prototype: {
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

				var basicInfo = $("#frmDefault").serializeObject();
				var param = $.extend(basicInfo, {
					ml: arrData
				});

				return flow.process(param);

			}
		}
	}

	window.flow = flow;
});