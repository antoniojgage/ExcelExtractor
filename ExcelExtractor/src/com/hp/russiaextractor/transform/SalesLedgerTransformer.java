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
	
		InputStream inputS = new FileInputStream(open);
		
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
			
			
		
			 boolean getNextRow = true;
			
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
			
			
			rowNumber++;
		} 
			
	}
	}else
		{
		
		//Sales Ledger Logic
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
		outRow.createCell(10).setCellValue("getNameOfPurchaser");
		outRow.createCell(11).setCellValue("getTinOfPurchaser");
		outRow.createCell(12).setCellValue("getCrrOfPurchaser");
		outRow.createCell(13).setCellValue("getNameOfIntermediary");
		outRow.createCell(14).setCellValue("getTinOfIntermediary");
		outRow.createCell(15).setCellValue("getCrrofIntermediary");
		outRow.createCell(16).setCellValue("getNumberOfPaymentConfirmationDocument");
		outRow.createCell(17).setCellValue("getDateOfPaymentConfirmationDocument"); 
		outRow.createCell(18).setCellValue("getCurrencyCodePerOKV"); 
		outRow.createCell(19).setCellValue("NumberOfCustomsDeclaration");
		outRow.createCell(20).setCellValue("CurrencyCodePerOKV");
		outRow.createCell(21).setCellValue("getValueOfSalesInvoiceCurrency"); 
		outRow.createCell(22).setCellValue("getValueOfSalesRur");
		outRow.createCell(23).setCellValue("getDifferenceInValueInvoiceCurrency");
		outRow.createCell(24).setCellValue("getDifferenceInValueInvoiceRur");
		outRow.createCell(25).setCellValue("getValueOfSales18");
		outRow.createCell(26).setCellValue("getValueOfSales10");
		outRow.createCell(27).setCellValue("getValueOfSales0");
		outRow.createCell(28).setCellValue("getAmountOfVat18");
		outRow.createCell(29).setCellValue("getAmountOfVat10");
		outRow.createCell(30).setCellValue("getValueOfTaxExemptSales");
		outRow.createCell(31).setCellValue("getDifferenceInValueOfTaxExemptSales");
		outRow.createCell(32).setCellValue("getDifferenceOfCorrective18");
		outRow.createCell(33).setCellValue("getDifferenceOfCorrective10");
		outRow.createCell(34).setCellValue("getDifferenceInValueInvoiceRur");
		outRow.createCell(35).setCellValue("getDifferenceInValueInvoiceRur");
		
		
	for(Row inRow: inSheet) { 
		if((inRow.getRowNum() > 18) && (inRow.getCell(0) != null)  && (inRow.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) && !(inRow.getCell(0).getCellType() == Cell.CELL_TYPE_ERROR))  { 
			

		SalesLedgerVO purchase = new SalesLedgerVO();	
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
		outRow.createCell(9).setCellValue(purchase.getDateOfAdjustedSellersCorrectiveInvoice()); //10
		outRow.createCell(10).setCellValue(purchase.getNameOfPurchaser()); //11
		outRow.createCell(11).setCellValue(purchase.getTinOfPurchaser()); //12
		outRow.createCell(12).setCellValue(purchase.getCrrOfPurchaser()); //13
		outRow.createCell(13).setCellValue(purchase.getNameOfIntermediary()); //14
		outRow.createCell(14).setCellValue(purchase.getTinOfIntermediary()); //15
		outRow.createCell(15).setCellValue(purchase.getCrrofIntermediary()); //16
		outRow.createCell(16).setCellValue(purchase.getNumberOfPaymentConfirmationDocument()); //17
		outRow.createCell(17).setCellValue(purchase.getDateOfPaymentConfirmationDocument()); //18
		outRow.createCell(18).setCellValue(purchase.getCurrencyCodePerOKV()); //19
		
		if (purchase.getValueOfSalesInvoiceCurrency()==null) outRow.createCell(19).setCellValue(new String("-"));
		else outRow.createCell(19).setCellValue(purchase.getValueOfSalesInvoiceCurrency()); //20
		
		if (purchase.getValueOfSalesRur()==null) outRow.createCell(20).setCellValue(new String("-"));
		else outRow.createCell(20).setCellValue(purchase.getValueOfSalesRur()); //21
		
		if (purchase.getDifferenceInValueInvoiceCurrency()==null) outRow.createCell(21).setCellValue(new String("-"));
		else outRow.createCell(21).setCellValue(purchase.getDifferenceInValueInvoiceCurrency()); //22
		
		if (purchase.getDifferenceInValueInvoiceRur()==null) outRow.createCell(22).setCellValue(new String("-"));
		else outRow.createCell(22).setCellValue(purchase.getDifferenceInValueInvoiceRur()); //23
		
		if (purchase.getValueOfSales18()==null) outRow.createCell(23).setCellValue(new String("-"));
		else outRow.createCell(23).setCellValue(purchase.getValueOfSales18()); //24
		
		if (purchase.getValueOfSales10()==null) outRow.createCell(24).setCellValue(new String("-"));
		else outRow.createCell(24).setCellValue(purchase.getValueOfSales10()); //25
		
		if (purchase.getValueOfSales0()==null) outRow.createCell(25).setCellValue(new String("-"));
		else outRow.createCell(25).setCellValue(purchase.getValueOfSales0()); //26
		
		if (purchase.getAmountOfVat18()==null) outRow.createCell(26).setCellValue(new String("-"));
		else outRow.createCell(26).setCellValue(purchase.getAmountOfVat18()); //27
		
		if (purchase.getAmountOfVat10()==null) outRow.createCell(27).setCellValue(new String("-"));
		else outRow.createCell(27).setCellValue(purchase.getAmountOfVat10()); //28
		
		if (purchase.getValueOfTaxExemptSales()==null) outRow.createCell(28).setCellValue(new String("-"));
		else outRow.createCell(28).setCellValue(purchase.getValueOfTaxExemptSales()); //29
		
		if (purchase.getDifferenceInValueOfTaxExemptSales()==null) outRow.createCell(29).setCellValue(new String("-"));
		else outRow.createCell(29).setCellValue(purchase.getDifferenceInValueOfTaxExemptSales()); //30
		
		if (purchase.getDifferenceOfCorrective18()==null) outRow.createCell(30).setCellValue(new String("-"));
		else outRow.createCell(30).setCellValue(purchase.getDifferenceOfCorrective18()); //31
		
		if (purchase.getDifferenceOfCorrective10()==null) outRow.createCell(31).setCellValue(new String("-"));
		else outRow.createCell(31).setCellValue(purchase.getDifferenceOfCorrective10()); //32
		
		if (purchase.getDifferenceOfCorrective0()==null) outRow.createCell(32).setCellValue(new String("-"));
		else outRow.createCell(32).setCellValue(purchase.getDifferenceOfCorrective0()); //33
		
		if (purchase.getDifferenceOfVat18()==null) outRow.createCell(33).setCellValue(new String("-"));
		else outRow.createCell(33).setCellValue(purchase.getDifferenceOfVat18()); //34
		
		if (purchase.getDifferenceOfVat10()==null) outRow.createCell(34).setCellValue(new String("-"));
		else outRow.createCell(34).setCellValue(purchase.getDifferenceOfVat10()); //35
		
		
		
		
		
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


