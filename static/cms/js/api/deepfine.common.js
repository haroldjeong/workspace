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

			// iCheck Init
			$('.i-checks').iCheck({
				checkboxClass: 'icheckbox_square-green',
				radioClass: 'iradio_square-green',
			});

			_calendar.init();
		}
	},
	action: {

	}
};

var _calendar = {
	init: function () {
		// Date Picker
		$('._flow-init-datepicker').each(function (i, item) {
			if ($(item).find('span.input-group-addon').length && $(item).find('input:text').length) {
				var opts = {
					todayBtn: "linked",
					format: "yyyy-mm-dd",
					keyboardNavigation: false,
					forceParse: false,
					calendarWeeks: false,
					autoclose: true
				};

				// 미래 날짜만 선택 가능 (오늘 포함)
				if ($(item).hasClass('future-today')) {
					opts = $.extend(true, opts, {
						startDate: moment().format('YYYY-MM-DD')
					})
				}

				// 미래 날짜만 선택 가능 (오늘 제외)
				if ($(item).hasClass('future')) {
					opts = $.extend(true, opts, {
						startDate: moment().add(1, 'day').format('YYYY-MM-DD')
					})
				}

				// 과거 날짜만 선택 가능 (오늘 포함)
				if ($(item).hasClass('past-today')) {
					opts = $.extend(true, opts, {
						endDate: moment().format('YYYY-MM-DD')
					})
				}

				// 과거 날짜만 선택 가능 (오늘 제외)
				if ($(item).hasClass('past')) {
					opts = $.extend(true, opts, {
						endDate: moment().add(-1, 'day').format('YYYY-MM-DD')
					})
				}

				// 그 외 커스텀 옵션은 각 페이지에서 처리
				$(item).datepicker(opts);
			}
		});

		// 날짜 범위 선택버튼
		$(".input-daterange").find("button[class*='_flow-range-']").off("click").on("click", function () {
			var $btn = $(this);
			var $startCal = $(this).closest(".input-daterange").find("input:text:first");
			var $endCal = $(this).closest(".input-daterange").find("input:text:last");

			var _startDt = ""
				, _endDt = ""
				, _today = moment().format("L");

			if ($btn.hasClass("ago")) {
				_endDt = $endCal.val() || _today;
				if ($btn.closest(".input-daterange").hasClass("past")){
					_endDt = $endCal.val() || moment().add(-1, 'days').format("L");
				}
			} else {
				_startDt = $startCal.val() || _today;
				if ($btn.closest(".input-daterange").hasClass("future")){
					_startDt = $startCal.val() || moment().add(1, 'days').format("L");
				}
			}

			if ($btn.hasClass("_flow-range-today")) {
				$($startCal).datepicker("setDate", _today);
				$($endCal).datepicker("setDate", _today);
			} else if ($btn.hasClass("_flow-range-7day")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-6, 'days').format("L") : moment(_endDt).add(-6, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(7, 'days').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-15day")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-14, 'days').format("L") : moment(_endDt).add(-14, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(15, 'days').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-1month")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-1, 'months').add(-1, 'days').format("L") : moment(_endDt).add(-1, 'months').add(1, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(1, 'months').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-3month")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-3, 'months').add(-1, 'days').format("L") : moment(_endDt).add(-3, 'months').add(1, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(3, 'months').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-6month")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-6, 'months').add(-1, 'days').format("L") : moment(_endDt).add(-6, 'months').add(1, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(6, 'months').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-1year")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-1, 'years').add(-1, 'days').format("L") : moment(_endDt).add(-1, 'years').add(1, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(1, 'years').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-3year")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-3, 'years').add(-1, 'days').format("L") : moment(_endDt).add(-3, 'years').add(1, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(3, 'years').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-5year")) {
				if ($btn.hasClass("ago")) {
					$($startCal).datepicker("setDate", (_endDt === "" ? moment().add(-5, 'years').add(-1, 'days').format("L") : moment(_endDt).add(-5, 'years').add(1, 'days').format("L")));
					$($endCal).datepicker("setDate", _endDt);
				} else {
					$($startCal).datepicker("setDate", _startDt);
					$($endCal).datepicker("setDate", moment($($startCal).val()).add(5, 'years').add(-1, 'days').format("L"));
				}
			} else if ($btn.hasClass("_flow-range-clear")) {
				$($startCal).datepicker("setDate", "");
				$($endCal).datepicker("setDate", "");
			}
		});
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