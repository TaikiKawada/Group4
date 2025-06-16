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

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 fixed-top">
	<div class="container-fluid">
		<a class="navbar-brand" href="DashboardServlet">ダッシュボード</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav me-auto">
				<c:if test="${authority == 1 || authority == 3}">
					<li class="nav-item"><a class="nav-link" href="SalesEntryServlet">売上登録</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link" href="SalesSearchServlet">売上検索</a></li>
				<c:if test="${authority == 2 || authority == 3}">
					<li class="nav-item"><a class="nav-link" href="AccountEntryServlet">アカウント登録</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link" href="AccountSerchFormServlet">アカウント検索</a></li>
			</ul>
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link btn btn-outline-light px-3 py-1" href="login">ログアウト</a></li>
			</ul>
		</div>
	</div>
</nav>

	<div class="container mt-5 d-flex justify-content-center">
		<div class="text-center" style="max-width: 600px;">
			<h2 class="mb-4 text-success">削除完了</h2>

			<c:if test="${not empty message}">
				<div class="alert alert-success">${message}</div>
			</c:if>

			<a href="sales_search_form.jsp" class="btn btn-primary mt-3">検索画面に戻る</a>
		</div>
	</div>
</body>
</html>
