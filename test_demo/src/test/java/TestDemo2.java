import org.joda.time.DateTime;
import org.junit.Test;

import java.util.*;

public class TestDemo2 {
    public static void main(String[] args) {


        //读取文件
        //每一行存为一个String

        List<String> stringList = new ArrayList<>();

        stringList.add("www.22eee.com,1");
        stringList.add("www.91pron.com,1");

        List<String> strs = new ArrayList<>();
        stringList.forEach(str -> strs.add(str.split("\\.")[1]));
        strs.forEach(System.out::println);


    }

    @Test
    public void streamTest(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("1","111"));
        userList.add(new User("2","222"));
        userList.add(new User("3","333"));

        userList.forEach(user -> {
            if(!(user.getName().equals("1")) && !(user.getName().equals("3"))){
                user.setName("4");
            }
        });

        userList.forEach(System.out::println);
    }

    @Test
    public void getId(){
//        System.out.println(UUID.randomUUID().toString());
        String 测试 = "this is a test";
        System.out.println(测试);

    }

    @Test
    public void zeroT(){
        int i = 100%10;
        System.out.println(i);
    }

    @Test
    public void timeTest(){
        System.out.println(new DateTime());
        long millis = new DateTime().withMillisOfSecond(0).getMillis();
        System.out.println(millis);
        DateTime dateTime = new DateTime(millis);
        System.out.println(dateTime);
    }



}

class User{
    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
