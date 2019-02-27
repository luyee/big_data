package com.caiw;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetPhoto {
    public static void main(String[] args) throws IOException {
        //<img src="https://img-blog.csdn.net/20151102172300294" alt="这里写图片描述" title="">
        String src="http://img-blog.csdn.net/20151102172300294";//获取img中的src路径
        //获取后缀名
        String imageName = src.substring(src.lastIndexOf("/") + 1,src.length())+".png";
        //连接url
        URL url = new URL(src);
        URLConnection uri=url.openConnection();
        //获取数据流
        InputStream is=uri.getInputStream();
        //写入数据流
        OutputStream os = new FileOutputStream(new File("D:\\tmp\\", imageName));
        byte[] buf = new byte[1024];
        int l=0;
        while((l=is.read(buf))!=-1){
            os.write(buf, 0, l);
        }
    }
}
