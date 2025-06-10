<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ダッシュボード</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4">
    <h2 class="mb-4">ダッシュボード</h2>

    <!-- ボタンエリア -->
    <div class="mb-4 d-flex flex-wrap gap-2">
        <form action="SalesEntry" method="get">
            <button class="btn btn-primary" type="submit">売上登録</button>
        </form>
        <form action="SalesSearchForm" method="get">
            <button class="btn btn-secondary" type="submit">売上検索</button>
        </form>
        <form action="AccountEntry" method="get">
            <button class="btn btn-success" type="submit">アカウント登録</button>
        </form>
        <form action="AccountSearchForm" method="get">
            <button class="btn btn-warning" type="submit">アカウント検索</button>
        </form>
        <form action="LoginServlet" method="get">
            <button class="btn btn-danger" type="submit">ログアウト</button>
        </form>
    </div>

    <!-- エラーメッセージ表示 -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- 売上一覧テーブル -->
    <div class="card">
        <div class="card-header bg-dark text-white">売上情報一覧</div>
        <div class="card-body p-0">
            <table class="table table-striped table-hover mb-0">
                <thead class="table-dark">
                <tr>
                    <th>日付</th>
                    <th>アカウントID</th>
                    <th>カテゴリID</th>
                    <th>商品名</th>
                    <th>単価</th>
                    <th>個数</th>
                    <th>備考</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sale" items="${sales}">
                    <tr>
                        <td>${sale.saleDate}</td>
                        <td>${sale.accountId}</td>
                        <td>${sale.categoryId}</td>
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
</div>
</body>
</html>
