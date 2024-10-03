package api.utilities;

import org.testng.annotations.DataProvider;

import java.util.*;

public class DataProviders {

    @DataProvider(name = "UserData")
    public static Iterator<Map<String, String>> getAllData() throws Exception {
        List<Map<String,String>> dataList = new ArrayList<>();

        String excelFilePath = System.getProperty("user.dir")+"//testData//UserData.xlsx";
        ExcelUtil.setExcelFile(excelFilePath);
        int totalRows = ExcelUtil.getRowCount("Sheet1");
        int totalCols = ExcelUtil.getCellCount("Sheet1");

        for(int i=1; i<=totalRows; i++){
            Map<String, String> rowDataMap = new HashMap<>();
            for(int j=0; j<totalCols; j++){
                //Store header as Key and Data as value
                rowDataMap.put(ExcelUtil.getCellData(0,j,"Sheet1"), ExcelUtil.getCellData(i,j,"Sheet1"));
            }
            dataList.add(rowDataMap);
        }
        return dataList.iterator();
    }

    @DataProvider(name = "UserNames")
    public static Iterator<String> getUserNames() throws Exception {
        String excelFilePath = System.getProperty("user.dir")+"//testData//UserData.xlsx";
        ExcelUtil.setExcelFile(excelFilePath);
        int totalRows = ExcelUtil.getRowCount("Sheet1");
        String usernames[] = new String[totalRows];

        for(int i=1; i<=totalRows; i++){
            usernames[i-1] = ExcelUtil.getCellData(i, 1, "Sheet1");
        }
        return Arrays.stream(usernames).iterator();
    }

}
