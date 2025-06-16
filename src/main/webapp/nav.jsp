<!-- nav.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="DashboardServlet">ダッシュボード</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto">
        <c:if test="${authority == 1 || authority == 3}">
          <li class="nav-item"><a class="nav-link" href="SalesEntryServlet">売上登録</a></li>
        </c:if>
        <li class="nav-item"><a class="nav-link" href="SalesSearchServlet">売上検索</a></li>
        <c:if test="${authority == 2 || authority == 3}">
          <li class="nav-item"><a class="nav-link" href="AccountEntryServlet">アカウント登録</a></li>
        </c:if>
        <li class="nav-item"><a class="nav-link" href="AccountSerchFormServlet">アカウント検索</a></li>
      </ul>
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link btn btn-outline-light px-3 py-1" href="login">ログアウト</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
