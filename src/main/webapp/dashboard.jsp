<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ダッシュボード</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/dashboard.css" type="text/css">
</head>
<body class="bg-light" style="padding-top: 70px;">

	<jsp:include page="/nav.jsp" />

	<div class="container">
		<!-- エラーメッセージ -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<!-- 売上情報一覧 -->
		<div class="card mb-4">
			<div class="card-header bg-dark text-white">売上情報一覧</div>
			<div class="card-body p-0">
				<table class="table table-striped table-hover mb-0">
					<thead class="table-dark">
						<tr>
							<th>売上ID</th>
							<th>日付</th>
							<th>担当者名</th>
							<th>商品名</th>
							<th>単価</th>
							<th>個数</th>
							<th>備考</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sale" items="${sales}">
							<tr>
								<td>${sale.saleId}</td>
								<td>${sale.saleDate}</td>
								<td>${sale.accountName}</td>
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

		<div class="row">
			<!-- 売上推移グラフ -->
			<div class="col-md-6 mb-4">
				<div class="card h-100">
					<div class="card-header bg-dark text-white">日付ごとの売上推移グラフ</div>
					<div class="card-body">
						<canvas id="saleChart" style="width: 100%; height: 300px;"></canvas>
					</div>
				</div>
			</div>

			<!-- 売上カテゴリー割合 円グラフ -->
			<div class="col-md-6 mb-4">
				<div class="card h-100">
					<div class="card-header bg-dark text-white">売上カテゴリー割合</div>
					<div class="card-body">
						<canvas id="categoryPieChart"
							style="display: block; margin: auto; max-width: 300px; max-height: 300px; width: 100%; height: auto;"></canvas>
					</div>
				</div>
			</div>
		</div>

		<!-- 売上データをJavaScript配列として埋め込み -->
		<script>
  const salesData = [  
    <c:forEach var="sale" items="${sales}" varStatus="status">
      {
        saleDate: "${sale.saleDate}",
        unitPrice: ${sale.unitPrice},
        saleNumber: ${sale.saleNumber}
      }<c:if test="${!status.last}">,</c:if>
    </c:forEach>
  ];
</script>

		<!-- Chart.jsライブラリ -->
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

		<script>
  		const categoryLabels = [
    		<c:forEach var="entry" items="${categorySalesMap}" varStatus="status">
      			'${entry.key}'<c:if test="${!status.last}">,</c:if>
   			 </c:forEach>
  ];

  const categoryData = [
    <c:forEach var="entry" items="${categorySalesMap}" varStatus="status">
      ${entry.value}<c:if test="${!status.last}">,</c:if>
    </c:forEach>
  ];
</script>

		<script src="js/categoryPieChart.js"></script>

		<!-- 外部JSファイルでグラフ描画 -->
		<script src="js/salesChart.js"></script>

		<!-- Bootstrap JS -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
