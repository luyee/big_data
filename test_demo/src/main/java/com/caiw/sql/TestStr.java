package com.caiw.sql;

/**
 * @Author: caiwei
 * @Description:
 * @Date: create in 2019/4/9 16:49
 */
public class TestStr {
    public static void main(String[] args) {
        String str = args[0];
//        String str = "import org.apache.spark.sql._\nvar df = sqlContext.sql(\"SELECT tbl10.`id`, tbl10.`named`, tbl11.`email`, tbl11.`age`, tbl12.`china_phone`, tbl12.`us_phone`, tbl12.`credit_card`, tbl12.`birthday`  FROM  `mysql_no_biaozhun_db`.`mysql_no_biaozhun_tb` tbl10 INNER JOIN `mysql_no_biaozhun_db`.`mysql_no_biaozhun_tb_feed` tbl11 ON tbl10.`id` = tbl11.`id` INNER JOIN `mysql_no_biaozhun_db`.`mysql_no_biaozhun_tb_valid` tbl12 ON tbl12.`id` = tbl11.`id` \")\ndf";
        String ss = "\\\\n";
        String s = str.replaceAll(ss, "\n");
        System.out.println(s);
    }
}
