<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>売上検索結果</title>
</head>
<body>
    <h2>売上検索結果</h2>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty results}">
            <p>該当する売上データはありませんでした。</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="5">
                <tr>
                    <th>売上ID</th>
                    <th>販売日</th>
                    <th>担当</th>
                    <th>商品カテゴリ</th>
                    <th>商品名</th>
                    <th>単価</th>
                    <th>個数</th>
                    <th>備考</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="row" items="${results}">
                    <tr>
                        <td>${row[0]}</td>
                        <td>${row[1]}</td>
                        <td>${row[2]}</td>
                        <td>${row[3]}</td>
                        <td>${row[4]}</td>
                        <td>${row[5]}</td>
                        <td>${row[6]}</td>
                        <td>${row[7]}</td>
                        <td>
                            <form action="SalesDetailServlet" method="get" style="display:inline;">
                                <input type="hidden" name="saleId" value="${row[0]}">
                                <input type="submit" value="編集">
                            </form>
                            <form action="SalesDeleteConfirmServlet" method="get" style="display:inline;">
                                <input type="hidden" name="saleId" value="${row[0]}">
                                <input type="submit" value="削除">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <div style="margin-top:20px;">
        <a href="sales_search_form.jsp">検索条件に戻る</a>
    </div>
</body>
</html>
