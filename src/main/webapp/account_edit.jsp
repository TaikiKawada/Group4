
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント詳細編集画面</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

	<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5 d-flex justify-content-center">
		<div class="mx-auto w-100" style="max-width: 800px;">
			<h2 class="mb-4">アカウント詳細編集画面</h2>

			<!--登録フォーム-->
			<form method="post"
				action="${pageContext.request.contextPath}/S0042.html"
				onsubmit="return validateForm()">

				<!--account_idの値だけ送信-->
				<input type="hidden" name="account_id"
					value="${ account.account_id }" />

				<!--権限がない時にエラーメッセージを表示-->
				<c:if test="${ not empty error }">
					<div class="alert alert-danger" role="alert">${ error }</div>
				</c:if>

				<!--氏名-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">氏名<span
							class="badge text-bg-secondary">必須</span></label>
					</div>
					<div class="form-input-col">
						<input type="text" name="name" class="form-control"
							placeholder="氏名" value="${ account.name }" />
						<div id="error-name" class="text-danger">
							<c:if test="${ not empty errors.name }">
							${errors.name}
							</c:if>
						</div>
					</div>
				</div>

				<!--メールアドレス-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">メールアドレス<span
							class="badge text-bg-secondary">必須</span></label>
					</div>
					<div class="form-input-col">
						<input type="email" name="mail" class="form-control"
							placeholder="メールアドレス" value="${ account.mail }" />
						<div id="error-mail" class="text-danger">
							<c:if test="${ not empty errors.mail }">
							${errors.mail}
							</c:if>
						</div>
					</div>
				</div>

				<!--パスワード-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">パスワード<span
							class="badge text-bg-secondary">必須</span></label>
					</div>
					<div class="form-input-col">
						<input type="password" name="password" class="form-control"
							placeholder="パスワード" />
						<div id="error-password" class="text-danger">
							<c:if test="${ not empty errors.password }">
							${errors.password}
							</c:if>
						</div>
					</div>
				</div>


				<!--パスワード確認-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">パスワード確認<span
							class="badge text-bg-secondary">必須</span></label>
					</div>
					<div class="form-input-col">
						<input type="password" name="passConfirm" class="form-control"
							placeholder="パスワード（確認）" />
						<div id="error-confirm" class="text-danger">
							<c:if test="${ not empty errors.passConfirm }">
							${errors.passConfirm}
							</c:if>
						</div>
					</div>
				</div>

				<!--権限-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label label-box"> 権限<span
								class="badge text-bg-secondary">必須</span></label>
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

				<div class="text-center mt-4">
					<button class="btn btn-primary">
						<i class="bi bi-check-lg"></i>更新
					</button>
					<a
						href="${pageContext.request.contextPath}/S0041.html"
						class="btn btn-white-custom">キャンセル</a>
				</div>
			</form>
		</div>
	</div>
	<script src="${ pageContext.request.contextPath }/js/validate.js"></script>
</body>
</html>