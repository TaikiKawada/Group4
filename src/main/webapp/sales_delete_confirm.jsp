<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上削除確認</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/nav.jsp" />

<div class="container mt-5 pt-5 d-flex justify-content-center">
  <div class="w-50" style="max-width: 600px;">
    <h2 class="mb-4 text-start text-danger">売上詳細削除確認</h2>

    <form action="S0025.html" method="post">
      <input type="hidden" name="saleId" value="${sale.saleId}" />

      <!-- 販売日 -->
      <div class="mb-3 row align-items-center">
        <label class="col-sm-4 col-form-label text-end">販売日</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" value="${sale.saleDate}" disabled />
        </div>
      </div>

      <!-- 担当 -->
      <div class="mb-3 row align-items-center">
        <label class="col-sm-4 col-form-label text-end">担当</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" value="${sale.accountName}" disabled />
          <input type="hidden" name="staff" value="${sale.accountId}" />
        </div>
      </div>

      <!-- 商品カテゴリー -->
      <div class="mb-3 row align-items-center">
        <label class="col-sm-4 col-form-label text-end">商品カテゴリー</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" value="${sale.categoryName}" disabled />
          <input type="hidden" name="category" value="${sale.categoryId}" />
        </div>
      </div>

      <!-- 商品名 -->
      <div class="mb-3 row align-items-center">
        <label class="col-sm-4 col-form-label text-end">商品名</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" value="${sale.tradeName}" disabled />
        </div>
      </div>

      <!-- 単価 -->
      <div class="mb-3 row align-items-center">
        <label class="col-sm-4 col-form-label text-end">単価</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" value="${sale.unitPrice} 円" disabled />
        </div>
      </div>

      <!-- 個数 -->
      <div class="mb-3 row align-items-center">
        <label class="col-sm-4 col-form-label text-end">個数</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" value="${sale.saleNumber}" disabled />
        </div>
      </div>

      <!-- 小計 -->
      <div class="mb-3 row align-items-center">
        <label class="col-sm-4 col-form-label text-end">小計</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" value="${sale.unitPrice * sale.saleNumber} 円" disabled />
        </div>
      </div>

      <!-- 備考 -->
      <div class="mb-3 row">
        <label class="col-sm-4 col-form-label text-end">備考</label>
        <div class="col-sm-8">
          <textarea class="form-control" rows="3" disabled>${sale.note}</textarea>
        </div>
      </div>

      <!-- ボタン -->
      <div class="text-center mt-4">
        <button type="submit" class="btn btn-danger me-2">
          <i class="bi bi-x-lg"></i> 削除
        </button>
        <a href="S0022.html?saleId=${sale.saleId}" class="btn btn-secondary">キャンセル</a>
      </div>
    </form>
  </div>
</div>

</body>
</html>
