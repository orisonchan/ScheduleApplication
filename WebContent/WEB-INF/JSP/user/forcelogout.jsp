<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录已过</title>
<script src="js/jquery-2.1.4.min.js"></script>
<script>
	$(document).ready(function() {
		alert("您没有登录或者登录起效已过！将返回登录界面！");
		window.location.href = "login.do";
	});
</script>
</head>
<body>

</body>
</html>