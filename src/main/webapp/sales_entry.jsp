<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上登録</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5 d-flex justify-content-center">
		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-center">売上登録</h2>

			<form action="SalesEntryServlet" method="post">
				<div class="mb-3">
					<label class="form-label">販売日</label>
					<span class="badge text-bg-secondary">必須</span>
					<input type="date" name="salesDate" class="form-control" required>
				</div>

				<div class="mb-3">
					<label class="form-label">担当</label>
					<span class="badge text-bg-secondary">必須</span>
					<select name="staff" class="form-select" required>
						<option value="">選択してください</option>
						<!-- 担当者一覧をここに動的に出力 -->
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label">商品カテゴリー</label>
					<span class="badge text-bg-secondary">必須</span>
					<select name="category" class="form-select" required>
						<option value="">選択してください</option>
						<!-- カテゴリー一覧をここに動的に出力 -->
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label">商品名</label>
					<span class="badge text-bg-secondary">必須</span>
					<input type="text" name="productName" class="form-control" required>
				</div>

				<div class="mb-3">
					<label class="form-label">単価</label>
					<span class="badge text-bg-secondary">必須</span>
					<input type="number" name="unitPrice" class="form-control" required>
				</div>

				<div class="mb-3">
					<label class="form-label">個数</label>
					<span class="badge text-bg-secondary">必須</span>
					<input type="number" name="quantity" class="form-control" required>
				</div>

				<div class="mb-3">
					<label class="form-label">備考</label>
					<textarea name="remarks" class="form-control" rows="3"></textarea>
				</div>

				<div class="text-end mt-4">
					<button class="btn btn-primary" type="submit">登録</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
