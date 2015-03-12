package com.hp.russiaextractor.vo;
	
	import java.io.Serializable;
	import java.util.Calendar;

	public class PurchaseLedgerVO implements Serializable {

		private static final long serialVersionUID = 7382825154783915791L;
		
		//Purchase Ledger Variables 
		private Integer no; //1
		private Integer transactionTypeCode; //2
		private Calendar invoiceDate; //3
		private Integer sellersInvoice; //4
		private Integer sellersAdjustmentAmount; //5
		private Calendar dateOfSellersAdjustment; //6
		private Integer sellersCorrectiveInvoiceNo; //7
		private Calendar dateOfCorrectiveSellersInvoice; //8
		private Integer adjustiveSellersCorrectiveInvoiceNo; //9
		private Calendar dateOfAdjustedSellersCorrectiveInvoice; //10
		private Integer numberOfPaymentConfirmationDocument; //11
		private Calendar dateOfPaymentConfirmationDocument; //12
		private Calendar dateOfRecording; //13
		private char nameOfSeller; //14
		private Integer tinOfSeller; //15
		private Integer crrOfSeller; //16
		private char nameOfIntermediary; //17
		private Integer tinOfIntermediary; //18
		private Integer crrofIntermediary; //19
		private Integer numberOfCustomsDeclaration; //20
		private Integer currencyCodePerOKV; //21
		private Integer valueOfPurchasesVAT; //22
		private Integer differenceInValueVatToCorrectiveInvoice; //23
		private Integer amountOfDeductibleVat; //24
		private Integer differenceInVatAccordingToCorrectiveInvoice; //25
		private char copiedLine; //26
		
		
		
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
		public Integer getSellersInvoice() {
			return sellersInvoice;
		}
		public void setSellersInvoice(Integer sellersInvoice) {
			this.sellersInvoice = sellersInvoice;
		}
		public Integer getSellersAdjustmentAmount() {
			return sellersAdjustmentAmount;
		}
		public void setSellersAdjustmentAmount(Integer sellersAdjustmentAmount) {
			this.sellersAdjustmentAmount = sellersAdjustmentAmount;
		}
		public Calendar getDateOfSellersAdjustment() {
			return dateOfSellersAdjustment;
		}
		public void setDateOfSellersAdjustment(Calendar dateOfSellersAdjustment) {
			this.dateOfSellersAdjustment = dateOfSellersAdjustment;
		}
		public Integer getSellersCorrectiveInvoiceNo() {
			return sellersCorrectiveInvoiceNo;
		}
		public void setSellersCorrectiveInvoiceNo(Integer sellersCorrectiveInvoiceNo) {
			this.sellersCorrectiveInvoiceNo = sellersCorrectiveInvoiceNo;
		}
		public Calendar getDateOfCorrectiveSellersInvoice() {
			return dateOfCorrectiveSellersInvoice;
		}
		public void setDateOfCorrectiveSellersInvoice(
				Calendar dateOfCorrectiveSellersInvoice) {
			this.dateOfCorrectiveSellersInvoice = dateOfCorrectiveSellersInvoice;
		}
		public Integer getAdjustiveSellersCorrectiveInvoiceNo() {
			return adjustiveSellersCorrectiveInvoiceNo;
		}
		public void setAdjustiveSellersCorrectiveInvoiceNo(
				Integer adjustiveSellersCorrectiveInvoiceNo) {
			this.adjustiveSellersCorrectiveInvoiceNo = adjustiveSellersCorrectiveInvoiceNo;
		}
		public Calendar getDateOfAdjustedSellersCorrectiveInvoice() {
			return dateOfAdjustedSellersCorrectiveInvoice;
		}
		public void setDateOfAdjustedSellersCorrectiveInvoice(
				Calendar dateOfAdjustedSellersCorrectiveInvoice) {
			this.dateOfAdjustedSellersCorrectiveInvoice = dateOfAdjustedSellersCorrectiveInvoice;
		}
		public Integer getNumberOfPaymentConfirmationDocument() {
			return numberOfPaymentConfirmationDocument;
		}
		public void setNumberOfPaymentConfirmationDocument(
				Integer numberOfPaymentConfirmationDocument) {
			this.numberOfPaymentConfirmationDocument = numberOfPaymentConfirmationDocument;
		}
		public Calendar getDateOfPaymentConfirmationDocument() {
			return dateOfPaymentConfirmationDocument;
		}
		public void setDateOfPaymentConfirmationDocument(
				Calendar dateOfPaymentConfirmationDocument) {
			this.dateOfPaymentConfirmationDocument = dateOfPaymentConfirmationDocument;
		}
		public Calendar getDateOfRecording() {
			return dateOfRecording;
		}
		public void setDateOfRecording(Calendar dateOfRecording) {
			this.dateOfRecording = dateOfRecording;
		}
		public char getNameOfSeller() {
			return nameOfSeller;
		}
		public void setNameOfSeller(char nameOfSeller) {
			this.nameOfSeller = nameOfSeller;
		}
		public Integer getTinOfSeller() {
			return tinOfSeller;
		}
		public void setTinOfSeller(Integer tinOfSeller) {
			this.tinOfSeller = tinOfSeller;
		}
		public Integer getCrrOfSeller() {
			return crrOfSeller;
		}
		public void setCrrOfSeller(Integer crrOfSeller) {
			this.crrOfSeller = crrOfSeller;
		}
		public char getNameOfIntermediary() {
			return nameOfIntermediary;
		}
		public void setNameOfIntermediary(char nameOfIntermediary) {
			this.nameOfIntermediary = nameOfIntermediary;
		}
		public Integer getTinOfIntermediary() {
			return tinOfIntermediary;
		}
		public void setTinOfIntermediary(Integer tinOfIntermediary) {
			this.tinOfIntermediary = tinOfIntermediary;
		}
		public Integer getCrrofIntermediary() {
			return crrofIntermediary;
		}
		public void setCrrofIntermediary(Integer crrofIntermediary) {
			this.crrofIntermediary = crrofIntermediary;
		}
		public Integer getNumberOfCustomsDeclaration() {
			return numberOfCustomsDeclaration;
		}
		public void setNumberOfCustomsDeclaration(Integer numberOfCustomsDeclaration) {
			this.numberOfCustomsDeclaration = numberOfCustomsDeclaration;
		}
		public Integer getCurrencyCodePerOKV() {
			return currencyCodePerOKV;
		}
		public void setCurrencyCodePerOKV(Integer currencyCodePerOKV) {
			this.currencyCodePerOKV = currencyCodePerOKV;
		}
		public Integer getValueOfPurchasesVAT() {
			return valueOfPurchasesVAT;
		}
		public void setValueOfPurchasesVAT(Integer valueOfPurchasesVAT) {
			this.valueOfPurchasesVAT = valueOfPurchasesVAT;
		}
		public Integer getDifferenceInValueVatToCorrectiveInvoice() {
			return differenceInValueVatToCorrectiveInvoice;
		}
		public void setDifferenceInValueVatToCorrectiveInvoice(
				Integer differenceInValueVatToCorrectiveInvoice) {
			this.differenceInValueVatToCorrectiveInvoice = differenceInValueVatToCorrectiveInvoice;
		}
		public Integer getAmountOfDeductibleVat() {
			return amountOfDeductibleVat;
		}
		public void setAmountOfDeductibleVat(Integer amountOfDeductibleVat) {
			this.amountOfDeductibleVat = amountOfDeductibleVat;
		}
		public Integer getDifferenceInVatAccordingToCorrectiveInvoice() {
			return differenceInVatAccordingToCorrectiveInvoice;
		}
		public void setDifferenceInVatAccordingToCorrectiveInvoice(
				Integer differenceInVatAccordingToCorrectiveInvoice) {
			this.differenceInVatAccordingToCorrectiveInvoice = differenceInVatAccordingToCorrectiveInvoice;
		}
		public char getCopiedLine() {
			return copiedLine;
		}
		public void setCopiedLine(char copiedLine) {
			this.copiedLine = copiedLine;
		}
}
