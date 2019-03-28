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

public class HbaseDemo {



    public static void main(String[] args) throws IOException {
//        String[] strings = new String[]{"20190315test:test01","20190315test:test02","20190315test:test03","20190315test:test04","20190315test:test05","cwk_test_20190327:407ed93aeb4a7b8f169591732d125dd8","cwk_test_20190327:a670c1f33583891060509539de61ee1d","hbase_test","t1","test_20190308:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190308:d94f70242efd0a973c2bf6489ed6910f","test_20190309:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190309:d94f70242efd0a973c2bf6489ed6910f","test_20190310:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190310:d94f70242efd0a973c2bf6489ed6910f","test_20190311:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190311:d94f70242efd0a973c2bf6489ed6910f","test_20190312:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190312:d94f70242efd0a973c2bf6489ed6910f","test_20190313:6539c1b3c9cb8fd9a25b1e37dacaba75","test_201903141532:0d2d97b4994903ea7cab2aaf256ff19b","test_201903141532:0e843973d34ec2fed68b54380920f95b","test_201903141532:11c8dc20e925da06226965fc51309635","test_201903141532:130174c18cac1d48fc162ce5ac747aa4","test_201903141532:1e10367d735eb766ecc73b4c244bcf09","test_201903141532:279fdda91fd5d796164bf19c7c0b4c49","test_201903141532:27dd5fcffa525db3e1c65e0ba5c9e0fa","test_201903141532:29de21d53633a584d07e83f7a786bf09","test_201903141532:2d4a54ec01a5a1c2c2c172efff916043","test_201903141532:3149ca5773434ee689b769cc8ae7e51c","test_201903141532:33997c18b58e50d673c71534fdc911b4","test_201903141532:33bde2a3c050df0359baccb252ea0fe4","test_201903141532:359d1fdc19de2cc6525f752659458e7d","test_201903141532:37c6938ffeafef16e2d843fda12f3362","test_201903141532:3cb8e4d961b74440a919fd5ec285ec57","test_201903141532:3d5bf3713cccf763cc4864b435970757","test_201903141532:4736736eb650ce21b63c7fd546b4efcd","test_201903141532:4b8aca089a09c6cc1b7414a22e703df1","test_201903141532:4ca4f06f38c1484396222ed73aa13d67","test_201903141532:4d86f56c21b11a7f41b421c92441688d","test_201903141532:4fac0afc630c6e84f52000f6d0ac2ba3","test_201903141532:5169f8c41caa2399c0280bbf9b2c3e92","test_201903141532:5a73791c081101ed84d8187c1b64ef2e","test_201903141532:6140d5dd416b5e34f6008769ebd6c643","test_201903141532:6795962df79ebe856a16430d45e8dd5e","test_201903141532:6a497f74e0dd06d3b8ddb576b20edfc1","test_201903141532:6babdb5d2deadab3190c39142792c8c3","test_201903141532:722d110e5fd9e92c40ca2fefd2364220","test_201903141532:73880b4e7a77671918b90c5b66457e47","test_201903141532:768c2942ce747fad3aed8feef486c588","test_201903141532:7cfd05baf236ed264002cd383d2a3f2d","test_201903141532:7e3a22ef4a19ba9b1c2e839a778a2e27","test_201903141532:800ecf6cecc9d84f7ffc9c8141279338","test_201903141532:82ce2f57f8264901ce2d6e02d0b17dee","test_201903141532:83758ed825ba51c2fa2c52c6f3cb8184","test_201903141532:8c79d7f4d664ff19d2973993721b084a","test_201903141532:8e0608b47d7c08b1dbb9736011eb1edb","test_201903141532:8e16497370b87b15069f247aee377b75","test_201903141532:912494e6601f1efe8361ad6957cee614","test_201903141532:9389580f7cd13ef179d2c941c4638686","test_201903141532:946545cc8d5665c385ef351330d2de49","test_201903141532:9e611f8cff7fb7af15a4e701c9edd832","test_201903141532:a1178d7df92299332814a9bbcea520c1","test_201903141532:a4393d98f139753f4618887be0260d10","test_201903141532:a9398b952a5f3dd811924670668b851f","test_201903141532:aa52b11a9dfac2a2d9e2a561e47cd7cd","test_201903141532:af37e2bf48f82572e9ad253886da28c8","test_201903141532:b095c15dd895c16a46fc22209fdc0b17","test_201903141532:bd38daa6e89aaac491f95359db794a2d","test_201903141532:c53cecabdb0be565ba22462bde7fa358","test_201903141532:c5a28d9266c7767c85f501cd08e57547","test_201903141532:d31cc1463ac9ddd25ee6cc1d29b33d0b","test_201903141532:e3227f6e0bb19f498b99f505dae62e61","test_201903141532:e3d5592ce4c536816880ead94bf8a446","test_201903141532:e936ce6eeb574d1cd94374080ec8254a","test_201903141532:f0398a52300dc068afda5e647db244be","test_201903141532:f1213a807ba67c7ebc15ec41df96e63e","test_201903141532:f4c6e7a000ba40ca7cc8f5228eecdcaa","test_201903141532:fb5975017256716b0cd1cbd11dc863ed","test_201903141532:fbbf594242699d297bd462dc2063fc6c","test_201903141638:018b251bdc1675d9472577f7d4f6d3c6","test_201903141638:176665ae011e1bf6f94e0e36aed0c532","test_201903141638:d2b18418151a9694c4a8de3c70378762","test_201903141638:f9e4f03b1b0fba7328e99949764532cd","test_20190314:d94f70242efd0a973c2bf6489ed6910f","test_201903151515:2d2060e6344344675dc16da6c23ad1b1","test_201903151515:3d0d1e44eb3a08e9a8b0bd28fd1bbafc","test_201903151515:724441a51f3807f9b120a1e275b814f9","test_201903151515:7b8d29bed3a022e17aba21686ab8db18","test_201903151515:814d8c0a1f8eb0525f121385ee4fd6f4","test_201903151515:81d1d9ae451e93feaad23b3b0c9f92d7","test_201903151515:b0116a88c246a67c2a198302e8fb7dfb","test_201903151515:b608a7ca176a7c538f0ecafb5c5e54bf","test_201903151515:c0859b262936245a6b513c3831e77ff9","test_201903151515:d787032eb778de1e36f400d69707490e","test_201903151515:dfd621e0c459b1a642fbfae2c805788d","test_201903151515:f42b138122f81b83ff1b87d77a7b3209","test_20190317:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190317:d94f70242efd0a973c2bf6489ed6910f","test_20190318:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190319:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190319:d94f70242efd0a973c2bf6489ed6910f","test_20190320:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190320:d94f70242efd0a973c2bf6489ed6910f","test_20190321:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190321:d94f70242efd0a973c2bf6489ed6910f","test_20190322:6539c1b3c9cb8fd9a25b1e37dacaba75","test_20190322:d94f70242efd0a973c2bf6489ed6910f","test_20190323:359d1fdc19de2cc6525f752659458e7d","test_20190323:7cfd05baf236ed264002cd383d2a3f2d","test_20190324:359d1fdc19de2cc6525f752659458e7d","test_20190324:7cfd05baf236ed264002cd383d2a3f2d","test_20190325:359d1fdc19de2cc6525f752659458e7d","test_20190325:407ed93aeb4a7b8f169591732d125dd8","test_20190325:7cfd05baf236ed264002cd383d2a3f2d","test_20190326:004d66b6dc6c52a0e782d14e6cefb5ef","test_20190326:040f77a358e159592a6d44eaa630fc04","test_20190326:0b6ad6a6680388edfa77b84759fb33d0","test_20190326:1cd1f5353658241c035fdcb69b413291","test_20190326:215824f001ff0eb39142a6b216ecaf46","test_20190326:2934d0d7ccd7cbac0010404c249571d7","test_20190326:2da7198286a7a5bf910261e2d1b0373d","test_20190326:359d1fdc19de2cc6525f752659458e7d","test_20190326:35fda7a451b4fae1cb7df793fc9f6564","test_20190326:37cfadf588ad13548b83909da4fcd1ee","test_20190326:38aaca68f7a97dd53027ccec05189d7c","test_20190326:3c18001fa1b068691acab6fdda9fa688","test_20190326:3fbaaa937967ed06b5a785557e0719fd","test_20190326:40c53521778388a8d4b984ed8e8d0d11","test_20190326:47cc879889fc4831829fcee8a82aabb6","test_20190326:493eba30350b223135a1aef16047a629","test_20190326:4fea838dd22e34efd2f798ce6818a212","test_20190326:50de7cad61fcc8b529c353dcbc5cb863","test_20190326:6328f167e00c98fae6ee5dc16b705def","test_20190326:675289bb156478b46fd4ee8fc3978a02","test_20190326:67da04662bfbe15e856003b31dcf0414","test_20190326:69e030cef183329aed22ed602f2d9f00","test_20190326:6d17d49a2e7dbf4f33f6b2a379044804","test_20190326:7cfd05baf236ed264002cd383d2a3f2d","test_20190326:8debfdf77e71f5fd82c8547415aa2e39","test_20190326:9278a65b5b855427c43fc9619d228093","test_20190326:95797428afc8108dc2b421ce340e2209","test_20190326:963f8257cb0eb3eab4ed2ebe96226de3","test_20190326:9a93928896a79211413f638622eb37b0","test_20190326:a670c1f33583891060509539de61ee1d","test_20190326:bf4cbc06743395783feb768036e58f22","test_20190326:c30981b2e0f15855a3ba5051382e0fa9","test_20190326:efb6deb87b935a025fcb20b57dda5d04","test_20190326:f36b0497ea712c7cfba20de20d47d9d3","test_20190326:f4de5c7898b42fcb5eeff2bc146428cd","test_20190326:fd276d0c0401be73ff626b25c89eb3b8","test_20190326:tab_demo","test_20190327:359d1fdc19de2cc6525f752659458e7d","test_20190327:7cfd05baf236ed264002cd383d2a3f2d","test_20190327:dd2d26fab8bc386e8064808ca7faa5de","test_20190328:dd2d26fab8bc386e8064808ca7faa5de","test_20190329:071849195c35274969b61f10dc10c950","test_20190329:08563f36607b544db7d01172a2b97620","test_20190329:08a71c2d06ed07f5f8ca910b55b544d1","test_20190329:0a6aaa5c6d58e5d52402db458b595ef5","test_20190329:0ee82d645581c34bf0468743ea85dc05","test_20190329:124f462c98f01477e44f2d31bdfc1e33","test_20190329:1cdc5b18fa21e18b9db08f07842b0225","test_20190329:1e92a97a1fdfdc4881ffc2a0064871d8","test_20190329:1ec5d47291f0d71dce4a10bfda0daee0","test_20190329:1f706e942dc9bf523afc2146d8d1bc37","test_20190329:25a9409e7679555cec82772cb0def5b6","test_20190329:2f8ff9a47f3c591dfb0ba63c04744231","test_20190329:389a333d58bbc275acfd595330eaae6d","test_20190329:3a944edb4c70f86a50d213602be87a01","test_20190329:3b01e21c830dc19ee390c36b2519a82a","test_20190329:3d0d1e44eb3a08e9a8b0bd28fd1bbafc","test_20190329:3f5623a2c9fb3396ef3aa470595319d9","test_20190329:3fbf465d033e149ff1a3107e3316d2f2","test_20190329:407ed93aeb4a7b8f169591732d125dd8","test_20190329:408c88d58e90832a61f6c3d5c440691c","test_20190329:4170639c50e963379e57e6d27cec13c6","test_20190329:4b196097ea891c6fc6e3a2e5538f8254","test_20190329:4d392226cfcdef09063d94546d816c5f","test_20190329:50de7cad61fcc8b529c353dcbc5cb863","test_20190329:540ba175f0dab19979e3e82848600512","test_20190329:56a2ddbce15dacdf8b8224904cc5f722","test_20190329:56d284112fc49f06f23a0d24801bb0f1","test_20190329:5a91cca5d2bed0debd139222dda952ee","test_20190329:643dcec934bbecdec1b38eb7a2c3c890","test_20190329:665e8ae22d9607f071eaab35620a1089","test_20190329:6661ba247575a1e1f67b0912b8c3d9d7","test_20190329:6a2d22ac89134fdddf85f8307b47476a","test_20190329:6a79e8296f10ae865b487ef6f3007199","test_20190329:6d995c4bf8359b34566b3bb82e9faf26","test_20190329:705853c211fdb089c60069ff591649cb","test_20190329:7084b8e0a73cb1c4a15c1d01d74158dd","test_20190329:79585da47b828b9232d7c63029c14a74","test_20190329:7f186189c0fdac96db6763fd7a4012b3","test_20190329:7f85a7ba786074619742f9da947f265d","test_20190329:825c497771d55174cf5f0bdde4cc16cc","test_20190329:834b66e756f1212e818f47d260bc37ed","test_20190329:8373564eb683f4e64869129443a2bec4","test_20190329:87263565eb2bc996d99965fcea72ca6b","test_20190329:889c5ee906590ab8db41e71f53c3aa52","test_20190329:8b8d945e228188f766a5458448cb9e86","test_20190329:8e1fbcdd81e27e4c931cfad0fbe1edc5","test_20190329:8eda21cb47967cd33ab9dd6282238fb3","test_20190329:90aa265b58a44257c5477f4621311ab4","test_20190329:9490c464acce58d9cd6cc9eb18042677","test_20190329:97255ff8b9b62d1de3210fecb803eaf4","test_20190329:9e98721812edd6eec8d419150f26e5a4","test_20190329:9f96775c9201be4fa13e0a24f58d8cd9","test_20190329:a01e9c7ba7fe6dd741b4a24e43ecd4e5","test_20190329:a04db1d883cd0fe98c66cde4c75774ad","test_20190329:a05a7f846da5a4597ce0b093365efce6","test_20190329:a0c9a3cb20a5a325647b4b211a2acd79","test_20190329:a2aef90dbfabb615917f8898904217af","test_20190329:a670c1f33583891060509539de61ee1d","test_20190329:a7bdd094e608c50c3aef442502eb0ca3","test_20190329:afc9b64add217819a4e292009622d49d","test_20190329:b7e6e1f05b927cd0de567a83ba0f3e21","test_20190329:b967a3a5a4efc2b8a594b2d79c3fe284","test_20190329:baebbacadcaf54d69c05b2500f590e1a","test_20190329:bb39864087d2003bece03412ab908e08","test_20190329:be42c23b279a8681d19177c3ad07b3d7","test_20190329:c072b03b28f44a83b30852f5e075f501","test_20190329:c357a313f54ad5e584f0b61745d5e9ac","test_20190329:ca96235efa18dc9320a7cea70b90bd59","test_20190329:cef4e6e50710f14323079884ee7ccc2e","test_20190329:cfc4f68a9769354c4c7e0c4cf12ac6ac","test_20190329:d1b4c766e10d03ff0efa58790b6fadd7","test_20190329:d3ec5198db4ed2b6e27bb61bfe8e18e3","test_20190329:d53d8bbf9e05b24f2038183f5da92469","test_20190329:d61aeb0fe080df9bb1ec3d03e98f6b67","test_20190329:d6d417781c7c29f8b19d93005fce5293","test_20190329:d79ba6ca414c5adf37332984485464f7","test_20190329:daa580ba2934dafe540a8884a71285a1","test_20190329:dd2d26fab8bc386e8064808ca7faa5de","test_20190329:defe2f66db47bbcebc38c07b8cb419bb","test_20190329:dfd621e0c459b1a642fbfae2c805788d","test_20190329:e3a4fd7bfcc1634c116e2ef85e08bc56","test_20190329:e979dd7d186e1336ad5370388ef8ec8b","test_20190329:e9c0c6a5f0f4c170e77cdd4f92e09ed5","test_20190329:ede8971c48fbdaa4c83c9b326849b57a","test_20190329:f42b138122f81b83ff1b87d77a7b3209","test_20190329:f8f0020920f3b29983a9e2334578f38f","test_20190329:ff5e99d80d62be8c238fc3764277e7c9","test_20190329:ffb3f52ae6ef1aed6cf3c2a57d4483a8"};
        String[] strings = new String[]{"user","user_info"};
        HbaseUtil.init();
        for (String str :
                strings) {
            System.out.println(str);
            HbaseUtil.deleteTable(str);
        }
        HbaseUtil.close();

        //createTable("t2", new String[] { "cf1", "cf2" });
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
