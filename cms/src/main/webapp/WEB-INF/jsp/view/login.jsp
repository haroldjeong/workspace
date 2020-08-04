<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Safety Management System Login</title>
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/font-awesome/css/font-awesome.css" rel="stylesheet">
	<link href="/static/css/animate.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
</head>

<body class="gray-bg">

<div class="loginColumns animated fadeInDown">
	<div class="row">

		<div class="col-md-6">
			<h3 class="font-bold" style="margin-bottom: 0px;">Gyeonggi Province Government</h3>
			<h2 class="font-bold" style="margin-top: 0px; margin-bottom: 45px;">Safety Management System</h2>
			<p>본 시스템은 경기도청 공무원 및 그 외 인가된 사용자에 한하여 사용하실 수 있으며, 불법적인 접근 및 사용 시 관련 법규에 의해 처벌될 수 있습니다.</p>
			<p>모든 사용자는 지정된 PC에서만 로그인이 가능합니다</p>
			<p>비밀번호와 IP주소 오류가 5회 누적되면 사용이 정지되오니 주의해주시기 바랍니다.</p>
			<p>
				<small>This system can be used only by Gyeonggi Provincial Government officials and other authorized users, and may be punished by relevant laws and regulations when illegal access and use.</small>
			</p>

		</div>
		<div class="col-md-6">
			<div class="ibox-content">
				<form class="m-t" role="form" action="index.html">
					<div class="form-group">
						<input type="text" class="form-control" name="loginId" id="loginId" placeholder="로그인 아이디" required="">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="loginPw" id="loginPw" placeholder="비밀번호" required="">
					</div>
					<br/><br/><br/><br/><br/>
					<button type="submit" class="btn btn-primary block full-width m-b">Login</button>

					<%--<a href="#">--%>
						<%--<small>Forgot password?</small>--%>
					<%--</a>--%>

					<%--<p class="text-muted text-center">--%>
						<%--<small>Do not have an account?</small>--%>
					<%--</p>--%>
					<%--<a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>--%>
				</form>
				<p class="m-t">
					<small>로그인 관련 문의는 정보기획담당관에게 문의 바랍니다.</small>
				</p>
			</div>
		</div>
	</div>
	<hr/>
	<div class="row">
		<div class="col-md-6">
			Copyright Gyeonggi Province Government
		</div>
		<div class="col-md-6 text-right">
			<small>© 2020 ∼</small>
		</div>
	</div>
</div>

</body>

</html>
