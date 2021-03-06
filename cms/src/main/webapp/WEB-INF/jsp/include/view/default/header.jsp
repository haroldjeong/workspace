<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/jsp/include/taglib.jsp"/>

<nav class="navbar navbar-static-top">
	<div class="navbar-header">
		<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
		<form role="search" class="navbar-form-custom" action="search_results.html">
			<div class="form-group">
				<input type="text" placeholder="Search for menu..." class="form-control" name="top-search" id="top-search">
			</div>
		</form>
	</div>
	<ul class="nav navbar-top-links navbar-right">
		<li>
			<span class="m-r-sm text-muted welcome-message">마지막 접속일시 : 2020년 07월 27일 16시 25분</span>
		</li>
		<li class="dropdown">
			<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
				<i class="fa fa-bell"></i>  <span class="label label-primary">8</span>
			</a>
			<ul class="dropdown-menu dropdown-alerts">
				<li>
					<a href="mailbox.html" class="dropdown-item">
						<div>
							<i class="fa fa-envelope fa-fw"></i> You have 16 messages
							<span class="float-right text-muted small">4 minutes ago</span>
						</div>
					</a>
				</li>
				<li class="dropdown-divider"></li>
				<li>
					<a href="profile.html" class="dropdown-item">
						<div>
							<i class="fa fa-twitter fa-fw"></i> 3 New Followers
							<span class="float-right text-muted small">12 minutes ago</span>
						</div>
					</a>
				</li>
				<li class="dropdown-divider"></li>
				<li>
					<a href="grid_options.html" class="dropdown-item">
						<div>
							<i class="fa fa-upload fa-fw"></i> Server Rebooted
							<span class="float-right text-muted small">4 minutes ago</span>
						</div>
					</a>
				</li>
				<li class="dropdown-divider"></li>
				<li>
					<div class="text-center link-block">
						<a href="notifications.html" class="dropdown-item">
							<strong>See All Alerts</strong>
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
				</li>
			</ul>
		</li>
		<li>
			<a href="#" class="_flow-action-logout">
				<i class="fa fa-sign-out"></i> Log out
			</a>
		</li>
	</ul>
</nav>
