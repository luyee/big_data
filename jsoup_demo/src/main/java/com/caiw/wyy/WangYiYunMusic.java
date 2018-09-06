package com.caiw.wyy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * TODO 该类用于获取保存在Arraylist中的歌曲对象
 *
 * @ClassName WangYiYunMusic
 * @Author caiwe
 * @CreateTime 2018/9/5  17:42
 *
 */

public class WangYiYunMusic {

    /**
     * 该方法爬取了歌曲标题和链接地址并且调用了GetTheNumberOfPlays()方法用于获取歌曲链接地址页面的详细播放次数
     *
     * @param url_str
     * @param charset
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static ArrayList<WangYiYun> Crawl(String url_str, String charset)
            throws ClientProtocolException, IOException {
        // 获取
        CloseableHttpClient hc=HttpClients.createDefault();
        //HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(url_str);
        HttpResponse response = hc.execute(hg);
        HttpEntity entity = response.getEntity();

        InputStream htm_in = null;
        ArrayList<WangYiYun> arrayList = new ArrayList<>();
        if (entity != null) {
            htm_in = entity.getContent();
            String htm_str = InputStream2String(htm_in, charset);
            Document doc = Jsoup.parse(htm_str);
//            Document doc = Jsoup.connect(url_str).get();
            Elements links = doc.select("div[class=g-bd]").select("div[class=g-wrap p-pl f-pr]")
                    .select("ul[class=m-cvrlst f-cb]").select("div[class=u-cover u-cover-1");
            for (Element link : links) {
                Elements lin = link.select("a");
                // 歌曲描述
                String description = lin.attr("title");
                // 歌曲链接地址
                String href = lin.attr("href");
                href = "http://music.163.com" + href;
                /*
                 * System.out.print(re_title + "       ");
                 * System.out.print(re_url + "       "); int nums =
                 * GetTheNumberOfPlays(re_url, charset);
                 */
                int nums =GetTheNumberOfPlays(href, charset);
                WangYiYun wangyiyun = new WangYiYun(description, href, nums);
                arrayList.add(wangyiyun);
            }
        }
        return arrayList;
    }

    /**
     * 该方法爬取歌曲链接地址页面的播放次数
     *
     * @param url_str
     * @param charset
     * @throws ClientProtocolException
     * @throws
     *
     */
    public static int GetTheNumberOfPlays(String url_str, String charset) throws ClientProtocolException, IOException {
        CloseableHttpClient hc=HttpClients.createDefault();
        HttpGet hg = new HttpGet(url_str);
        HttpResponse response = hc.execute(hg);
        HttpEntity entity = response.getEntity();

        InputStream htm_in = null;
        int nums = 0;
        if (entity != null) {
            htm_in = entity.getContent();
            String htm_str = InputStream2String(htm_in, charset);
            Document doc = Jsoup.parse(htm_str);
            String links = doc.select("div[class=u-title u-title-1 f-cb]").select("div[class=more s-fc3]")
                    .select("strong").text();
            nums = Integer.parseInt(links);
        }
        return nums;
    }

    /*
     * public static void saveHtml(String filepath, String str) {
     *
     * try { OutputStreamWriter outs = new OutputStreamWriter(new
     * FileOutputStream(filepath, true), "utf-8");
     * outs.write("http://www.dailystrength.org" + str + "\r\n"); outs.close();
     * } catch (IOException e) { System.out.println("Error at save html...");
     * System.out.println(str); e.printStackTrace(); } }
     */

    public static String InputStream2String(InputStream in_st, String charset) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while ((line = buff.readLine()) != null) {
            res.append(line);
        }
        return res.toString();
    }
}