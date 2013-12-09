<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
</head>
<body>

	<c:if test="${not empty message}">
		<div>${message}</div>
	</c:if>
	<a href="${ctx}/index">返回首页</a>
	<a href="${ctx}/book/add">存书</a>
	<table id="ShowTable">
		<thead><tr><th>书名</th><th>作者</th><th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td>
					<a href="${ctx}/book/${book.id}/delete">取书</a>
					<a href="${ctx}/book/${book.id}/update">更新</a>					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>