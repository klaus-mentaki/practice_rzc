import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class spiltContent2File {
    /**
     * 切分一个大的文件，并把切分的内容保存成一个文件
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        String fileName = null;
        String content = null;

        File file = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比", "81_321.csv");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        FileWriter fw = null;
        BufferedWriter bufferedWriter = null;


//                bufferedReader.readLine().split("\n");

        do{
            line = bufferedReader.readLine();
            String readline[] = line.split(",");

            fileName=readline[3];

            String contentArr[] = null;
            System.out.println(readline.length);

            content=line;


/*            System.arraycopy(readline,9,contentArr,0,readline.length-10);
            contentArr.toString();
            System.out.println(contentArr);*/



//            int num = fileName.indexOf("\\");
//            fileName=fileName.substring(0,num);


            //过滤特殊字符
           /* String regEx = "[`~!@#$%^&*()_\\-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(fileName);
            fileName = m.replaceAll("_").trim();*/


            if (fileName.contains("/"))
                fileName = fileName.replaceAll("/", "_");
            else if (fileName.contains("<") && fileName.contains(">")) {
                //fileName=fileName.replaceAll("<","");

                int num = fileName.indexOf(">");
                fileName = fileName.substring(num + 1, fileName.length());
            } else if (fileName.contains(">"))
                fileName = fileName.replaceAll(">", "");
            else
                fileName = fileName;
            fw = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\81_321\\" + fileName);


            /*File writename = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\" + fileName); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            bufferedWriter = new BufferedWriter(new FileWriter(writename));*/

            /*System.out.println(fileName + ":" + "\n");
            System.out.println(content);*/
            //while (content!=null){
            fw.write(content);

            fw.flush();
            fw.close();

        }while (line!=null);


    }

}