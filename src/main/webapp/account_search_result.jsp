<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索結果</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5">
		<h2 class="mb-4">アカウント検索結果表示</h2>

		<jsp:include page="/alert.jsp" />

		<!--検索結果の表示-->
		<table class="table table-striped table-hover align-middle mt-1">
			<thead class="table-light">
				<tr>
					<th scope="col">操作</th>
					<th scope="col">No</th>
					<th scope="col">氏名</th>
					<th scope="col">メールアドレス</th>
					<th scope="col">権限</th>
				</tr>
			</thead>

			<tbody>
				<!--該当アカウントがない時の処理-->
				<c:if test="${empty accountList}">
					<tr>
						<td colspan="5" class="text-center">該当するアカウントが見つかりませんでした。<br />
							<form method="get"
								action="${ pageContext.request.contextPath }/S0040.html"
								class="d-inline-block mt-3">
								<button type="submit" class="btn btn-secondary">検索条件の入力に戻る</button>
							</form>
						</td>
					</tr>
				</c:if>
				<!--検索結果の表示-->
				<c:forEach var="account" items="${ accountList }" varStatus="status">
					<tr>
						<td>
							<div class="d-flex gap-2">
								<form method="get"
									action="${ pageContext.request.contextPath }/S0042.html">
									<input type="hidden" name="account_id"
										value="${ account.account_id }" />
									<c:if test="${ account != null }">
										<c:if test="${ user.auth == 2 || user.auth  == 3 }">
											<button type="submit" class="btn btn-primary btn-sm">
												<i class="bi bi-check-lg"></i>編集
											</button>
										</c:if>
									</c:if>
								</form>
								<form method="get"
									action="${ pageContext.request.contextPath }/S0044.html">
									<input type="hidden" name="account_id"
										value="${ account.account_id }" />
									<button type="submit" class="btn btn-danger btn-sm">
										<i class="bi bi-x"></i>削除
									</button>
								</form>
							</div>
						</td>
						<td>${ status.index + 1 }</td>
						<td>${ account.name }</td>
						<td>${ account.mail }</td>
						<td><c:choose>
								<c:when test="${ account.auth == 0 }">権限なし</c:when>
								<c:when test="${ account.auth == 1 }">売上登録</c:when>
								<c:when test="${ account.auth == 2 }">アカウント登録</c:when>
								<c:when test="${ account.auth == 3 }">売上登録・アカウント登録</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>