<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5 d-flex justify-content-center">
		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4 text-center">物品売上管理システム</h2>

			<form id="login-form" action="LoginServlet" method="post">
				<div class="mb-3">
					<label for="email" class="form-label">メールアドレス</label>
					<span class="badge text-bg-secondary">必須</span>
					<input type="text" name="email" class="form-control" id="email" required>
				</div>

				<div class="mb-3">
					<label for="password" class="form-label">パスワード</label>
					<span class="badge text-bg-secondary">必須</span>
					<input type="password" name="password" class="form-control" id="password" required>
				</div>

				<div class="text-end mt-4">
					<button type="submit" class="btn btn-primary">ログイン</button>
				</div>
			</form>
		</div>
	</div>

	<script>
		// ログインフォームの送信処理
		document.getElementById('login-form').addEventListener('submit', function(event) {
			event.preventDefault(); // フォーム送信をキャンセル

			const email = document.getElementById('email').value;
			const password = document.getElementById('password').value;

			// 簡易認証チェック
			if (email === '@' && password === 'password') {
				window.location.href = 'dashboard.jsp';
			} else {
				alert('メールアドレスまたはパスワードが間違っています');
			}
		});
	</script>
</body>
</html>
