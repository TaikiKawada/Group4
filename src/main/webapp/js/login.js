//パスワードの表示・非表示の設定

// public/js/script.js

document.addEventListener('DOMContentLoaded', function () {

  const toggleBtn = document.getElementById('toggle-password');

  const passwordField = document.getElementById('password');
 
  if (toggleBtn && passwordField) {

    toggleBtn.addEventListener('click', function () {

      const isPassword = passwordField.type === 'password';

      passwordField.type = isPassword ? 'text' : 'password';

      this.textContent = isPassword ? '非表示' : '表示';

    });

  }

});
 
