package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}

	public int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		fi.close();
		return cellcount;
		
	}

	public String getCellData(String sheetName ,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		
		String data;
		try
		{
			//data=cell.toString();
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);// returns the value of cell i string
			
		}
		catch (Exception e)
		{
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
		
	}
	
	public void setCellData(String sheetName, int rownum,int colnum, String data) throws IOException
	{
		File xlfile=new File(path);
		if(xlfile.exists())  //if file not exist then create new file
		{
		wb=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		wb.write(fo);
		}
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetName)==-1) //if sheet not exist then create new sheet
			wb.createSheet(sheetName);
		ws=wb.getSheet(sheetName);
		
		if(ws.getRow(rownum)==null) // if row not exist then create new row
			ws.createRow(rownum);
		row=ws.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillGreenColor(String sheetName, int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		
		style=wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		
		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String sheetName, int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		
		style=wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		
		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
