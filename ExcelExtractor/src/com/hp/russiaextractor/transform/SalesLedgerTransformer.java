package com.hp.russiaextractor.transform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
			Workbook wb = WorkbookFactory.create(inputS);
			Sheet sheet = wb.getSheetAt(0);
			// boolean getNextRow = true;
			//Row row = null;
			Row row = sheet.getRow(23);
			Cell cell = row.getCell(27);
			System.out.print(cell.getStringCellValue());

			/* for (int r = 0; getNextRow; row = sheet.getRow(r++)) {
				SalesLedgerVO salesLedgerVO = new SalesLedgerVO();
				//Field No
				salesLedgerVO.setNo(new Double(row.getCell(0).getNumericCellValue()).intValue());
				//salesLedgerVO.setTransactionTypeCode(row.getCell(1).getCellType());
				
				
				salesLedgerList.add(salesLedgerVO);
				
				
				
			} */
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
