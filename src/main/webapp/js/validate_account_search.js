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