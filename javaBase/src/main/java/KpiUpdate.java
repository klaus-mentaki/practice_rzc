import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

/**
 * 对比阿里云数据库和远航通数据库中KPI指标脚本内容，有差异的代码替换为远航通代码
 */

public class KpiUpdate {

    static String sql_str = "INSERT INTO index_parameter(`name`,`organization`,`state`,`last_update_time`,`buzz_classification`,`detail`)VALUES('%name%','%org%',1,now(),'Oil','{\"interval\":null,\"script\":{\"string\": \"%script%\"}}');";

    static String update_sql = "UPDATE index_parameter SET `detail` = '{\"interval\":null,\"script\":{\"string\": \"%script%\"}}' WHERE `name` = '%name%';";


    /*public static void main(String[] args) throws IOException {
        List<String> orgs = Arrays.asList(new String[]{"HO", "BK"});
        String path = "C:\\Users\\Public\\Nwt\\cache\\recv\\任中超\\new_kpi";
        File[] files = new File(path).listFiles();
        for(File f : files){
            String name = f.getName();
            List<String> scripts = Files.readAllLines(f.toPath());
            String encoded = Base64.getEncoder().encodeToString(String.join("\n", scripts).getBytes(Charset.forName("UTF-8")));
            for(String org : orgs){
                System.out.println(sql_str.replace("%name%", name).replace("%org%", org).replace("%script%", encoded));
            }
        }
    }*/

        public static void main(String[] args) throws IOException {
            String path = "C:\\Users\\Public\\Nwt\\cache\\recv\\任中超\\需要更改的指标";

            File[] files = new File(path).listFiles();
            for(File f : files){
                String name = f.getName();
                List<String> scripts = Files.readAllLines(f.toPath());
                String encoded = Base64.getEncoder().encodeToString(String.join("\n", scripts).getBytes(Charset.forName("UTF-8")));
                System.out.println(update_sql.replace("%name%", name).replace("%script%", encoded));
            }
        }

        /*public static void main(String[] args) throws IOException {
            String a = "def getRes(self, q):\n" +
                    "    return q.getRow(1)!=None and  \"AIR\" in q.map_AIRGND and  \"AIR\" in q.getRow(1).map_AIRGND\n" +
                    " \n" +
                    "def getvalue(self,orglist, f):\n" +
                    "    \"\"\"\n" +
                    "        测量值计算方法(此函数名固定为getvalue(list, f)不可变,传入两个参数)\n" +
                    "        :param list: QAR文件数据集合的标准操作,有: count();max();min();first();last();linkcount();filter();\n" +
                    "        :param f: 构型的'常量参数', 可以通过f对象访问,如: f.K_1933_TOL_H; 以及其它测量值的调用,如: f.kpi_TAKEOFF_ELEVATION_ANGLE();\n" +
                    "        :return: 计算结果\n" +
                    "        \"\"\"\n" +
                    "    \n" +
                    "    tmp = List(filter(lambda q: self.getRes(q), orglist))\n" +
                    "    \n" +
                    "    f1=float(tmp.first().map_TOTAL_FUEL_QTY)\n" +
                    "    f2=float(tmp.last().map_TOTAL_FUEL_QTY)\n" +
                    "    \n" +
                    "    \n" +
                    "    if f1 == 0:\n" +
                    "        for i in range(-1,-60,-1):\n" +
                    "            if float(tmp.first().getRow(i).map_TOTAL_FUEL_QTY) !=0:\n" +
                    "                f1= float(tmp.first().getRow(i).map_TOTAL_FUEL_QTY)\n" +
                    "                break\n" +
                    "    else:\n" +
                    "        f1= float(tmp.first().map_TOTAL_FUEL_QTY)\n" +
                    "        \n" +
                    "        \n" +
                    "    if f2 == 0:\n" +
                    "        for i in range(-1,-60,-1):\n" +
                    "            if float(tmp.last().getRow(i).map_TOTAL_FUEL_QTY) !=0:\n" +
                    "                f2=float(tmp.last().getRow(i).map_TOTAL_FUEL_QTY)\n" +
                    "                break\n" +
                    "    else:\n" +
                    "        f2= float(tmp.last().map_TOTAL_FUEL_QTY)\n" +
                    "        \n" +
                    "    return (str(f1-f2),'')";

            //File[] files = new File(path).listFiles();
            //for (File f : files) {
            //String name = f.getName();
            //List<String> scripts = Files.readAllLines(f.toPath());
            String encoded = Base64.getEncoder().encodeToString(String.join("\n", a).getBytes(Charset.forName("UTF-8")));
            //String name = a.getName();
            System.out.println(update_sql.replace("%script%", encoded));
        }*/
    }


