<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>売上編集</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>

<jsp:include page="/nav.jsp" />

<div class="container mt-5 pt-5 d-flex justify-content-center">
  <div class="mx-auto w-100" style="max-width: 800px;">
    <h2 class="mb-4">売上編集</h2>

    <form method="post" action="${pageContext.request.contextPath}/S0024.html">

      <input type="hidden" name="saleId" value="${sale.saleId}" />

      <!-- 販売日 -->
      <div class="form-row">
        <div class="form-label-col">
          <label class="form-label label-box">販売日 <span class="badge text-bg-secondary">必須</span></label>
        </div>
        <div class="form-input-col">
          <input type="date" name="saleDate" class="form-control" value="${sale.saleDate}" />
          <div id="error-saleDate" class="text-danger">
            <c:if test="${not empty errors['saleDate']}">
              ${errors.saleDate}
            </c:if>
          </div>
        </div>
      </div>

      <!-- 担当 -->
      <div class="form-row">
        <div class="form-label-col">
          <label class="form-label label-box">担当 <span class="badge text-bg-secondary">必須</span></label>
        </div>
        <div class="form-input-col">
          <select name="staff" class="form-select">
            <option value="">選択してください</option>
            <c:forEach var="staffItem" items="${staffList}">
              <option value="${staffItem.id}" <c:if test="${staffItem.id == sale.accountId}">selected</c:if>>
                ${staffItem.name}
              </option>
            </c:forEach>
          </select>
          <div id="error-staff" class="text-danger">
            <c:if test="${not empty errors['staff']}">
              ${errors.staff}
            </c:if>
          </div>
        </div>
      </div>

      <!-- カテゴリー -->
      <div class="form-row">
        <div class="form-label-col">
          <label class="form-label label-box">商品カテゴリー <span class="badge text-bg-secondary">必須</span></label>
        </div>
        <div class="form-input-col">
          <select name="category" class="form-select">
            <option value="">選択してください</option>
            <c:forEach var="cat" items="${categoryList}">
              <option value="${cat.id}" <c:if test="${cat.id == sale.categoryId}">selected</c:if>>
                ${cat.name}
              </option>
            </c:forEach>
          </select>
          <div id="error-category" class="text-danger">
            <c:if test="${not empty errors['category']}">
              ${errors.category}
            </c:if>
          </div>
        </div>
      </div>

      <!-- 商品名 -->
      <div class="form-row">
        <div class="form-label-col">
          <label class="form-label label-box">商品名 <span class="badge text-bg-secondary">必須</span></label>
        </div>
        <div class="form-input-col">
          <input type="text" name="tradeName" class="form-control" value="${sale.tradeName}" />
          <div id="error-tradeName" class="text-danger">
            <c:if test="${not empty errors['tradeName']}">
              ${errors.tradeName}
            </c:if>
          </div>
        </div>
      </div>

      <!-- 単価 -->
      <div class="form-row">
        <div class="form-label-col">
          <label class="form-label label-box">単価 <span class="badge text-bg-secondary">必須</span></label>
        </div>
        <div class="form-input-col">
          <input type="number" name="unitPrice" class="form-control" value="${sale.unitPrice}" />
          <div id="error-unitPrice" class="text-danger">
            <c:if test="${not empty errors['unitPrice']}">
              ${errors.unitPrice}
            </c:if>
          </div>
        </div>
      </div>

      <!-- 個数 -->
      <div class="form-row">
        <div class="form-label-col">
          <label class="form-label label-box">個数 <span class="badge text-bg-secondary">必須</span></label>
        </div>
        <div class="form-input-col">
          <input type="number" name="saleNumber" class="form-control" value="${sale.saleNumber}" />
          <div id="error-saleNumber" class="text-danger">
            <c:if test="${not empty errors['saleNumber']}">
              ${errors.saleNumber}
            </c:if>
          </div>
        </div>
      </div>

      <!-- 備考 -->
      <div class="form-row">
        <div class="form-label-col">
          <label class="form-label label-box">備考</label>
        </div>
        <div class="form-input-col">
          <textarea name="note" class="form-control" rows="3">${sale.note}</textarea>
        </div>
      </div>

      <!-- ボタン -->
      <div class="text-end mt-4">
        <button type="submit" class="btn btn-primary">
          <i class="bi bi-check-lg"></i> 更新
        </button>
        <a href="javascript:history.back()" class="btn btn-outline-secondary">キャンセル</a>
      </div>

    </form>
  </div>
</div>

</body>
</html>
