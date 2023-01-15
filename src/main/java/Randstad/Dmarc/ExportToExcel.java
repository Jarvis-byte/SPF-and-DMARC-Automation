package Randstad.Dmarc;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExportToExcel {
    public void export(ArrayList<Data> list) throws IOException {
        File fis = new File("C:\\Users\\arkam\\IdeaProjects\\SPF_And_DMARC_Record\\src\\main\\Data\\Opco_Name.xlsx");
        //  String excelPath = ".\\src\\main\\Data\\Opco_Name.xlsx";
        FileInputStream inputStream = new FileInputStream(fis);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        for (int r = 1; r < rows; r++) {
            XSSFRow row = sheet.getRow(r);

            for (int c = 3; c < 5; c++) {
                XSSFCell cell = row.createCell(c);
                if (c == 3) {
                    cell.setCellValue(list.get(r).getSpfPolicy());
                } else {
                    cell.setCellValue(list.get(r).getDMARCPolicy());
                }
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(".\\src\\main\\Data\\Opco_Name.xlsx"));

            workbook.write(out);
            out.close();
            System.out.println("Opco_.xlsx has been created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }

    }
}
