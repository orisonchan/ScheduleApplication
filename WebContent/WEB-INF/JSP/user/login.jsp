<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<title>Schedule Application</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/mycss.css">
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
</head>
<body>
	<section style="background: url(./img/bg.jpg) 0 0/cover no-repeat;">
	<nav class="navbar">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#primary-navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<p class="navbar-brand">
				<a href="/" class="navbar-logo">Schedule Application</a>
			</p>
		</div>
		<div class="collapse navbar-collapse" id="primary-navbar">
			<ul class="nav navbar-nav navbar-right navbar-lean">
				<li><a href="#" data-toggle="modal" data-target="#signupModal"
					style="font-family: 微软雅黑; height: 36px;">注册</a></li>
				<li><a href="#" class="btn-signup ga-signup"
					data-toggle="modal" data-target="#aboutModal">关于作者</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container">
		<section class="hero-heading text-center">
		<h1 class="hero-headline" id="hero-headline"
			style="opacity: 1; transform: matrix(1, 0, 0, 1, 0, 0);">您的日程管家助手</h1>
		<h2 class="hero-tagline"
			style="opacity: 1; transform: matrix(1, 0, 0, 1, 0, 0);">使用它，可以方便管理您的日程，并给予适当时候的提醒</h2>
		</section>
		<section class="hero-buttons text-center"> <a href="#"
			class="btn-hero1 btn btn-bold ga-signup" role="button"
			data-toggle="modal" data-target="#loginModal"
			style="opacity: 1; transform: matrix(1, 0, 0, 1, 0, 0);">开始使用</a> </section>
	</div>
	</section>
	<section class="announcement">
	<div class="container">
		<p style="margin: 0">Hope you enjoy it~</p>
	</div>
	</section>
	<section class="who-are-authors" id="who-are-authors">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1 class="section-headline">Which language it uses</h1>
				<h2 class="section-tagline">Of Course Java</h2>
				<p class="section-description section-space">
					It uses bootstrap and javascript file which was written by myself.<br />Also,it
					uses SpringMVC framework.
				</p>
			</div>
		</div>
	</div>
	</section>

	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="loginModalLabel"
						style="font-size: 28px">开始使用</h4>
					<br />
					<p>登录您的管家账号</p>
				</div>
				<form id="loginform" name="loginform" class="wufoo topLabel page"
					accept-charset="UTF-8" action="">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group mc-field-group">
									<label for="Field4">用户名</label> <input name="username"
										class="form-control" id="username1" autocomplete="off"
										required="">
								</div>
								<div class="form-group mc-field-group">
									<label for="Field5">密码</label> <input type="password"
										name="password" class="form-control" id="password1"
										autocomplete="off" required="">
								</div>
								<div class="form-group mc-field-group">
									<label for="Field6">验证码</label>
									<div>
										<input type="validate" name="code"
											class="form-control form-control2 required" id="code"
											autocomplete="off" required=""><label
											style="width: 18%; text-align: right; padding-right: 5px"><img
											onclick="refreshCode();" id="imgCode" title="看不清楚?点我换一张"
											src="codeAction" width="80px" height="30px" /></label>
									</div>

								</div>
								<input type="hidden" id="idstamp" name="idstamp"
									value="oPWTZAtU3zGs5VLhol1HDTyPSgx7cjdTd9PxI0LVR70=">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-link" data-dismiss="modal">关闭</button>
						<button type="button" name="loginbutton" id="loginbutton"
							data-toggle="popover" onclick="verifylogin()"
							class="btn btn-bold-revert btn-arrow-right btn-arrow-white">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="aboutModal" tabindex="-1" role="dialog"
		aria-labelledby="aboutModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="aboutModalLabel"
						style="font-size: 28px">关于作者</h4>
					<br />
					<p>这是一个Web Application</p>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div style="margin-bottom: 8px">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne" class="collapselink"> 陈奕杰 (点击展开)</a>
							</div>
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body" style="color: #fff">Orison Chan
									Q:327065327</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" name="saveForm" id="saveForm"
						class="btn btn-bold-revert btn-arrow-right btn-arrow-white"
						data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="signupModal" tabindex="-1" role="dialog"
		aria-labelledby="signupModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="signupModalLabel"
						style="font-size: 28px">注册</h4>
					<br />
					<p>注册您的管家账号</p>
				</div>
				<form id="signupform" name="signupform" class="wufoo topLabel page"
					accept-charset="UTF-8" action="">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group mc-field-group">
									<label for="username">姓名</label> <input name="username"
										class="form-control" id="username" autocomplete="off"
										required="">
								</div>
								<div class="form-group mc-field-group">
									<label for="password">密码</label> <input name="password"
										class="form-control" id="password" autocomplete="off"
										required="" type="password">
								</div>
								<div class="form-group mc-field-group">
									<label for="Field6">性别</label> <input type="radio"
										name="gender" class="form-control form-control3 required"
										id="Field6" autocomplete="off" required="" value="M"
										style="vertical-align: middle"><label>男</label><input
										type="radio" name="gender"
										class="form-control form-control3 required" id="Field6"
										autocomplete="off" required="" value="F"
										style="vertical-align: middle" /><label>女</label>
								</div>
								<div class="form-group mc-field-group">
									<label for="age">年龄</label> <input name="age"
										class="form-control required" id="age" autocomplete="off"
										required="">
								</div>
								<input type="hidden" id="idstamp" name="idstamp"
									value="oPWTZAtU3zGs5VLhol1HDTyPSgx7cjdTd9PxI0LVR70=">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-link" data-dismiss="modal">关闭</button>
						<button type="button" name="signupbutton" id="signupbutton"
							onclick="signup()"
							class="btn btn-bold-revert btn-arrow-right btn-arrow-white">注册</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<footer class="footer">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ul class="footer-nav">
					<li>This Schedule Application</li>
					<li>Written By:</li>
					<li>Orison.Chan（陈奕杰）</li>
				</ul>
			</div>
		</div>
	</div>
	</footer>
	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/common.js" charset="gbk"></script>
	<script type="text/javascript">
		function signup() {
			$.ajax({
				method : 'POST',
				url : 'signup.do',
				data : {
					'username' : $('#username').val(),
					'password' : $('#password').val(),
					'gender' : $('#gender').val(),
					'age' : $('#age').val()
				},
				success : function(data, status) {
					if (data["message"] == "success") {
						alert("注册成功！现在为您自动跳转~");
						window.location.href = "index.do";
					} else {
						showsignupbuttonpop("有点问题，再试试？");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					showsignupbuttonpop("有点问题，再试试？");
				}
			});
		}
		function verifylogin() {
			//验证验证码
			$.ajax({
				method : 'POST',
				url : 'verifycode.do',
				data : {
					'code' : $('#code').val()
				},
				success : function(data, status) {
					if (data["message"] == "success") {
						//验证登录名密码
						$.ajax({
							method : "POST",
							url : 'signin.do',
							data : {
								'username' : $('#username1').val(),
								'password' : $('#password1').val()
							},
							success : function(data, status) {
								if (data["message"] == "success") {
									//验证通过后台会存储session,可以直接跳转
									alert("验证通过！");
									window.location.href = "index.do";
								} else {
									alert("验证不通过！");
								}
							},
							error : function(data, status) {
								showloginbuttonpop("好像有点问题，再试试？");
							}
						});
					} else {
						showloginbuttonpop("您的验证码错误，再试试？");
					}
					;
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					showloginbuttonpop("好像有点问题，再刷新下验证码？");
				}
			});
		}
		function showsignupbuttonpop(contents) {
			$('#signupbutton').popover({
				trigger : "click",
				placement : "top",
				selector : $("#code"),
				content : contents
			});
			$('#signupbutton').popover('show');
			setTimeout("$('#signupbutton').popover('hide')", 1800);
			setTimeout("$('#signupbutton').popover('destroy')", 1800);
		}
		function showloginbuttonpop(contents) {
			$('#loginbutton').popover({
				trigger : "click",
				placement : "top",
				selector : $("#code"),
				content : contents
			});
			$('#loginbutton').popover('show');
			setTimeout("$('#loginbutton').popover('hide')", 1800);
			setTimeout("$('#loginbutton').popover('destroy')", 1800);
		}
	</script>
</body>
</html>