<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>


<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録画面</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
	<h1>アカウント登録</h1>

	<label class="form-label">氏名</label>
	<input type="text" name="name" class="form-control">

	<label class="form-label">メールアドレス</label>
	<input type="email" name="mail" class="form-control">

	<label class="form-label">パスワード</label>
	<input type="password" name="pass" class="form-control">

	<label class="form-label">パスワード確認</label>
	<input type="password" name="pass-confirm" class="form-control">



</body>
</html>