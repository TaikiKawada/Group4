<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ダッシュボード</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/dashboard.css" type="text/css">
</head>
<body class="bg-light" style="padding-top: 70px;">

	<!-- ナビゲーションバー -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="DashboardServlet">ダッシュボード</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto">
					<!-- 売上登録：権限 1 または 3 -->
					<c:if test="${authority == 1 || authority == 3}">
						<li class="nav-item"><a class="nav-link"
							href="sales_entry.jsp">売上登録</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link"
						href="sales_search_form.jsp">売上検索</a></li>

					<!-- アカウント登録：権限 2 または 3 -->
					<c:if test="${authority == 2 || authority == 3}">
						<li class="nav-item"><a class="nav-link"
							href="account_entry.jsp">アカウント登録</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link"
						href="AccountSerchFormServlet">アカウント検索</a></li>
				</ul>
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a
						class="nav-link btn btn-outline-light px-3 py-1" href=login>ログアウト</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<!-- エラーメッセージ -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<!-- 売上情報一覧 -->
		<div class="card">
			<div class="card-header bg-dark text-white">売上情報一覧</div>
			<div class="card-body p-0">
				<table class="table table-striped table-hover mb-0">
					<thead class="table-dark">
						<tr>
							<th>日付</th>
							<th>アカウントID</th>
							<th>カテゴリID</th>
							<th>商品名</th>
							<th>単価</th>
							<th>個数</th>
							<th>備考</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sale" items="${sales}">
							<tr>
								<td>${sale.saleDate}</td>
								<td>${sale.accountId}</td>
								<td>${sale.categoryId}</td>
								<td>${sale.tradeName}</td>
								<td>${sale.unitPrice}</td>
								<td>${sale.saleNumber}</td>
								<td>${sale.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
