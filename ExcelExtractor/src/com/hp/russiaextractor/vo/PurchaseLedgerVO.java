package com.hp.russiaextractor.vo;
	
	import java.io.Serializable;
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
		private int no; //1
		private String transactionTypeCode; //2
		private String invoiceDate; //3
		private String sellersInvoice; //4
		private String sellersAdjustmentAmount; //5
		private String dateOfSellersAdjustment; //6
		private String sellersCorrectiveInvoiceNo; //7
		private String dateOfCorrectiveSellersInvoice; //8
		private String adjustiveSellersCorrectiveInvoiceNo; //9
		private String dateOfAdjustedSellersCorrectiveInvoice; //10
		private String numberOfPaymentConfirmationDocument; //11
		private String dateOfPaymentConfirmationDocument; //12
		private String dateOfRecording; //13
		private String nameOfSeller; //14
		private String tinOfSeller; //15
		private String crrOfSeller; //16
		private String nameOfIntermediary; //17
		private String tinOfIntermediary; //18
		private String crrofIntermediary; //19
		private String numberOfCustomsDeclaration; //20
		private String currencyCodePerOKV; //21
		private String valueOfPurchasesVAT; //22
		private String differenceInValueVatToCorrectiveInvoice; //23
		private String amountOfDeductibleVat; //24
		private String differenceInVatAccordingToCorrectiveInvoice; //25
		private String copiedLine; //26
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
						
			// Start 9_10
				String[] intermediate9_10 = this.caseInfo(newRow.getCell(11), "\\u007c");
					try {
						setAdjustiveSellersCorrectiveInvoiceNo(intermediate9_10[0]); 
						} catch (NullPointerException e) {
						}
						try{
						setDateOfAdjustedSellersCorrectiveInvoice(intermediate9_10[1]);
						} catch (NullPointerException e) {
						} catch (ArrayIndexOutOfBoundsException e2) {				
						}
			//End of 9_10			
						
			// Start 11_12 NEED CHARACTER SYMBOL TO SPLIT
				String[] intermediate11_12 = this.caseInfo(newRow.getCell(14), " ");
					try {
								setNumberOfPaymentConfirmationDocument(intermediate11_12[0]); 
								} catch (NullPointerException e) {
								}
								try{
								setDateOfPaymentConfirmationDocument(intermediate11_12[1]);
								} catch (NullPointerException e) {
								} catch (ArrayIndexOutOfBoundsException e2) {				
								}
			//End of 11_12			
							
			
			this.dateOfRecording = newRow.getCell(27).getStringCellValue();
			this.nameOfSeller = newRow.getCell(38).getStringCellValue();		
		
		// Start 15_16 ED CHARACTER SYMBOL TO SPLIT
			String[] intermediate15_16 = this.caseInfo(newRow.getCell(27), "\\u007c"); //set back to 27
				try {
							setTinOfSeller(intermediate15_16[0]);
							
							} catch (NullPointerException e) {
							}
							try{
							setCrrOfSeller(intermediate15_16[1]);
							} catch (NullPointerException e) {
							} catch (ArrayIndexOutOfBoundsException e2) {				
									if (!(newRow.getCell(27).getStringCellValue().contains("|"))){
									
									}
							}
							

							System.out.println("look here");
		//End of 13_14		
							
			
			this.nameOfIntermediary = newRow.getCell(64).getStringCellValue();					
							
							
							
		}
		
		private void outPut(){
			
		}
		
		public String [] caseInfo (Cell info, String Stringacter){
			double num;
			
			switch (info.getCellType()){
			
			case Cell.CELL_TYPE_NUMERIC:{
				
				num = info.getNumericCellValue();
				return new String[] {Double.toString(num)};
			}
			case Cell.CELL_TYPE_BLANK:
				return new String[] {"-"};
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
					parts = info.getStringCellValue().split(Stringacter);
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
		//  Stringset.forName("UTF-8").encode(info);
		//	byte bytes[] = info.getBytes("ISO-8859-1");
		//	String myString = new String(bytes, "UTF-8");
		//	String[] parts = info.getStringCellValue().split(Stringacter);
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
		
		
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
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
		public String getAdjustiveSellersCorrectiveInvoiceNo() {
			return adjustiveSellersCorrectiveInvoiceNo;
		}
		public void setAdjustiveSellersCorrectiveInvoiceNo(
				String adjustiveSellersCorrectiveInvoiceNo) {
			this.adjustiveSellersCorrectiveInvoiceNo = adjustiveSellersCorrectiveInvoiceNo;
		}
		public String getDateOfAdjustedSellersCorrectiveInvoice() {
			return dateOfAdjustedSellersCorrectiveInvoice;
		}
		public void setDateOfAdjustedSellersCorrectiveInvoice(
				String dateOfAdjustedSellersCorrectiveInvoice) {
			this.dateOfAdjustedSellersCorrectiveInvoice = dateOfAdjustedSellersCorrectiveInvoice;
		}
		public String getNumberOfPaymentConfirmationDocument() {
			return numberOfPaymentConfirmationDocument;
		}
		public void setNumberOfPaymentConfirmationDocument(
				String numberOfPaymentConfirmationDocument) {
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
		
		public String getNameOfSeller() {
			return nameOfSeller;
		}
		public void setNameOfSeller(String nameOfSeller) {
			this.nameOfSeller = nameOfSeller;
		}
		public String getTinOfSeller() {
			return tinOfSeller;
		}
		public void setTinOfSeller(String tinOfSeller) {
			this.tinOfSeller = tinOfSeller;
		}
		public String getCrrOfSeller() {
			return crrOfSeller;
		}
		public void setCrrOfSeller(String crrOfSeller) {
			this.crrOfSeller = crrOfSeller;
		}
		public String getNameOfIntermediary() {
			return nameOfIntermediary;
		}
		public void setNameOfIntermediary(String nameOfIntermediary) {
			this.nameOfIntermediary = nameOfIntermediary;
		}
		public String getTinOfIntermediary() {
			return tinOfIntermediary;
		}
		public void setTinOfIntermediary(String tinOfIntermediary) {
			this.tinOfIntermediary = tinOfIntermediary;
		}
		public String getCrrofIntermediary() {
			return crrofIntermediary;
		}
		public void setCrrofIntermediary(String crrofIntermediary) {
			this.crrofIntermediary = crrofIntermediary;
		}
		public String getNumberOfCustomsDeclaration() {
			return numberOfCustomsDeclaration;
		}
		public void setNumberOfCustomsDeclaration(String numberOfCustomsDeclaration) {
			this.numberOfCustomsDeclaration = numberOfCustomsDeclaration;
		}
		public String getCurrencyCodePerOKV() {
			return currencyCodePerOKV;
		}
		public void setCurrencyCodePerOKV(String currencyCodePerOKV) {
			this.currencyCodePerOKV = currencyCodePerOKV;
		}
		public String getValueOfPurchasesVAT() {
			return valueOfPurchasesVAT;
		}
		public void setValueOfPurchasesVAT(String valueOfPurchasesVAT) {
			this.valueOfPurchasesVAT = valueOfPurchasesVAT;
		}
		public String getDifferenceInValueVatToCorrectiveInvoice() {
			return differenceInValueVatToCorrectiveInvoice;
		}
		public void setDifferenceInValueVatToCorrectiveInvoice(
				String differenceInValueVatToCorrectiveInvoice) {
			this.differenceInValueVatToCorrectiveInvoice = differenceInValueVatToCorrectiveInvoice;
		}
		public String getAmountOfDeductibleVat() {
			return amountOfDeductibleVat;
		}
		public void setAmountOfDeductibleVat(String amountOfDeductibleVat) {
			this.amountOfDeductibleVat = amountOfDeductibleVat;
		}
		public String getDifferenceInVatAccordingToCorrectiveInvoice() {
			return differenceInVatAccordingToCorrectiveInvoice;
		}
		public void setDifferenceInVatAccordingToCorrectiveInvoice(
				String differenceInVatAccordingToCorrectiveInvoice) {
			this.differenceInVatAccordingToCorrectiveInvoice = differenceInVatAccordingToCorrectiveInvoice;
		}
		public String getCopiedLine() {
			return copiedLine;
		}
		public void setCopiedLine(String copiedLine) {
			this.copiedLine = copiedLine;
		}
}
