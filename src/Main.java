
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
            cell.setCellValue("Seconds");
            cell = row.createCell(3);
            cell.setCellValue("Temp in Celsius");
            cell = row.createCell(4);
            cell.setCellValue("Total Seconds");



            if (buffer.ready()) {
                int index1 = 1;
                while ((line = buffer.readLine()) != null) {
                    String[] parseLine = line.split(",");
                    String first = parseLine[0].split(":")[0];



                    if (first.equals("Entry")){
                        row = sheet.createRow(index1);
                        index1 += 1;
                        int index2= 0;
                        for (String s: parseLine){
                            String[] entry = s.split(":");
                            String name = entry[0];
                            String value = entry[1];
                            double valueD = Double.valueOf(value.replace(" ",""));

                            cell = row.createCell(index2);
                            cell.setCellValue(valueD);
                            //System.out.println(s);
                            index2 += 1;
                        }
                        String min = "-1";
                        String sec = "-1";
                        min = parseLine[1].split(":")[1];
                        sec = parseLine[2].split(":")[1];
                        double minD = Double.valueOf(min);
                        double secD = Double.valueOf(sec);
                        cell = row.createCell(4);
                        cell.setCellValue((minD*60)+secD);
                    }
                    //System.out.println(line);
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
