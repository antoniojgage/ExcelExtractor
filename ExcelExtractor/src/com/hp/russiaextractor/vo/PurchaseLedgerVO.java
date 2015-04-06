package com.hp.russiaextractor.vo;
	
	import java.io.Serializable;
import java.util.Calendar;
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
		private Calendar dateOfRecording; //13
		private String nameOfSeller; //14
		private String tinOfSeller; //15
		private String crrOfSeller; //16
		private String nameOfIntermediary; //17
		private String tinOfIntermediary; //18
		private String crrofIntermediary; //19
		private String numberOfCustomsDeclaration; //20
		private String currencyCodePerOKV; //21
		private Double valueOfPurchasesVAT; //22
		private Double differenceInValueVatToCorrectiveInvoice; //23
		private Double amountOfDeductibleVat; //24
		private Double differenceInVatAccordingToCorrectiveInvoice; //25
		private String copiedLine; //26
		private Row createRow;
		private String Temp;
	    private	Date date;
		
		public void transformRow (Row newRow)
		{
			this.setNo((int) newRow.getCell(0).getNumericCellValue());
			System.out.println(newRow.getCell(0).getNumericCellValue());
			
			
			this.transactionTypeCode = newRow.getCell(4).getStringCellValue();
			
			// Start 3_4
			String[] intermediate3_4 = this.caseInfo(newRow.getCell(5), "\\u007c");
			try {
				this.sellersInvoice = intermediate3_4[0];
			} catch (NullPointerException e) {
				//System.out.println(e.getMessage().toString());
			}
			try {
				this.invoiceDate = intermediate3_4[1];; //set to temp to clear the last value
			}catch (NullPointerException e) {
			}catch (ArrayIndexOutOfBoundsException e2){
				
				//System.out.println(e.getMessage().toString());
			}
			 
			// End 3_4
			
			// Start 5_6
			
			String[] intermediate5_6 = this.caseInfo(newRow.getCell(7), "\\u007c");
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
			String[] intermediate7_8 = this.caseInfo(newRow.getCell(9), "\\u007c");
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
				String[] intermediate9_10 = this.caseInfo(newRow.getCell(12), "\\u007c");
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
				String[] intermediate11_12 = this.caseInfo(newRow.getCell(15), "от");
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
							
			
			//Date of recording logic			
		     this.dateOfRecording = Calendar.getInstance();
		     try {
		    	 this.dateOfRecording.setTime(newRow.getCell(24).getDateCellValue());
		     } catch (IllegalStateException ise) {this.dateOfRecording = null;}
		    	 /*if (DateUtil.isCellDateFormatted(newRow.getCell (24)))
		    	 {
		    	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    	     Cell currentCell = null;
					dateOfRecording = sdf(currentCell.getDateCellValue());
		    	 }*/
		     //end date of recording logic
		    	 
			 this.nameOfSeller = newRow.getCell(34).getStringCellValue();	//VAT14
		
		// Start 15_16 ED CHARACTER SYMBOL TO SPLIT
			String[] intermediate15_16 = this.caseInfo(newRow.getCell(45), "/"); 
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
		//End of 13_14		
							
			
			this.nameOfIntermediary = newRow.getCell(57).getStringCellValue();	//field 17				
		
			// Start 18_19 ED CHARACTER SYMBOL TO SPLIT
						String[] intermediate18_19 = this.caseInfo(newRow.getCell(67), "/"); 
							try {
										setTinOfIntermediary(intermediate18_19[0]);
										
										} catch (NullPointerException e) {
										}
										try{
										setCrrofIntermediary(intermediate18_19[1]);
										} catch (NullPointerException e) {
										} catch (ArrayIndexOutOfBoundsException e2) {				
												if (!(newRow.getCell(27).getStringCellValue().contains("|"))){
												
												}
										}
			
			this.numberOfCustomsDeclaration = newRow.getCell(68).getStringCellValue();	// VAT20

			// Start 21 ED CHARACTER SYMBOL TO SPLIT
			String[] intermediate_19 = this.caseInfo(newRow.getCell(78), ","); //VAT21
				try {
					setCurrencyCodePerOKV(intermediate_19[1]);
							} catch (NullPointerException e) {
							} catch (ArrayIndexOutOfBoundsException e2) {				
									if (!(newRow.getCell(27).getStringCellValue().contains("|"))){
									
									}
							}

			
			//PL15 LOGIC 			
			//3 || 4 not empty VAT22=15; VAT23=null
					if (newRow.getCell(9).getStringCellValue().length()+newRow.getCell(12).getStringCellValue().length()>0) {
								try {
								this.differenceInValueVatToCorrectiveInvoice = newRow.getCell(88).getNumericCellValue(); //VAT20
								this.valueOfPurchasesVAT = null; //22
							} catch (IllegalStateException ise) {this.valueOfPurchasesVAT = null;}
						}
			//5 || 6
																							
					else if (newRow.getCell(5).getStringCellValue().length()+newRow.getCell(7).getStringCellValue().length()>0) {
								try{
								this.valueOfPurchasesVAT = newRow.getCell(88).getNumericCellValue(); //VAT22
								this.differenceInValueVatToCorrectiveInvoice = null; //20
							} catch (IllegalStateException ise) {this.differenceInValueVatToCorrectiveInvoice = null;
						}
					}	
					//end logic for 15PL
	
					
					//PL16 LOGIC 			
					//5 || 6 not empty VAT22=15; VAT23=null
							if (newRow.getCell(9).getStringCellValue().length()+newRow.getCell(12).getStringCellValue().length()>0) {
										try {
										this.differenceInVatAccordingToCorrectiveInvoice = newRow.getCell(98).getNumericCellValue(); //VAT25
										this.amountOfDeductibleVat = null; //24
									} catch (IllegalStateException ise) {this.amountOfDeductibleVat = null;}
								}
					//3 || 4
																									
							else if (newRow.getCell(5).getStringCellValue().length()+newRow.getCell(7).getStringCellValue().length()>0) {
										try{
										this.amountOfDeductibleVat = newRow.getCell(98).getNumericCellValue(); //VAT24
										this.differenceInVatAccordingToCorrectiveInvoice = null; //25
									} catch (IllegalStateException ise) {this.differenceInVatAccordingToCorrectiveInvoice = null;
								}
							}	
			
			
			
			
			
			
			//this.amountOfDeductibleVat = newRow.getCell(98).getNumericCellValue(); //VAT24
			
			
			
			
			
			
			
			
			
			
			//this.differenceInVatAccordingToCorrectiveInvoice = newRow.getCell(98).getNumericCellValue(); //VAT25
		
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
					
					/*System.out.println("parts 0 " + parts[0]);
					System.out.println("parts 1 " + parts[1]);
					System.out.println(parts.toString()); */
						}
						catch(ArrayIndexOutOfBoundsException e) {
							//System.out.println(part1 + " broken 1 " + part2);
							//System.out.println(info.getCellType());
						}catch(StringIndexOutOfBoundsException e) {
							//System.out.println(part1 + " broken 2 " + part2);
						//	System.out.println(info.getCellType());
						}
						
						//System.out.println(part1 + " broken 3 " + part2);
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
		public Calendar getDateOfRecording() {
			return dateOfRecording;
		}
		public void setDateOfRecording(Calendar dateOfRecording) {
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
		public Double getValueOfPurchasesVAT() {
			return valueOfPurchasesVAT;
		}
		public void setValueOfPurchasesVAT(Double valueOfPurchasesVAT) {
			this.valueOfPurchasesVAT = valueOfPurchasesVAT;
		}
		public Double getDifferenceInValueVatToCorrectiveInvoice() {
			return differenceInValueVatToCorrectiveInvoice;
		}
		public void setDifferenceInValueVatToCorrectiveInvoice(
				Double differenceInValueVatToCorrectiveInvoice) {
			this.differenceInValueVatToCorrectiveInvoice = differenceInValueVatToCorrectiveInvoice;
		}
		public Double getAmountOfDeductibleVat() {
			return amountOfDeductibleVat;
		}
		public void setAmountOfDeductibleVat(Double amountOfDeductibleVat) {
			this.amountOfDeductibleVat = amountOfDeductibleVat;
		}
		public Double getDifferenceInVatAccordingToCorrectiveInvoice() {
			return differenceInVatAccordingToCorrectiveInvoice;
		}
		public void setDifferenceInVatAccordingToCorrectiveInvoice(
				Double differenceInVatAccordingToCorrectiveInvoice) {
			this.differenceInVatAccordingToCorrectiveInvoice = differenceInVatAccordingToCorrectiveInvoice;
		}
		public String getCopiedLine() {
			return copiedLine;
		}
		public void setCopiedLine(String copiedLine) {
			this.copiedLine = copiedLine;
		}
}
