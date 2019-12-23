import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLfile {
    /**
     * 往MySQL数据库插入大量数据
     */
    public static void main(String[] args) {

        String url = "jdbc:mysql://172.16.10.25/rzc"; //数据库连接
        String name = "com.mysql.jdbc.Driver";   //程序驱动
        String user = "root";  //用户名
        String password = "123456"; //密码
        Long beginTime = System.currentTimeMillis();

        try {
            Class.forName(name);// 指定连接类型
            Connection conn = null;
            PreparedStatement pst = null;
            conn = DriverManager.getConnection(url, user, password);// 获取连接

            String sql = "INSERT INTO `fp_detail2` VALUES (?, 3231213, 272, 'ZY', 1035.268, 76, NULL, 4185, 1280, 502, 31100, 38.691667, 122.16, 0.737, 79, 29, NULL, 'UDETI', 881.552, 2410, 'W106', 17, -32, 15, 922.296, 69, 71, 92.6, 255, 6, 446, 56, 275, 61, NULL, NULL, 0)";
            conn.setAutoCommit(false);//关闭事务
            pst = conn.prepareStatement(sql);// 准备执行语句


            for (int i = 100001; i < 1000000; i++) {

                pst.setInt(1, i);
                pst.addBatch();
                pst.execute();

            }
            Long endTime = System.currentTimeMillis();
            System.out.println("pst:"+(endTime-beginTime)/1000+"秒");//计算时间
            conn.commit();
            pst.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
