<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>売上登録確認</title>
</head>
<body>
    <h2>売上登録内容の確認</h2>

    <table border="1" cellpadding="5">
        <tr>
            <th>販売日</th>
            <td>${salesDate}</td>
        </tr>
        <tr>
            <th>担当</th>
            <td>${staff}</td>
        </tr>
        <tr>
            <th>商品カテゴリー</th>
            <td>${category}</td>
        </tr>
        <tr>
            <th>商品名</th>
            <td>${productName}</td>
        </tr>
        <tr>
            <th>単価</th>
            <td>${unitPrice} 円</td>
        </tr>
        <tr>
            <th>個数</th>
            <td>${quantity}</td>
        </tr>
        <tr>
            <th>備考</th>
            <td>${remarks}</td>
        </tr>
    </table>

    <form action="SalesRegisterServlet" method="post">
        <!-- hiddenでデータを保持 -->
        <input type="hidden" name="salesDate" value="${salesDate}">
        <input type="hidden" name="staff" value="${staff}">
        <input type="hidden" name="category" value="${category}">
        <input type="hidden" name="productName" value="${productName}">
        <input type="hidden" name="unitPrice" value="${unitPrice}">
        <input type="hidden" name="quantity" value="${quantity}">
        <input type="hidden" name="remarks" value="${remarks}">

        <div style="margin-top:20px;">
            <input type="submit" value="この内容で登録">
        </div>
    </form>

    <form action="sales_entry.jsp" method="post" style="margin-top:10px;">
        <input type="submit" value="修正する">
    </form>
</body>
</html>
