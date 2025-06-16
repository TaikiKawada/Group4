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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/formLabel.css"
	type="text/css">
</head>

<body>

	<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5 d-flex justify-content-center">
		<div class="w-50" style="max-width: 600px;">

			<h2 class="mb-4">この内容で登録しますか？</h2>

			<!--登録内容確認フォーム-->
			<form method="post"
				action="${pageContext.request.contextPath}/account/entry/confirm.html">

				<!--氏名-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">氏名</label>
					</div>
					<div class="form-badge-col">
						<span class="badge text-bg-secondary">必須</span>
					</div>
					<div clas="form-input-col">
						<input type="text" name="name" class="form-control"
							value="${ sessionScope.accountData.name }" readonly />
					</div>
				</div>

				<!--メールアドレス-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">メールアドレス</label>
					</div>
					<div class="form-badge-col">
						<span class="badge text-bg-secondary">必須</span>
					</div>
					<div class="form-inpupt-col">
						<input type="email" name="mail" class="form-control"
							value="${ sessionScope.accountData.mail }" readonly />
					</div>
				</div>

				<!--パスワード-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">パスワード</label>
					</div>
					<div class="form-badge-col">
						<span class="badge text-bg-secondary">必須</span>
					</div>
					<div class="form-input-col">
						<input type="password" name="password" class="form-control"
							value="${ sessionScope.accountData.password }" readonly />
					</div>
				</div>

				<!--パスワード確認-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">パスワード確認</label>
					</div>
					<div class="form-badge-col">
						<span class="badge text-bg-secondary">必須</span>
					</div>
					<div class="form-input-col">
						<input type="password" name="pass-confirm" class="form-control"
							value="${ sessionScope.accountData.password }" readonly />
					</div>
				</div>

				<!--権限-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-2 d-flex align-items-center">
								権限 </label>
						</div>
					</div>
					<div class="form-badge-col">
						<span class="badge text-bg-secondary">必須</span>
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
				<div class="text-end mt-4">
					<button type="submit" class="btn btn-primary">登録</button>
					<a href="${pageContext.request.contextPath}/account/entry.html"
						class="btn btn-secondary">キャンセル</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>