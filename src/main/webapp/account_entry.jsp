
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5 d-flex justify-content-center">
		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4">アカウント登録</h2>

			<!--登録フォーム-->
			<form method="post" action="AccountEntryServlet"
				onsubmit="return validatePasswords()">

				<div class="mb-3">
					<label class="form-label">氏名</label> <span
						class="badge text-bg-secondary">必須</span> <input type="text"
						name="name" class="form-control" placeholder="氏名"
						value="${sessionScope.accountData.name }" />
					<div id="error-name" class="text-danger"></div>
				</div>

				<div class="mb-3">
					<label class="form-label">メールアドレス</label> <span
						class="badge text-bg-secondary">必須</span> <input type="email"
						name="mail" class="form-control" placeholder="メールアドレス"
						value="${sessionScope.accountData.mail }" />
					<div id="error-mail" class="text-danger"></div>
				</div>

				<div class="mb-3">
					<label class="form-label">パスワード</label> <span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="password" class="form-control" placeholder="パスワード" />
					<div id="error-password" class="text-danger"></div>
				</div>

				<div class="mb-3">
					<label class="form-label">パスワード確認</label> <span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="passConfirm" class="form-control" placeholder="パスワード（確認）" />
					<div id="error-confirm" class="text-danger"></div>
				</div>

				<div class="mb-3 d-flex align-items-center flex-wrap gap-3">
					<label class="form-label mb-0 me-2 d-flex align-items-center">
						権限 <span class="badge text-bg-secondary ms-2">必須</span>
					</label>
					<div class="d-flex gap-3 flex-wrap">

						<label class="d-flex align-items-center gap-1"> <input
							type="checkbox" id="authNone" name="auth" value="0"
							<c:if test="${ hasNoneAuth }">checked</c:if> /> 権限なし
						</label> <label class="d-flex align-items-center gap-1"> <input
							type="checkbox" id="authSales" name="auth" value="1"
							<c:if test="${ hasSalesAuth }">checked</c:if> /> 売上登録
						</label> <label class="d-flex align-items-center gap-1"> <input
							type="checkbox" id="authAccount" name="auth" value="2"
							<c:if test="${ hasAccountAuth }">checked</c:if> /> アカウント登録
						</label>
					</div>
				</div>


				<script>
				<!--入力項目のチェック-->
					function validatePasswords() {
						// 入力要素取得
						const name = document
								.querySelector('input[name="name"]');
						const mail = document
								.querySelector('input[name="mail"]');
						const password = document
								.querySelector('input[name="password"]');
						const confirm = document
								.querySelector('input[name="passConfirm"]');

						// エラー表示領域取得
						const errorName = document.getElementById("error-name");
						const errorMail = document.getElementById("error-mail");
						const errorPassword = document
								.getElementById("error-password");
						const errorConfirm = document
								.getElementById("error-confirm");

						// エラーメッセージクリア
						errorName.textContent = "";
						errorMail.textContent = "";
						errorPassword.textContent = "";
						errorConfirm.textContent = "";

						let isValid = true;

						// 必須チェック
						if (name.value.trim() === "") {
							errorName.textContent = "氏名を入力してください";
							isValid = false;
						}
						if (mail.value.trim() === "") {
							errorMail.textContent = "メールアドレスを入力してください";
							isValid = false;
						}
						if (password.value.trim() === "") {
							errorPassword.textContent = "パスワードを入力してください";
							isValid = false;
						}
						if (confirm.value.trim() === "") {
							errorConfirm.textContent = "パスワード確認を入力してください";
							isValid = false;
						}

						// パスワード一致チェック
						if (password.value !== confirm.value) {
							errorConfirm.textContent = "パスワードが一致しません";
							isValid = false;
						}

						return isValid;
					}

					<!--チェックボックスの処理-->
					const authNone = document.getElementById("authNone");
					const authSales = document.getElementById("authSales");
					const authAccount = document.getElementById("authAccount");

					function updateAuthCheckboxes() {
						if (authNone.checked) {
							authSales.checked = false;
							authAccount.checked = false;
							authSales.disabled = true;
							authAccount.disabled = true;
						} else {
							authSales.disabled = false;
							authAccount.disabled = false;
						}

						if (authSales.checked || authAccount.checked) {
							authNone.checked = false;
						}
					}

					authNone.addEventListener("change", updateAuthCheckboxes);
					authSales.addEventListener("change", updateAuthCheckboxes);
					authAccount
							.addEventListener("change", updateAuthCheckboxes);
				</script>

				<div class="text-end mt-4">
					<button class="btn btn-primary">登録</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>