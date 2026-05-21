//package utils;
//
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelReader {
//
//    public static List<String[]> getData() {
//
//        List<String[]> data = new ArrayList<>();
//
//        try {
//            FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
//            Workbook wb = new XSSFWorkbook(fis);
//            Sheet sheet = wb.getSheet("Sheet1");
//
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//
//                Row row = sheet.getRow(i);
//                if (row == null) continue;
//
//                String loan = getValue(row.getCell(0));
//                String interest = getValue(row.getCell(1));
//                String tenure = getValue(row.getCell(2));
//
//                data.add(new String[]{loan, interest, tenure});
//            }
//
//            wb.close();
//            fis.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }
//
//    // ✅ Fix numeric value issue
//    public static String getValue(Cell cell) {
//
//        if (cell == null) return "";
//
//        if (cell.getCellType() == CellType.NUMERIC) {
//            double value = cell.getNumericCellValue();
//
//            if (value % 1 == 0) {
//                return String.valueOf((long) value);
//            } else {
//                return String.valueOf(value);
//            }
//        }
//
//        return cell.toString().trim();
//    }
//}


package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static List<String[]> getData() {

        List<String[]> data = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheet("Sheet1");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                String loan = getValue(row.getCell(0));
                String interest = getValue(row.getCell(1));
                String tenure = getValue(row.getCell(2));

                data.add(new String[]{loan, interest, tenure});
            }

            wb.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private static String getValue(Cell cell) {

        if (cell == null) return "";

        if (cell.getCellType() == CellType.NUMERIC) {
            double val = cell.getNumericCellValue();

            if (val % 1 == 0) {
                return String.valueOf((long) val); // remove .0
            } else {
                return String.valueOf(val);
            }
        }

        return cell.toString().trim();
    }
}