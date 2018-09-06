package com.caiw.excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelToSql
 * @Author caiwe
 * @CreateTime 2018/8/21  17:00
 **/

public class ExcelToSql {

    public static void main(String[] args) throws Exception {
        main("综合管理");
    }

    /**
     * 传入文件名，excel文件，表数据文件。通过excel的sheet从txt文件获取表名
     * @throws Exception
     */
    public static void main(String path) throws Exception {
//        String path = "数据交换接口-3.1.3.5-公共卫生-社区体检";
        String filePath = "D:\\DSG\\hospital_data\\"+path+".xlsx";
        String sqlPath = "D:\\DSG\\hospital_data\\"+path+".sql";
        String tablePath = "D:\\DSG\\hospital_data\\"+path+".txt";

        List<String> tableNames = readTableName(tablePath);
        XSSFWorkbook xssfSheets = ExcelUtil.readXlsx(filePath);

        int size = xssfSheets.getNumberOfSheets();

        for (int i = 0; i < size; i++) {
            XSSFSheet sheetAt = xssfSheets.getSheetAt(i);
            List<List<String>> lists = ExcelUtil.readSheet(sheetAt);
            String sheetName = sheetAt.getSheetName();
            if(tableNames.size() != 0){
                System.out.println("sheetName = " + sheetName);
                String tableName = tableNames.get(i);
                System.out.println("tableName = " + tableName);
                if(tableName.startsWith(sheetName)){
                    String finalTableName = tableName.replaceAll(sheetName,"");
                    System.out.println("finalTableName = " + finalTableName);
                    String sql = getSql(lists, finalTableName);
                    writeSqlFile(sqlPath,sql,tableName);
                }else {
                    System.out.println("不匹配");
                }
            }else {
                String sql = getSql(lists, sheetName);
                writeSqlFile(sqlPath,sql,sheetName);
            }

        }



    }

    /**
     * create table classes(
        id number(9) not null primary key,
        classname varchar2(40) not null
        )
     * 1,4,5
     * @param lists
     * @param tableName
     */
    private static String getSql(List<List<String>> lists, String tableName){
        StringBuilder buffer = new StringBuilder("");
        //oracle if table exists
        buffer.append("declare\n" + "      num   number;\n" + "begin\n" + "    select count(1) into num from user_tables where table_name = upper('").append(tableName).append("') ;\n").append("    if num > 0 then\n").append("        execute immediate 'drop table ").append(tableName).append("' ;\n").append("    end if;\n").append("end;\n");
        //create table
        buffer.append(" create table  ");
        buffer.append(tableName).append("( \n");
        int size = lists.size();
        if(lists.get(size-1).get(0).equals("")){
            size = size-1;
        }
        for (int i = 0 ; i < size ; i++ ) {
            List<String> list = lists.get(i);
            if(i >= size-1 ){
                buffer.append(" ").append(list.get(1)).append(" ").append(list.get(4));
            }else {
                buffer.append(" ").append(list.get(1)).append(" ").append(list.get(4)).append(" ,\n");
            }
        }
        buffer.append(" ); \n");
        //字段注释
        for (int i = 0 ; i < size ; i++ ) {
            List<String> list = lists.get(i);
            buffer.append("comment on column ").append(tableName).append(".").append(list.get(1)).append(" is '").append(list.get(2));
            buffer.append("';\n");
        }
        buffer.append("commit; ");
        return buffer.toString();
    }



    private static void writeSqlFile(String filePath, String sql, String tableName) {
        try {
            File file = new File(filePath);
            PrintWriter pw = new PrintWriter(new FileWriter(file,true));
            pw.write("--- tableName: " + tableName +"\n");
            pw.write(sql);
            pw.write("\n");
            pw.write("\n");
            pw.write("\n");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<String> readTableName(String filePath) {
        List<String> lineList = new ArrayList<String>();
        String line = "";
        try {
            BufferedReader in=new BufferedReader(new FileReader(filePath));
            lineList.add(in.readLine());
            while (line!=null) {
                line=in.readLine();
                lineList.add(line);
            }
            in.close();
        } catch (IOException e) {
            System.err.println(filePath+"文件不存在");
        }
        return lineList;
    }
}
