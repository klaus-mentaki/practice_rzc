import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class kpiisorno2 {

    public static void main(String[] args) throws IOException, SQLException {
        Connection conn = null;    //与特定数据库的连接（会话）的变量
        String driver = "com.mysql.jdbc.Driver";  //驱动程序名？？？
        String url = "jdbc:mysql://47.94.204.25:3306/aero_test";  //指向要访问的数据库！注意后面跟的是数据库名称
        String user = "root";   //navicat for sql配置的用户名
        String password = "123456";  //navicat for sql配置的密码
        ArrayList<String> arrayList =new ArrayList<>();


        Statement statement = (Statement) conn.createStatement();  //用statement 来执行sql语句
        String sql = "select name,state from index_parameter";
        ResultSet rs = statement.executeQuery(sql);  //用于返回结果
        System.out.println("---------------------------------------------------");
        System.out.println("表中的数据有:");


        String name = null;
        String state = null;
        ArrayList<String> sqlArr = new ArrayList<>();

        while(rs.next()){
            name = rs.getString("name");
            state = rs.getString("state");

            System.out.println(name + "\t\t" + state + "\t");

            sqlArr.add(name);
            sqlArr.add(state);
        }
        rs.close();
        try{
            Class.forName(driver);  //用class加载动态链接库——驱动程序？？？
            conn = DriverManager.getConnection(url,user,password);  //利用信息链接数据库
            if(!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");

            File file = new File("E:\\CTFO\\业务\\民航大数据平台\\数据", "阿里云数据库指标存在与否.txt");
            //String split = "\n";
            //read file
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuffer sb = new StringBuffer();
            String fileName = null;
            String newLine = System.lineSeparator();
            line = br.readLine();

            for (int i = 0; i < sqlArr.size(); i++) {
                if (line.equals(sqlArr.get(0)))
                    System.out.println(line);

            }

//            while (!line.equals(arrayList))
//                System.out.println(line);

            /*List<String> scripts = Files.readAllLines(f.toPath());
            for (int i = 0; i < scripts.size(); i++) {
                if (!line.equals(arrayList.get(i)))
                    System.out.println(scripts.get(i));
            }*/

            conn.close();
        }catch(ClassNotFoundException e){  //catch不同的错误信息，并报错
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("数据库各项操作顺利进行！");
        }



    }

}
