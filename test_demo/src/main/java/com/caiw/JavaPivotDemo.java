package com.caiw;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaPivotDemo {
    public static void main(String[] args) {
//        String json = "[{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533105253471\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"975\",\"processing_dttm\":\"1533105253471\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"25\",\"processing_dttm\":\"1533105253471\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533613395301\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"975\",\"processing_dttm\":\"1533613395301\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"25\",\"processing_dttm\":\"1533613395301\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533633547968\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"975\",\"processing_dttm\":\"1533633547968\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"25\",\"processing_dttm\":\"1533633547968\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533633977337\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"975\",\"processing_dttm\":\"1533633977337\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"25\",\"processing_dttm\":\"1533633977337\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533634397451\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"970\",\"processing_dttm\":\"1533634397451\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"30\",\"processing_dttm\":\"1533634397451\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533714299509\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"971\",\"processing_dttm\":\"1533714299509\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"29\",\"processing_dttm\":\"1533714299509\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533715790036\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"970\",\"processing_dttm\":\"1533715790036\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"30\",\"processing_dttm\":\"1533715790036\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533716456380\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"970\",\"processing_dttm\":\"1533716456380\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"30\",\"processing_dttm\":\"1533716456380\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533720380894\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"982\",\"processing_dttm\":\"1533720380894\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"18\",\"processing_dttm\":\"1533720380894\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533873246770\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"975\",\"processing_dttm\":\"1533873246770\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"25\",\"processing_dttm\":\"1533873246770\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533961732064\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"974\",\"processing_dttm\":\"1533961732064\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"26\",\"processing_dttm\":\"1533961732064\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533967392704\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"982\",\"processing_dttm\":\"1533967392704\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"18\",\"processing_dttm\":\"1533967392704\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"1000\",\"processing_dttm\":\"1533968362910\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"971\",\"processing_dttm\":\"1533968362910\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"29\",\"processing_dttm\":\"1533968362910\"}]";
        String json = "[{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"5\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"5\",\"processing_dttm\":\"1546418653902\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546418653902\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546418653902\"}]";
//        String json = "[{\"columnname\":\"name\",\"metrictype\":\"COLUMN_DATATYPE\",\"metricvalue\":\"StringType\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"COLUMN_NULLABLE\",\"metricvalue\":\"true\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"COLUMN_METADATA\",\"metricvalue\":\"{}\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"NULL_COUNT\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"UNIQUE_COUNT\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"PERC_NULL_VALUES\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"PERC_UNIQUE_VALUES\",\"metricvalue\":\"100\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"PERC_DUPLICATE_VALUES\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"TOP_N_VALUES\",\"metricvalue\":\"1^A1^A1^B2^A^A1^B3^A3^A1^B4^A2^A1^B\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"MAX_LENGTH\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"MIN_LENGTH\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"LONGEST_STRING\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"SHORTEST_STRING\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"EMPTY_COUNT\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"PERC_EMPTY_VALUES\",\"metricvalue\":\"25\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"MIN_STRING_CASE\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"MAX_STRING_CASE\",\"metricvalue\":\"3\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"5\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"VALID_COUNT\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"(ALL)\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"MIN_STRING_ICASE\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"MAX_STRING_ICASE\",\"metricvalue\":\"3\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"COLUMN_DATATYPE\",\"metricvalue\":\"IntegerType\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"COLUMN_NULLABLE\",\"metricvalue\":\"true\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"COLUMN_METADATA\",\"metricvalue\":\"{}\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"NULL_COUNT\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"TOTAL_COUNT\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"UNIQUE_COUNT\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"PERC_NULL_VALUES\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"PERC_UNIQUE_VALUES\",\"metricvalue\":\"100\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"PERC_DUPLICATE_VALUES\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"TOP_N_VALUES\",\"metricvalue\":\"1^A2^A1^B2^A4^A1^B3^A1^A1^B4^A3^A1^B\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"MAX\",\"metricvalue\":\"4\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"MIN\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"SUM\",\"metricvalue\":\"10\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"MEAN\",\"metricvalue\":\"2.5\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"STDDEV\",\"metricvalue\":\"1.118\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"VARIANCE\",\"metricvalue\":\"1.25\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"id\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"1\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"name\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"},{\"columnname\":\"dlp_reject_reason\",\"metrictype\":\"INVALID_COUNT\",\"metricvalue\":\"0\",\"processing_dttm\":\"1546416571395\"}]";
        List<PivotEntity> pivotEntities = JSON.parseArray(json, PivotEntity.class);
        Map<String, ResultData> stringResultDataMap = doPivot(pivotEntities);
//        pivotEntities.forEach(pivotEntity -> {
//            if(pivotEntity.getColumnname().equals("id")){
//                System.out.println(pivotEntity.getMetrictype());
//            }
//        });
        System.out.println(stringResultDataMap.size());
    }

     public static Map<String,ResultData> doPivot(List<PivotEntity> pivotEntities){
         Map<String,ResultData> map = new HashMap<>();

        pivotEntities.forEach(pivotEntity -> {
            String date = pivotEntity.getProcessing_dttm();
            switch (pivotEntity.getMetrictype()) {
                case "TOTAL_COUNT": {
                    String metricValue = pivotEntity.getMetricvalue();
                    ResultData resultData = new ResultData();
                    resultData.setTotal(metricValue);
                    resultData.setTime(date);
                    ResultData orDefault = map.getOrDefault(date, resultData);
                    orDefault.setTotal(metricValue);
                    map.put(date, orDefault);
                    break;
                }
                case "VALID_COUNT": {
                    String metricValue = pivotEntity.getMetricvalue();
                    ResultData resultData = new ResultData();
                    resultData.setValid(metricValue);
                    resultData.setTime(date);
                    ResultData orDefault = map.getOrDefault(date, resultData);
                    orDefault.setValid(metricValue);
                    map.put(date, orDefault);
                    break;
                }
                case "INVALID_COUNT": {
                    String metricValue = pivotEntity.getMetricvalue();
                    ResultData resultData = new ResultData();
                    resultData.setInValid(metricValue);
                    resultData.setTime(date);
                    ResultData orDefault = map.getOrDefault(date, resultData);
                    orDefault.setInValid(metricValue);
                    map.put(date, orDefault);
                    break;
                }
            }
        });
         return map;
     }
}

class ResultData{
    private String valid;
    private String inValid;
    private String total;
    private String time;

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getInValid() {
        return inValid;
    }

    public void setInValid(String inValid) {
        this.inValid = inValid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
