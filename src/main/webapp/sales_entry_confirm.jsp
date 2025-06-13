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


		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-center">売上登録確認</h2>

			<table class="table table-bordered">
				<tr><th>販売日</th><td>${salesDate}</td></tr>
				<tr><th>担当</th><td>${staff}</td></tr>
				<tr><th>商品カテゴリー</th><td>${category}</td></tr>
				<tr><th>商品名</th><td>${tradeName}</td></tr>
				<tr><th>単価</th><td>${unitPrice} 円</td></tr>
				<tr><th>個数</th><td>${saleNumber}</td></tr>
				<tr><th>備考</th><td>${note}</td></tr>
			</table>

			<!-- 登録処理へ送る hidden フォーム -->
			<form action="SalesRegisterServlet" method="post">
				<input type="hidden" name="salesDate" value="${salesDate}" />
				<input type="hidden" name="staff" value="${staff}" />
				<input type="hidden" name="category" value="${category}" />
				<input type="hidden" name="tradeName" value="${tradeName}" />
				<input type="hidden" name="unitPrice" value="${unitPrice}" />
				<input type="hidden" name="saleNumber" value="${saleNumber}" />
				<input type="hidden" name="note" value="${note}" />

				<div class="text-end">
					<button class="btn btn-success">この内容で登録</button>
				</div>
			</form>

			<!-- 修正へ戻るボタン -->
			<form action="sales_entry.jsp" method="post" class="mt-3">
				<div class="text-end">
					<button class="btn btn-secondary">修正する</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
