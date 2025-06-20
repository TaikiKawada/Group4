<!-- nav.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 fixed-top">
	<div class="container-fluid">
		<a class="navbar-brand"
			href="${ pageContext.request.contextPath }/C0020.html">ダッシュボード</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav me-auto">
				<c:if test="${user.getAuth() == 1 || user.getAuth() == 3}">
					<li class="nav-item"><a class="nav-link"
						href="${ pageContext.request.contextPath }/SalesEntryServlet">売上登録</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/SalesSearchFormServlet">売上検索</a>
				</li>

				<c:if test="${user.getAuth() == 2 || user.getAuth() == 3}">
					<li class="nav-item"><a class="nav-link"
						href="${ pageContext.request.contextPath }/S0030.html">アカウント登録</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href="${ pageContext.request.contextPath }/S0040.html">アカウント検索</a></li>
			</ul>
			<ul class="navbar-nav ms-auto">
				<li class="nav-item d-flex align-items-center"><span
					class="navbar-text text-light me-2">${ user.name }</span></li>
				<li class="nav-item"><a class="nav-link text-danger"
					href="${pageContext.request.contextPath}/C0030.html">ログアウト</a></li>
			</ul>
		</div>
	</div>
</nav>
