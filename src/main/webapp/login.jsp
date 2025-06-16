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

			<form id="login-form" action="login" method="post"
				class="needs-validate" novalidate>

				<!--メールアドレスを入力-->
				<div class="mb-3">
					<label for="email" class="form-label">メールアドレス</label> <span
						class="badge text-bg-secondary">必須</span> <input type="text"
						name="mail" class="form-control" id="email" placeholder="メールアドレス"
						required> <small>例：user@example.com</small>
					<c:if test="${not empty emailError}">
						<div class="text-danger">${emailError}</div>
					</c:if>
				</div>

				<!--パスワードを入力-->
				<div class="mb-3">
					<label for="password" class="form-label">パスワード</label> <span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="password" class="form-control" id="password"
						placeholder="パスワード" required> <small>8文字以上であり、大文字・数字・小文字・記号を含める必要があります</small>
				</div>
				<!-- パスワード表示切替ボタン -->
				<div>
					<button type="button" id="toggle-password"
						class="btn btn-outline-secondary">表示</button>

					<c:if test="${not empty passwordError}">
						<div class="text-danger">${passwordError}</div>
					</c:if>
				</div>

				<!--ログインボタン-->
				<div class="text-end mb-3">
					<button type="submit" class="btn btn-primary">ログイン</button>
				</div>
			</form>

			<!--アカウントが一致しなかった場合のエラーメッセージを表示-->
			<c:if test="${not empty error}">
				<div class="alert alert-danger mt-3">${error}</div>
			</c:if>


			<script src="${pageContext.request.contextPath}/js/login.js"></script>
		</div>
	</div>

</body>
</html>
