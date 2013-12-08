<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
</head>
<body>
	<table id="ShowTable">
		<thead><tr><th>书名</th><th>作者</th><th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td><a href="${ctx}/book/delete/${book.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>