package com.hp.russiaextractor.vo;
	
	import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.String;
import java.util.Date;
//import java.util.GregorianString;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

	public class PurchaseLedgerVO implements Serializable {

		private static final long serialVersionUID = 7382825154783915791L;
		
		//Purchase Ledger Variables 
		private Integer no; //1
		private String transactionTypeCode; //2
		private String invoiceDate; //3
		private String sellersInvoice; //4
		private String sellersAdjustmentAmount; //5
		private String dateOfSellersAdjustment; //6
		private String sellersCorrectiveInvoiceNo; //7
		private String dateOfCorrectiveSellersInvoice; //8
		private Integer adjustiveSellersCorrectiveInvoiceNo; //9
		private String dateOfAdjustedSellersCorrectiveInvoice; //10
		private Integer numberOfPaymentConfirmationDocument; //11
		private String dateOfPaymentConfirmationDocument; //12
		private String dateOfRecording; //13
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
		private Row createRow;
		private String Temp;
		
		public void transformRow (Row newRow)
		{
			this.setNo((int) newRow.getCell(0).getNumericCellValue());
			this.transactionTypeCode = newRow.getCell(1).getStringCellValue();
			
			// Start 3_4
			String[] intermediate3_4 = this.caseInfo(newRow.getCell(2), "\\u007c");
			try {
				this.invoiceDate = intermediate3_4[0];
			} catch (NullPointerException e) {
				//System.out.println(e.getMessage().toString());
			}
			try {
				this.sellersInvoice = intermediate3_4[1];; //set to temp to clear the last value
			}catch (NullPointerException e) {
				//System.out.println(e.getMessage().toString());
			}
			 
			// End 3_4
			
			// Start 5_6
			
			String[] intermediate5_6 = this.caseInfo(newRow.getCell(5), "\\u007c");
			try {
				//this.dateOfSellersAdjustment = intermediate6_7[0];
				setSellersAdjustmentAmount(intermediate5_6[0]); //setDateOfSellersAdjustment(intermediate5_6[0]);
			} catch (NullPointerException e) {
				//System.out.println(e.getMessage().toString());
			}
			try{
				setDateOfSellersAdjustment(intermediate5_6[1]); //setSellersCorrectiveInvoiceNo(intermediate5_6[1]);
			} catch (NullPointerException e) {
				//System.out.println(e.getMessage().toString());
			} catch (ArrayIndexOutOfBoundsException e2) {
				
			}
			//End of 5_6
			
			
			// Start 7_8
			
			String[] intermediate7_8 = this.caseInfo(newRow.getCell(8), "\\u007c");
				try {
					setSellersCorrectiveInvoiceNo(intermediate7_8[0]); 
						} catch (NullPointerException e) {
						}
						try{
							setDateOfCorrectiveSellersInvoice(intermediate7_8[1]);
						} catch (NullPointerException e) {
						} catch (ArrayIndexOutOfBoundsException e2) {				
						}
			//End of 7_8
		}
		
		private void outPut(){
			
		}
		
		public String [] caseInfo (Cell info, String character){
			double num;
			
			switch (info.getCellType()){
			
			case Cell.CELL_TYPE_NUMERIC:{
				
				num = info.getNumericCellValue();
				return new String[] {Double.toString(num)};
			}
			case Cell.CELL_TYPE_BLANK:
				return null;
			case Cell.CELL_TYPE_ERROR:
				
				return null;
			case Cell.CELL_TYPE_FORMULA:
				return null;
	
				default:{
					
					
				//	if (info.getStringCellValue() != ""){
					
						String part1 = null;
						String part2 = null;
						String[] parts = null;
						
						System.out.println(info.getCellType());
						System.out.println(this.getNo());
						System.out.println(info.getRowIndex());
						System.out.println(info.getStringCellValue());
						
						try{
					parts = info.getStringCellValue().split(character);
					part1 = parts[0];
					part2 = parts[1];
					System.out.println("parts 0 " + parts[0]);
					System.out.println("parts 1 " + parts[1]);
					System.out.println(parts.toString());
						}
						catch(ArrayIndexOutOfBoundsException e) {
							System.out.println(part1 + " broken 1 " + part2);
							System.out.println(info.getCellType());
						}catch(StringIndexOutOfBoundsException e) {
							System.out.println(part1 + " broken 2 " + part2);
							System.out.println(info.getCellType());
						}
						
						System.out.println(part1 + " broken 3 " + part2);
					Temp = part2;
					//	int num = (int)letter[0]
				//	part1 = part1.substring(0,part1.length()-1);
					System.out.println(part1 + " " + part2);
					return parts;
				}
			}
					
				//		}
				//	else
				//		return null;
				//	}
				
		//	return null;
			
		// if ((info.getStringCellValue() != null) && (info.getStringCellValue() != "")) {
		//  Charset.forName("UTF-8").encode(info);
		//	byte bytes[] = info.getBytes("ISO-8859-1");
		//	String myString = new String(bytes, "UTF-8");
		//	String[] parts = info.getStringCellValue().split(character);
		//	String part1 = parts[0];
		//	String part2 = parts[1];
		//	Temp = part2;
		//	int num = (int)letter[0]
		//	part1 = part1.substring(0,part1.length()-1);
		//	System.out.println(part1 + " " + part2);
		//	return part1;
		//}
	//	else {
		//	Temp = null;
			
			//	return info.getStringCellValue();
			}
		//}
		
		
		public Integer getNo() {
			return no;
		}
		public void setNo(Integer no) {
			this.no = no;
		}
		public String getTransactionTypeCode() {
			return transactionTypeCode;
		}
		public void setTransactionTypeCode(String transactionTypeCode) {
			this.transactionTypeCode = transactionTypeCode;
		}
		public String getInvoiceDate() {
			return invoiceDate;
		}
		public void setInvoiceDate(String invoiceDate) {
			
			this.invoiceDate = invoiceDate;
		}
		public String getSellersInvoice() {
			return sellersInvoice;
		}
		public void setSellersInvoice(String sellersInvoice) {
			this.sellersInvoice = sellersInvoice;
		}
		public String getSellersAdjustmentAmount() {
			return sellersAdjustmentAmount;
		}
		public void setSellersAdjustmentAmount(String sellersAdjustmentAmount) {
			this.sellersAdjustmentAmount = sellersAdjustmentAmount;
		}
		public String getDateOfSellersAdjustment() {
			return dateOfSellersAdjustment;
		}
		public void setDateOfSellersAdjustment(String dateOfSellersAdjustment) {
			this.dateOfSellersAdjustment = dateOfSellersAdjustment;
		}
		public String getSellersCorrectiveInvoiceNo() {
			return sellersCorrectiveInvoiceNo;
		}
		public void setSellersCorrectiveInvoiceNo(String sellersCorrectiveInvoiceNo) {
			this.sellersCorrectiveInvoiceNo = sellersCorrectiveInvoiceNo;
		}
		public String getDateOfCorrectiveSellersInvoice() {
			return dateOfCorrectiveSellersInvoice;
		}
		public void setDateOfCorrectiveSellersInvoice(
				String dateOfCorrectiveSellersInvoice) {
			this.dateOfCorrectiveSellersInvoice = dateOfCorrectiveSellersInvoice;
		}
		public Integer getAdjustiveSellersCorrectiveInvoiceNo() {
			return adjustiveSellersCorrectiveInvoiceNo;
		}
		public void setAdjustiveSellersCorrectiveInvoiceNo(
				Integer adjustiveSellersCorrectiveInvoiceNo) {
			this.adjustiveSellersCorrectiveInvoiceNo = adjustiveSellersCorrectiveInvoiceNo;
		}
		public String getDateOfAdjustedSellersCorrectiveInvoice() {
			return dateOfAdjustedSellersCorrectiveInvoice;
		}
		public void setDateOfAdjustedSellersCorrectiveInvoice(
				String dateOfAdjustedSellersCorrectiveInvoice) {
			this.dateOfAdjustedSellersCorrectiveInvoice = dateOfAdjustedSellersCorrectiveInvoice;
		}
		public Integer getNumberOfPaymentConfirmationDocument() {
			return numberOfPaymentConfirmationDocument;
		}
		public void setNumberOfPaymentConfirmationDocument(
				Integer numberOfPaymentConfirmationDocument) {
			this.numberOfPaymentConfirmationDocument = numberOfPaymentConfirmationDocument;
		}
		public String getDateOfPaymentConfirmationDocument() {
			return dateOfPaymentConfirmationDocument;
		}
		public void setDateOfPaymentConfirmationDocument(
				String dateOfPaymentConfirmationDocument) {
			this.dateOfPaymentConfirmationDocument = dateOfPaymentConfirmationDocument;
		}
		public String getDateOfRecording() {
			return dateOfRecording;
		}
		public void setDateOfRecording(String dateOfRecording) {
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
