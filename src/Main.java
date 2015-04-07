
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class Main{

    public static void main(String[] args) {

        Main main = new Main();
        try{
            //String dir = "JillData/nysp2i/doublefrozenbottles/";
            String filename = "JillData/nysp2i/doublefrozenbottles/doublefrozenbottles_1.txt";
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            main.parse(buffer, filename);
        }catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    public boolean parse(BufferedReader buffer, String filename){
        String line = "";

        try{
            Workbook wb = new HSSFWorkbook();
            CreationHelper creationHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet();

            int rowN = 0; // row number
            int colN = 0; // column number

            Row row = sheet.createRow((short)0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Entry");
            cell = row.createCell(1);
            cell.setCellValue("Total Minutes");
            cell = row.createCell(2);
            cell.setCellValue("Total Seconds");
            cell = row.createCell(3);
            cell.setCellValue("Temp in Celsius");



            if (buffer.ready()) {
                while ((line = buffer.readLine()) != null) {
                    System.out.println(line);
                }
                buffer.close();
            }

            filename = filename.replace(".txt", ".xls");
            FileOutputStream fileOut = new FileOutputStream(filename);
            wb.write(fileOut);
            fileOut.close();


        }catch (IOException ex){
            System.err.println(ex.getMessage());

            return false;
        }catch (NullPointerException e){
            System.err.println(e.getMessage());
        }
        return true;
    }
}
