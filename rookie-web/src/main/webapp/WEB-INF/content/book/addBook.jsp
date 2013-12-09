<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>add</title>
</head>
<body>
	<a href="${ctx}/index">返回首页</a>
	<form action="${ctx}/book/add" method="post">
		<fieldset>
			<legend>存书</legend>
			<label>书名:</label><input type="text" name="name"/>
			<label>作者:</label><input type="text" name="author"/>
			<input type="submit" value="保存" />
		</fieldset>
	</form>
</body>
</html>