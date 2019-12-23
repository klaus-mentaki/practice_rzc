import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class MySQLHelper {
    public static final String url = "jdbc:mysql://176.16.10.25/rzc"; //数据库连接
    public static final String name = "com.mysql.jdbc.Driver";   //程序驱动
    public static final String user = "root";  //用户名
    public static final String password = "123456"; //密码

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

    private void  insert1() throws Exception {

        /*Class.forName(name);// 指定连接类型
        conn = DriverManager.getConnection(url, user, password);// 获取连接

        String sql="INSERT INTO `fp_detail` \n" +
                "(id,flight_id,calibratedairspeed,countrycode,distance,elapsedtimemin,firname,fuelconsumption,fuelflow,groundspeed,highlayer,latitude,longitude,mach,magneticheading,minimumsafealtitude,overlap,pointname,residualairdistance,residualoilcontent,routecode,routeindex,temperature,temperaturedeviation,theremainingdistance,timeremaining,trueheading,useddis,usedfuel,usedtime,vacuumspeed,windcommponent,winddirection,windspeed,created_at,updated_at,status)\n" +
                "VALUES \n" +
                "(?, 3160431, 287, 'CC', 203.72, 19, NULL, 1652, 1947, 434, 30100, 32.11,120.77, 0, 333, 32, NULL, 'TOC', 1522.344, 4754, 'G330', 3, -33, 12, 1313.068, 113, 327, 44.448, 152, 2, 458, -24, 261, 73,\n" +
                "NULL, NULL, 0)";

        pst = conn.prepareStatement(sql);// 准备执行语句

        int i =1;

        for (i=1;i<10;i++){
            pst.setInt(1,i);
        }*/
    }
    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://176.16.10.25/rzc"; //数据库连接
        String name = "com.mysql.jdbc.Driver";   //程序驱动
        String user = "root";  //用户名
        String password = "123456"; //密码
        Class.forName(name);// 指定连接类型
        Connection conn = null;
        PreparedStatement pst = null;

        conn = DriverManager.getConnection(url, user, password);// 获取连接

        String sql="INSERT INTO `fp_detail` \n" +
                "(id,flight_id,calibratedairspeed,countrycode,distance,elapsedtimemin,firname,fuelconsumption,fuelflow,groundspeed,highlayer,latitude,longitude,mach,magneticheading,minimumsafealtitude,overlap,pointname,residualairdistance,residualoilcontent,routecode,routeindex,temperature,temperaturedeviation,theremainingdistance,timeremaining,trueheading,useddis,usedfuel,usedtime,vacuumspeed,windcommponent,winddirection,windspeed,created_at,updated_at,status)\n" +
                "VALUES \n" +
                "(?, 3160431, 287, 'CC', 203.72, 19, NULL, 1652, 1947, 434, 30100, 32.11,120.77, 0, 333, 32, NULL, 'TOC', 1522.344, 4754, 'G330', 3, -33, 12, 1313.068, 113, 327, 44.448, 152, 2, 458, -24, 261, 73,\n" +
                "NULL, NULL, 0)";

        pst = conn.prepareStatement(sql);// 准备执行语句

        int i =1;

        for (i=1;i<10;i++){
            pst.setInt(1,i);
        }


    }

}