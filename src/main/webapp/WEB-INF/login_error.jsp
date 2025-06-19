<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>システムエラー</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
        }
        h1 {
            font-size: 36px;
            color: #e74c3c;
        }
        p {
            font-size: 18px;
            color: #555;
        }
        .error-code {
            font-size: 48px;
            font-weight: bold;
            color: #e74c3c;
        }
        .suggestions {
            margin-top: 20px;
            font-size: 16px;
            color: #777;
        }
        .suggestions a {
            color: #3498db;
            text-decoration: none;
        }
        .suggestions a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="error-code">500</div>
        <h1>申し訳ありません、システムエラーが発生しました。</h1>
        <p>現在、システムに問題が発生しており、正常に処理を行
