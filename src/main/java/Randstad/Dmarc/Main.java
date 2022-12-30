package Randstad.Dmarc;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String excelPath = ".\\src\\main\\Data\\Opco_Name.xlsx";

        //Obj for read data
        DataFromExcel dataFromExcel= new DataFromExcel();
        dataFromExcel.readData(excelPath);

    }
}