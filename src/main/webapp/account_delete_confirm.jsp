<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント詳細削除確認</title>
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

	<!-- ナビゲーションバー -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="Dashboard">ダッシュボード</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto">
					<!-- 売上登録：権限 1 または 3 -->
					<c:if test="${authority == 1 || authority == 3}">
						<li class="nav-item"><a class="nav-link"
							href="SalesEntryServlet">売上登録</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link"
						href="SalesSearchServlet">売上検索</a></li>

					<!-- アカウント登録：権限 2 または 3 -->
					<c:if test="${authority == 2 || authority == 3}">
						<li class="nav-item"><a class="nav-link"
							href="account/entry.html">アカウント登録</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link"
						href="account/search.html">アカウント検索</a></li>
				</ul>
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a
						class="nav-link btn btn-outline-light px-3 py-1" href="login">ログアウト</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-5 pt-5 d-flex justify-content-center">
		<div class="mx-auto w-100" style="max-width: 800px;">
			<h2 class="mb-4">アカウント詳細削除確認</h2>

			<!--登録フォーム-->
			<form method="post"
				action="${pageContext.request.contextPath}/account/delete.html"
				onsubmit="return validateForm()">

				<!--削除できなかったらエラーメッセージを表示-->
				<c:if test="${ not empty error }">
					<div class="alert alert-danger" role="alert">${ error }</div>
				</c:if>

				<!--account_idの値だけ送信-->
				<input type="hidden" name="account_id"
					value="${ account.account_id }" />

				<!--氏名-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">氏名</label>
					</div>
					<div class="form-input-col">
						<input type="text" name="name" class="form-control"
							placeholder="氏名" value="${ account.name }" readonly />
						<div id="error-name" class="text-danger"></div>
					</div>
				</div>

				<!--メールアドレス-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">メールアドレス</label>
					</div>
					<div class="form-input-col">
						<input type="email" name="mail" class="form-control"
							placeholder="メールアドレス" value="${ account.mail }" readonly />
						<div id="error-mail" class="text-danger"></div>
					</div>
				</div>

				<!--パスワード-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">パスワード</label>
					</div>
					<div class="form-input-col">
						<input type="password" name="password" class="form-control"
							placeholder="パスワード" value="${ account.password }" readonly />
						<div id="error-password" class="text-danger"></div>
					</div>
				</div>


				<!--パスワード確認-->
				<div class="form-row">
					<div class="form-label-col">
						<label class="form-label label-box">パスワード（確認）</label>
					</div>
					<div class="form-input-col">
						<input type="password" name="passConfirm" class="form-control"
							placeholder="パスワード（確認）" value="${ account.password }" readonly />
						<div id="error-confirm" class="text-danger"></div>
					</div>
				</div>

				<!--権限-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-2 d-flex align-items-center">
								権限</label>
						</div>
					</div>
					<div class="form-input-col">
						<div class="checkbox-group">
							<!--チェックボックスの表示-->
							<label class="form-check-label"> <input type="checkbox"
								id="authNone" name="auth" value="0" 　disabled
								<c:if test="${ hasNoneAuth }">checked</c:if> /> 権限なし
							</label> <label class="form-check-label"> <input type="checkbox"
								id="authSales" name="auth" value="1" disabled
								<c:if test="${ hasSalesAuth }">checked</c:if> /> 売上登録
							</label> <label class="form-check-label"> <input type="checkbox"
								id="authAccount" name="auth" value="2" disabled
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
					<button class="btn btn-primary">削除</button>
					<a
						href="${ pageContext.request.contextPath }/account/search/result.html"
						class="btn btn-secondary">キャンセル</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>