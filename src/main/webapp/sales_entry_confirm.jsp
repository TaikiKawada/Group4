<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上登録確認</title>
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

	<div class="container mt-5 pt-5 d-flex justify-content-center">

		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-start">売上登録確認</h2>

			<form action="SalesRegisterServlet" method="post">
				
				<%-- 販売日 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						販売日 <span class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${salesDate}" disabled>
						<input type="hidden" name="salesDate" value="${salesDate}" />
					</div>
				</div>

				<%-- 担当 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						担当 <span class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${staff}" disabled>
						<input type="hidden" name="staff" value="${staff}" />
					</div>
				</div>

				<%-- 商品カテゴリー --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						商品カテゴリー <span class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${category}" disabled>
						<input type="hidden" name="category" value="${category}" />
					</div>
				</div>

				<%-- 商品名 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						商品名 <span class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${tradeName}" disabled>
						<input type="hidden" name="tradeName" value="${tradeName}" />
					</div>
				</div>

				<%-- 単価 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						単価 <span class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${unitPrice} 円" disabled>
						<input type="hidden" name="unitPrice" value="${unitPrice}" />
					</div>
				</div>

				<%-- 個数 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						個数 <span class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${saleNumber}" disabled>
						<input type="hidden" name="saleNumber" value="${saleNumber}" />
					</div>
				</div>

				<%-- 備考 --%>
				<div class="mb-3 row">
					<label class="col-sm-4 col-form-label text-end">備考</label>
					<div class="col-sm-8">
						<textarea class="form-control" rows="3" disabled>${note}</textarea>
						<input type="hidden" name="note" value="${note}" />
					</div>
				</div>

				<div class="text-center mt-4">
					<button class="btn btn-primary" type="submit">✔ 登録</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
