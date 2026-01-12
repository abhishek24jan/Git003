package utils;

import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

public class ExcelUtils {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws Exception {

        FileInputStream fis = new FileInputStream("testdata/LoginData.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet("Sheet1");

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][1];

        Row headerRow = sheet.getRow(0);
        List<String> keys = new ArrayList<>();

        for (int i = 0; i < colCount; i++) {
            keys.add(headerRow.getCell(i).getStringCellValue());
        }

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            HashMap<String, String> map = new HashMap<>();

            for (int j = 0; j < colCount; j++) {
                String value = row.getCell(j).toString();
                map.put(keys.get(j), value);
            }
            data[i - 1][0] = map;
        }

        wb.close();
        return data;
    }
}
