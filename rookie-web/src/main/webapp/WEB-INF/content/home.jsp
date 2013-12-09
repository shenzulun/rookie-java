<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
</head>
<body>
	<h2>hey man!</h2>
	
	<form action="${ctx}/book" method="get">
		<fieldset>
			<legend>登录</legend>
			<input type="text" name="userName"/>
			<input type="password" name="pass"/>
			<input type="submit" value="登录" />
		</fieldset>
	</form>
</body>
</html>