
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
<link rel="stylesheet" href="css/dashboard.css" type="text/css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container py-4">
		<h2 class="text-center mb-4">ダッシュボード</h2>

		<!-- 上部ボタンバー -->
		<div class="header-buttons">
			<!-- 左側ボタン -->
			<div class="left-buttons">
				<form action="SalesEntryServlet" method="get">
					<button class="btn custom-btn" type="submit">売上登録</button>
				</form>
				<form action="SalesSearchFormServlet" method="get">
					<button class="btn custom-btn" type="submit">売上検索</button>
				</form>
				<form action="AccountEntryServlet" method="get">
					<button class="btn custom-btn" type="submit">アカウント登録</button>
				</form>
				<form action="AccountSearchFormServlet" method="get">
					<button class="btn custom-btn" type="submit">アカウント検索</button>
				</form>
			</div>
			<!-- 右側（ログアウト） -->
			<div class="right-button">
				<form action="LoginServlet" method="get">
					<button class="btn custom-btn" type="submit">ログアウト</button>
				</form>
			</div>
		</div>
	</div>
	<div class="container mt-5 d-flex justify-content-center">

		<div class="w-50" style="max-width: 600px;">
			<h2 class="mb-4">アカウント登録</h2>

			<!--登録フォーム-->
			<form method="post" action="AccountEntryServlet"
				onsubmit="return validateForm()">

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


				<!--入力項目のチェック-->
				<script>
					//氏名:空チェック、長さチェック
					function validateName() {
						const name = document
								.querySelector('input[name="name"]');
						const errorName = document.getElementById("error-name");
						errorName.textContent = "";

						if (name.value.trim() === "") {
							errorName.textContent = "氏名を入力してください";
							return false;
						}

						if (getByteLength(name.value) > 20) {
							errorName.textContent = "氏名は20バイト以内で入力してください";
							return false;
						}

						return true;
					}

					//メールアドレス:空チェック、長さチェック
					function validateMail() {
						const mail = document
								.querySelector('input[name="mail"]');
						const errorMail = document.getElementById("error-mail");
						errorMail.textContent = "";

						if (mail.value.trim() === "") {
							errorMail.textContent = "メールアドレスを入力してください";
							return false;
						}

						if (getByteLength(mail.value) > 100) {
							errorMail.textContent = "メールアドレスが長すぎます";
							return false;
						}

						return true;
					}

					//パスワード&確認:空チェック、長さチェック、一致チェック
					function validatePasswords() {
						const password = document
								.querySelector('input[name="password"]');
						const confirm = document
								.querySelector('input[name="passConfirm"]');
						const errorPassword = document
								.getElementById("error-password");
						const errorConfirm = document
								.getElementById("error-confirm");

						errorPassword.textContent = "";
						errorConfirm.textContent = "";

						let valid = true;

						if (password.value.trim() === "") {
							errorPassword.textContent = "パスワードを入力してください";
							valid = false;
						}

						if (confirm.value.trim() === "") {
							errorConfirm.textContent = "パスワード（確認）を入力してください";
							valid = false;
						}

						if (getByteLength(password.value) > 30) {
							errorPassword.textContent = "パスワードが長すぎます";
							return false;
						}

						if (password.value !== confirm.value) {
							errorConfirm.textContent = "パスワードとパスワード（確認）の入力内容が異なっています";
							valid = false;
						}

						return valid;
					}

					// 全体バリデーション
					function validateForm() {
						const isNameValid = validateName();
						const isMailValid = validateMail();
						const isPasswordValid = validatePasswords();

						return isNameValid && isMailValid && isPasswordValid;
					}

					// バイト数取得関数（全角2バイト、半角1バイト）
					function getByteLength(str) {
						let byteLength = 0;
						for (let i = 0; i < str.length; i++) {
							const charCode = str.charCodeAt(i);
							byteLength += (charCode > 0x7f) ? 2 : 1;
						}
						return byteLength;
					}

					// チェックボックスの処理
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