<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>売上検索条件入力</title>
</head>
<body>
    <h2>売上検索</h2>

    <form action="SalesSearchServlet" method="get">
        <table>
            <tr>
                <th>販売日（開始）</th>
                <td><input type="date" name="fromDate"></td>
            </tr>
            <tr>
                <th>販売日（終了）</th>
                <td><input type="date" name="toDate"></td>
            </tr>
            <tr>
                <th>担当</th>
                <td>
                    <select name="staff">
                        <option value="">指定なし</option>
                        <!-- 担当一覧を後で動的に出力 -->
                    </select>
                </td>
            </tr>
            <tr>
                <th>商品カテゴリー</th>
                <td>
                    <select name="category">
                        <option value="">指定なし</option>
                        <!-- カテゴリー一覧を後で動的に出力 -->
                    </select>
                </td>
            </tr>
            <tr>
                <th>商品名</th>
                <td><input type="text" name="productName"></td>
            </tr>
        </table>

        <div style="margin-top: 20px;">
            <input type="submit" value="検索">
        </div>
    </form>
</body>
</html>
