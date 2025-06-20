
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
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="${ pageContext.request.contextPath }/js/validate_account_search.js"></script>
</head>
<body>

	<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5 d-flex justify-content-center">
		<div class="mx-auto w-100" style="max-width: 800px;">
			<h2 class="mb-4">アカウント検索条件入力</h2>

			<!--登録フォーム-->
			<form method="post"
				action="${pageContext.request.contextPath}/S0040.html"
				onsubmit="return validateForm()">

				<!--氏名-->
				<div class="form-row">
					<div class="form-label-col">
						<div class="label-box">
							<label class="form-label mb-0 me-1">氏名</label> <span
								class="badge bg-secondary">部分一致</span> </span>
						</div>
					</div>
					<div class="form-input-col">
						<input type="text" name="name" class="form-control"
							placeholder="氏名" />
						<div id="error-name" class="text-danger">
							<c:if test="${ not empty errors.name }">
						${ errors.name }
						</c:if>
						</div>
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
						<div id="error-mail" class="text-danger">
							<c:if test="${ not empty errors.email }">
						${ errors.email }
						</c:if>
						</div>
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

				<div class="text-end mt-4">
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-search"></i> 検索
					</button>
					<button type="button" class="btn btn-white-custom"
						onclick="clearForm()">クリア</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>