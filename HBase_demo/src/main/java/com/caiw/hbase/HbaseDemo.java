package com.caiw.hbase;

import java.io.File;
import java.io.IOException;

import com.caiw.hbaseutils.HbaseUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseDemo {



    public static void main(String[] args) throws IOException {
        //createTable("t2", new String[] { "cf1", "cf2" });
//        HbaseUtil.listTables();
//        HbaseUtil.scanData("CW_TEST001");
        HbaseUtil.insterRow("CW_TEST001","test001","c","NAME","华为");
        /*
         * insterRow("t2", "rw1", "cf1", "q1", "val1"); getData("t2", "rw1",
         * "cf1", "q1"); scanData("t2", "rw1", "rw2");
         * deleRow("t2","rw1","cf1","q1"); deleteTable("t2");
         */
    }





}
