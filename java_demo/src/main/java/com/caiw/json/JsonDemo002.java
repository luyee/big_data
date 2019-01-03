package com.caiw.json;

import com.alibaba.fastjson.JSON;

public class JsonDemo002 {
    public static void main(String[] args) {
        String json = "{\"id\":\"1\",\"name\":\"test\"}";

        JsonEntity jsonEntity = JSON.parseObject(json, JsonEntityImpl.class);


        System.out.println(jsonEntity);
    }
}
