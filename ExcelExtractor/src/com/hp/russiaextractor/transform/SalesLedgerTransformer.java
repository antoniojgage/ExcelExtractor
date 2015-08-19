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
			outRow.createCell(0).setCellValue("No");
			outRow.createCell(1).setCellValue("Transaction type (code)");
			outRow.createCell(2).setCellValue("Invoice date");
			outRow.createCell(3).setCellValue("Number of seller’s invoice");
			outRow.createCell(4).setCellValue("Number of adjustment to seller’s invoice"); //5
			outRow.createCell(5).setCellValue("Date of adjustment to seller’s invoice");
			outRow.createCell(6).setCellValue("Number of seller’s corrective invoice");
			outRow.createCell(7).setCellValue("Date of seller’s corrective invoice");
			outRow.createCell(8).setCellValue("Number of adjustment to seller’s corrective invoice");
			outRow.createCell(9).setCellValue("Date of adjustment to seller’s corrective invoice");
			outRow.createCell(10).setCellValue("Number of payment confirmation document");
			outRow.createCell(11).setCellValue("Date of payment confirmation document");
			outRow.createCell(12).setCellValue("Date of recording");
			outRow.createCell(13).setCellValue("Name of seller");
			outRow.createCell(14).setCellValue("TIN of seller");
			outRow.createCell(15).setCellValue("CRR of seller");
			outRow.createCell(16).setCellValue("Name of intermediary");
			outRow.createCell(17).setCellValue("TIN of intermediary"); 
			outRow.createCell(18).setCellValue("CRR of intermediary"); 
			outRow.createCell(19).setCellValue("Number of customs declaration");
			outRow.createCell(20).setCellValue("Currency code per OKV");
			outRow.createCell(21).setCellValue("Value of purchases, including VAT "); 
			outRow.createCell(22).setCellValue("Difference in value inclusive of VAT according to corrective invoice");
			outRow.createCell(23).setCellValue("Amount of deductible VAT ");
			outRow.createCell(24).setCellValue("Difference in VAT according to corrective invoice");
			outRow.createCell(25).setCellValue("Is this line copied from an additional sheet of purchase book? (yes/no)");
			
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
			if (purchase.getDateOfRecording()==null) outRow.createCell(12).setCellValue(new String(""));
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
			if (purchase.getDifferenceInValueVatToCorrectiveInvoice()==null) outRow.createCell(22).setCellValue(new String("")); 
			else outRow.createCell(22).setCellValue(purchase.getDifferenceInValueVatToCorrectiveInvoice());
			if (purchase.getAmountOfDeductibleVat()==null) outRow.createCell(23).setCellValue(new String(""));
			else outRow.createCell(23).setCellValue(purchase.getAmountOfDeductibleVat());
			if (purchase.getDifferenceInVatAccordingToCorrectiveInvoice()==null) outRow.createCell(24).setCellValue(new String(""));
			else outRow.createCell(24).setCellValue(purchase.getDifferenceInVatAccordingToCorrectiveInvoice());
			
			
			rowNumber++;
		} 
			
	}
	}else
		{
		
		//Sales Ledger Logic
		outRow = outSheet.createRow(0);
		outRow.createCell(0).setCellValue("No"); //1
		outRow.createCell(1).setCellValue("Transaction type (code)"); //2
		outRow.createCell(2).setCellValue("Invoice date"); //3
		outRow.createCell(3).setCellValue("Number of seller's invoice"); //4
		outRow.createCell(4).setCellValue("Number of seller's invoice adjustment"); //5
		outRow.createCell(5).setCellValue("Date of seller's invoice adjustment"); //6
		outRow.createCell(6).setCellValue("Number of seller's corrective invoice"); //7
		outRow.createCell(7).setCellValue("Date of seller's corrective invoice"); //8
		outRow.createCell(8).setCellValue("Number of adjustment to seller's corrective invoice"); //9
		outRow.createCell(9).setCellValue("Date of adjustment to seller's corrective invoice");
		outRow.createCell(10).setCellValue("Name of purchaser");
		outRow.createCell(11).setCellValue("TIN of purchaser");
		outRow.createCell(12).setCellValue("CRR of purchaser");
		outRow.createCell(13).setCellValue("Name of intermediary");
		outRow.createCell(14).setCellValue("TIN of intermediary");
		outRow.createCell(15).setCellValue("CRR of intermediary");
		outRow.createCell(16).setCellValue("Number of payment confirmation document");
		outRow.createCell(17).setCellValue("Date of payment confirmation document"); 
		outRow.createCell(18).setCellValue("Currency code per OKV"); 
		outRow.createCell(19).setCellValue("Value of sales, including VAT in invoice currency "); 
		outRow.createCell(20).setCellValue("Value of sales, including VAT in RUR");
		outRow.createCell(21).setCellValue("Difference in value according to corrective invoice, in invoice currency");
		outRow.createCell(22).setCellValue("Difference in value according to corrective invoice, in RUR");
		outRow.createCell(23).setCellValue("Value of sales (excluding VAT) at the rate of 18%");
		outRow.createCell(24).setCellValue("Value of sales (excluding VAT) at the rate of 10%");
		outRow.createCell(25).setCellValue("Value of sales (excluding VAT) at the rate of 0%");
		outRow.createCell(26).setCellValue("Amount of VAT (rate - 18%)");
		outRow.createCell(27).setCellValue("Amount of VAT (rate - 10%)");
		outRow.createCell(28).setCellValue("Value of tax-exempt sales");
		outRow.createCell(29).setCellValue("Difference in value of tax-exempt sales according to corrective invoice");
		outRow.createCell(30).setCellValue("Difference in value excluding VAT according to corrective invoice (18%)");
		outRow.createCell(31).setCellValue("Difference in value excluding VAT according to corrective invoice (10%)");
		outRow.createCell(32).setCellValue("Difference in value excluding VAT according to corrective invoice (0%)");
		outRow.createCell(33).setCellValue("Difference in VAT according to corrective invoice (18%)");
		outRow.createCell(34).setCellValue("Difference in VAT according to corrective invoice (10%)");
		outRow.createCell(35).setCellValue("Is this line copied from an additional sheet of sales book? (yes/no)");
		
		
		
	for(Row inRow: inSheet) { 
		if((inRow.getRowNum() > 18) && (inRow.getCell(0) != null)  && (inRow.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) && !(inRow.getCell(0).getCellType() == Cell.CELL_TYPE_ERROR))  { 
			

			SalesLedgerVO sales = new SalesLedgerVO();	
			//outRow = outSheet.createRow(rowNumber); //creates row on new virutal worksheet starting at row 2
			sales.transformRow(inRow);
			outRow = outSheet.createRow(rowNumber);
			outRow.createCell(0).setCellValue(sales.getNo()); //1
			System.out.println(sales.getNo()); //2
			outRow.createCell(1).setCellValue(sales.getTransactionTypeCode()); //3
			outRow.createCell(2).setCellValue(sales.getSellersInvoice()); //4
			outRow.createCell(3).setCellValue(sales.getInvoiceDate()); //5
			outRow.createCell(4).setCellValue(sales.getSellersAdjustmentAmount()); //6
			outRow.createCell(5).setCellValue(sales.getDateOfSellersAdjustment()); //7 
			outRow.createCell(6).setCellValue(sales.getSellersCorrectiveInvoiceNo()); //8 
			outRow.createCell(7).setCellValue(sales.getDateOfCorrectiveSellersInvoice()); //9
			outRow.createCell(8).setCellValue(sales.getAdjustiveSellersCorrectiveInvoiceNo()); //10
			outRow.createCell(9).setCellValue(sales.getDateOfAdjustedSellersCorrectiveInvoice()); //11
			outRow.createCell(10).setCellValue(sales.getNameOfPurchaser()); //11
			outRow.createCell(11).setCellValue(sales.getTinOfPurchaser()); //12
			outRow.createCell(12).setCellValue(sales.getCrrOfPurchaser()); //13
			outRow.createCell(13).setCellValue(sales.getNameOfIntermediary()); //14
			outRow.createCell(14).setCellValue(sales.getTinOfIntermediary()); //15
			outRow.createCell(15).setCellValue(sales.getCrrofIntermediary()); //16
			outRow.createCell(16).setCellValue(sales.getNumberOfPaymentConfirmationDocument()); //17
			outRow.createCell(17).setCellValue(sales.getDateOfPaymentConfirmationDocument()); //18
			outRow.createCell(18).setCellValue(sales.getCurrencyCodePerOKV()); //19
			
			if (sales.getValueOfSalesInvoiceCurrency()==null) outRow.createCell(19).setCellValue(new String(""));
			else outRow.createCell(19).setCellValue(sales.getValueOfSalesInvoiceCurrency()); //20
			
			if (sales.getValueOfSalesRur()==null) outRow.createCell(20).setCellValue(new String(""));
			else outRow.createCell(20).setCellValue(sales.getValueOfSalesRur()); //21
			
			if (sales.getDifferenceInValueInvoiceCurrency()==null) outRow.createCell(21).setCellValue(new String(""));
			else outRow.createCell(21).setCellValue(sales.getDifferenceInValueInvoiceCurrency()); //22
			
			if (sales.getDifferenceInValueInvoiceRur()==null) outRow.createCell(22).setCellValue(new String(""));
			else outRow.createCell(22).setCellValue(sales.getDifferenceInValueInvoiceRur()); //23
			
			if (sales.getValueOfSales18()==null) outRow.createCell(23).setCellValue(new String(""));
			else outRow.createCell(23).setCellValue(sales.getValueOfSales18()); //24
			
			if (sales.getValueOfSales10()==null) outRow.createCell(24).setCellValue(new String(""));
			else outRow.createCell(24).setCellValue(sales.getValueOfSales10()); //25
			
			if (sales.getValueOfSales0()==null) outRow.createCell(25).setCellValue(new String(""));
			else outRow.createCell(25).setCellValue(sales.getValueOfSales0()); //26
			
			if (sales.getAmountOfVat18()==null) outRow.createCell(26).setCellValue(new String(""));
			else outRow.createCell(26).setCellValue(sales.getAmountOfVat18()); //27
			
			if (sales.getAmountOfVat10()==null) outRow.createCell(27).setCellValue(new String(""));
			else outRow.createCell(27).setCellValue(sales.getAmountOfVat10()); //28
			
			if (sales.getValueOfTaxExemptSales()==null) outRow.createCell(28).setCellValue(new String(""));
			else outRow.createCell(28).setCellValue(sales.getValueOfTaxExemptSales()); //29
			
			if (sales.getDifferenceInValueOfTaxExemptSales()==null) outRow.createCell(29).setCellValue(new String(""));
			else outRow.createCell(29).setCellValue(sales.getDifferenceInValueOfTaxExemptSales()); //30
			
			if (sales.getDifferenceOfCorrective18()==null) outRow.createCell(30).setCellValue(new String(""));
			else outRow.createCell(30).setCellValue(sales.getDifferenceOfCorrective18()); //31
			
			if (sales.getDifferenceOfCorrective10()==null) outRow.createCell(31).setCellValue(new String(""));
			else outRow.createCell(31).setCellValue(sales.getDifferenceOfCorrective10()); //32
			
			if (sales.getDifferenceOfCorrective0()==null) outRow.createCell(32).setCellValue(new String(""));
			else outRow.createCell(32).setCellValue(sales.getDifferenceOfCorrective0()); //33
			
			if (sales.getDifferenceOfVat18()==null) outRow.createCell(33).setCellValue(new String(""));
			else outRow.createCell(33).setCellValue(sales.getDifferenceOfVat18()); //34
			
			if (sales.getDifferenceOfVat10()==null) outRow.createCell(34).setCellValue(new String(""));
			else outRow.createCell(34).setCellValue(sales.getDifferenceOfVat10()); //35
			
			
			
			
		
		
		rowNumber++;
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


