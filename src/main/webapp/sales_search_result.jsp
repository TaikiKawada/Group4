<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上検索結果</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		.sale-entry { padding: 1rem 0; }
		.sale-entry + .sale-entry { border-top: 1px solid #ccc; }
	</style>
</head>
<body>

<jsp:include page="/nav.jsp" />

<div class="container mt-5 pt-5">
    <h2 class="mb-4 text-start">売上検索結果</h2>

    <c:choose>
        <c:when test="${empty resultList}">
            <p class="text-center">該当する売上データはありませんでした。</p>
        </c:when>
        <c:otherwise>
            <div class="d-flex fw-bold border-bottom py-2">
                <div class="me-3" style="width: 70px;"></div>
                <div class="me-3" style="width: 60px;">No</div>
                <div class="me-3" style="width: 120px;">販売日</div>
                <div class="me-3" style="width: 100px;">担当</div>
                <div class="me-3" style="width: 120px;">商品カテゴリー</div>
                <div class="me-3" style="width: 100px;">商品名</div>
                <div class="me-3" style="width: 80px;">単価</div>
                <div class="me-3" style="width: 80px;">個数</div>
                <div class="me-3" style="width: 100px;">小計</div>
            </div>

            <c:forEach var="row" items="${resultList}" varStatus="status">
                <div class="d-flex align-items-center border-bottom py-2">
                    <form action="S0022.html" method="get" class="me-3">
                        <input type="hidden" name="saleId" value="${row.saleId}" />
                        <button class="btn btn-sm btn-outline-primary">詳細</button>
                    </form>
                    <div class="me-3" style="width: 60px;">${status.index + 1}</div>
                    <div class="me-3" style="width: 120px;">
                        <fmt:parseDate value="${row.saleDate}" pattern="yyyy-MM-dd" var="parsedDate" />
                        <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
                    </div>
                    <div class="me-3" style="width: 100px;">${row.accountName}</div>
                    <div class="me-3" style="width: 120px;">${row.categoryName}</div>
                    <div class="me-3" style="width: 100px;">${row.tradeName}</div>
                    <div class="me-3" style="width: 80px;">${row.unitPrice}</div>
                    <div class="me-3" style="width: 80px;">${row.saleNumber}</div>
                    <div class="me-3" style="width: 100px;">
                        ${row.unitPrice * row.saleNumber}
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/S0020.html" class="btn btn-secondary">検索条件に戻る</a>

    </div>
</div>

</body>
</html>
