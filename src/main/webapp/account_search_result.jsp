<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${ pageContext.request.contextPath}/account/search.html">
		氏名：<input type="text" name="name"> メール：<input type="text"
			name="mail"> 権限：<input type="text" name="auth"> <input
			type="submit" value="検索">
	</form>

	<c:forEach var="a" items="${accountList}">
		<p>${a.name}（${a.mail}）権限: ${a.authority}</p>
	</c:forEach>

</body>
</html>