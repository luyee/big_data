package com.caiw.hbaseutils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HbaseUtil {

    private static Connection connection;
    private static Admin admin;


    // 初始化链接
    public static void init() {
        Configuration configuration = HBaseConfiguration.create();
        /*
         * configuration.set("hbase.zookeeper.quorum",
         * "10.10.3.181,10.10.3.182,10.10.3.183");
         * configuration.set("hbase.zookeeper.property.clientPort","2181");
         * configuration.set("zookeeper.znode.parent","/hbase");
         */
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.23.54,192.168.23.55,192.168.23.56");
        configuration.set("hbase.master", "192.168.23.54:16000");
        configuration.set("zookeeper.znode.parent","/hbase-unsecure");
        File workaround = new File(".");
        System.getProperties().put("hadoop.home.dir",
                workaround.getAbsolutePath());
        new File("./bin").mkdirs();
        try {
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 关闭连接
    public static void close() {
        try {
            if (null != admin)
                admin.close();
            if (null != connection)
                connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Admin getAdmin(){
        init();
        return admin;
    }

    public static Connection getConnection(){
        init();
        return connection;
    }



    // 建表
    public static void createTable(String nameSpace,String srcTableName, String[] cols) throws IOException {

        init();
        TableName tableName = TableName.valueOf(srcTableName);

        if (admin.tableExists(tableName)) {
            System.out.println("table is exists!");
        } else {
            try{
                admin.getNamespaceDescriptor(nameSpace);
            }catch(NamespaceNotFoundException e){
                //若发生特定的异常，即找不到命名空间，则创建命名空间
                NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(nameSpace).build();
                admin.createNamespace(namespaceDescriptor);
            }
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
        close();
    }

    // 删表
    public static void deleteTable(String tableName) throws IOException {
//        init();
        TableName tn = TableName.valueOf(tableName);
        if (admin.tableExists(tn)) {
            admin.disableTable(tn);
            admin.deleteTable(tn);
        }
//        close();
    }

    // 查看已有表
    public static void listTables() throws IOException {
        init();
        HTableDescriptor hTableDescriptors[] = admin.listTables();
        for (HTableDescriptor hTableDescriptor : hTableDescriptors) {
            System.out.println(hTableDescriptor.getNameAsString());
        }
        close();
    }

    // 插入数据
    public static void insterRow(String tableName, String rowkey, String colFamily, String col, String val)
            throws IOException {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(val));
        put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes("age"), Bytes.toBytes("20"));
        table.put(put);

        // 批量插入
        /*
         * List<Put> putList = new ArrayList<Put>(); puts.add(put);
         * table.put(putList);
         */
        table.close();
        close();
    }

    // 删除数据
    public static void deleRow(String tableName, String rowkey, String colFamily, String col) throws IOException {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        // 删除指定列族
        // delete.addFamily(Bytes.toBytes(colFamily));
        // 删除指定列
        // delete.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
        table.delete(delete);
        // 批量删除
        /*
         * List<Delete> deleteList = new ArrayList<Delete>();
         * deleteList.add(delete); table.delete(deleteList);
         */
        table.close();
        close();
    }

    // 根据rowkey查找数据
    public static void getData(String tableName, String rowkey, String colFamily, String col) throws IOException {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowkey));
        // 获取指定列族数据
        // get.addFamily(Bytes.toBytes(colFamily));
        // 获取指定列数据
        // get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
        Result result = table.get(get);

        showCell(result);
        table.close();
        close();
    }

    // 格式化输出
    public static void showCell(Result result) throws UnsupportedEncodingException {
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.print("RowName:" + new String(CellUtil.cloneRow(cell),"unicode")  + " ");
            System.out.print("Timetamp:" + cell.getTimestamp() + " ");
            System.out.print("column Family:" + Bytes.toString(CellUtil.cloneFamily(cell)) + " ");
            System.out.print("row Name:" + Bytes.toString(CellUtil.cloneQualifier(cell)) + " ");
            System.out.print("value:" + Bytes.toString(CellUtil.cloneValue(cell)) + " \n");
        }
    }

    // 批量查找数据
    public static void scanData(String tableName, String startRow, String stopRow) throws IOException {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
         scan.setStartRow(Bytes.toBytes(startRow));
         scan.setStopRow(Bytes.toBytes(stopRow));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            showCell(result);
        }
        table.close();
        close();
    }
    // 批量查找数据
    public static void scanData(String tableName) throws IOException {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            showCell(result);
        }
        table.close();
        close();
    }
}
