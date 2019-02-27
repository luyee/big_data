package com.caiw.json2avro;

public class JsonToAvro {
    public static void main(String[] args) {
        String schema = "{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"products\",\"fields\":[{\"name\":\"product_seris\",\"type\":\"string\"},{\"name\":\"product_name\",\"type\":\"string\"},{\"name\":\"prices\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"price\",\"fields\":[{\"name\":\"model\",\"type\":\"string\"},{\"name\":\"price\",\"type\":\"float\"}]}}}]}}";
        String data = "[{\"product_seris\":\"S_01\",\"product_name\":\"iphone7\",\"prices\":[{\"model\":\"iphone7\",\"price\":5200},{\"model\":\"iphone7 plus\",\"price\":5800}]},{\"product_seris\":\"S_02\",\"product_name\":\"iphone6\",\"prices\":[{\"model\":\"iphone6\",\"price\":4600},{\"model\":\"iphone6 plus\",\"price\":5200}]}]";

        

    }
}
