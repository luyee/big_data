package com.caiw.wyy;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.xuxueli.poi.excel.ExcelExportUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @ClassName tst
 * @Author caiwe
 * @CreateTime 2018/9/5  17:44
 **/


public class TestWangYiYun {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        int page = 0;
        // 这里只爬取了3页数据
        ArrayList<WangYiYun> wangYiYunArrayList=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
//             爬取
          //https://music.163.com/#/discover/playlist/?order=hot&cat=%E8%BD%BB%E9%9F%B3%E4%B9%90&limit=1000&offset=0
          String url_str = "http://music.163.com/#/discover/playlist/?order=hot&cat=轻音乐&limit=35&offset=" + page;
//        Document document = Jsoup.connect(url_str).get();
//        System.out.println(document);
            ArrayList<WangYiYun> arrayList = WangYiYunMusic.Crawl(url_str, "utf-8");
            wangYiYunArrayList.addAll(arrayList);
            for (WangYiYun wangYiYun : arrayList) {
                System.out.println(wangYiYun);
            }

            /*
             * Excel导出：Object 转换为 Excel
             */
            // page参数加35（这个35是分析民谣栏）
            page = page + 35;
        }
        String filePath = "D:\\DSG\\project\\big_data\\jsoup_demo\\src\\main\\resources\\wangyiyun.xls";
        ExcelExportUtil.exportToFile(filePath, wangYiYunArrayList, wangYiYunArrayList);
    }

}