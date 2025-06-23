<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5 d-flex justify-content-center">
		<div class="w-40" style="max-width: 800px;">

			<h2 class="mb-4 text-center">物品売上管理システム</h2>

			<form id="login-form" action="${pageContext.request.contextPath}/C0010.html" method="post"
				class="needs-validate" novalidate>

				<!--メールアドレスを入力-->
				<div class="mb-3">
					<label for="mail" class="form-label">メールアドレス</label>
					<span 
						class="badge text-bg-secondary">必須</span> <input type="text"
						name="mail" class="form-control" id="mail" placeholder="メールアドレス"
						required value="${param.mail}"> <small>例：user@example.com</small>
						
					<c:if test="${not empty errors.email}">
						<div class="text-danger">${errors.email}</div>
					</c:if>
				</div>

				<!--パスワードを入力-->
				<div class="mb-3">
					<label for="password" class="form-label">パスワード</label>
					<span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="password" class="form-control" id="password"
						placeholder="パスワード" required>
					<small>8文字以上であり、大文字・数字・小文字・記号を含める必要があります</small>
					
					<c:if test="${not empty errors.password}">
						<div class="text-danger">${errors.password}</div>
					</c:if>
				</div>
				
				<!-- パスワード表示切替ボタン -->
				<div>
					<button type="button" id="toggle-password" class="btn btn-outline-secondary">表示</button>
				</div>

				<!--ログインボタン-->
				<div class="text-end mb-3">
					<button type="submit" class="btn btn-primary">ログイン</button>
				</div>
			</form>
			
							<!--アカウントが一致しなかった場合のエラーメッセージを表示-->
					<c:if test="${not empty errors.error}">
						<div class="alert alert-danger mt-3">${errors.error}</div>
					</c:if>


		</div>
	</div>
			<script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>
