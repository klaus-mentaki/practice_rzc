import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

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
}
