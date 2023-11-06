package Utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellAddress;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;


public class DatabaseUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String excelFilePath = "staff_list.xlsx";

        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            // Access the first sheet in the Excel workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Read data from the Excel file
            readDataFromExcel(sheet);

            // Modify or write data to the Excel file
            writeDataToExcel(sheet);

            // Save the changes back to the Excel file
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	   // Read data from the Excel sheet
    private static void readDataFromExcel(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                // Check the cell type and process accordingly
                if (cell.getCellType() == CellType.STRING) {
                    System.out.print(cell.getStringCellValue() + "\t");
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    System.out.print(cell.getNumericCellValue() + "\t");
                }
            }
            System.out.println();
        }
    }
    
    	// Write data to the Excel sheet
    private static void writeDataToExcel(Sheet sheet) {
        // Create a new row and cell for writing data
        Row newRow = sheet.createRow(2); // For example, creating a new row at index 2
        Cell newCell = newRow.createCell(0); // For example, creating a new cell at column index 0

        // Set the cell value (you can use different data types)
        newCell.setCellValue("New Data");
    }
}
