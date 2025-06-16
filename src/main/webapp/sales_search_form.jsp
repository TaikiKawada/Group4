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

<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5 d-flex justify-content-center">
		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-start">売上検索</h2>

			<form method="post" action="SalesSearchServlet">
				
				<!-- 販売日（開始～終了） -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">販売日</label>
					<div class="col-sm-3">
						<input type="date" name="fromDate" class="form-control" />
					</div>
					<div class="col-sm-1 text-center">～</div>
					<div class="col-sm-4">
						<input type="date" name="toDate" class="form-control" />
					</div>
				</div>
				

				<!-- 担当者 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">担当</label>
					<div class="col-sm-8">
						<select name="staff" class="form-select">
							<option value="">選択してください</option>
							<c:forEach var="staff" items="${staffList}">
								<option value="${staff.id}">${staff.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<!-- 商品カテゴリー -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">商品カテゴリー</label>
					<div class="col-sm-8">
						<select name="category" class="form-select">
							<option value="">選択してください</option>
							<c:forEach var="cat" items="${categoryList}">
								<option value="${cat.id}">${cat.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<!-- 商品名 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						商品名 <span class="badge bg-secondary">部分一致</span>
					</label>
					<div class="col-sm-8">
						<input type="text" name="productName" class="form-control" placeholder="商品名" />
					</div>
				</div>

				<!-- 備考 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end">
						備考 <span class="badge bg-secondary">部分一致</span>
					</label>
					<div class="col-sm-8">
						<input type="text" name="note" class="form-control" placeholder="備考" />
					</div>
				</div>

				<!-- ボタン -->
				<div class="text-center mt-4">
					<button class="btn btn-primary me-2" type="submit">🔍 検索</button>
					<button class="btn btn-secondary" type="reset">クリア</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
