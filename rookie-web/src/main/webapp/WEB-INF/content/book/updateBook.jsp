<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>update</title>
</head>
<body>
	<a href="${ctx}/index">返回首页</a>
	<form action="${ctx}/book/update" method="post">
		<fieldset>
			<legend>更新</legend>
			<label>书名:</label><input type="text" name="name" value="${book.name}"/>
			<label>作者:</label><input type="text" name="author" value="${book.author}"/>
			<input type="hidden" name="id" value="${book.id}"/>
			<input type="submit" value="更新" />
		</fieldset>
	</form>
</body>
</html>