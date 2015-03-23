package com.hp.russiaextractor.vo;
	
	import java.io.Serializable;
import java.util.Calendar;
//import java.util.String;
import java.util.Date;
//import java.util.GregorianString;




import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

	public class SalesLedgerVO implements Serializable {

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
		private String dateOfAdjustedSellersCorrectiveInvoice; //10 Same on SL/PL
		private String nameOfPurchaser; //SL11
		private String tinOfPurchaser; //SL12
		private String crrOfPurchaser; //SL13
		private String nameOfIntermediary; //SL14
		private String tinOfIntermediary; //SL15
		private String crrofIntermediary; //SL16
		private String numberOfPaymentConfirmationDocument; //SL17
		private String dateOfPaymentConfirmationDocument; //SL18
		private String currencyCodePerOKV; //SL19
		private Double valueOfSalesInvoiceCurrency; //SL20
		private Double valueOfSalesRur; //SL21
		private Double differenceInValueInvoiceCurrency; //SL22
		private Double differenceInValueInvoiceRur; //SL23
		private Double valueOfSales18; //SL24
		private Double valueOfSales10; //SL25
		private Double valueOfSales0; //SL26
		private Double amountOfVat18; //SL27
		private Double amountOfVat10; //SL28
		private Double valueOfTaxExemptSales; //SL29
		private Double differenceInValueOfTaxExemptSales; //SL30
		private Double differenceOfCorrective18; //SL31
		private Double differenceOfCorrective10; //SL32
		private Double differenceOfCorrective0; //SL33
		private Double differenceOfVat18; //SL34
		private Double differenceOfVat10; //SL35
		private String copiedLine; //SL36
		private Row createRow;
		private String Temp;
	    private	Date date;
		
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
			}catch (ArrayIndexOutOfBoundsException e2){
				
				//System.out.println(e.getMessage().toString());
			}
			 
			// End 3_4
			
			// Start 5_6
			
			String[] intermediate5_6 = this.caseInfo(newRow.getCell(5), "\\u007c");
			try {
				//this.dateOfSellersAdjustment = intermediate6_7[0];
				setSellersAdjustmentAmount(intermediate5_6[0]); //setDateOfSellersAdjustment(intermediate5_6[0]); //5
			} catch (NullPointerException e) {
				//System.out.println(e.getMessage().toString());
			}
			try{
				setDateOfSellersAdjustment(intermediate5_6[1]); //setSellersCorrectiveInvoiceNo(intermediate5_6[1]); //6
			} catch (NullPointerException e) {
				//System.out.println(e.getMessage().toString());
			} catch (ArrayIndexOutOfBoundsException e2) {
				
			}
			//End of 5_6
			
			
			// Start 7_8
			String[] intermediate7_8 = this.caseInfo(newRow.getCell(9), "\\u007c");
				try {
					setSellersCorrectiveInvoiceNo(intermediate7_8[0]); //7
						} catch (NullPointerException e) {
						}
						try{
							setDateOfCorrectiveSellersInvoice(intermediate7_8[1]);
						} catch (NullPointerException e) {
						} catch (ArrayIndexOutOfBoundsException e2) {				
						}
			//End of 7_8
						
			// Start 9_10
				String[] intermediate9_10 = this.caseInfo(newRow.getCell(12), "\\u007c");
					try {
						setAdjustiveSellersCorrectiveInvoiceNo(intermediate9_10[0]); //9
						} catch (NullPointerException e) {
						}
						try{
						setDateOfAdjustedSellersCorrectiveInvoice(intermediate9_10[1]); //10
						} catch (NullPointerException e) {
						} catch (ArrayIndexOutOfBoundsException e2) {				
						}
			//End of 9_10			
			
				this.nameOfPurchaser = newRow.getCell(14).getStringCellValue();		//11	
			
				
				// Start 12_13
				String[] intermediate12_13 = this.caseInfo(newRow.getCell(27), "/");
					try {
						setTinOfPurchaser(intermediate12_13[0]); //12
						} catch (NullPointerException e) {
						}
						try{
						setCrrOfPurchaser(intermediate12_13[1]); //13
						} catch (NullPointerException e) {
						} catch (ArrayIndexOutOfBoundsException e2) {				
						}
			//End of 12_13
						
				this.nameOfIntermediary = newRow.getCell(38).getStringCellValue();	//14		
				
				// Start 15_16
				String[] intermediate15_16 = this.caseInfo(newRow.getCell(51), "/");
					try {
						setTinOfIntermediary(intermediate15_16[0]); //15
						} catch (NullPointerException e) {
						}
						try{
						setCrrofIntermediary(intermediate15_16[1]); //15
						} catch (NullPointerException e) {
						} catch (ArrayIndexOutOfBoundsException e2) {				
						}
				//End of 15_16
						
				// Start 17_18 NEED CHARACTER SYMBOL TO SPLIT
						String[] intermediate16_17 = this.caseInfo(newRow.getCell(64), "от");
							try {
								setNumberOfPaymentConfirmationDocument(intermediate16_17[0]); 
								} catch (NullPointerException e) {
								}
								try{
								setDateOfPaymentConfirmationDocument(intermediate16_17[1]);
								} catch (NullPointerException e) {
								} catch (ArrayIndexOutOfBoundsException e2) {				
								}
					//End of 17_18						
						
								
					//19 Currancy Code			
								String[] intermediate_19 = this.caseInfo(newRow.getCell(74), ","); //VAT21
								try {
									setCurrencyCodePerOKV(intermediate_19[1]);
											} catch (NullPointerException e) {
											} catch (ArrayIndexOutOfBoundsException e2) {				
													if (!(newRow.getCell(27).getStringCellValue().contains("|"))){
											}
											}		
								
				/*				
					//SL13A LOGIC 			
					//3 || 4 not empty VAT22=15; VAT23=null
							try {
								if (newRow.getCell(2).getStringCellValue().length()+newRow.getCell(5).getStringCellValue().length()>0) {
								this.valueOfSalesInvoiceCurrency = newRow.getCell(82).getNumericCellValue(); //VAT20
									this.differenceInValueInvoiceCurrency = null; //22
									
								}
								}catch (NullPointerException e) {
								} catch (ArrayIndexOutOfBoundsException e2) {
								//5 || 6
								try{
									if (newRow.getCell(8).getStringCellValue().length()+newRow.getCell(11).getStringCellValue().length()>0) {
									this.differenceInValueInvoiceCurrency = newRow.getCell(82).getNumericCellValue(); //VAT22
									this.valueOfSalesInvoiceCurrency = null; //20
									
								}
								}catch (NullPointerException e3) {
								} catch (ArrayIndexOutOfBoundsException e4) {				
								}						
						
								
					//VAT 21 Logic		
								//3 || 4 not empty VAT22=15; VAT23=null
							try {	
							if (newRow.getCell(2).getStringCellValue().length()+newRow.getCell(5).getStringCellValue().length()>0) {
									this.valueOfSalesRur = newRow.getCell(93).getNumericCellValue(); //VAT21
										this.differenceInValueInvoiceRur = null; //22
									}
									//5 || 6
									if (newRow.getCell(8).getStringCellValue().length()+newRow.getCell(11).getStringCellValue().length()>0) {
										this.differenceInValueInvoiceCurrency = newRow.getCell(93).getNumericCellValue(); //VAT23
										this.valueOfSalesRur = null; //VAT21
									}	
							}catch (NullPointerException e) {
							}catch (ArrayIndexOutOfBoundsException e21) {
							}	
								}	
								*/
								
		}
				
				
			

				
				 
		    
		
		
		private void setNo(int numericCellValue) {
			// TODO Auto-generated method stub
			
		}









		private Object getCellValue(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		private Cell getCell(int i) {
				// TODO Auto-generated method stub
				return null;
			}
		private Date sdf(Date dateCellValue) {
			// TODO Auto-generated method stub
			return null;
		}

		private void outPut(){
			
		}
		
		public String [] caseInfo (Cell info, String Stringacter){
			Double num;  
			
			switch (info.getCellType()) {
			
			case Cell.CELL_TYPE_NUMERIC:{
				if (DateUtil.isCellDateFormatted(info)) {
					num = info.getNumericCellValue();
					return new String[] {Double.toString(num)};
			}
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
						
						//System.out.println(info.getCellType());
						//System.out.println(this.getNo());
						//System.out.println(info.getRowIndex());
						//System.out.println(info.getStringCellValue());
						
						try{
					parts = info.getStringCellValue().split(Stringacter);
					part1 = parts[0];
					part2 = parts[1];
					System.out.println("parts 0 " + parts[0]);
					System.out.println("parts 1 " + parts[1]);
					System.out.println(parts.toString());
						}
						catch(ArrayIndexOutOfBoundsException e) {
							//System.out.println(part1 + " broken 1 " + part2);
							//System.out.println(info.getCellType());
						}catch(StringIndexOutOfBoundsException e) {
							//System.out.println(part1 + " broken 2 " + part2);
						//	System.out.println(info.getCellType());
						}
						
						System.out.println(part1 + " broken 3 " + part2);
					Temp = part2;
					//	int num = (int)letter[0]
				//	part1 = part1.substring(0,part1.length()-1);
					System.out.println(part1 + " " + part2);
					return parts;
				}
			}
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









		public String getNameOfPurchaser() {
			return nameOfPurchaser;
		}









		public void setNameOfPurchaser(String nameOfPurchaser) {
			this.nameOfPurchaser = nameOfPurchaser;
		}









		public String getTinOfPurchaser() {
			return tinOfPurchaser;
		}









		public void setTinOfPurchaser(String tinOfPurchaser) {
			this.tinOfPurchaser = tinOfPurchaser;
		}









		public String getCrrOfPurchaser() {
			return crrOfPurchaser;
		}









		public void setCrrOfPurchaser(String crrOfPurchaser) {
			this.crrOfPurchaser = crrOfPurchaser;
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









		public String getCurrencyCodePerOKV() {
			return currencyCodePerOKV;
		}









		public void setCurrencyCodePerOKV(String currencyCodePerOKV) {
			this.currencyCodePerOKV = currencyCodePerOKV;
		}









		public Double getValueOfSalesInvoiceCurrency() {
			return valueOfSalesInvoiceCurrency;
		}









		public void setValueOfSalesInvoiceCurrency(Double valueOfSalesInvoiceCurrency) {
			this.valueOfSalesInvoiceCurrency = valueOfSalesInvoiceCurrency;
		}









		public Double getValueOfSalesRur() {
			return valueOfSalesRur;
		}









		public void setValueOfSalesRur(Double valueOfSalesRur) {
			this.valueOfSalesRur = valueOfSalesRur;
		}









		public Double getDifferenceInValueInvoiceCurrency() {
			return differenceInValueInvoiceCurrency;
		}









		public void setDifferenceInValueInvoiceCurrency(
				Double differenceInValueInvoiceCurrency) {
			this.differenceInValueInvoiceCurrency = differenceInValueInvoiceCurrency;
		}









		public Double getDifferenceInValueInvoiceRur() {
			return differenceInValueInvoiceRur;
		}









		public void setDifferenceInValueInvoiceRur(Double differenceInValueInvoiceRur) {
			this.differenceInValueInvoiceRur = differenceInValueInvoiceRur;
		}









		public Double getValueOfSales18() {
			return valueOfSales18;
		}









		public void setValueOfSales18(Double valueOfSales18) {
			this.valueOfSales18 = valueOfSales18;
		}









		public Double getValueOfSales10() {
			return valueOfSales10;
		}









		public void setValueOfSales10(Double valueOfSales10) {
			this.valueOfSales10 = valueOfSales10;
		}









		public Double getValueOfSales0() {
			return valueOfSales0;
		}









		public void setValueOfSales0(Double valueOfSales0) {
			this.valueOfSales0 = valueOfSales0;
		}









		public Double getAmountOfVat18() {
			return amountOfVat18;
		}









		public void setAmountOfVat18(Double amountOfVat18) {
			this.amountOfVat18 = amountOfVat18;
		}









		public Double getAmountOfVat10() {
			return amountOfVat10;
		}









		public void setAmountOfVat10(Double amountOfVat10) {
			this.amountOfVat10 = amountOfVat10;
		}









		public Double getValueOfTaxExemptSales() {
			return valueOfTaxExemptSales;
		}









		public void setValueOfTaxExemptSales(Double valueOfTaxExemptSales) {
			this.valueOfTaxExemptSales = valueOfTaxExemptSales;
		}









		public Double getDifferenceInValueOfTaxExemptSales() {
			return differenceInValueOfTaxExemptSales;
		}









		public void setDifferenceInValueOfTaxExemptSales(
				Double differenceInValueOfTaxExemptSales) {
			this.differenceInValueOfTaxExemptSales = differenceInValueOfTaxExemptSales;
		}









		public Double getDifferenceOfCorrective18() {
			return differenceOfCorrective18;
		}









		public void setDifferenceOfCorrective18(Double differenceOfCorrective18) {
			this.differenceOfCorrective18 = differenceOfCorrective18;
		}









		public Double getDifferenceOfCorrective10() {
			return differenceOfCorrective10;
		}









		public void setDifferenceOfCorrective10(Double differenceOfCorrective10) {
			this.differenceOfCorrective10 = differenceOfCorrective10;
		}









		public Double getDifferenceOfCorrective0() {
			return differenceOfCorrective0;
		}









		public void setDifferenceOfCorrective0(Double differenceOfCorrective0) {
			this.differenceOfCorrective0 = differenceOfCorrective0;
		}









		public Double getDifferenceOfVat18() {
			return differenceOfVat18;
		}









		public void setDifferenceOfVat18(Double differenceOfVat18) {
			this.differenceOfVat18 = differenceOfVat18;
		}









		public Double getDifferenceOfVat10() {
			return differenceOfVat10;
		}









		public void setDifferenceOfVat10(Double differenceOfVat10) {
			this.differenceOfVat10 = differenceOfVat10;
		}









		public String getCopiedLine() {
			return copiedLine;
		}









		public void setCopiedLine(String copiedLine) {
			this.copiedLine = copiedLine;
		}









		public Row getCreateRow() {
			return createRow;
		}









		public void setCreateRow(Row createRow) {
			this.createRow = createRow;
		}









		public String getTemp() {
			return Temp;
		}









		public void setTemp(String temp) {
			Temp = temp;
		}









		public Date getDate() {
			return date;
		}









		public void setDate(Date date) {
			this.date = date;
		}









		public static long getSerialversionuid() {
			return serialVersionUID;
		}









		public int getNo() {
			return no;
		}
		}