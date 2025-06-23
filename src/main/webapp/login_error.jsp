<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>システムエラー</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f6f9;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.error-container {
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	padding: 40px;
	max-width: 600px;
	width: 100%;
	text-align: center;
}

.error-container h1 {
	color: #e74c3c;
	font-size: 2.5rem;
	margin-bottom: 20px;
}

.error-container p {
	color: #555;
	font-size: 1.1rem;
	margin-bottom: 30px;
}

.error-detail {
	background-color: #f9e0e0;
	color: #c0392b;
	padding: 15px;
	border-radius: 6px;
	margin-bottom: 30px;
	font-weight: 600;
}

.btn {
	display: inline-block;
	padding: 12px 30px;
	background-color: #3498db;
	color: white;
	text-decoration: none;
	font-weight: 600;
	border-radius: 6px;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #2980b9;
}
</style>
</head>
<body>
	<div class="error-container">
		<h1>システムエラーが発生しました</h1>
		<p>申し訳ありませんが、現在システムに問題が発生しています。しばらくしてから再度お試しください。</p>
		<a href="login.jsp" class="btn">ログイン画面に戻る</a>
	</div>
</body>
</html>
