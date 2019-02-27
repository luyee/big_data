import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName TestDemo
 * @Author caiwe
 * @CreateTime 2018/7/1817:11
 **/

public class TestDemo {
    public static void main(String[] args) {
//        String a = "12123123123123123121";
//        Double.parseDouble(a);
//        Long.parseLong(a)

//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(timestamp);
//        Date date = new Date(timestamp.getTime());
//        System.out.println(date);
//        String phone1 = "123";
//        String phone2 = new String("123");
//        String phone3 = phone1;
//
//        System.out.println(phone1 == phone2);
//        System.out.println(phone1 == phone3);


//        String name = "DEFAULT.test001";
//
//        String name1 = null;
//        if(name.toLowerCase().contains("default")){
//            name1 = name.substring(name.indexOf(".")+1);
//        }
//        System.out.println(name1);
        
//
//        String s = "and | or";
//
////        String s1 = s.split("\\.")[0];
//        String[] s1 = s.split("\\|",2);
//
//        Arrays.stream(s1).forEach(System.out::println);
//        System.out.println("s1 = " + s1.);
//        //011
//        int a = 3;
//        //110
//        int b = 6;
//
//        System.out.println(a|b);
//        System.out.println(a&b);
//        System.out.println(a^b);
//
//        int c = 3;
//
//        c |= 6;
//        System.out.println(c);
//
//
//        System.out.println("{\"id\":\"2\",\"name\":\"caiw\",\"age\":18}");
//
//        final StringBuffer buffer = new StringBuffer();
//
////        buffer.append("i");
//
//        String s = buffer.toString();
//        if(s.isEmpty()){
//            System.out.println(11111);
//        }
//        System.out.println(s);

//        String json = "{\"id\":\"2\",\"name\":\"caiw\",\"age\":18}";
//
//
//        String ss = "import org.apache.spark.sql._\n" +
//                "val tbl10 = datasourceProvider.getTableFromDatasource(\"test.user_test\", \"4951557f-ef85-4ff8-98af-42b21f84154c\", sqlContext).alias(\"tbl10\")\n" +
//                "var df = tbl10.select(tbl10.col(\"registration_dttm\").as(\"registration_dttm\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"id\").as(\"id\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"first_name\").as(\"first_name\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"last_name\").as(\"last_name\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"email\").as(\"email\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"gender\").as(\"gender\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"ip_address\").as(\"ip_address\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"cc\").as(\"cc\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"country\").as(\"country\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"birthdate\").as(\"birthdate\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"salary\").as(\"salary\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"title\").as(\"title\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()))\n" +
//                "df = df\n" +
//                "df";
//        JSONObject data = JSONObject.parseObject(json);
//
//
//        Map<String,String> jsonMap = new HashMap<>();
//
//
//        data.keySet().forEach(key -> {
//            jsonMap.put(key,data.getString(key));
//        });
//
//
////        Set<String> strings = jsonMap.keySet();
//
////        strings.forEach(s -> System.out.println(jsonMap.get(s)));
//
//        Object json1 = JSONObject.toJSON(jsonMap);
//
//        System.out.println(json1.toString());
        String json = "{\"id\":\"2\",\"name\":\"caiw\",\"age\":null}";

        Object parse = JSON.parse(json);
        System.out.println(parse);

    }

    public static void jsonTx() {





    }
}
