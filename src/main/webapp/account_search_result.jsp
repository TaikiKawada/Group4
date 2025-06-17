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

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<jsp:include page="/nav.jsp" />

	<div class="container mt-5 pt-5">
		<h2 class="mb-4">アカウント検索結果表示</h2>

		<!--検索結果の表示-->
		<table class="table mt-1">
			<thead>
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
						<td colspan="5" class="text-center">該当するアカウントが見つかりませんでした。</td>
					</tr>
				</c:if>
				<!--検索結果の表示-->
				<c:forEach var="account" items="${ accountList }" varStatus="status">
					<tr>
						<td>
							<div class="d-flex gap-2">
								<form method="get"
									action="${ pageContext.request.contextPath }/account/edit.html">
									<input type="hidden" name="account_id"
										value="${ account.account_id }" />
									<c:if test="${ account != null }"
										<c:if
										test="${ account.authority == 2 || account.authority == 3) ">
										<button type="submit" class="btn btn-primary">編集</button>
									</c:if></c:if>
								</form>
								<form method="get"
									action="${ pageContext.request.contextPath }/account/delete.html">
									<input type="hidden" name="account_id"
										value="${ account.account_id }" />
									<button type="submit" class="btn btn-danger">削除</button>
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