<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
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
<link href="bower_components/fullcalendar/dist/fullcalendar.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/mycss.css">
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
<!-- jQuery -->
<script src="bower_components/jquery/jquery.min.js"></script>
</head>
<body>
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
			<div class=" row">
				<div class="col-md-3 col-sm-3 col-xs-6">
					<a style="color:#BC9EC0" data-toggle="tooltip"
						title="您今天还有${today_schedules_count}个日程等待完成。"
						class="well top-block" href="#"> <i
						class="glyphicon glyphicon-user blue"></i>

						<div>所有日程</div>
						<div>${total_schedules_count}</div> <span
						class="notification">${today_schedules_count}</span>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a style="color:#BC9EC0" data-toggle="tooltip"
						title="最近一周新建了${within_oneweek}个分类" class="well top-block" href="#"> <i
						class="glyphicon glyphicon-align-left green"></i>

						<div>所有分类</div>
						<div>${total_classes_count}</div>
						<span class="notification red">${within_oneweek}</span>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a style="color:#BC9EC0" data-toggle="tooltip"
						title="还没做" class="well top-block" href="#"> <i
						class="glyphicon glyphicon-star yellow"></i>

						<div>星标日程</div>
						<div>无</div> <span class="notification yellow">0</span>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a style="color:#BC9EC0" data-toggle="tooltip"
						title="还没做" class="well top-block" href="#"> <i
						class="glyphicon glyphicon-bell red"></i>

						<div>提醒事项</div>
						<div>无</div> <span class="notification red">0</span>
					</a>
				</div>
			</div>
			<div class=" row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2>
								<i class="glyphicon glyphicon-th-list"></i> 今天日程
							</h2>
						</div>
						<div class="box-content row">
							<h2>
								&nbsp;&nbsp;${user.username}，欢迎回来！<span id="abcdefg"></span>
							</h2>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>所属分类</th>
										<th>标题</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${today_schedules_list}" var="scheVO" varStatus="status">
									<tr>
										<td class="center">${scheVO.start_time.toString().substring(0, 16)}</td>
										<td class="center">${scheVO.end_time.toString().substring(0, 16)}</td>
										<td class="center">${scheVO.classiname}</td>
										<td class="center">${scheVO.title}</td>
										<td class="center"><a href="schedule/${scheVO.id}/detail.do"><span class="label-default label label-danger delete">详情</span></a></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content ends -->
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
					<form action="logout.do" method="post">
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
	<script>
		$(function() {
			showweather();
			setInterval(showweather, 60000);

		});
		function showweather() {
			$
					.ajax({
						url : "https://api.heweather.com/x3/weather?cityid=CN101020100&key=0618996a0d0d4bfd88935e22fd81c753",
						data : {},
						success : function(result) {
							var json = eval(result);
							var heweather = json['HeWeather data service 3.0'];
							var city = heweather[0]['basic']['city'];
							var time = new Date();
							var timestring = time.getFullYear() + "年"
									+ (time.getMonth() + 1) + "月"
									+ time.getDate() + "日  " + time.getHours()
									+ "时" + time.getMinutes() + "分";
							var now = heweather[0]['now'];
							var tmp = now['tmp'];
							var txt = now['cond']['txt'];
							$("#abcdefg").html(
									"当前时间：" + timestring + "<br />" + "您所处的城市："
											+ city + "&nbsp;&nbsp;天气：" + txt
											+ "&nbsp;&nbsp;温度：" + tmp + "℃");
						},
						error : function(result) {
						}
					});
		}
	</script>
	<script>
		$(document).ready(function() {
			$('span.delete').click(function() {
				var me = $(this), txt = me.data('no') + '';
				window.sessionStorage.setItem("scheduleid", txt);
			});
		});
	</script>
</body>
</html>
