<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<link href="/static/css/style.css" rel="stylesheet">
</head>

<body class="gray-bg">

<div class="loginColumns animated fadeInDown">
	<div class="row">
		<div class="col-md-6">
			<h3 class="font-bold" style="margin-bottom: 0px;">샘플 로그인 페이지</h3>
			<h2 class="font-bold" style="margin-top: 0px; margin-bottom: 45px;">Log In</h2>
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

					<a href="#">
						<small>Forgot password?</small>
					</a>

					<p class="text-muted text-center">
						<small>Do not have an account?</small>
					</p>
					<a class="btn btn-sm btn-white btn-block" href="">Create an account</a>
				</form>
			</div>
		</div>
	</div>
	<hr/>
</div>

</body>

</html>
