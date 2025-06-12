
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
<link rel="stylesheet" href="css/formLabel.css" type="text/css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<div class="mx-auto w-100" style="max-width: 800px;">
			<h2 class="mb-4">アカウント登録</h2>

			<!--登録フォーム-->
			<form method="post"
				action="${pageContext.request.contextPath}/account/entry.html"
				onsubmit="return validateForm()">

				<!--氏名-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-2"> 氏名 </label> <span
								class="badge text-bg-secondary">必須</span>
						</div>
					</div>
					<div class="form-input-col">
						<input type="text" name="name" class="form-control"
							placeholder="氏名" value="${sessionScope.accountData.name }" />
						<div id="error-name" class="text-danger"></div>
					</div>
				</div>

				<!--メールアドレス-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-2">メールアドレス</label> <span
								class="badge text-bg-secondary">必須</span>
						</div>
					</div>
					<div class="form-input-col">
						<input type="email" name="mail" class="form-control"
							placeholder="メールアドレス" value="${sessionScope.accountData.mail }" />
						<div id="error-mail" class="text-danger"></div>
					</div>
				</div>

				<!--パスワード-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-2">パスワード</label> <span
								class="badge text-bg-secondary">必須</span>
						</div>
					</div>
					<div class="form-input-col">
						<input type="password" name="password" class="form-control"
							placeholder="パスワード" />
						<div id="error-password" class="text-danger"></div>
					</div>
				</div>

				<!--パスワード確認-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="col-form-label mb-0 me-2">パスワード確認</label> <span
								class="badge text-bg-secondary">必須</span>
						</div>
					</div>
					<div class="form-input-col">
						<input type="password" name="passConfirm" class="form-control"
							placeholder="パスワード（確認）" />
						<div id="error-confirm" class="text-danger"></div>
					</div>
				</div>

				<!--権限-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-2 d-flex align-items-center">
								権限</label> <span class="badge text-bg-secondary ms-2">必須</span>
						</div>
					</div>
					<div class="form-input-col">
						<div class="checkbox-group">
							<label class="form-check-label"> <input type="checkbox"
								id="authNone" name="auth" value="0"
								<c:if test="${ hasNoneAuth }">checked</c:if> /> 権限なし
							</label> <label class="form-check-label"> <input type="checkbox"
								id="authSales" name="auth" value="1"
								<c:if test="${ hasSalesAuth }">checked</c:if> /> 売上登録
							</label> <label class="form-check-label"> <input type="checkbox"
								id="authAccount" name="auth" value="2"
								<c:if test="${ hasAccountAuth }">checked</c:if> /> アカウント登録
							</label>
						</div>
					</div>
				</div>


				<!--入力項目のチェック-->
				<script>
					// 氏名:空チェック、長さチェック
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

					// メールアドレス:空チェック、長さチェック、形式チェック
					function validateMail() {
						const mail = document
								.querySelector('input[name="mail"]');
						const errorMail = document.getElementById("error-mail");
						errorMail.textContent = "";
						const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

						// 空チェック
						if (mail.value.trim() === "") {
							errorMail.textContent = "メールアドレスを入力してください";
							return false;
						}

						// 長さチェック
						if (getByteLength(mail.value) > 100) {
							errorMail.textContent = "メールアドレスが長すぎます";
							return false;
						}

						// 形式チェック
						if (!emailRegex.test(mail.value.trim())) {
							errorMail.textContent = "メールアドレスを正しく入力して下さい";
							return false;
						}

						return true;
					}

					// パスワード&確認:空チェック、長さチェック、一致チェック、形式チェック
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

						// 入力チェック
						if (password.value.trim() === "") {
							errorPassword.textContent = "パスワードを入力してください";
							valid = false;
						}

						// 入力チェック（確認）
						if (confirm.value.trim() === "") {
							errorConfirm.textContent = "パスワード（確認）を入力してください";
							valid = false;
						}

						// 長さチェック
						if (getByteLength(password.value) > 30) {
							errorPassword.textContent = "パスワードが長すぎます";
							return false;
						}

						// 一致チェック
						if (password.value !== confirm.value) {
							errorConfirm.textContent = "パスワードとパスワード（確認）の入力内容が異なっています";
							valid = false;
						}

						// 形式チェック
						const hasUpper = /[A-Z]/.test(password.value);
						const hasLower = /[a-z]/.test(password.value);
						const hasNumber = /[0-9]/.test(password.value);
						const hasSymbol = /[^A-Za-z0-9]/.test(password.value);

						if (!(hasUpper && hasLower && hasNumber && hasSymbol)) {
							errorPassword.textContent = "パスワードは英大文字・小文字・数字・記号をすべて含めてください";
							return false;
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