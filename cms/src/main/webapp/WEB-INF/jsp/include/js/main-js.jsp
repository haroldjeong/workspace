<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>

<!-- Mainly scripts -->
<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/popper.min.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="/static/js/inspinia.js"></script>
<script src="/static/js/plugins/pace/pace.min.js"></script>

<!-- iCheck -->
<script src="/static/js/plugins/iCheck/icheck.min.js"></script>

<script>
	$(document).ready(function(){
		$('.i-checks').iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green',
		});

		$("._flow-action-logout").each(function(i, item) {
			$(item).off("click").on("click", function () {
				if (confirm("로그아웃하시겠습니까?")) {
					$("form[name='frmHidden']").prop("action", "<c:url value="/logout.do"/>").submit();
				}
			});
		});
	});
</script>
<form name="frmHidden" method="post" action=""></form>
