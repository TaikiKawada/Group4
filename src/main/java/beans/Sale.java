package beans;

import java.io.Serializable;
import java.sql.Date;

//DBからのデータをまとめるクラス
public class Sale implements Serializable {//Serializableを使わないとセッションで例外が出る
	private int saleId;
	private Date saleDate;
	private int accountId;
	private int categoryId;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;
	
	//setter・getter
	public Date getSaleDate() {
		return saleDate;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
