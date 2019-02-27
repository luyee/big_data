package com.caiw.qq;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.testmail.MailUtilByYock;

/**
 * @ClassName QQDemo
 * @Author caiwe
 * @CreateTime 2018/9/5  17:00
 **/

public class QQDemo {

        public static void main(String[] args) throws IOException {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet("https://h5.qzone.qq.com/proxy/domain/r.qzone.qq.com/cgi-bin/tfriend/friend_show_qqfriends.cgi?uin=1968568204&follow_flag=1&groupface_flag=0&fupdate=1&g_tk=1618527651");
            get.addHeader("Accept",
                    "*/*");
            get.addHeader("Accept-Encoding", "gzip,deflate,sdch");
            get.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            get.addHeader("referer", "http://user.qzone.qq.com/1968568204");
            get.addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
            get.addHeader("Cookie","pgv_pvid=9819136700; pgv_info=ssid=s6397738714; rv2=80EE89992BE55328CF8338C99CFAEE34CBBC055C4F5ECF894A; property20=A7B48B66A2D072B8FF5247EDB8EDE20A6C9898B2079E2E522E2A57D73C545AFBB41C103681E2A7E5; __Q_w_s_hat_seed=1; __Q_w_s__QZN_TodoMsgCnt=1; pgv_pvi=9889131520; pgv_si=s7529074688; RK=p8ULeKXiGJ; ptui_loginuin=1968568204; ptisp=cnc; ptcz=deee8e0b7cc45b3e11aed27680441216e0f1de716869ceff0772f5c5dd54cf18; pt2gguin=o1968568204; uin=o1968568204; skey=@GmTwovsnK; p_uin=o1968568204; p_skey=g5EU8BU5M50Eg1Ry4*xxDFaBgCOLo2liCSUgI2VIIYg_; pt4_token=PZ9V0DRBiVLhaupZk*KSn3XXK7TZaYVpkZBzxaD08i8_; Loading=Yes; qzspeedup=sdch; blabla=dynamic; QZ_FE_WEBP_SUPPORT=1; cpu_performance_v8=1");
            try {
                CloseableHttpResponse response = client.execute(get);
                String bodyAsString = EntityUtils.toString(response.getEntity());
                bodyAsString = bodyAsString.substring(10,bodyAsString.length()-2);
                ObjectMapper objectMapper = new ObjectMapper();


                StringBuffer sb = new StringBuffer();
                sb.append("<html><head></head><body>");
                Map map = objectMapper.readValue(bodyAsString, Map.class);
                Map map2 = (Map) map.get("data");
                List<Map> map3 = (List<Map>) map2.get("items");
                for (int i = 0; i < map3.size(); i++) {
                    sb.append("<p><img src="+map3.get(i).get("img")+"/>"+map3.get(i).get("uin") + "\n"
                            + "昵称："+map3.get(i).get("name")+"("+map3.get(i).get("remark")+")"+"\n</P>");
                }
                sb.append("</body></html>");
//                String receiveEmail = "1968568204@qq.com";
//                String receiveNick = "longyi";
//                String subject = "Your QQ friends:";
                String contents = sb.toString();
                System.out.println(contents);
//                MailUtilByYock.sendMail(receiveEmail, receiveNick, subject, contents);
                response.close();
                client.close();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
}
