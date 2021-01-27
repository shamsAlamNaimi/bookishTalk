package com.bookishTalk.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.bookishTalk.pojo.BookPojo;

public class XlsView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<BookPojo> list= (List<BookPojo>) request.getAttribute("bookList");
		System.out.println("---------------------------------------------------------------bookList"+list);
		HSSFSheet sheet= workbook.createSheet("Book-Detail");
		
		CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
//        style.setFillForegroundColor(HSSFColor.BLUE.index);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Book Title");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Author");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Description");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Liked By");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Added By");
        header.getCell(4).setCellStyle(style);
        int rowCount=1;
        
        for (BookPojo Book : list) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(Book.getBookName());
            aRow.createCell(1).setCellValue(Book.getBookAuthor());
            aRow.createCell(2).setCellValue(Book.getDescription());
            aRow.createCell(3).setCellValue(Book.getLikedBy());
            aRow.createCell(4).setCellValue(Book.getAddedBy());
        }
        
		
		
		
		
	}

}
