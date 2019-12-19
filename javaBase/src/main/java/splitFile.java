import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class splitFile {
    /**
     * 读取文件中的内容，并去掉所有的换行符，然后写成新文件
     */
    public static void getContent() throws Exception {

        //获取文件路径
        File fileContent = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\数据库语句\\db_dump\\table_DDL", "aero_{org}.sql");


        FileInputStream fileInputStream = new FileInputStream(fileContent);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String sqlFile = null;
        FileWriter fileWriter = null;
        fileWriter = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\数据库语句\\db_dump\\table_DDL\\" + "aero_{org}.txt");

        do {
            sqlFile = bufferedReader.readLine();
            if (sqlFile != null) {
                sqlFile.replaceAll("[\\\\r\\\\n]+", "1");
                System.out.println(sqlFile);
                fileWriter.append(sqlFile);
            }

        } while (sqlFile != null);


        fileWriter.flush();
        fileWriter.close();

    }


    /**
     * 分割SQL文件中的内容，每一个建表语句为一个文件，文件名为表名
     */
    public static void splitContent() throws Exception {

        //获取文件路径
        File fileContent = new File("E:\\CTFO\\业务\\民航大数据平台\\数据\\数据库语句\\db_dump\\table_DDL\\", "aero_org.txt");

        FileInputStream fileInputStream = new FileInputStream(fileContent);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String sqlFile = null;
        FileWriter fileWriter = null;


        do {
            sqlFile = bufferedReader.readLine();
            if (sqlFile != null) {

                //截取内容中建表语句部分
                String sqlContent = null;
                String fileName=null;
                Pattern p1 = Pattern.compile(".?(DROP).*?;");
                Pattern p = Pattern.compile(".?(CREATE.*?;)");
                Matcher m = p.matcher(sqlFile);

                while (m.find()) {
                    sqlContent = m.group(1);
                    fileName=sqlContent.split("`")[1];
                    System.out.println(fileName);
                    fileWriter = new FileWriter("E:\\CTFO\\业务\\民航大数据平台\\数据\\数据库语句\\db_dump\\table_DDL\\aero_org\\" + fileName+".sql");
                    fileWriter.write(sqlContent);
                    fileWriter.flush();
                }
                //fileWriter.write(sqlFile);
            }
        } while (sqlFile != null);
        fileWriter.close();
    }


    public static void main(String[] args) throws Exception {
//        getContent();
        splitContent();
    }

}
