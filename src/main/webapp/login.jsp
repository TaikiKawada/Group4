<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h2>物品売上管理システム</h2>
	<form action="LoginServlet" method="post">
		<div class="mb-3">
			<label for="username" class="form-label">メールアドレス</label>
			<input type ="text" class="form-control" id="email" required>
			<small class="form-text text-muted">形式：example@example.com</small>
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">パスワード</label>
			<input type="password" class="form-control" id="password" required>
			<small class="form-text text-muted">英数字6文字以上</small>
		</div>
		
		<script>
    // ログインフォームの送信処理
    document.getElementById('login-form').addEventListener('submit', function(event) {
      event.preventDefault(); // フォーム送信をキャンセル

      // メールアドレスとパスワードを取得
      const email = document.getElementById('email').value;
      const password = document.getElementById('password').value;

      // 簡単な認証チェック（例：固定のメールアドレスとパスワード）
      if (email === '@' && password === 'password') {
        // 認証成功：ダッシュボードページへ遷移
        window.location.href = 'dashboard.jsp';
      } else {
        // 認証失敗
        alert('メールアドレスまたはパスワードが間違っています');
      }
    });
  </script>

		<button type="submit" class="btn btn-primary btn-block">ログイン</button>
	</form>
</body>
</html>