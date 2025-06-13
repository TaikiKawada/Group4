
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/formLabel.css"
	type="text/css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<div class="mx-auto w-100" style="max-width: 800px;">
			<h2 class="mb-4">アカウント検索条件入力</h2>

			<!--登録フォーム-->
			<form method="post"
				action="${pageContext.request.contextPath}/account/search.html"
				onsubmit="return validateForm()">

				<!--氏名-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-1">氏名</label> <span
								class="badge text-bg-secondary">部分一致</span> </span>
						</div>
					</div>
					<div class="form-input-col">
						<input type="text" name="name" class="form-control"
							placeholder="氏名" />
						<div id="error-name" class="text-danger"></div>
					</div>
				</div>

				<!--メールアドレス-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-1">メールアドレス</label>
						</div>
					</div>
					<div class="form-input-col">
						<input type="text" name="mail" class="form-control"
							placeholder="メールアドレス" />
						<div id="error-mail" class="text-danger"></div>
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
								id="authNone" name="auth" value="0" /> 権限なし
							</label> <label class="form-check-label"> <input type="checkbox"
								id="authSales" name="auth" value="1" /> 売上登録
							</label> <label class="form-check-label"> <input type="checkbox"
								id="authAccount" name="auth" value="2" /> アカウント登録
							</label>
						</div>
					</div>
				</div>


				<!--入力項目のチェック-->
				<script>
					// 氏名:長さチェック
					function validateName() {
						const name = document
								.querySelector('input[name="name"]');
						const errorName = document.getElementById("error-name");
						errorName.textContent = "";

						if (getByteLength(name.value) > 20) {
							errorName.textContent = "氏名は20バイト以内で入力してください";
							return false;
						}

						return true;
					}

					// メールアドレス:長さチェック
					function validateMail() {
						const mail = document
								.querySelector('input[name="mail"]');
						const errorMail = document.getElementById("error-mail");
						errorMail.textContent = "";

						// 長さチェック
						if (getByteLength(mail.value) > 100) {
							errorMail.textContent = "メールアドレスが長すぎます";
							return false;
						}

						return true;
					}

					// 全体バリデーション
					function validateForm() {
						const isNameValid = validateName();
						const isMailValid = validateMail();

						return isNameValid && isMailValid;
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

					// クリアボタンの処理
					function clearForm() {
						// 入力値を空にする
						document.querySelector('input[name="name"]').value = "";
						document.querySelector('input[name="mail"]').value = "";

						// エラーメッセージもクリア
						document.getElementById("error-name").textContent = "";
						document.getElementById("error-mail").textContent = "";

						// チェックボックスをすべて外す
						authNone.checked = false;
						authSales.checked = false;
						authAccount.checked = false;

						// 無効になってるチェックボックスも有効に戻す
						authSales.disabled = false;
						authAccount.disabled = false;
					}
				</script>

				<div class="text-end mt-4">
					<button type="submit" class="btn btn-primary">検索</button>
					<button type="button" class="btn btn-secondary ms-2"
						onclick="clearForm()">クリア</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>