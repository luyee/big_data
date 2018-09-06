import java.sql.Date;
import java.sql.Timestamp;

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
        

        String s = "and | or";

//        String s1 = s.split("\\.")[0];
        String s1 = s.split("\\|")[0];

        System.out.println("s1 = " + s1);
    }
}
