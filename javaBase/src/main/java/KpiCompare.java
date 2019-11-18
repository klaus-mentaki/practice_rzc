import javafx.util.Pair;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KpiCompare {
    /**

     * @throws IOException
     * 实例：
     * kpi_Refueling_time:
     * from List import *
     * def getvalue(self,list, f):
     *     """
     *         测量值计算方法(此函数名固定为getvalue(list, f)不可变,传入两个参数)
     *         :param list: QAR文件数据集合的标准操作,有: count();max();min();first();last();linkcount();filter();
     *         :param f: 构型的'常量参数', 可以通过f对象访问,如: f.K_1933_TOL_H; 以及其它测量值的调用,如: f.kpi_TAKEOFF_ELEVATION_ANGLE();
     *         :return: 计算结果
     *         """
     *     # tmp = List(filter(lambda q: self.getRes(q), list)).first()
     *     # id=0
     *     # for i in range(120):
     *     #     if float(tmp.getRow(i).map_TOTAL_FUEL_QTY) > float(tmp.getRow(i+1).map_TOTAL_FUEL_QTY):
     *     #         id+=1
     *     #         if id>2:
     *     #             return (tmp.map_DATETIME,tmp.map_DATETIME)
     *     # return (tmp.map_DATETIME,tmp.map_DATETIME)
     *     return None
     */

    public static void main(String[] args) throws IOException {
//        File file = new File("C:\\Users\\Public\\Nwt\\cache\\recv\\任中超", "ali.csv");
        File file = new File("E:\\CTFO\\业务\\民航大数据平台\\指标内容差异对比\\阿里云和HO_TEST3自定义参数对比\\原始文件", "ALI_Custom.csv");
        String split = ":";
        //read file
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuffer sb = new StringBuffer();
        String fileName = null;
        String newLine = System.lineSeparator();
        do{
        //walk through lines
            line = br.readLine();
            Pair<String, String> newFileName = fetchFileName(line, split);
            if(newFileName != null){
                if(sb.length() > 0){
                    //save as file
                   saveFile(fileName, sb.toString());
                   sb = new StringBuffer(newFileName.getValue());
                }
                fileName = newFileName.getKey();
            } else if(line != null) {
                sb.append(newLine).append(line);
            }
        } while(line != null);
        saveFile(fileName, sb.toString());
    }

    private static void saveFile(String fileName, String content) throws IOException {
//        FileWriter fw = new FileWriter("C:\\Users\\Public\\Nwt\\cache\\recv\\任中超\\"+fileName);
        FileWriter fw = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\指标内容差异对比\\阿里云和HO_TEST3自定义参数对比\\ali_result\\"+fileName);
        System.out.println(fileName+":"+"\n");
        fw.write(content);
        fw.flush();
        fw.close();
    }

//    static String regex = "^(kpi_\\w+),(.*)$";
    static String regex = "^(ext_\\w+),(.*)$";

    private static Pair<String, String> fetchFileName(String line, String split) {
        if(line != null){
            Matcher m = Pattern.compile(regex.replace(",", split)).matcher(line);
            if(m.find()){
                return new Pair(m.group(1), m.group(2));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /*public static void main(String[] args){
        Pair<String, String> p = fetchFileName("kpi_FUEL_FLIGHT,def getvalue(self,orglist, f):", ",");
        System.out.println(p.getKey()+"------"+p.getValue());
    }*/
}
