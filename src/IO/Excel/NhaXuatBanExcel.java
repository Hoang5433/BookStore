
package IO.Excel;

import DTO.NhaXuatBanDTO;
import GUI.SweetAlert.SweetAlert;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NhaXuatBanExcel extends ObjectExcel{
        public static final int CELL_MANHAXUATBAN = 0;
        public static final int CELL_TENNHAXUATBAN  = 1;
        public static ArrayList<NhaXuatBanDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
        {     
            if(!ReadFile(excelFilePath))
            {
                SweetAlert.showSweetAlert(new JFrame(), "File rỗng", "Vui lòng chọn file excel", 1);
            }
            if(!VerifyData(fields)){
                SweetAlert.showSweetAlert(new JFrame(), "Dữ liệu không khớp", "Vui lòng chọn đúng file dữ liệu", 1);
            }
            ArrayList<NhaXuatBanDTO> accountList = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
            Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) 
                    {
                        continue;
                    }
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    NhaXuatBanDTO dto = new NhaXuatBanDTO();
                    while (cellIterator.hasNext()) 
                    {
                        Cell cell = cellIterator.next();
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) 
                        {
                        case CELL_MANHAXUATBAN:
                            dto.setMaNhaXuatBan(cell.getStringCellValue());
                            break;
                        case CELL_TENNHAXUATBAN:
                            dto.setTenNhaXuatBan(cell.getStringCellValue());
                            break;
                        }
                    }
                    accountList.add(dto);
            }
            return accountList;
        }
        public static void ExportExcelFile( String excelFilePath, ArrayList<NhaXuatBanDTO> accountList ) throws IOException
        {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();            
        // Chỉnh font,...
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight((short)3);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Ghi Header
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);
        Cell cellHeader = headerRow.createCell(CELL_MANHAXUATBAN);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã nhà xuất bản");
 
        cellHeader = headerRow.createCell(CELL_TENNHAXUATBAN);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Tên nhà xuất bản");

        int rowNum = 1;
        for (int i = 0;i < accountList.size();i++){
            Row row =  sheet.createRow(rowNum++);
            Cell cell = row.createCell(CELL_MANHAXUATBAN);
            cell.setCellValue(accountList.get(i).getMaNhaXuatBan());
            
            cell = row.createCell(CELL_TENNHAXUATBAN);
            cell.setCellValue(accountList.get(i).getTenNhaXuatBan());
            

        }
        // Canh đều column
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        OutputStream fileOut = new FileOutputStream(new File(excelFilePath));
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("6120-Success");
    }   
}
