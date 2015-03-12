package com.hp.russiaextractor.transform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.hp.russiaextractor.vo.SalesLedgerVO;

public class SalesLedgerTransformer {

	 public static void main(String[] args) throws FileNotFoundException {
	 // public void readWriteSpreadsheet(File in, File out) throws FileNotFoundException {
		
		 String in2 = "C:\\Users\\gagean\\Documents\\FY15-GFIT-GRC-FIN-STD-BRAZIL\\Russia Java Project\\Sales Ledger HPFS Analysis.xlsx";
		
		InputStream inputS = new FileInputStream(in2);
		
		// ArrayList<SalesLedgerVO> salesLedgerList = new ArrayList<SalesLedgerVO>();
		
		try {
			
			Workbook iwb = WorkbookFactory.create(inputS);
			Sheet inSheet = iwb.getSheetAt(0);
			
			private Workbook outWorkbook;
			private Sheet outSheet;
			private Row outRow;
			private Cell outCell;
			private int rowNumber;
			
			// Row inRow = inSheet.getRow(); 
			// boolean getNextRow = true;
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
			outSheet = outWorkbook.createSheet("antonio");
			
			rowNumber = 0;
	
		for(Row inRow: inSheet) { 
			if((inRow.getRowNum() > 19) && !(inRow.getCell(0).getCellType()==Cell.CELL_TYPE_BLANK)) { 
				
			outRow = outSheet.createRow(rowNumber); //creates row on new virutal worksheet starting at row 1
			
		for(int r = 0; r < 36; r++){ 
				outRow.createCell(r).setCellValue(inRow.getCell(r).getStringCellValue());
			}
			rowNumber++;
			}
	}
	

			 
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

