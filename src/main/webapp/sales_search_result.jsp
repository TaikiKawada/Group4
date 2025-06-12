<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上検索結果</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4 text-center">売上検索結果</h2>

		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<c:choose>
			<c:when test="${empty resultList}">
				<p class="text-center">該当する売上データはありませんでした。</p>
			</c:when>
			<c:otherwise>
				<div class="table-responsive">
					<table class="table table-bordered table-striped">
						<thead class="table-light">
							<tr>
								<th>売上ID</th>
								<th>販売日</th>
								<th>担当ID</th>
								<th>カテゴリID</th>
								<th>商品名</th>
								<th>単価</th>
								<th>個数</th>
								<th>備考</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="row" items="${resultList}">
								<tr>
									<td>${row.saleId}</td>
									<td>${row.saleDate}</td>
									<td>${row.accountId}</td>
									<td>${row.categoryId}</td>
									<td>${row.tradeName}</td>
									<td>${row.unitPrice}</td>
									<td>${row.saleNumber}</td>
									<td>${row.note}</td>
									<td class="d-flex gap-1">
										<form action="SalesDetailServlet" method="get">
											<input type="hidden" name="saleId" value="${row.saleId}" />
											<button class="btn btn-sm btn-outline-primary">編集</button>
										</form>
										<form action="SalesDeleteConfirmServlet" method="get">
											<input type="hidden" name="saleId" value="${row.saleId}" />
											<button class="btn btn-sm btn-outline-danger">削除</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>

		<div class="text-center mt-4">
			<a href="sales_search_form.jsp" class="btn btn-secondary">検索条件に戻る</a>
		</div>
	</div>
</body>
</html>
