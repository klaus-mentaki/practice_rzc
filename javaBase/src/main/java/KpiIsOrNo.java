import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KpiIsOrNo {
    /**
     * 读取数据
     *
     * @param conn
     */
    public static Map<String, String> fetchdata(Connection conn)   //读取数据函数
    {
        List<String> ll = new ArrayList<>();
//        Pair<String,String> pp = null;
        Map<String, String> mysqlMap = new HashMap<String, String>();
        try {
            Statement statement = (Statement) conn.createStatement();  //用statement 来执行sql语句
            String sql = "select name,state from index_parameter where organization='HO'";
            ResultSet rs = statement.executeQuery(sql);  //用于返回结果
            /*System.out.println("---------------------------------------------------");
            System.out.println("表中的数据有:");*/


            String name = null;
            String state = null;


            while (rs.next()) {
                /*name = rs.getString("name").toUpperCase();
                state = rs.getString("state");*/
                mysqlMap.put(rs.getString("name").toUpperCase(),rs.getString("status"));

                //System.out.println(name + "\t\t" + state + "\t");
                //pp= new Pair<String, String>(name,state);

                //ll.add(name);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.out.println("数据库数据读取成功！"+"\n");
        }

        return null;
    }


    public static void main(String[] args) throws IOException {
        Connection conn = null;    //与特定数据库的连接（会话）的变量
        String driver = "com.mysql.jdbc.Driver";  //驱动程序名？？？
        String url = "jdbc:mysql://47.94.204.25:3306/aero_test";  //指向要访问的数据库！注意后面跟的是数据库名称
        String user = "root";   //navicat for sql配置的用户名
        String password = "123456";  //navicat for sql配置的密码
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Class.forName(driver);  //用class加载动态链接库——驱动程序？？？
            conn = DriverManager.getConnection(url, user, password);  //利用信息链接数据库
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");


            String path = "E:\\CTFO\\业务\\民航大数据平台\\数据\\阿里云数据库指标存在与否.txt";

            File file = new File("E:\\CTFO\\业务\\民航大数据平台\\数据", "阿里云数据库指标存在与否.txt");
            //String split = "\n";
            //read file
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuffer sb = new StringBuffer();
            String fileName = null;
            String newLine = System.lineSeparator();
            System.out.println(11111111);

            ArrayList<String> targetTXT = new ArrayList<>();
            do {
                //walk through lines
                line = br.readLine();
                //System.out.println(line);
                if (line != null) {
                    targetTXT.add(line);
                }


            } while (line != null);
            int count = 0;
            List<String> mysqlList = new ArrayList<>();

            //mysqlList = Collections.singletonList(fetchdata(conn).getKey());
            for (String mysqlkpi : mysqlList) {
                /*for (String a:targetTXT) {
                    if (!mysqlkpi.contains(a)){
                        System.out.println(a);
                        count++;
                    }
                }*/
                System.out.println(mysqlkpi);
            }
            System.out.println(count);

            /*for (String a:targetTXT) {
                String kpiName = (String) fetchdata(conn).getKey();
                if (!kpiName.contains(a)){
                    //System.out.println(kpiName+"****"+a);
                    //count++;
                }
                System.out.println(kpiName);
                count++;

            }
            System.out.println(count);*/


//            while (!line.equals(arrayList))
//                System.out.println(line);

            /*List<String> scripts = Files.readAllLines(f.toPath());
            for (int i = 0; i < scripts.size(); i++) {
                if (!line.equals(arrayList.get(i)))
                    System.out.println(scripts.get(i));
            }*/

            conn.close();
        } catch (ClassNotFoundException e) {  //catch不同的错误信息，并报错
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("数据库各项操作顺利进行！");
        }


    }

}
