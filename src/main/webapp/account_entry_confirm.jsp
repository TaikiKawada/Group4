<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

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

			<h2 class="mb-4">この内容で登録しますか？</h2>

			<!--登録内容確認フォーム-->
			<form method="post" action="AccountEntryConfirmServlet">

				<div class="mb-3">
					<label class="form-label">氏名</label> <span
						class="badge text-bg-secondary">必須</span> <input type="text"
						name="name" class="form-control"
						value="${ sessionScope.accountData.name }" />
				</div>

				<div class="mb-3">
					<label class="form-label">メールアドレス</label> <span
						class="badge text-bg-secondary">必須</span> <input type="email"
						name="mail" class="form-control"
						value="${ sessionScope.accountData.mail }" />

				</div>

				<div class="mb-3">
					<label class="form-label">パスワード</label> <span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="password" class="form-control"
						vvalue="${ sessionScope.accountData.password }" />
				</div>

				<div class="mb-3">
					<label class="form-label">パスワード確認</label> <span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="pass-confirm" class="form-control"
						value="${ sessionScope.accountData.password }" />
				</div>

				<div class="mb-3 d-flex align-items-center flex-wrap gap-3">
					<label class="form-label mb-0 me-2 d-flex align-items-center">
						権限 <span class="badge text-bg-secondary ms-2">必須</span>
					</label>
					<div class="d-flex gap-3 flex-wrap">
						<label class="d-flex align-items-center gap-1"> <input
							type="checkbox" name="auth" value="0" /> 権限なし
						</label> <label class="d-flex align-items-center gap-1"> <input
							type="checkbox" name="auth" value="1"
							　${ hasSalesAuth ? "checked" : "" } /> 売上登録
						</label> <label class="d-flex align-items-center gap-1"> <input
							type="checkbox" name="auth" value="2"
							${ hasAccountAuth ? "checked" : "" } /> アカウント登録
						</label>
					</div>
				</div>

				<div class="text-end mt-4">
					<button class="btn btn-primary">登録</button>
					<button class="btn btn-secondary">cancel</button>
				</div>


			</form>
		</div>
	</div>
</body>
</html>