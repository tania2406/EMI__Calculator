
package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    public static List<String[]> getData() {

        List<String[]> data = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                XSSFRow row = sheet.getRow(i);

                if (row == null) continue;


String loan = getValue(row.getCell(2));
String interest = getValue(row.getCell(3));
String tenure = getValue(row.getCell(4));

                data.add(new String[]{loan, interest, tenure});
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // ✅ IMPORTANT: This fixes wrong values
    public static String getValue(XSSFCell cell) {

        if (cell == null) return "";

        if (cell.getCellType() == CellType.NUMERIC) {

            double value = cell.getNumericCellValue();

            if (value % 1 == 0) {
                return String.valueOf((long) value);  // remove .0
            } else {
                return String.valueOf(value);         // keep decimal
            }
        }

        return cell.toString().trim();
    }
}