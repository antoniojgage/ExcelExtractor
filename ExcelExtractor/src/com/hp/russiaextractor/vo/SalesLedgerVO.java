package com.hp.russiaextractor.vo;

import java.io.Serializable;
import java.util.Calendar;

public class SalesLedgerVO implements Serializable {

	private static final long serialVersionUID = 7382825154783915791L;
	
	private Integer no;
	private Integer transactionTypeCode;
	private Calendar invoiceDate;
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getTransactionTypeCode() {
		return transactionTypeCode;
	}
	public void setTransactionTypeCode(Integer transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}
	public Calendar getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Calendar invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
}
