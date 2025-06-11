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

			<h2 class="mb-4">この内容で登録しますか？</h2>

			<!--登録内容確認フォーム-->
			<form method="post" action="AccountEntryConfirmServlet">

				<!--氏名-->
				<div class="mb-3">
					<label class="form-label">氏名</label> <span
						class="badge text-bg-secondary">必須</span> <input type="text"
						name="name" class="form-control"
						value="${ sessionScope.accountData.name }" readonly />
				</div>

				<!--メールアドレス-->
				<div class="mb-3">
					<label class="form-label">メールアドレス</label> <span
						class="badge text-bg-secondary">必須</span> <input type="email"
						name="mail" class="form-control"
						value="${ sessionScope.accountData.mail }" readonly />
				</div>

				<!--パスワード-->
				<div class="mb-3">
					<label class="form-label">パスワード</label> <span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="password" class="form-control"
						value="${ sessionScope.accountData.password }" readonly />
				</div>

				<!--パスワード確認-->
				<div class="mb-3">
					<label class="form-label">パスワード確認</label> <span
						class="badge text-bg-secondary">必須</span> <input type="password"
						name="pass-confirm" class="form-control"
						value="${ sessionScope.accountData.password }" readonly />
				</div>

				<!--権限-->
				<div class="mb-3 d-flex align-items-center flex-wrap gap-3">
					<label class="form-label mb-0 me-2 d-flex align-items-center">
						権限 <span class="badge text-bg-secondary ms-2">必須</span>
					</label>
					<div class="d-flex gap-3 flex-wrap">

						<!--チェックボックスの表示-->
						<label class="d-flex align-items-center gap-1"> <input
							type="checkbox" name="auth" value="0" disabled
							<c:if test="${ hasNoneAuth }">checked</c:if> /> 権限なし
						</label> <label class="d-flex align-items-center gap-1"> <input
							type="checkbox" name="auth" value="1" disabled
							<c:if test="${ hasSalesAuth }">checked</c:if> /> 売上登録
						</label> <label class="d-flex align-items-center gap-1"> <input
							type="checkbox" name="auth" value="2" disabled
							<c:if test="${ hasAccountAuth }">checked</c:if> /> アカウント登録
						</label>
					</div>
				</div>

				<!--実際の値を送信-->
				<c:if test="${hasNoneAuth}">
					<input type="hidden" name="auth" value="0" />
				</c:if>
				<c:if test="${hasSalesAuth}">
					<input type="hidden" name="auth" value="1" />
				</c:if>
				<c:if test="${hasAccountAuth}">
					<input type="hidden" name="auth" value="2" />
				</c:if>

				<div class="text-end mt-4">
					<button type="submit" class="btn btn-primary">登録</button>
					<a href="AccountEntryServlet" class="btn btn-secondary">キャンセル</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>