package com.caiw.generatedata;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 模拟数据程序
 *
 * @author caiw
 */

public class CreateData {

    /**
     * 模拟数据
     *
     * @param sc
     * @param sqlContext
     */
    private static final String ACTION_PATH = "D:\\test\\TXT\\action.txt";
    private static final String USER_PATH = "D:\\test\\TXT\\user.txt";
    private static final String PRODUCT_PATH = "D:\\test\\TXT\\product.txt";

    private static void mock() {

        String[] searchKeywords = new String[]{"火锅", "蛋糕", "重庆辣子鸡", "重庆小面",
                "呷哺呷哺", "新辣道鱼火锅", "国贸大厦", "太古商场", "日本料理", "温泉"};

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis())).substring(0,10);
        String[] actions = new String[]{"search", "click", "order", "pay"};
        Random random = new Random();
        CreateData.createFile(ACTION_PATH);
        for (int i = 0; i < 50; i++) {
            long userId = random.nextInt(100);

            for (int j = 0; j < 10; j++) {
                String sessionId = UUID.randomUUID().toString().replace("-", "");
                String baseActionTime = date + " " + random.nextInt(23);

                long clickCategoryId = 1;

                for (int k = 0; k < random.nextInt(100); k++) {
                    long pageId = random.nextInt(10);
                    String actionTime = baseActionTime + ":" + random.nextInt(59) + ":" + random.nextInt(59);
                    String searchKeyword = null;
                    long clickProductId = 1;
                    String orderCategoryIds = null;
                    String orderProductIds = null;
                    String payCategoryIds = null;
                    String payProductIds = null;

                    String action = actions[random.nextInt(4)];
                    switch (action) {
                        case "search":
                            searchKeyword = searchKeywords[random.nextInt(10)];
                            break;
                        case "click":
                            if (clickCategoryId == 1) {
                                clickCategoryId = Long.valueOf(String.valueOf(random.nextInt(100))) + 1;
                            }
                            clickProductId = Long.valueOf(String.valueOf(random.nextInt(9))) + 1;

                            break;
                        case "order":
                            orderCategoryIds = String.valueOf(random.nextInt(100));
                            orderProductIds = String.valueOf(random.nextInt(100));
                            break;
                        case "pay":
                            payCategoryIds = String.valueOf(random.nextInt(100));
                            payProductIds = String.valueOf(random.nextInt(100));
                            break;
                    }

                    int city_id = random.nextInt(31)+1;
                    String wordLine = date + "," + userId + "," + sessionId + "," + pageId + ","
                            + actionTime + "," + searchKeyword + "," +clickCategoryId+","
                            + clickProductId + "," + orderCategoryIds + "," + orderProductIds
                            + "," + payCategoryIds + "," + payProductIds+","+city_id;

                    CreateData.upData(ACTION_PATH, wordLine);
                }
            }
        }


        /**
         * ========================用户==========================================
         */

        CreateData.createFile(USER_PATH);
        String[] sexes = new String[]{"male", "female"};
        for (int i = 0; i < 100; i++) {
            String userId = String.valueOf(i);
            String username = "user" + i;
            String name = "name" + i;
            int age = random.nextInt(60);
            String professional = "professional" + random.nextInt(100);
            String city = "city" + random.nextInt(100);
            String sex = sexes[random.nextInt(2)];
            String wordLine = userId + "," + username + "," + name + "," + age + "," + professional + "," + city + "," + sex;
            CreateData.upData(USER_PATH, wordLine);

        }


        /**
         * ===================产品===============================================
         */

        CreateData.createFile(PRODUCT_PATH);
        int[] productStatus = new int[]{0, 1};

        for (int i = 0; i < 100; i++) {
            String productId = String.valueOf(i);
            String productName = "product" + i;
            String extendInfo = "" + productStatus[random.nextInt(2)] ;
            String worldLine = productId+","+productName+","+extendInfo;
            CreateData.upData(PRODUCT_PATH,worldLine);
        }
    }


    private static void createFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean delete = file.delete();
            if(delete){
                System.out.println("delete success");
            }
        } else {
            try {
                boolean newFile = file.createNewFile();
                if(newFile){
                    System.out.println("create success");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void upData(String path, String data) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(data + "\r\n");
            bufferWriter.flush();
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            mock();
    }
}
