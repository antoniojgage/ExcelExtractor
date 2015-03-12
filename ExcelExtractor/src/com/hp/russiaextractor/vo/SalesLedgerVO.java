package com.hp.russiaextractor.vo;

import java.io.Serializable;
import java.util.Calendar;

public class SalesLedgerVO implements Serializable {

	private static final long serialVersionUID = 7382825154783915791L;
	
	//Sales Ledger Variables 
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

	
	
	
	
}
