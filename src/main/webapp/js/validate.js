//入力項目のチェック

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
document.addEventListener("DOMContentLoaded", function() {
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
	
	updateAuthCheckboxes();

	authNone.addEventListener("change", updateAuthCheckboxes);
	authSales.addEventListener("change", updateAuthCheckboxes);
	authAccount.addEventListener("change", updateAuthCheckboxes);
});