package com.caiw.hbase;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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

import static com.caiw.hbaseutils.HbaseUtil.createTable;
import static com.caiw.hbaseutils.HbaseUtil.deleteTable;

public class HbaseDemo {



    public static void main(String[] args) throws IOException {
        //"cwk_test_20190329:427ad303f250acb172695aa831397a8f","cwk_test_20190329:43acb681e2cbd606d269ce5e3194421f","cwk_test_20190329:46ea1bfb5a921559cf4316ec8e8ce412","cwk_test_20190329:47b42e464cbba2bcd1c0f96d4b96fbb1","cwk_test_20190329:48156a5b40fe862b83683cc5e841932a","cwk_test_20190329:50160da92bcc766af4446b4389e2e372","cwk_test_20190329:5055f4548534769fa33b51fac11afe80","cwk_test_20190329:518b5669cb7ebd25ed33b1d1b80223b5","cwk_test_20190329:57da5f124d0304d57d6afe8acfe63e46","cwk_test_20190329:58619dff4ebeda63f58c9e73f0cf897d","cwk_test_20190329:614288ae4e2c422df606433f6662dd56","cwk_test_20190329:619010a334d5dda70b2b879e5fdeb6bb","cwk_test_20190329:6262dac09b3db5aa3bc4765225a691b4","cwk_test_20190329:645e1c64f400505dea0a32489001b635","cwk_test_20190329:654f8fb22f9899b6a4473183f4532958","cwk_test_20190329:678c1037d57806775db36d80f14354d8","cwk_test_20190329:6874ffe7abca70b308b702dd3bc90a00","cwk_test_20190329:6897328180b7859b59869009272ed7cc","cwk_test_20190329:6bd8c330425093db9760f2202caf651c","cwk_test_20190329:6d0615d6840210e0777cae95fa9e9e06","cwk_test_20190329:6e8fcea9e785004bb76c451b43195aba","cwk_test_20190329:6fa5bf71afa7bc51230e699691dad0b5","cwk_test_20190329:700e60bde009d5cdf8c13a3977d04e90","cwk_test_20190329:71ef4a67158bb45920ce1dce5fb2b583","cwk_test_20190329:72d4fc8caed7073f1633bb20dd3f489f","cwk_test_20190329:741eed4ac1cc2b8d6fc64a3aa2cf0d71","cwk_test_20190329:76818ad65d38f844a7ee1c100abfc5b3","cwk_test_20190329:7c3c9174c1563929ebec6224fc82fa9b","cwk_test_20190329:7ee2fd90555fdc1c54a90d39c66ad9b4","cwk_test_20190329:8118b894337ee65b9c9e7fef08cf04cf","cwk_test_20190329:8401110828b38c030254e4ba80408429","cwk_test_20190329:85fee9fbaf978b0f3d2eb8a0f17a1f83","cwk_test_20190329:875dbf360320d80b7df232b639262bbc","cwk_test_20190329:8e54a7b5cacbf5737758a58d09fd8cf0","cwk_test_20190329:969f2673169252d64a1cd22e0eacbc70","cwk_test_20190329:96d688b2b3b3fadf6b10d437b4912218","cwk_test_20190329:9727ef326949c6df88fdaea54204d281","cwk_test_20190329:98cfa8eed9f703884f7e1afd33d48af3","cwk_test_20190329:9b2ed53e30dc3a9539e7258e41d4d69d","cwk_test_20190329:9b59e235fab2ddabcfeb95092eae532f","cwk_test_20190329:9c56d56612be1a6150297e501d03af26","cwk_test_20190329:9c6d55fc9f4df9500d5782752188bc69","cwk_test_20190329:a23e30b2bec1da413b03ab0188bd98ca","cwk_test_20190329:a599a94b75dedd69063514eb85159697","cwk_test_20190329:a7683171746540b7272e8a9802fbf26d","cwk_test_20190329:a84eaa721f86ef81bc374d725741a10f","cwk_test_20190329:a8510fe1d4daf94c6040665ed61987a7","cwk_test_20190329:a8a7be88fb095fe99539ff16b8bb4433","cwk_test_20190329:aa7c371e333c04e39389677a594272c7","cwk_test_20190329:ac77ab0effc8636d1dc903523f3d58dd","cwk_test_20190329:af6396fb3450f92fb71bb857ea7987e9","cwk_test_20190329:b0271395ad4129c494ec19b49592fcb3","cwk_test_20190329:b1251a909eeb4d50f85842bc3d0f1e33","cwk_test_20190329:b415ba61b53e993c22c2e4be29b6e185","cwk_test_20190329:bdf63e5da42f7780930fda8219e3c08e","cwk_test_20190329:bf555b799e3b013ed1e10b43ab2db144","cwk_test_20190329:c20e71fa1865f7f9d63ddd752cc81bff","cwk_test_20190329:c4514c2f4635ee85d62726bc7c8c5c4c","cwk_test_20190329:c5e15ddcb75b1e6a671512c735356edd","cwk_test_20190329:c74d46d715def66e9ed890323ce232b4","cwk_test_20190329:cb39ac9e565942c50c145af2df2c108d","cwk_test_20190329:cdb10160619e2bdc606aeb69be5dc613","cwk_test_20190329:cdf2b60515298697138279a9a7cf1df4","cwk_test_20190329:cfb5320a37f07b91611fbb20c5d5ffbf","cwk_test_20190329:d2243ea1c0a6088f3ea545cd8ec8ed86","cwk_test_20190329:dc4e1630b9978907f3a23ef73a65cef5","cwk_test_20190329:dc6d86a69cafc68a53df01e0d8dd6a10","cwk_test_20190329:e0395e9f638fe724735693c0f49d95e8","cwk_test_20190329:e5402519953a988cfb4feb30454c0f5f","cwk_test_20190329:e573b4f6f11adc879e6f8cd63549177c","cwk_test_20190329:e5902a5403477e86fc0f2e0315f8bcb0","cwk_test_20190329:e5bab782b8352a8695d2f4283c430331","cwk_test_20190329:e8ed5873843981a65ad2516cd7827211","cwk_test_20190329:ea3d670e4d3e8bb38e9bc188932b51fc","cwk_test_20190329:eb6fd5cad993b8e3f11d7fb602f8ffcc","cwk_test_20190329:eeb51c1b121e9f69a24d7d95f4e6a949","cwk_test_20190329:ef163943e16620aaca4b6f4db9371d65","cwk_test_20190329:f3b699f9f7ed259e9712a5643e7ed6b0","cwk_test_20190329:f44e7dd367f7c15d1543947504e366a4","cwk_test_20190329:f7c8bdba7bcba50878cc9c7d88f9cb35","cwk_test_20190329:f826e634f4beb965a0a1dd83c0da5500","cwk_test_20190329:f826f9c42934bedec6d8f9654c72ecb2","cwk_test_20190329:f8c5f807ec7e08b9c4d3b9827d39822f","cwk_test_20190329:fa3dbe6bea45aa12f4772b4a537a889a","cwk_test_20190329:fa84ee0bd9d8efec2c4511d1f99eab53","cwk_test_20190329:fa8a952928a77dc10e95c6e5b5aa4d13","cwk_test_20190329:fc1d5160cf31e40ee89a13d5b859e9de","cwk_test_20190329:fc3cc7281a7de738dbbbd22c2cad066a","cwk_test_20190329:ffe275a3876475627e195ab2ad9768c9","hbase_test20190416","myNewTestTable","replication_source_table","t1","test1:user_test_2019040901","test2:user_test_2019040902","test2:user_test_2019041001","test2:user_test_2019041002","test:user_test_20190408","test_20190326:0498044be64dd7a223baddcc8a4e7ad6","test_20190326:50de7cad61fcc8b529c353dcbc5cb863","test_20190326:7443afceb16f2fded8a2aa1d9036b16e","test_20190326:7ca12bcb93f9e6c8ef4b3de2a1d596f6","test_20190326:9ae000d1c11c3e86d416afa83d51a257","test_20190326:b17560640f5d5a469a6941cac0ce6a1e","test_20190326:c6b6bf41ceb370a85aac1b5e329e6fe0","test_20190326:e9159f0b57694b608912ee4cfd7e9154","test_20190326:e9b045f7124c9a6c8ee9cfc6235d2fb8","test_20190326:ff468719ebb7020c7ab72cff4214be2a","test_20190326:tab_201904121054",
        String[] strings = new String[]{"tmp:wyl","user_info","wyl"};
//        System.out.println(strings.length);
        String aa = "adad";
        aa.replaceAll(",,","");
        int length = strings.length;
//        String[] strings = new String[]{"user","user_info"};
        HbaseUtil.init();
        for (String str :
                strings) {
            System.out.println(str);
            HbaseUtil.deleteTable(str);
            length -= 1;
            System.out.println(length);
        }
        HbaseUtil.close();

//        createTable("test1","test1:user_test_2019040901", new String[] { "cf1"});
//        deleteTable("test:user_test_2019040901");
//        HbaseUtil.listTables();
//        HbaseUtil.scanData("CW_TEST001");
//        HbaseUtil.insterRow("user_info","test002","cf1","name","测试");

//        Admin admin = HbaseUtil.getAdmin();
//        Connection connection = HbaseUtil.getConnection();
//        TableName tableName = TableName.valueOf("user_info");
////        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
//
//        Table table = connection.getTable(tableName);
//        System.out.println(1);
//        long writeBufferSize = table.getWriteBufferSize();
//        HTableDescriptor tableDescriptor = table.getTableDescriptor();
//        Map<String, String> configuration = tableDescriptor.getConfiguration();


//        System.out.println(configuration);
        /*
         * insterRow("t2", "rw1", "cf1", "q1", "val1"); getData("t2", "rw1",
         * "cf1", "q1"); scanData("t2", "rw1", "rw2");
         * deleRow("t2","rw1","cf1","q1"); deleteTable("t2");
         */
    }





}
