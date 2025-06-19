<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上登録確認</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5 d-flex justify-content-center">

		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-start">売上登録確認</h2>

			<form action="SalesRegisterServlet" method="post">

				<%-- 販売日 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 販売日 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${salesDate}"
							disabled> <input type="hidden" name="salesDate"
							value="${salesDate}" />
					</div>
				</div>

				<!-- 担当 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 担当 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${staffName}"
							disabled> <input type="hidden" name="staff"
							value="${staff}" />
					</div>
				</div>

				<!-- 商品カテゴリー -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 商品カテゴリー <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${categoryName}"
							disabled> <input type="hidden" name="category"
							value="${category}" />
					</div>
				</div>


				<%-- 商品名 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 商品名 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${tradeName}"
							disabled> <input type="hidden" name="tradeName"
							value="${tradeName}" />
					</div>
				</div>

				<%-- 単価 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 単価 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${unitPrice} 円"
							disabled> <input type="hidden" name="unitPrice"
							value="${unitPrice}" />
					</div>
				</div>

				<%-- 個数 --%>
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 個数 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" value="${saleNumber}"
							disabled> <input type="hidden" name="saleNumber"
							value="${saleNumber}" />
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

					<button type="submit" class="btn btn-primary me-2">
						<i class="bi bi-check-lg"></i> 登録
					</button>
					<button type="button" class="btn btn-outline-secondary">
						キャンセル</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
