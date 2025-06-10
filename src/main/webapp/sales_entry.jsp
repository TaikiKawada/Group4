<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>売上登録</title>
</head>
<body>
    <h2>売上登録</h2>

    <form action="SalesEntryServlet" method="post">
        <table>
            <tr>
                <th>販売日<span style="color:red;">※</span></th>
                <td><input type="date" name="salesDate" required></td>
            </tr>
            <tr>
                <th>担当<span style="color:red;">※</span></th>
                <td>
                    <select name="staff" required>
                        <option value="">選択してください</option>
                        <!-- 担当一覧は後で動的に出力 -->
                    </select>
                </td>
            </tr>
            <tr>
                <th>商品カテゴリー<span style="color:red;">※</span></th>
                <td>
                    <select name="category" required>
                        <option value="">選択してください</option>
                        <!-- カテゴリー一覧は後で動的に出力 -->
                    </select>
                </td>
            </tr>
            <tr>
                <th>商品名<span style="color:red;">※</span></th>
                <td><input type="text" name="productName" required></td>
            </tr>
            <tr>
                <th>単価<span style="color:red;">※</span></th>
                <td><input type="number" name="unitPrice" required></td>
            </tr>
            <tr>
                <th>個数<span style="color:red;">※</span></th>
                <td><input type="number" name="quantity" required></td>
            </tr>
            <tr>
                <th>備考</th>
                <td><textarea name="remarks" rows="3" cols="40"></textarea></td>
            </tr>
        </table>

        <div style="text-align:center; margin-top:20px;">
            <input type="submit" value="登録">
        </div>
    </form>
</body>
</html>
