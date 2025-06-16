<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上削除確認</title>
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
		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-center text-danger">この売上情報を削除しますか？</h2>

			<table class="table table-bordered">
				<tr><th>販売日</th><td>${salesDate}</td></tr>
				<tr><th>担当</th><td>${staff}</td></tr>
				<tr><th>商品カテゴリー</th><td>${category}</td></tr>
				<tr><th>商品名</th><td>${productName}</td></tr>
				<tr><th>単価</th><td>${unitPrice}</td></tr>
				<tr><th>個数</th><td>${quantity}</td></tr>
				<tr><th>備考</th><td>${remarks}</td></tr>
			</table>

			<form action="SalesDeleteServlet" method="post">
				<input type="hidden" name="saleId" value="${saleId}" />
				<div class="text-end mt-4 d-flex justify-content-between">
					<a href="sales_search_result.jsp" class="btn btn-secondary">キャンセル</a>
					<button type="submit" class="btn btn-danger">削除する</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
