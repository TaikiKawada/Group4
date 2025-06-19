<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上編集</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>

	<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5 d-flex justify-content-center">
		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-start">売上編集</h2>

			<form action="SalesEditServlet" method="post">
				<input type="hidden" name="saleId" value="${sale.saleId}" />

				<!-- 販売日 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 販売日 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="date" name="saleDate" class="form-control"
							value="${sale.saleDate}" />
						<c:if test="${errors != null && errors.saleDate != null}">
							<small class="text-danger">${errors.saleDate}</small>
						</c:if>

					</div>
				</div>

				<!-- 担当 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 担当 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<select name="staff" class="form-select">
							<option value="">選択してください</option>
							<c:forEach var="staffItem" items="${staffList}">
								<option value="${staffItem.id}"
									<c:if test="${staffItem.id == sale.accountId}">selected</c:if>>
									${staffItem.name}</option>
							</c:forEach>
						</select>
						<c:if test="${errors != null && errors.staff != null}">
							<small class="text-danger">${errors.staff}</small>
						</c:if>


					</div>
				</div>

				<!-- 商品カテゴリー -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 商品カテゴリー <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<select name="category" class="form-select">
							<option value="">選択してください</option>
							<c:forEach var="cat" items="${categoryList}">
								<option value="${cat.id}"
									<c:if test="${cat.id == sale.categoryId}">selected</c:if>>
									${cat.name}</option>
							</c:forEach>
						</select>
						<c:if test="${errors != null && errors.category != null}">
							<small class="text-danger">${errors.category}</small>
						</c:if>


					</div>
				</div>

				<!-- 商品名 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 商品名 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="text" name="tradeName" class="form-control"
							value="${sale.tradeName}" />
						<c:if test="${errors != null && errors.tradeName != null}">
							<small class="text-danger">${errors.tradeName}</small>
						</c:if>

					</div>
				</div>

				<!-- 単価 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 単価 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="number" name="unitPrice" class="form-control"
							value="${sale.unitPrice}" />
						<c:if test="${errors != null && errors.unitPrice != null}">
							<small class="text-danger">${errors.unitPrice}</small>
						</c:if>


					</div>
				</div>

				<!-- 個数 -->
				<div class="mb-3 row align-items-center">
					<label class="col-sm-4 col-form-label text-end"> 個数 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="col-sm-8">
						<input type="number" name="saleNumber" class="form-control"
							value="${sale.saleNumber}" />
						<c:if test="${errors != null && errors.saleNumber != null}">
							<small class="text-danger">${errors.saleNumber}</small>
						</c:if>


					</div>
				</div>

				<!-- 備考 -->
				<div class="mb-3 row">
					<label class="col-sm-4 col-form-label text-end">備考</label>
					<div class="col-sm-8">
						<textarea name="note" class="form-control" rows="3">${sale.note}</textarea>
					</div>
				</div>

<!--				 権限エラー -->
<!--				<c:if test="${errors != null && errors.saleDate != null}">-->
<!--					<small class="text-danger">${errors.saleDate}</small>-->
<!--				</c:if>-->


				<div class="text-center mt-4">

					<button type="submit" class="btn btn-primary me-2">
						<i class="bi bi-check-lg"></i>更新
					</button>
					<button type="button" class="btn btn-outline-secondary">
						キャンセル</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
