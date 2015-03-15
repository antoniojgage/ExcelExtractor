package com.hp.russiaextractor.transform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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

import com.hp.russiaextractor.vo.SalesLedgerVO;

public class SalesLedgerTransformer {

	
	 public static void main(String[] args) throws FileNotFoundException {
	 // public void readWriteSpreadsheet(File in, File out) throws FileNotFoundException {
		
		// String in2 = "C:\\Users\\gagean\\Documents\\FY15-GFIT-GRC-FIN-STD-BRAZIL\\Russia Java Project\\Sales Ledger HPFS Analysis.xlsx";
		String in3 = "C:\\Users\\jrcoo_000\\Desktop\\Sales Ledger MS AX Original.xlsx";
		//InputStream inputS = new FileInputStream(in2);
		InputStream inputS = new FileInputStream(in3);
		
		// ArrayList<SalesLedgerVO> salesLedgerList = new ArrayList<SalesLedgerVO>();
		
		try {
			
			Workbook iwb = WorkbookFactory.create(inputS);
			Sheet inSheet = iwb.getSheetAt(0);
			
			Workbook outWorkbook;
			Sheet outSheet;
			Row outRow;
			Cell outCell;
			int rowNumber;
			int SL1 =5;
			String data1;
			
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
			
			rowNumber = 1;
	
		for(Row inRow: inSheet) { 
			if((inRow.getRowNum() > 18) && (inRow.getCell(0) != null) && (inRow.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC)) { 
			
			outRow = outSheet.createRow(rowNumber); //creates row on new virutal worksheet starting at row 1
			
			
		for(int r = 3; r < 5; r++){
				
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
			data1 = inRow.getCell(SL1).getStringCellValue();
			System.out.println(data1);
				
			}
			rowNumber++;
			}
	}
		
		Row row = inSheet.getRow(19);
	 	Cell cell = row.getCell(4);
		System.out.print(cell.getStringCellValue());
		FileOutputStream fileOut = new FileOutputStream("C:\\users\\jrcoo_000\\Desktop\\workbook.xls");
	    outWorkbook.write(fileOut);
	    fileOut.close();
		outWorkbook.close();

			 
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

