package dto;

import jakarta.servlet.http.HttpServletRequest;

public class SalesDto {
    private int saleId;
    private String saleDate;
    private int accountId;
    private int categoryId;
    private String tradeName;
    private int unitPrice;
    private int saleNumber;
    private String note;
    private String accountName;
    private String categoryName;
    private String fromDate;  // 検索開始日
    private String toDate;    // 検索終了日

    // デフォルトコンストラクタ
    public SalesDto() {}

    // 登録用コンストラクタ
    public SalesDto(String saleDate, int accountId, int categoryId,
                    String tradeName, int unitPrice, int saleNumber, String note) {
        this.saleDate = saleDate;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.tradeName = tradeName;
        this.unitPrice = unitPrice;
        this.saleNumber = saleNumber;
        this.note = note;
    }

    // 更新用コンストラクタ
    public SalesDto(int saleId, String saleDate, int accountId, int categoryId,
                    String tradeName, int unitPrice, int saleNumber, String note) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.tradeName = tradeName;
        this.unitPrice = unitPrice;
        this.saleNumber = saleNumber;
        this.note = note;
    }

    // 検索結果用コンストラクタ
    public SalesDto(int saleId, String saleDate, int accountId, int categoryId,
                    String tradeName, int unitPrice, int saleNumber, String note,
                    String accountName, String categoryName) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.tradeName = tradeName;
        this.unitPrice = unitPrice;
        this.saleNumber = saleNumber;
        this.note = note;
        this.accountName = accountName;
        this.categoryName = categoryName;
    }

    // HttpServletRequest から取得するコンストラクタ（フォーム入力系）
    public SalesDto(HttpServletRequest request) {
        this.saleId = parseIntSafe(request.getParameter("saleId"));
        this.saleDate = request.getParameter("salesDate") != null
                      ? request.getParameter("salesDate")
                      : request.getParameter("saleDate");
        this.accountId = parseIntSafe(request.getParameter("staff"));
        this.categoryId = parseIntSafe(request.getParameter("category"));
        this.tradeName = request.getParameter("tradeName");
        this.unitPrice = parseIntSafe(request.getParameter("unitPrice"));
        this.saleNumber = parseIntSafe(request.getParameter("saleNumber"));
        this.note = request.getParameter("note");
        this.fromDate = request.getParameter("fromDate");
        this.toDate = request.getParameter("toDate");
    }

    private int parseIntSafe(String val) {
        try {
            return (val != null && !val.isEmpty()) ? Integer.parseInt(val) : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    // getter / setter
    public int getSaleId() { return saleId; }
    public void setSaleId(int saleId) { this.saleId = saleId; }

    public String getSaleDate() { return saleDate; }
    public void setSaleDate(String saleDate) { this.saleDate = saleDate; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getTradeName() { return tradeName; }
    public void setTradeName(String tradeName) { this.tradeName = tradeName; }

    public int getUnitPrice() { return unitPrice; }
    public void setUnitPrice(int unitPrice) { this.unitPrice = unitPrice; }

    public int getSaleNumber() { return saleNumber; }
    public void setSaleNumber(int saleNumber) { this.saleNumber = saleNumber; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }

    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }
}
