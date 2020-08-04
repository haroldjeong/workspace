/**
 * Deep.Fine Core Script
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.22
 * @version 1.0.0
 */
;(function (global, $) {
	if (!$.core) {
		$.core = {};
	}
	var core = {
		config: {},
		init: function () {
			// 로그 관련 처리
			this.log = function(text) {
				try {
					console.log.apply(this, arguments);
				} catch(e){}
			};
			core.ready();
		},
		ready: function () {
			// CSRF 공통 처리 설정
			var token = $("meta[name='_csrf']").attr("content");
			$.ajaxSetup({
				headers: {
					"X-CSRF-TOKEN": token
				}
			});
		},
		alert: function (message, callback, iconType) {
			alert(message);
			// todo: 기본 설정 완료 후 custom
/*			swal({
					title: message,
					//text: message2,
					type: (typeof iconType == "string" && "success|error|warning|info".indexOf(iconType) > -1 ? iconType : ""),		// success, error, warning, info
					showCancelButton: false,
					confirmButtonColor: '#DD6B55',
					//confirmButtonText: '확인',
					closeOnConfirm: true
				},
				function(){
					if (typeof callback == "function" || typeof callback == "object") {
						callback();
					}
				});*/
		},
		confirm: function (message, successCallback, failCallback) {
			successCallback = $.isFunction (successCallback) ? successCallback : $.noop;
			failCallback = $.isFunction (failCallback) ? failCallback : $.noop;
			// todo: 기본 설정 완료 후 custom
			return confirm(message) ? successCallback() : failCallback();
		},
		ajax: {
			get: function(o, isLoading) {
				isLoading = (isLoading == undefined || isLoading == null) ? true : isLoading;
				var options = {
					method: "GET"
					, contentType: "application/json; charset=utf-8"
					, dataType: "JSON"
				};
				if (typeof o == "string") {
					options.url = o;
				} else {
					$.extend(options, o);
				}

				// error 상태값 처리가 없으면 기본 오류 처리 실행
				if (!options.hasOwnProperty("error")) {
					options.error = function (e) {
						var message ='처리 중 오류가 발생하였습니다.\n다시 시도하거나 관리자에게 문의 바랍니다.';
						if (e.responseJSON && e.responseJSON.message) {
							message = e.responseJSON.message;
						} else if (e.responseText) {
							message = JSON.parse(e.responseText).message;
						}
						$.core.alert(message);
					}
				} else {
					var originErrorFunction = options.error;
					options.error = function (e) {
						originErrorFunction(e);
					}
				}

				if (isLoading) {
					$.core.loading.start();
				}

				return $.ajax(options)
					.always(function() {
						if (isLoading) {
							$.core.loading.stop();
						}
					});
			},
			post: function(o, isLoading) {
				isLoading = (isLoading == undefined || isLoading == null) ? true : isLoading;
				var options = {
					method: "POST"
					//, contentType: "application/json; charset=utf-8"
					, dataType: "JSON"
				};
				if (typeof o == "string") {
					options.url = o;
				} else {
					$.extend(options, o);
				}

				// error 상태값 처리가 없으면 기본 오류 처리 실행
				if (!options.hasOwnProperty("error")) {
					options.error = function (e) {
						try {console.log(e);} catch(e) {}

						var message ='처리 중 오류가 발생하였습니다.\n다시 시도하거나 관리자에게 문의 바랍니다.';
						if (e.responseJSON && e.responseJSON.message) {
							message = e.responseJSON.message;
						} else if (e.responseText) {
							message = JSON.parse(e.responseText).message;
						}
						$.core.alert(message);
					}
				} else {
					var originErrorFunction = options.error;
					options.error = function (e) {
						//alert(e);
						originErrorFunction(e);
					}
				}


				if (isLoading) {
					$.core.loading.start();
				}
				return $.ajax(options)
					.always(function() {
						if (isLoading) {
							$.core.loading.stop();
						}
					});
			}
		},
		loading: {
			start: function () {
				$.blockUI({
					css: {
						border: 'none'
						, padding: '15px'
						, backgroundColor: '#000', '-webkit-border-radius': '10px', '-moz-border-radius': '10px'
						, opacity: .5
						, color: '#fff'
						, 'z-index': '9999'
					}
				});
			},
			stop: function () {
				$.unblockUI();
			}
		},
		randomUUID: function() {
			function s4() {
				return Math.floor((1 + Math.random()) * 0x10000)
					.toString(16)
					.substring(1);
			}
			return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
		}
	};

	$.extend($.core, core, true);
	$.core.init();
}(window, jQuery));


/* Deep.Fine Common Object Serializer */
;(function (global, $) {
	if ($.object) {
		return false;
	}
	(function () {
		if (!$.serializeObject) {
			$.fn.serializeObject = function() {
				var obj = null;
				try {
					if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
						var arr = this.serializeArray();
						if (arr) {
							obj = {};
							thisObj = null;
							jQuery.each(arr, function() {

								// 기존 동일한 이름으로 객체가 있는 경우 배열 처리
								if (obj.hasOwnProperty(this.name)) {
									// 배열로 처리된 경우 단순 push
									if (typeof obj[this.name] == "object" && obj[this.name].hasOwnProperty("length")) {
										obj[this.name].push(this.value);
									} else {
										var temp = obj[this.name]; // 기존값 저장
										obj[this.name] = [];
										obj[this.name].push(temp);
										obj[this.name].push(this.value);
									}
								} else {
									obj[this.name] = this.value;
								}

								/*
                                if (this.name == "changeFile") {
                                    if (!obj.hasOwnProperty("changeFile")) {
                                        obj.changeFile = [];
                                    }
                                    obj.changeFile.push(this.value);
                                } else {
                                    obj[this.name] = this.value;
                                }
                                */

							});
						}
					}
				} catch (e) {
					alert(e.message);
				} finally {}

				return obj;
			};
		}
	}());

	$.object = $.extend({}, {

	}, true);
}(window, jQuery));
