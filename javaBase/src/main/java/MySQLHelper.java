import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class MySQLHelper {
    public static final String url = "jdbc:mysql://127.0.0.1/vge_whu"; //数据库连接
    public static final String name = "com.mysql.jdbc.Driver";   //程序驱动
    public static final String user = "root";  //用户名
    public static final String password = "abc@123"; //密码

    public Connection conn = null;
    public PreparedStatement pst = null;

    /**
     *
     * 创建一个新的实例 DBHelper.
     *
     * @param sql: SQL查询语句
     */
    public MySQLHelper(String sql)
    {
        try
        {
            Class.forName(name);// 指定连接类型
            conn = DriverManager.getConnection(url, user, password);// 获取连接
            pst = conn.prepareStatement(sql);// 准备执行语句
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * 方法名称: close ；
     * 方法描述:  关闭数据库连接 ；
     * 参数 ：
     * 返回类型: void ；
     * 创建人：James；
     * 创建时间：2014-11-25 下午7:00:12；
     * @throws
     */
    public void close()
    {
        try
        {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void getMysqlFile() {

    }

    public static void main(String[] args) {


    }

}