import javafx.util.Pair;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class mainTest {

    private static void getFileContent() throws Exception {
        /**
         * 获取文件夹内文件的内容
         */
        File file = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\a\\");

        String filename=null;
        File fileChild = null;
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        Pair<String, String> filePair1 =null;


        File[] a = file.listFiles();
        for (File f : a) {
            //System.out.println(f);

            fileChild = new File(f.toString());
            fileInputStream = new FileInputStream(fileChild);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String fileRead = bufferedReader.readLine().toString();
            //System.out.println(fileContent);

            //截取路径中的文件名
            String[] a2=f.toString().split("\\\\");
            filename=a2[a2.length-1];

            //截取内容中脚本部分
            String fileContent=null;
            String regex = "\\{.*}";
            Pattern p=Pattern.compile(".*?(\\{.*}).*");
            Matcher m =p.matcher(fileRead);

            while (m.find()){
                fileContent=m.group(1);
            }


            filePair1=new Pair<>(filename,fileContent);

            System.out.println(filePair1.getKey()+"--------"+filePair1.getValue());

            FileWriter fileWriter = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\c\\" + filePair1.getKey());
            fileWriter.write(filePair1.getValue());
            fileWriter.flush();
            fileWriter.close();
        };




    }



    public static void main(String[] args) throws Exception {
        //getFileContent();
        getFileContent();

        //Pair<String, String> filePair1 = contentCompare.getContent(Paths.get("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\a\\"));

        //List<String> nameList = null;



        /*nameList.add(filePair1.getKey());
        for (String a:nameList
             ) {
            System.out.println(a);
        }*/


        //Pair<String, String> filePair2 = contentCompare.getContent(Paths.get("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\b\\"));



    }


}

