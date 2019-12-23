
//import javafx.util.Pair;

import javafx.util.Pair;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class contentCompare {
    /**
     * 获取文件夹内的文件名
     */
    private List<StringBuffer> getFile(String path) {
        File file = new File(path);

        /**
         * file.listFiles()获取该目录下所有文件和目录的绝对路径 File类型
         * file.list()获取该目录下的所有文件 String类型
         */
        File[] filenames = file.listFiles(); //获取文件列表

        List<StringBuffer> fileDirectory = new ArrayList<>();
        List<StringBuffer> fileName = new ArrayList<>();


        for (File f : filenames) {
            /*
             * 判断这个是文件还是目录
             * 文件放到filename，目录放到dname
             */
            if (f.isFile()) {
                StringBuffer s1 = new StringBuffer(f.toString());
                fileName.add(s1);
//					System.out.println("1");
            } else if (f.isDirectory()) {
                StringBuffer s2 = new StringBuffer(f.toString());
                fileDirectory.add(s2);
//					System.out.println("2");
            } else {
                System.out.println(f.toString() + "：error，not f or d ");
            }
        }
        return fileName;
    }

    /**
     * 获取文件内容
     *
     * @param filepath
     */
    public static void getContent(Path filepath) throws Exception {
        //获取文件路径
        File file = new File(filepath.toString());
        File[] fileArr = file.listFiles();

        File fileChild = null;
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        Pair<String, String> pair = null;

        for (File f : fileArr) {
//            System.out.println(f);
            fileChild = new File(f.toString());//获取文件路径，准备读文件内容
            fileInputStream = new FileInputStream(fileChild);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String fileContent = bufferedReader.readLine();
            System.out.println(fileContent);

            pair = new Pair<>(f.toString(), fileContent);
            FileWriter fileWriter = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\b\\" + pair.getKey());
        }
        ;
    }

    /**
     * 正则提取文件中的内容
     */
    private void getContent2(Path filepath) throws Exception {
        File file = new File(filepath.toString());

        String filename = null;
        File fileChild = null;
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        Pair<String, String> filePair1 = null;


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
            String[] a2 = f.toString().split("\\\\");
            filename = a2[a2.length - 1];

            //截取内容中脚本部分
            String fileContent = null;
            String regex = "\\{.*}";
            Pattern p = Pattern.compile(".*?(\\{.*}).*");
            Matcher m = p.matcher(fileRead);

            while (m.find()) {
                fileContent = m.group(1);
            }

            //写入到指定路径
            filePair1=new Pair<>(filename,fileContent);
            //System.out.println(filePair1.getKey()+"--------"+filePair1.getValue());
            FileWriter fileWriter = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\c\\" + filePair1.getKey());
            fileWriter.write(filePair1.getValue());
            fileWriter.flush();
            fileWriter.close();
        }
    }

    /**
     * 比较文件夹中文件的内容
     */
    private void compareContent(Path path) throws Exception {
        /*File file = new File(filepath.toString());

        String filename = null;
        File fileChild = null;
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        Pair<String, String> filePair1 = null;


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
            String[] a2 = f.toString().split("\\\\");
            filename = a2[a2.length - 1];

            //截取内容中脚本部分
            String fileContent = null;
            String regex = "\\{.*}";
            Pattern p = Pattern.compile(".*?(\\{.*}).*");
            Matcher m = p.matcher(fileRead);

            while (m.find()) {
                fileContent = m.group(1);
            }



            //写入到指定路径
            filePair1=new Pair<>(filename,fileContent);
            //System.out.println(filePair1.getKey()+"--------"+filePair1.getValue());
            FileWriter fileWriter = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\c\\" + filePair1.getKey());
            fileWriter.write(filePair1.getValue());
            fileWriter.flush();
            fileWriter.close();
        }
*/
    }


    public static void main(String[] args) throws Exception {
        String fileName = null;
        String content = null;

        //File file1 = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\81_321\\");

        //File file2 = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\81_321\\");


        FileInputStream fileInputStream1 = null;

        InputStream fileInputStream = null;
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        FileWriter fw = null;
        BufferedWriter bufferedWriter = null;
        String readline[] = null;


//                bufferedReader.readLine().split("\n");

        while (bufferedReader.readLine() != null) {
            line = bufferedReader.readLine();

            readline = line.split(",");


            fileName = readline[3];

            String contentArr[] = null;
            System.out.println(readline.length);

            content = line;


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
            fw = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\a\\" + fileName);


            /*File writename = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\采集参数对比\\" + fileName); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            bufferedWriter = new BufferedWriter(new FileWriter(writename));*/

            /*System.out.println(fileName + ":" + "\n");
            System.out.println(content);*/
            //while (content!=null){
            fw.write(content);

            fw.flush();


            fw.close();

        }
    }

}

