<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f8f8f8;
            padding: 40px;
        }
        .error-container {
            max-width: 600px;
            margin: auto;
            padding: 30px;
            background-color: white;
            border: 1px solid #ccc;
            text-align: center;
        }
        h1 {
            color: #cc0000;
        }
        a {
            color: #007BFF;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>システムエラーが発生しました</h1>
        <p>申し訳ありません。処理中に予期しないエラーが発生しました。</p>

        <p>
            <strong>
                <%= request.getAttribute("error") != null ? request.getAttribute("error") : "詳細は管理者にお問い合わせください。" %>
            </strong>
        </p>

        <p><a href="<%= request.getContextPath() %>/login">ログイン画面へ戻る</a></p>
    </div>
</body>
</html>
