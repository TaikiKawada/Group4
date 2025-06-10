<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ダッシュボード</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* カスタムボタンスタイル */
        .custom-btn {
            background-color: #f0f0f0;
            color: #555;
            border: 1px solid #ccc;
        }

        .custom-btn:hover {
            background-color: #dcdcdc;
            color: #333;
        }

        .header-buttons {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .left-buttons, .right-button {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body class="bg-light">

<div class="container py-4">
    <h2 class="text-center mb-4">ダッシュボード</h2>

    <!-- 上部ボタンバー -->
    <div class="header-buttons">
        <!-- 左側ボタン -->
        <div class="left-buttons">
            <form action="SalesEntry" method="get">
                <button class="btn custom-btn" type="submit">売上登録</button>
            </form>
            <form action="SalesSearchForm" method="get">
                <button class="btn custom-btn" type="submit">売上検索</button>
            </form>
            <form action="AccountEntry" method="get">
                <button class="btn custom-btn" type="submit">アカウント登録</button>
            </form>
            <form action="AccountSearchForm" method="get">
                <button class="btn custom-btn" type="submit">アカウント検索</button>
            </form>
        </div>
        <!-- 右側（ログアウト） -->
        <div class="right-button">
            <form action="LoginServlet" method="get">
                <button class="btn custom-btn" type="submit">ログアウト</button>
            </form>
        </div>
    </div>

    <!-- エラーメッセージ -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- 売上情報一覧 -->
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

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
