<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>売上登録</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

  <jsp:include page="/nav.jsp" />

  <div class="container mt-5 pt-5 d-flex justify-content-center">
    <div class="w-50" style="max-width: 600px;">
      <h2 class="mb-4 text-start">売上登録</h2>

      <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
      </c:if>

      <form action="S0011.html" method="post">

        <!-- 販売日 -->
        <div class="mb-3 row align-items-center">
          <label class="col-sm-4 col-form-label text-end">販売日 <span class="badge bg-secondary">必須</span></label>
          <div class="col-sm-8">
            <input type="date" name="salesDate" class="form-control" required />
          </div>
        </div>

        <!-- 担当 -->
        <div class="mb-3 row align-items-center">
          <label class="col-sm-4 col-form-label text-end">担当 <span class="badge bg-secondary">必須</span></label>
          <div class="col-sm-8">
            <select name="staff" class="form-select" required>
              <option value="">選択してください</option>
              <c:forEach var="staff" items="${staffList}">
                <option value="${staff.id}">${staff.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <!-- カテゴリー -->
        <div class="mb-3 row align-items-center">
          <label class="col-sm-4 col-form-label text-end">商品カテゴリー <span class="badge bg-secondary">必須</span></label>
          <div class="col-sm-8">
            <select name="category" class="form-select" required>
              <option value="">選択してください</option>
              <c:forEach var="cat" items="${categoryList}">
                <option value="${cat.id}">${cat.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <!-- 商品名 -->
        <div class="mb-3 row align-items-center">
          <label class="col-sm-4 col-form-label text-end">商品名 <span class="badge bg-secondary">必須</span></label>
          <div class="col-sm-8">
            <input type="text" name="tradeName" class="form-control" required />
          </div>
        </div>

        <!-- 単価 -->
        <div class="mb-3 row align-items-center">
          <label class="col-sm-4 col-form-label text-end">単価 <span class="badge bg-secondary">必須</span></label>
          <div class="col-sm-8">
            <input type="number" name="unitPrice" class="form-control" required />
          </div>
        </div>

        <!-- 個数 -->
        <div class="mb-3 row align-items-center">
          <label class="col-sm-4 col-form-label text-end">個数 <span class="badge bg-secondary">必須</span></label>
          <div class="col-sm-8">
            <input type="number" name="saleNumber" class="form-control" required />
          </div>
        </div>

        <!-- 備考 -->
        <div class="mb-3 row">
          <label class="col-sm-4 col-form-label text-end">備考</label>
          <div class="col-sm-8">
            <textarea name="note" class="form-control" rows="3"></textarea>
          </div>
        </div>

        <div class="text-center mt-4">
          <button type="submit" class="btn btn-primary me-2">
            <i class="bi bi-check-lg"></i> 登録
          </button>
          
        </div>

      </form>
    </div>
  </div>
</body>
</html>
