package Randstad.Dmarc;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataFromExcel {
    public void readData(File excelPath) throws IOException {
        FileInputStream inputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        ArrayList<Data> list = new ArrayList<>();

        //UsingForLoop
        int rows = sheet.getLastRowNum();
        System.out.println("Rows\t"+rows);

        int cols = sheet.getRow(1).getLastCellNum();
        for (int i = 0; i <= rows; i++) {
            XSSFRow row = sheet.getRow(i);
            Data data = new Data();
            for (int j = 0; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                if (j == 0) {
                    data.setOpcoCode(cell.getStringCellValue());
                }
                if (j == 1) {
                    data.setDomainName(cell.getStringCellValue());
                }
                if (j == 2) {
                    data.setTransformation(cell.getStringCellValue());
                }


            }
            list.add(data);

        }
        HttpHandler httpHandler = new HttpHandler();
        httpHandler.GetRequest(list);


    }
}
