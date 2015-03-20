package com.hp.russiaextractor.transform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hp.russiaextractor.vo.PurchaseLedgerVO;
import com.hp.russiaextractor.vo.SalesLedgerVO;

public class SalesLedgerTransformer {

	
	 public static void run(File open, File save) throws FileNotFoundException {
	 // public void readWriteSpreadsheet(File in, File out) throws FileNotFoundException {
		
		// String in1 = "D:\\Dropbox\\Russia Java Project\\Purchase Ledger HPFS Analysis-combine.xlsx";
		// String in2 = "C:\\Users\\gagean\\Dropbox\\Russia Java Project\\Purchase Ledger HPFS Analysis-combine.xlsx";
		//String in3 = "C:\\Users\\jrcoo_000\\Desktop\\Purchase Ledger MS AX Original.xlsx";
		//InputStream inputS = new FileInputStream(in2);
		InputStream inputS = new FileInputStream(open);
		
		// ArrayList<SalesLedgerVO> salesLedgerList = new ArrayList<SalesLedgerVO>();
		
		try {
			
			Workbook iwb = WorkbookFactory.create(inputS);
			Sheet inSheet = iwb.getSheetAt(0);
			
			Workbook outWorkbook;
			Sheet outSheet;
			Row outRow, newRow;
			Cell outCell;
			int rowNumber;
			int SL1 =5;
			String data1;
			
			
			// Row inRow = inSheet.getRow(); 
			 boolean getNextRow = true;
			//Row row = null;
			/* 
			 * Kriegers original code
			 * for (int r = 0; getNextRow; row = sheet.getRow(r++)) {
				SalesLedgerVO salesLedgerVO = new SalesLedgerVO();
				//Field No
				salesLedgerVO.setNo(new Double(row.getCell(0).getNumericCellValue()).intValue());
				//salesLedgerVO.setTransactionTypeCode(row.getCell(1).getCellType());
				
				
				salesLedgerList.add(salesLedgerVO);
			} */
		
			//Logic for SL
			outWorkbook = new HSSFWorkbook(); //creates virtual workbook to store inputvalues
			outSheet = outWorkbook.createSheet("Conversion1");
			
			
			
			rowNumber = 1;
	
			//Run Purchase Ledger Logic if columns = 16
	if (inSheet.getRow(18).getCell(98).getStringCellValue().equalsIgnoreCase("16")) {
			
			outRow = outSheet.createRow(0);
			outRow.createCell(0).setCellValue("getNo");
			outRow.createCell(1).setCellValue("TransactionTypeCode");
			outRow.createCell(2).setCellValue("InvoiceDate");
			outRow.createCell(3).setCellValue("SellersInvoice");
			outRow.createCell(4).setCellValue("SellersAdjustmentAmount"); //5
			outRow.createCell(5).setCellValue("DateOfSellersAdjustment");
			outRow.createCell(6).setCellValue("SellersCorrectiveInvoiceNo");
			outRow.createCell(7).setCellValue("DateOfCorrectiveSellersInvoice");
			outRow.createCell(8).setCellValue("AdjustiveSellersCorrectiveInvoiceNo");
			outRow.createCell(9).setCellValue("DateOfAdjustedSellersCorrectiveInvoice");
			outRow.createCell(10).setCellValue("NumberOfPaymentConfirmationDocument");
			outRow.createCell(11).setCellValue("DateOfPaymentConfirmationDocument");
			outRow.createCell(12).setCellValue("DateOfRecording");
			outRow.createCell(13).setCellValue("NameOfSeller");
			outRow.createCell(14).setCellValue("TinOfSeller");
			outRow.createCell(15).setCellValue("CrrOfSeller");
			outRow.createCell(16).setCellValue("NameOfIntermediary");
			outRow.createCell(17).setCellValue("TinOfIntermediary"); 
			outRow.createCell(18).setCellValue("CrrofIntermediary"); 
			outRow.createCell(19).setCellValue("NumberOfCustomsDeclaration");
			outRow.createCell(20).setCellValue("CurrencyCodePerOKV");
			outRow.createCell(21).setCellValue("ValueOfPurchasesVAT"); 
			outRow.createCell(22).setCellValue("DifferenceInValueVatToCorrectiveInvoice");
			outRow.createCell(23).setCellValue("AmountOfDeductibleVat");
			outRow.createCell(23).setCellValue("DifferenceInVatAccordingToCorrectiveInvoice");
			
			
	
		for(Row inRow: inSheet) { 
			if((inRow.getRowNum() > 18) && (inRow.getCell(0) != null)  && (inRow.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) && !(inRow.getCell(0).getCellType() == Cell.CELL_TYPE_ERROR))  { 
				
	
			PurchaseLedgerVO purchase = new PurchaseLedgerVO();	
			//outRow = outSheet.createRow(rowNumber); //creates row on new virutal worksheet starting at row 2
			purchase.transformRow(inRow);
			outRow = outSheet.createRow(rowNumber);
			outRow.createCell(0).setCellValue(purchase.getNo());
			outRow.createCell(1).setCellValue(purchase.getTransactionTypeCode());
			outRow.createCell(2).setCellValue(purchase.getInvoiceDate());
			outRow.createCell(3).setCellValue(purchase.getSellersInvoice());
			outRow.createCell(4).setCellValue(purchase.getSellersAdjustmentAmount()); //5
			outRow.createCell(5).setCellValue(purchase.getDateOfSellersAdjustment());
			outRow.createCell(6).setCellValue(purchase.getSellersCorrectiveInvoiceNo());
			outRow.createCell(7).setCellValue(purchase.getDateOfCorrectiveSellersInvoice());
			outRow.createCell(8).setCellValue(purchase.getAdjustiveSellersCorrectiveInvoiceNo());
			outRow.createCell(9).setCellValue(purchase.getDateOfAdjustedSellersCorrectiveInvoice());
			outRow.createCell(10).setCellValue(purchase.getNumberOfPaymentConfirmationDocument());
			outRow.createCell(11).setCellValue(purchase.getDateOfPaymentConfirmationDocument());
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (purchase.getDateOfRecording()==null) outRow.createCell(12).setCellValue(new String("-"));
			else outRow.createCell(12).setCellValue(sdf.format(purchase.getDateOfRecording().getTime()));
			outRow.createCell(13).setCellValue(purchase.getNameOfSeller());
			outRow.createCell(14).setCellValue(purchase.getTinOfSeller());
			outRow.createCell(15).setCellValue(purchase.getCrrOfSeller());
			outRow.createCell(16).setCellValue(purchase.getNameOfIntermediary());
			outRow.createCell(17).setCellValue(purchase.getTinOfIntermediary()); 
			outRow.createCell(18).setCellValue(purchase.getCrrofIntermediary()); 
			outRow.createCell(19).setCellValue(purchase.getNumberOfCustomsDeclaration());
			outRow.createCell(20).setCellValue(purchase.getCurrencyCodePerOKV());
			if (purchase.getValueOfPurchasesVAT()==null) outRow.createCell(21).setCellValue(new String("-"));
			else outRow.createCell(21).setCellValue(purchase.getValueOfPurchasesVAT()); 
			if (purchase.getDifferenceInValueVatToCorrectiveInvoice()==null) outRow.createCell(22).setCellValue(new String("-")); 
			else outRow.createCell(22).setCellValue(purchase.getDifferenceInValueVatToCorrectiveInvoice());
			if (purchase.getAmountOfDeductibleVat()==null) outRow.createCell(23).setCellValue(new String("-"));
			else outRow.createCell(23).setCellValue(purchase.getAmountOfDeductibleVat());
			if (purchase.getDifferenceInVatAccordingToCorrectiveInvoice()==null) outRow.createCell(24).setCellValue(new String("-"));
			else outRow.createCell(24).setCellValue(purchase.getDifferenceInVatAccordingToCorrectiveInvoice());
			
			
/*		for(int r = 0; r < 10; r++){
				
			switch (inRow.getCell(r).getCellType()){
			
				case Cell.CELL_TYPE_NUMERIC:
					if(DateUtil.isCellDateFormatted(inRow.getCell(r)))
						outRow.createCell(r).setCellValue(inRow.getCell(r).getDateCellValue());
						else
							outRow.createCell(r).setCellValue(inRow.getCell(r).getNumericCellValue());
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR:
					break;
				case Cell.CELL_TYPE_FORMULA:
					break;
		
					default:{
						//CellRangeAddress SL1 = CellRangeAddress.valueOf("F2:G2");
						//outSheet.addMergedRegion(SL1);
						
						outRow.createCell(r).setCellValue(inRow.getCell(r).getStringCellValue());
					}
				}
		//	data1 = purchase.getNo().toString();
			//System.out.println(data1);
			//System.out.println(purchase.getTransactionTypeCode());
						
			}*/
			rowNumber++;
		} 
			
	}
	}else
		{
		outRow = outSheet.createRow(0);
		outRow.createCell(0).setCellValue("getNo");
		outRow.createCell(1).setCellValue("TransactionTypeCode");
		outRow.createCell(2).setCellValue("InvoiceDate");
		outRow.createCell(3).setCellValue("SellersInvoice");
		outRow.createCell(4).setCellValue("SellersAdjustmentAmount"); //5
		outRow.createCell(5).setCellValue("DateOfSellersAdjustment");
		outRow.createCell(6).setCellValue("SellersCorrectiveInvoiceNo");
		outRow.createCell(7).setCellValue("DateOfCorrectiveSellersInvoice");
		outRow.createCell(8).setCellValue("AdjustiveSellersCorrectiveInvoiceNo");
		outRow.createCell(9).setCellValue("DateOfAdjustedSellersCorrectiveInvoice");
		outRow.createCell(10).setCellValue("NumberOfPaymentConfirmationDocument");
		outRow.createCell(11).setCellValue("DateOfPaymentConfirmationDocument");
		outRow.createCell(12).setCellValue("DateOfRecording");
		outRow.createCell(13).setCellValue("NameOfSeller");
		outRow.createCell(14).setCellValue("TinOfSeller");
		outRow.createCell(15).setCellValue("CrrOfSeller");
		outRow.createCell(16).setCellValue("NameOfIntermediary");
		outRow.createCell(17).setCellValue("TinOfIntermediary"); 
		outRow.createCell(18).setCellValue("CrrofIntermediary"); 
		outRow.createCell(19).setCellValue("NumberOfCustomsDeclaration");
		outRow.createCell(20).setCellValue("CurrencyCodePerOKV");
		outRow.createCell(21).setCellValue("ValueOfPurchasesVAT"); 
		outRow.createCell(22).setCellValue("DifferenceInValueVatToCorrectiveInvoice");
		outRow.createCell(23).setCellValue("AmountOfDeductibleVat");
		outRow.createCell(23).setCellValue("DifferenceInVatAccordingToCorrectiveInvoice");
		

	for(Row inRow: inSheet) { 
		if((inRow.getRowNum() > 18) && (inRow.getCell(0) != null)  && (inRow.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) && !(inRow.getCell(0).getCellType() == Cell.CELL_TYPE_ERROR))  { 
			

		PurchaseLedgerVO purchase = new PurchaseLedgerVO();	
		//outRow = outSheet.createRow(rowNumber); //creates row on new virutal worksheet starting at row 2
		purchase.transformRow(inRow);
		outRow = outSheet.createRow(rowNumber);
		outRow.createCell(0).setCellValue(purchase.getNo());
		outRow.createCell(1).setCellValue(purchase.getTransactionTypeCode());
		outRow.createCell(2).setCellValue(purchase.getInvoiceDate());
		outRow.createCell(3).setCellValue(purchase.getSellersInvoice());
		outRow.createCell(4).setCellValue(purchase.getSellersAdjustmentAmount()); //5
		outRow.createCell(5).setCellValue(purchase.getDateOfSellersAdjustment());
		outRow.createCell(6).setCellValue(purchase.getSellersCorrectiveInvoiceNo());
		outRow.createCell(7).setCellValue(purchase.getDateOfCorrectiveSellersInvoice());
		outRow.createCell(8).setCellValue(purchase.getAdjustiveSellersCorrectiveInvoiceNo());
		outRow.createCell(9).setCellValue(purchase.getDateOfAdjustedSellersCorrectiveInvoice());
		outRow.createCell(10).setCellValue(purchase.getNumberOfPaymentConfirmationDocument());
		outRow.createCell(11).setCellValue(purchase.getDateOfPaymentConfirmationDocument());
		outRow.createCell(12).setCellValue(purchase.getDateOfRecording());
		outRow.createCell(13).setCellValue(purchase.getNameOfSeller());
		outRow.createCell(14).setCellValue(purchase.getTinOfSeller());
		outRow.createCell(15).setCellValue(purchase.getCrrOfSeller());
		outRow.createCell(16).setCellValue(purchase.getNameOfIntermediary());
		outRow.createCell(17).setCellValue(purchase.getTinOfIntermediary()); 
		outRow.createCell(18).setCellValue(purchase.getCrrofIntermediary()); 
		outRow.createCell(19).setCellValue(purchase.getNumberOfCustomsDeclaration());
		outRow.createCell(20).setCellValue(purchase.getCurrencyCodePerOKV());
		outRow.createCell(21).setCellValue(purchase.getValueOfPurchasesVAT()); 
		outRow.createCell(22).setCellValue(purchase.getDifferenceInValueVatToCorrectiveInvoice());
		outRow.createCell(23).setCellValue(purchase.getAmountOfDeductibleVat());
		outRow.createCell(24).setCellValue(purchase.getDifferenceInVatAccordingToCorrectiveInvoice());
		}
		

		}
	
		
	}
	FileOutputStream fileOut = new FileOutputStream(save);
	//FileOutputStream fileOut = new FileOutputStream("D:\\Dropbox\\Russia Java Project\\workbook.xls");
	outWorkbook.write(fileOut);
	fileOut.close();
	outWorkbook.close();
		}catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 }
		
	 }
	 
}


