<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上検索</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5 d-flex justify-content-center">
		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-center">売上検索</h2>

			<form method="get" action="SalesSearchServlet">
				<div class="mb-3">
					<label class="form-label">販売日（開始）</label>
					<input type="date" name="fromDate" class="form-control" />
				</div>

				<div class="mb-3">
					<label class="form-label">販売日（終了）</label>
					<input type="date" name="toDate" class="form-control" />
				</div>

				<div class="mb-3">
					<label class="form-label">担当</label>
					<select name="staff" class="form-select">
						<option value="">指定なし</option>
						<!-- 担当者一覧をここに動的に出力（今は未設定） -->
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label">商品カテゴリー</label>
					<select name="category" class="form-select">
						<option value="">指定なし</option>
						<!-- カテゴリー一覧をここに動的に出力（今は未設定） -->
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label">商品名</label>
					<input type="text" name="productName" class="form-control" placeholder="部分一致で検索可" />
				</div>

				<div class="text-end mt-4">
					<button class="btn btn-primary" type="submit">検索</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
