package Randstad.Dmarc;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File fis=new File("C:\\Users\\arkam\\IdeaProjects\\SPF_And_DMARC_Record\\src\\main\\Data\\Opco_Name.xlsx");


        //Obj for read data
        DataFromExcel dataFromExcel= new DataFromExcel();
        dataFromExcel.readData(fis);

    }
}