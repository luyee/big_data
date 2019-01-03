package com.caiw.okhttp;

import com.alibaba.fastjson.JSON;
import com.caiw.config.APIClientManager;
import com.caiw.config.APIConfig;
import com.caiw.config.StandardColumnsDO;
import com.caiw.config.TableColumns;
import com.thinkbiganalytics.feedmgr.rest.model.FeedCategory;
import com.thinkbiganalytics.metadata.api.category.CategoryColumnDescriptor;
import com.thinkbiganalytics.metadata.api.category.InterfaceSourceDescriptor;
import okhttp3.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OkHttpUtil {

    private APIConfig apiConfig;
    private static final Logger log = LoggerFactory.getLogger(OkHttpUtil.class);

    public OkHttpClient getAPIClient() {
        OkHttpClient apiClientManager = null;
        if (apiConfig == null) {
            synchronized (this) {
                if (apiConfig == null) {
                    apiConfig = new APIConfig("test",
                            "192.168.23.125:8400", "dladmin", "admin123");
                    apiClientManager = APIClientManager.getAPIClient(apiConfig);
                }
            }
        }
        return apiClientManager;

    }


    public String getResponse(Request request){
        OkHttpClient apiClient = getAPIClient();
        okhttp3.Response resp;
        String responseStr = "";
        try {
            resp = apiClient.newCall(request).execute();
            if (!resp.isSuccessful()) {
                // 返回错误
                String msg = "Found error response is not successfull: response = " + resp.toString();
                log.error(msg);
            }
            responseStr = resp.body().string();
        } catch (IOException e) {
            String msg = "Found error while executing request: " + request;
            log.error(msg);
        }
        return responseStr;
    }

    public String okHttpGetDemo(String url) {
        Request request = new Request.Builder().url("http://192.168.23.211:8767/api/metadata/v2/standardTable/getTableAndColumns?id=30").build();
        String responseStr = getResponse(request);
        System.out.println(responseStr);
        return responseStr;
    }


//    public static void main(String[] args) {
//        String url = "http://192.168.23.125:8400/proxy/v1/feedmgr/feeds/8024ffac-92dd-45ac-b66f-fc702a0a4e4d";
//        new OkHttpUtil().okHttpDemo(url);
//    }

    public void okHttpPostDemo(){
        FormBody formBody = new FormBody.Builder()
                .add("feedId","938365a6-40c0-496f-9eff-eaaef0d505df")
                .build();


        Request request = new Request.Builder()
                .url("http://192.168.23.125:8400/proxy/v1/feedmgr/feeds/disable/938365a6-40c0-496f-9eff-eaaef0d505df")
                .post(formBody)
                .build();

        String responseStr = getResponse(request);
        System.out.println(responseStr);
    }

    public void okHttpPostDemoWithEntity(){
        String body = "{\"name\":\"test\",\"age\":10}";
        Object parse = JSON.parse(body);
        String s = JSON.toJSONString(parse);
        System.out.println("s = " + s);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json"),s);
//        FormBody formBody = new FormBody.Builder()
//                .add("body",s)
//                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8765/api/datalake-feed/demo/test")
                .post(requestBody)
                .build();

        String responseStr = getResponse(request);
        System.out.println(responseStr);
    }


    public void getFile() throws IOException {
        OkHttpClient okHttpClient=getAPIClient();
        Request request=new Request.Builder()
                .get()
                .url("http://192.168.23.125:8400/proxy/v1/feedmgr/admin/export-feed/efde618b-9f19-4821-aaab-03876dcad4e7")
                .build();
        Response execute = okHttpClient.newCall(request).execute();
        byte[] bytes = execute.body().bytes();
        Headers headers = execute.headers();
//        Set<String> names = headers.names();
//        names.forEach(name -> {
//            System.out.println(name+"\t"+headers.get(name));
//        });
        String s = headers.get("Content-Disposition").replace("attachments; filename=\"","").replace("\"","");
        System.out.println(s);
    }


    public static void main(String[] args) throws IOException {
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        String s = okHttpUtil.okHttpGetDemo("");

        List<TableColumns> tableColumns = JSON.parseArray(s, TableColumns.class);
        System.out.println(tableColumns.get(0).getColumns().get(0).getStandardColumnsName());
        FeedCategory categoryByName = new FeedCategory();

        categoryByName.setColumns(updateColumns(tableColumns.get(0).getColumns()));

        System.out.println(categoryByName.getColumns().get(0).getNameEN());

    }

    public static List<CategoryColumnDescriptor> updateColumns(List<StandardColumnsDO> standardColumns) {
        List<CategoryColumnDescriptor> finalCategoryColumns = new ArrayList<>();
        standardColumns.forEach(standardColumnsDO -> {
            CategoryColumnDescriptor categoryColumn = new CategoryColumnDescriptor();
            categoryColumn.setColumnValueTable(standardColumnsDO.getColumnvauletable());
            categoryColumn.setComment(standardColumnsDO.getColumnen());
            categoryColumn.setDataType(standardColumnsDO.getDatatype());
            categoryColumn.setDefaultValue(standardColumnsDO.getDefaultvalue());
            categoryColumn.setDescription(standardColumnsDO.getDescription());
            InterfaceSourceDescriptor source = new InterfaceSourceDescriptor();
            source.setId(String.valueOf(standardColumnsDO.getInterfacesourceid()));
            source.setName(standardColumnsDO.getInterfacesourcename());
            source.setUrl(standardColumnsDO.getInterfacesourceurl());
            categoryColumn.setInterfaceSource(source);
            categoryColumn.setNameCN(standardColumnsDO.getNamecn());
            categoryColumn.setNameEN(standardColumnsDO.getStandardColumnsName());
            categoryColumn.setNullable(intTransFromBool(standardColumnsDO.getNullable()));
            categoryColumn.setPK(intTransFromBool(standardColumnsDO.getIspk()));
            finalCategoryColumns.add(categoryColumn);
        });
        return finalCategoryColumns;

    }

    public static boolean intTransFromBool(Integer integer){
        if(integer == null){
            return false;
        }
        return integer == 1;
    }
}
