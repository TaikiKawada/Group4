package dto;

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

    // デフォルトコンストラクタ
    public SalesDto() {}

    // コンストラクタ（saleIdなし：登録用）
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

    // コンストラクタ（全フィールド：取得・更新用）
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

    // コンストラクタ（名前も含む：検索結果用）
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
