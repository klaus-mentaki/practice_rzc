import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.*;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.FileUtils;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.converters.DefaultConverterLoader;
import com.alibaba.excel.read.metadata.ReadSheet;


import java.io.*;
import java.util.List;

public class easyExcel {
    private URI TestFileUtil;


    public static List noModelMultipleSheet() throws Exception {
        File file = new File("C:\\Users\\Public\\Nwt\\cache\\recv\\罗天成", "机场信息表.xls");
        InputStream inputStream = new FileInputStream(file);
        AirportFile airportFile = new AirportFile();
        List<AirportFile> contents = new ArrayList<AirportFile>();
        final List<String>[] list = new List[]{new ArrayList<>()};

        ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null,new AnalysisEventListener<List<String>>() {
                    public void invoke(List<String> object, AnalysisContext context) {
                        /*System.out.println(
                                "当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行：" + context.getCurrentRowNum()
                                        + " data:" + object);*/
                        //airportFile.setAirport(object.get(9));
                        list[object.size()] =object;
                        /*System.out.println(object.get(9).toString());
                        System.out.println(object.get(9).toString());*/
                        //airportFile.setGender(object.get(11));
                       /* airportFile.setLongitude(object.get(object.size()-2));
                        airportFile.setLatitude(object.get(object.size()-2));*/
                    }

                    public void doAfterAllAnalysed(AnalysisContext context) {}
                });



        reader.read();
        return list[4];
    }

    /*public void indexWrite() {
        String fileName = TestFileUtil.getPath() + "indexWrite" + System.currentTimeMillis() + ".xls";

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, IndexData.class).sheet("模板").doWrite(data());
    }*/

    public static void writeExcel(final File file, List<? extends BaseRowModel> list) throws Exception {
        OutputStream out = new FileOutputStream(file);
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet,  有模型映射关系
            Class t = list.get(0).getClass();
            com.alibaba.excel.metadata.Sheet sheet = new com.alibaba.excel.metadata.Sheet(1, 0, t);
            writer.write(list, sheet);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        List a = noModelMultipleSheet();
    }
}



