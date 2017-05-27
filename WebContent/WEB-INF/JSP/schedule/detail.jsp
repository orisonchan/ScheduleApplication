<%@ page language="java" import="java.util.*,com.bean.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	HttpSession s = request.getSession();
	Integer userid = (Integer) s.getAttribute("userid");
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
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link href="bower_components/fullcalendar/dist/fullcalendar.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/mycss.css">
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
<!-- jQuery -->
<script src="bower_components/jquery/jquery.min.js"></script>
</head>
<body>
	<%
		//如果未登录，跳转回login
		if (userid == null) {
	%>
	<script>
		alert("您还未登录！");
		window.location.href = "login.jsp";
	</script>
	<%
		}
	%>
	<nav class="navbar navbar-default" role="navigation"
		style="background: url(./img/bg.jpg) 0 0/cover no-repeat;padding-top:0px">
	<div class="navbar-inner">
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
				<li><a style="font-family:微软雅黑;height:36px;">${user.username}</a></li>
				<li><a href="#" class="btn-signup ga-signup"
					data-toggle="modal" data-target="#logoutModal">退出登录</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="row">
		<div class="col-sm-2 col-lg-2">
			<div class="sidebar-nav">
				<div class="nav-canvas">
					<div class="nav-sm nav nav-stacked"></div>
					<ul class="nav nav-pills nav-stacked main-menu">
						<li class="nav-header">Main</li>
						<li><a class="ajax-link" href="index.do"><i
								class="glyphicon glyphicon-home"></i><span> 首页</span></a></li>
						<li class="accordion"><a href="#"><i
								class="glyphicon glyphicon-list"></i><span> 分类管理</span></a>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="schedule/show.do" class="subli"><i
										class="glyphicon glyphicon-eye-open"></i><span> 分类查看</span></a></li>
								<li><a href="clazz/show.do" class="subli"><i
										class="glyphicon glyphicon-inbox"></i><span> 分类目录</span></a></li>
							</ul></li>
						<li><a class="ajax-link" href="schedule/new.do"><i
								class="glyphicon glyphicon-edit"></i><span> 新建日程</span></a></li>
						<li><a class="ajax-link" href="#"><i
								class="glyphicon glyphicon-calendar"></i><span> 日历</span></a></li>
						<li><a class="ajax-link" href="showinfo.do"><i
								class="glyphicon glyphicon-user"></i><span> 个人信息</span></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="content" class="col-lg-10 col-sm-10">
			<!-- content starts -->
			<div class="">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2>
								<i class="glyphicon glyphicon-edit"></i> 日程详情
							</h2>
						</div>
						<form method="post" action="schedule/update">
							<div class="box-content row">
								<div class="form-group">
									<label for="dtp_input1" class="col-md-2 control-label">时间选择</label>
									<div class="input-group date form_datetime col-md-4"
										data-date="" data-date-format="yyyy-mm-dd hh:ii:ss"
										data-link-field="dtp_input1">
										<input name="start_time" class="form-control" size="16"
											type="datetime"
											value="<%=((Schedule)request.getAttribute("scheinfo")).getStart_time().toString().substring(0, 19)%>"
											readonly> <span class="input-group-addon"><span
											class="glyphicon glyphicon-time"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" /><br />
									<div class="col-md-12">
										<label class="col-md-3 control-label"></label> <label
											class="col-md-9 control-label">至</label>
									</div>
									<label for="dtp_input2" class="col-md-2 control-label"></label>
									<div class="input-group date form_datetime col-md-4"
										data-date="" data-date-format="yyyy-mm-dd hh:ii:ss"
										data-link-field="dtp_input2">
										<input name="end_time" class="form-control" size="16"
											type="text"
											value="<%=((Schedule)request.getAttribute("scheinfo")).getEnd_time().toString().substring(0, 19)%>"
											readonly> <span class="input-group-addon"><span
											class="glyphicon glyphicon-time"></span></span>
									</div>
									<input type="hidden" id="dtp_input2" value="" /><br /> <label
										class="col-md-2 control-label">分类</label>
									<div class="input-group col-md-4">
										<select class="form-control" name="classiId">
											<%
												List classilist = (List) request.getAttribute("clazzlist");
												Iterator itee = classilist.iterator();
												while (itee.hasNext()) {
													Classi classi = (Classi) itee.next();
													String line = "";
													for (int i = 1; i <= classi.getLevel(); i++)
														line += "&nbsp;&nbsp;";
													String yes = "";
													if (((Schedule)request.getAttribute("scheinfo")).getClassiId() == classi.getId())
														yes = "selected";
													out.println("<option value=\"" + classi.getId() + "\"" + yes
															+ ">" + line + classi.getName() + "</option>");
												}
											%>
										</select>
									</div>
									<br /> <label class="col-md-2 control-label">标题</label>

									<div class="input-group col-md-4">
										<input name="title" class="form-control" size="16" type="text"
											value="<%=((Schedule)request.getAttribute("scheinfo")).getTitle()%>">
									</div>
									<br /> <label class="col-md-2 control-label">内容</label>
									<div class="input-group col-md-4">
										<textarea name="content" class="form-control" size="16"><%=((Schedule)request.getAttribute("scheinfo")).getContent()%></textarea>
									</div>
									<br /> <label class="col-md-2 control-label"></label>
									<div class="input-group col-md-4">
										<input type="submit" class="btn btn-primary"
											style="background-color:#6D7B95;border-color:#6D7B95"
											value="修改" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="schedule/delete?id=<%=((Schedule)request.getAttribute("scheinfo")).getId()%>"><button
												type="button" class="btn btn-danger">删除</button></a>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- content ends -->
			</div>
		</div>
	</div>
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="logoutModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="logoutModalLabel"
						style="font-size:22px;font-family:微软雅黑">有爱的提示框~</h4>
					<br />
					<p>确定退出吗？</p>
				</div>
				<div class="modal-footer">
					<form action="logout" method="post">
						<button type="button" class="btn btn-link" data-dismiss="modal">不退出</button>
						<button type="submit" name="logout" id="logout"
							class="btn btn-bold-revert btn-arrow-right btn-arrow-white">退出</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="bower_components/moment/min/moment.min.js"></script>
	<script src="bower_components/fullcalendar/dist/fullcalendar.js"></script>
	<script src="bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- external javascript -->

	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- library for cookie management -->
	<script src="js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='bower_components/moment/min/moment.min.js'></script>
	<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='js/jquery.dataTables.min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script src="bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="js/charisma.js"></script>
	<!-- datetimepicker -->
	<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script>
		$.fn.datetimepicker.dates['zh-CN'].today = "当前时间";
		$('.form_datetime').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		});
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		$('.form_time').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0
		});
	</script>
</body>
</html>
