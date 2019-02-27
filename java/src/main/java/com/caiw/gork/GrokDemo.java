package com.caiw.gork;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.Match;
import io.thekraken.grok.api.exception.GrokException;
import org.junit.Test;

import java.util.Map;

public class GrokDemo {
    private static final String GROK_PATTERN_PATH = "D:\\DSG\\project\\big_data\\java_demo\\src\\main\\resources\\patterns1";

    public static void pattern(String pattern,String message) {
        Match match = null;
        try {
            Grok grok = new Grok();
            //添加patter配置文件,默认的grok的pattern是null
            grok.addPatternFromFile(GROK_PATTERN_PATH);
            grok.compile(pattern);
            match = grok.match(message);
            match.captures();
            if(!match.isNull()){
                System.out.println(pattern);
                System.out.println(match.toJson().toString());
            }else{
                System.out.println("not match");
            }
        } catch (GrokException e) {
            e.printStackTrace();
        }
    }

    public static void pattern(String pattern,String message,Map<String,String> patternRule) {
        Match match = null;
        try {
            Grok grok = new Grok();
            //添加patter配置文件,默认的grok的pattern是null
            grok.addPatternFromFile(GROK_PATTERN_PATH);
            if(patternRule != null){
                patternRule.keySet().forEach(key -> {
                    try {
                        grok.addPattern(key,patternRule.get(key));
                    } catch (GrokException e) {
                        System.out.println("add pattern rule failed.\t"+e.getMessage());
                    }
                });
            }
            grok.compile(pattern);
            match = grok.match(message);
            match.captures();
            if(!match.isNull()){
                System.out.println(pattern);
                System.out.println(match.toJson().toString());
            }else{
                System.out.println("not match");
            }
        } catch (GrokException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GrokTest001(){
        String message = "2017-08-11:06:04:43: Processing thr#: 2, seq: 45382, ofs: 680808, len: 8584, scn0 0xe2c7175.20700001\n2017-08-11:06:04:43: Processed thr#: 2, seq: 45382, ofs: 689072, scn 0xe2c7175.56d30001\n2017-08-11:06:04:43: Processing thr#: 1, seq: 75718, ofs: 15992919, len: 3180499, scn0 0xe2c7175.28eb0001\n2017-08-11:06:04:43: Processed thr#: 1, seq: 75718, ofs: 19178294, scn 0xe2c7175.5c900001\n MINSCN 0xe2c7175.206f0001 2017-8-11:06:04:40 - MAXSCN 0xe2c7175.5c900001 2017-8-11:06:04:43" +
                " (U:937:142734,C:51:16268)\n" +
                "        14-I 4-D 38-U 0-DDL ~~\n        8-I 0-D 1-U 0-DDL ~~\n2017-08-11:06:04:43:\n  send scn    : 0xe2c7175.1ed70001 - 0xe2c7175.5c900001.\n  cached data : U:937:142734, C:0:0\n  cmt_grp     : free:16384, cmt:0, count:16384\n2017-08-11:06:04:43  aoxc wcommit:\n        1_75718 , scnu 0x0e2c.71755c90 .0001, ofs 19180953\n        2_45382 , scnu 0x0e2c.717556d3 .0001, ofs 690462";
//        String pattern = "%{TIME_DSG:time}([\\s\\S]*)MINSCN %{SCN_NUM:logMinscn} %{TIME_DSG:logMinTime}.*MAXSCN %{SCN_NUM:logMaxscn} %{TIME_DSG:logMaxTime}(.*)\\n(\\s*)%{SCN_NUM:insert}-I %{SCN_NUM:delete}-D %{SCN_NUM:update}-U %{SCN_NUM:DDL}-DDL";
        String pattern = "%{TIME_DSG:time}([\\s\\S]*)MINSCN %{SCN_NUM:logMinscn} %{TIME_DSG:logMinTime}.*MAXSCN %{SCN_NUM:logMaxscn} %{TIME_DSG:logMaxTime}" +
                "(.*\\s+)%{SCN_NUM:insert}-I %{SCN_NUM:delete}-D %{SCN_NUM:update}-U %{SCN_NUM:DDL}-DDL";
//        String pattern = "%{FILTER_AOXD}";
        pattern(pattern,message);
    }

    @Test
    public void GrokTest002(){
        String message = "grp#  size     seq#   bsz      nab flg dup TH Next-SCN  2017/08/09 19:10:18\n" +
                "10  262144    78670  4096   123690   8   1  1 0xffffffffffff 78670.123465.0\n" +
                "2017-08-09:19:10:18: alen:1331200, add_buffer:0, fsz:1073745920 bsz:4096\n" +
                "2017-08-09:19:10:18: processing rba 78670.123465.0 (ckp: 78670.123690.x) ...\n" +
                "2017-08-09:19:10:18: processed returned on rba 78670.123690.0 (ckp: 78670.123690.x)\n" +
                "      MINSCN 0xe2442f6.630d0001 2017-8-09:19:10:15 - MAXSCN 0xe2442f6.7aad0001 2017-8-09:19:10:18 (U:5:718093,C:1:72)\n" +
                "        1-I 0-D 0-U 0-DDL ~~\n" +
                "2017-08-09:19:10:18:\n" +
                "  send scn    : 0xe2442f6.5a5e0001 - 0xe2442f6.7aad0001.\n" +
                "  cached data : U:5:718093, C:0:0\n" +
                "  cmt_grp     : free:8192, cmt:0, count:8192";
        String pattern = "%{TIME_DSG:time}([\\s\\S]*)MINSCN %{SCN_NUM:logMinscn} %{TIME_DSG:logMinTime}.*MAXSCN %{SCN_NUM:logMaxscn} %{TIME_DSG:logMaxTime}" +
                "(.*\\s+)%{SCN_NUM:insert}-I %{SCN_NUM:delete}-D %{SCN_NUM:update}-U %{SCN_NUM:DDL}-DDL";
        pattern(pattern,message);
    }

    @Test
    public void GrokTest003(){
        String message = "2017-08-11:06:04:43: Processing thr#: 2, seq: 45382, ofs: 680808, len: 8584, scn0 0xe2c7175.20700001\\n2017-08-11:06:04:43: Processed thr#: 2, seq: 45382, ofs: 689072, scn 0xe2c7175.56d30001\\n2017-08-11:06:04:43: Processing thr#: 1, seq: 75718, ofs: 15992919, len: 3180499, scn0 0xe2c7175.28eb0001\\n2017-08-11:06:04:43: Processed thr#: 1, seq: 75718, ofs: 19178294, scn 0xe2c7175.5c900001\\n      MINSCN 0xe2c7175.206f0001 2017-8-11:06:04:40 - MAXSCN 0xe2c7175.5c900001 2017-8-11:06:04:43" +
                " (U:937:142734,C:51:16268)\\n" +
                "        14-I 4-D 38-U 0-DDL ~~\\n        8-I 0-D 1-U 0-DDL ~~\\n2017-08-11:06:04:43:\\n  send scn    : 0xe2c7175.1ed70001 - 0xe2c7175.5c900001.\\n  cached data : U:937:142734, C:0:0\\n  cmt_grp     : free:16384, cmt:0, count:16384\\n2017-08-11:06:04:43  aoxc wcommit:\\n\n" +
                "        1_75718 , scnu 0x0e2c.71755c90 .0001, ofs 19180953\\n        2_45382 , scnu 0x0e2c.717556d3 .0001, ofs 690462";
        String pattern = "%{TIME_DSG:time}([\\s\\S]*)MINSCN %{SCN_NUM:logMinscn} %{TIME_DSG:logMinTime}.*MAXSCN %{SCN_NUM:logMaxscn} %{TIME_DSG:logMaxTime}(.*\\s+)%{SCN_NUM:insert}-I %{SCN_NUM:delete}-D %{SCN_NUM:update}-U %{SCN_NUM:DDL}-DDL";

        pattern(pattern,message);
    }

}
