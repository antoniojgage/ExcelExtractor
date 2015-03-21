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
		private String valueOfSalesInvoiceCurrency; //SL20
		private String valueOfSalesRur; //SL21
		private String differenceInValueInvoiceCurrency; //SL22
		private String differenceInValueInvoiceRur; //SL23
		private String valueOfSales18; //SL24
		private String valueOfSales10; //SL25
		private String valueOfSales0; //SL26
		private String amountOfVat18; //SL27
		private String amountOfVat10; //SL28
		private String valueOfTaxExemptSales; //SL29
		private Double differenceInValueOfTaxExemptSales; //SL30
		private String differenceOfCorrective18; //SL31
		private String differenceOfCorrective10; //SL32
		private String differenceOfCorrective0; //SL33
		private String differenceOfVat18; //SL34
		private String differenceOfVat10; //SL35
		private String copiedLine; //SL36
		private Row createRow;
		private String Temp;
	    private	Date date;
		
		public void transformRow (Row newRow)
		{
			this.setNo((int) newRow.getCell(0).getNumericCellValue());
			this.transactionTypeCode = newRow.getCell(4).getStringCellValue();
			
			// Start 3_4
			String[] intermediate3_4 = this.caseInfo(newRow.getCell(5), "\\u007c");
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
		    //3 || 4 not empty VAT22=15; VAT23=null
			if (newRow.getCell(5).getStringCellValue().length()+newRow.getCell(7).getStringCellValue().length()>0) {
				this.valueOfPurchasesVAT = newRow.getCell(88).getNumericCellValue(); //VAT22
				this.differenceInValueVatToCorrectiveInvoice = null; //23
			}
			
			if (newRow.getCell(9).getStringCellValue().length()+newRow.getCell(12).getStringCellValue().length()>0) {
				this.differenceInValueVatToCorrectiveInvoice = newRow.getCell(88).getNumericCellValue(); //23
				this.valueOfPurchasesVAT = null; //22
			}

			this.amountOfDeductibleVat = newRow.getCell(98).getNumericCellValue(); //VAT24
			this.differenceInVatAccordingToCorrectiveInvoice = newRow.getCell(98).getNumericCellValue(); //VAT25
		
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
					


