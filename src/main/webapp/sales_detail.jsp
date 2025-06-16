<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上詳細</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

<jsp:include page="/nav.jsp" />

<div class="container mt-5 pt-5">
	<h2 class="mb-4 text-start">売上詳細表示</h2>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="mb-3 row">
				<label class="col-sm-4 col-form-label text-end">販売日</label>
				<div class="col-sm-8">
					<p class="form-control-plaintext">${sale.saleDate}</p>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-4 col-form-label text-end">担当</label>
				<div class="col-sm-8">
					<p class="form-control-plaintext">${sale.accountName}</p>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-4 col-form-label text-end">商品カテゴリー</label>
				<div class="col-sm-8">
					<p class="form-control-plaintext">${sale.categoryName}</p>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-4 col-form-label text-end">商品名</label>
				<div class="col-sm-8">
					<p class="form-control-plaintext">${sale.tradeName}</p>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-4 col-form-label text-end">単価</label>
				<div class="col-sm-8">
					<p class="form-control-plaintext">${sale.unitPrice}</p>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-4 col-form-label text-end">個数</label>
				<div class="col-sm-8">
					<p class="form-control-plaintext">${sale.saleNumber}</p>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-4 col-form-label text-end">備考</label>
				<div class="col-sm-8">
					<p class="form-control-plaintext">${sale.note}</p>
				</div>
			</div>

			<!-- ボタン配置 -->
			<div class="text-center mt-4">
				<form action="SalesEditServlet" method="get" class="d-inline">
					<input type="hidden" name="saleId" value="${sale.saleId}" />
					<button class="btn btn-primary" type="submit">✔ 編集</button>
				</form>
				<form action="SalesDeleteConfirmServlet" method="get" class="d-inline">
					<input type="hidden" name="saleId" value="${sale.saleId}" />
					<button class="btn btn-danger" type="submit">✖ 削除</button>
				</form>
				<a href="SalesSearchServlet" class="btn btn-secondary">キャンセル</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>
