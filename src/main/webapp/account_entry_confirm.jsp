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

			<h2 class="mb-4">この内容で登録しますか？</h2>

			<!--登録内容確認フォーム-->
			<form method="post"
				action="${pageContext.request.contextPath}/S0031.html">

				<!--氏名-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">氏名<span
							class="badge text-bg-secondary">必須</span></label>
					</div>
					<div class="form-input-col">
						<input type="text" name="name" class="form-control"
							value="${ sessionScope.accountData.name }" disabled />
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
							value="${ sessionScope.accountData.mail }" disabled />
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
							value="${ sessionScope.accountData.password }" disabled />
					</div>
				</div>

				<!--パスワード確認-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">パスワード確認<span
							class="badge text-bg-secondary">必須</span></label>
					</div>
					<div class="form-input-col">
						<input type="password" name="pass-confirm" class="form-control"
							value="${ sessionScope.accountData.password }" disabled />
					</div>
				</div>

				<!--権限-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">権限<span
							class="badge text-bg-secondary">必須</span>
						</label>
					</div>
					<div class="form-input-col">
						<div class="checkbox-group">
							<!--チェックボックスの表示-->
							<label class="form-check-label"> <input type="checkbox"
								name="auth" value="0" disabled
								<c:if test="${ hasNoneAuth }">checked</c:if> /> 権限なし
							</label> <label class="form-check-label"> <input type="checkbox"
								name="auth" value="1" disabled
								<c:if test="${ hasSalesAuth }">checked</c:if> /> 売上登録
							</label> <label class="form-check-label"> <input type="checkbox"
								name="auth" value="2" disabled
								<c:if test="${ hasAccountAuth }">checked</c:if> /> アカウント登録
							</label>
						</div>
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

				<!--ボタン-->
				<div class="text-center mt-4">
					<button type="submit" class="btn btn-primary">OK</button>
					<a href="${pageContext.request.contextPath}/S0030.html?from=confirm"
						class="btn btn-white-custom">キャンセル</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>