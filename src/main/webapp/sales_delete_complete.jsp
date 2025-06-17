<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>削除完了</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5 d-flex justify-content-center">
		<div class="text-center" style="max-width: 600px;">
			<h2 class="mb-4 text-success">削除完了</h2>

			<c:if test="${not empty message}">
				<div class="alert alert-success">${message}</div>
			</c:if>

			<a href="SalesSearchServlet" class="btn btn-primary mt-3">検索画面に戻る</a>
		</div>
	</div>
</body>
</html>
