import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.StringUtil;

import java.io.*;
import java.util.*;

public class disposeCSVFile3 {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Public\\Nwt\\cache\\recv\\罗天成", "机场信息表.xls");

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //按照HSSF的方式获取xls文件，还有XSSF的方式
        FileInputStream inputStream = new FileInputStream(file);

        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);// 创建Excel文件对象
        HSSFSheet sheet = workbook.getSheetAt(0);// 取出第一个工作表，索引是0

        String keyGender = null;
        String keyAirport = null;

        List<AirportFile> contents = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        Map<String, Object> objectMap = new HashMap<>();

        HSSFCell cell = null;// 单元格，最终按字符串处理

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            HSSFRow row = sheet.getRow(rowNum);// 获取行对象
            //System.out.println("行数" + rowNum);

            // 循环遍历单元格
            AirportFile airportFile = new AirportFile();

            if (row != null) {
                for (int j = 0; j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);// 获取单元格对象

                    if (cell != null) { //单元格不可为空，遇到null值不会继续进行
                        if (j == 9) {
                            keyAirport = cell.getStringCellValue();
                            airportFile.setAirport(cell.getStringCellValue());
                        }
                        if (j == 11) {
                            keyGender = cell.getStringCellValue();
                            airportFile.setGender(cell.getStringCellValue());
                        }
                        if (j == 42) {
                            airportFile.setLatitude(cell.getStringCellValue());
                        }
                        if (j == 43) {
                            airportFile.setLongitude(cell.getStringCellValue());
                        }
                    }
                }
                objectMap.put(keyGender + keyAirport, airportFile);
            }
        }

        /**
         * 写入目标文件操作
         */
        FileWriter fw = new FileWriter("E:\\" + "a11.txt", true);
        BufferedWriter writer = new BufferedWriter(fw);

        for (String str : objectMap.keySet()) {
            System.out.println(str + "----------" + objectMap.get(str));
            AirportFile airportFile1 = (AirportFile) objectMap.get(str);

            writer.write(airportFile1.getAirport() + "\t" + airportFile1.getGender() + "\t" + airportFile1.getLongitude() + "\t" + airportFile1.getLatitude() + "\n");
            writer.flush();
        }

        hssfWorkbook.close();

        /*for (AirportFile excelfile : contents) {
            System.out.println(excelfile);
            FileWriter fw = new FileWriter("E:\\" + "a1.xls", true);
            writer = new BufferedWriter(fw);
            if (excelfile.getAirport() != null) {
                writer.write(excelfile.getAirport());

                if (excelfile.getAirport() != null) {
                    writer.write(excelfile.getAirport());
                } else {

                }
                if (excelfile.getGender() != null && !" ".equals(excelfile.getGender().trim())) {
                    writer.write(excelfile.getGender());

                }
                if (excelfile.getLatitude() != null && !excelfile.getLatitude().trim().equals(" ")) {

                    writer.write(excelfile.getLatitude());
                }
                if (excelfile.getLongitude() != null && !excelfile.getLongitude().trim().equals(" ")) {
                    writer.write(excelfile.getLongitude());

                }
                writer.flush();*/

    }
}