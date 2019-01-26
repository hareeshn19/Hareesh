package com.company.ebay.util;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class XlsTestDataReader {
	public String[][] ReadTable(String xlpath,String sname,String tablename) throws BiffException, IOException
	{
		
		Workbook wb =Workbook.getWorkbook(new File(xlpath));
		Sheet sh=wb.getSheet(sname);
		Cell tablestart=sh.findCell(tablename);
		int startrow=tablestart.getRow();
		int startcolumn=tablestart.getColumn();
		Cell tablened =sh.findCell(tablename, startcolumn+1, startrow+1, 100, 6400,false);
		int endrow=tablened.getRow();
		int endcol=tablened.getColumn();
		System.out.println("Test:  " + tablename + " startRow=" + startrow
				+ ", endRow=" + endrow + ", " + "startCol=" + startcolumn
				+ ", endCol=" + endcol);
		String[][] s=null;;
		s = new String[endrow - startrow - 1][endcol - startcolumn - 1];
		int ci=0,cj;
		for(int i=startrow+1;i<endrow;i++,ci++)
		{
		cj=0;
		for(int j=startcolumn+1;j<endcol;j++,cj++)	
		{
			s[ci][cj]=sh.getCell(j, i).getContents();
			
		}
		}
		
		return s;
	}

}
