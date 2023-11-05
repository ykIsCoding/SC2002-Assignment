package Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

public class DatabaseUtils {
	 public static void main(String[] args) {
	        try {
	            FileInputStream fileInputStream = new FileInputStream("example.xlsx");
	            Workbook workbook = new XSSFWorkbook(fileInputStream);
	            Sheet sheet = workbook.getSheetAt(0); // Access the first sheet

	            for (Row row : sheet) {
	                for (Cell cell : row) {
	                    System.out.print(cell.toString() + "\t");
	                }
	                System.out.println();
	            }

	            workbook.close();
	            fileInputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
