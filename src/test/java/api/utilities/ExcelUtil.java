package api.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtil {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

    /**
     * This method is to set the File path and to open the Excel file Pass Excel
     * Path and SheetName as Arguments to this method
     *
     * @param Path
     * @throws Exception
     */
    public static void setExcelFile(String Path) throws Exception {
        FileInputStream ExcelFile = new FileInputStream(Path);
        ExcelWBook = new XSSFWorkbook(ExcelFile);
    }

    /**
     * This method is to read the test data from the Excel cell In this we are
     * passing Arguments as Row Num, Col Num & Sheet Name
     *
     * @param RowNum
     * @param ColNum
     * @param SheetName
     * @return String
     * @throws Exception
     */
    public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        DataFormatter formatter = new DataFormatter();
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            return formatter.formatCellValue(Cell);     //Returns the formatted value of cell as a String
        } catch (Exception e) {
            return "";
        }
    }

    // This method is to get the row count used of the excel sheet
    public static int getRowCount(String SheetName) {
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        int number = ExcelWSheet.getLastRowNum();
        return number;
    }

    //Method to get the col count of the excel sheet
    public static int getCellCount(String SheetName){
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        int colCount = ExcelWSheet.getRow(1).getLastCellNum();
        return colCount;
    }
}
