<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索結果</title>
</head>
<body>
	<div class="container mt-5"></div>
	<c:if test="${empty accountList}">
		<p>該当するアカウントが見つかりませんでした。</p>
	</c:if>

	<c:forEach var="a" items="${accountList}">
		<p>${a.name}（${a.mail}）権限:${a.auth}</p>
	</c:forEach>


</body>
</html>