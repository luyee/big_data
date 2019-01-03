package com.caiw;

public class PivotEntity {
    //{"columnname":"(ALL)","metrictype":"TOTAL_COUNT","metricvalue":"1000","processing_dttm":"1533105253471"}
    private String columnname;
    private String metrictype;
    private String metricvalue;
    private String processing_dttm;


    public String getColumnname() {
        return columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public String getMetrictype() {
        return metrictype;
    }

    public void setMetrictype(String metrictype) {
        this.metrictype = metrictype;
    }

    public String getMetricvalue() {
        return metricvalue;
    }

    public void setMetricvalue(String metricvalue) {
        this.metricvalue = metricvalue;
    }

    public String getProcessing_dttm() {
        return processing_dttm;
    }

    public void setProcessing_dttm(String processing_dttm) {
        this.processing_dttm = processing_dttm;
    }
}
