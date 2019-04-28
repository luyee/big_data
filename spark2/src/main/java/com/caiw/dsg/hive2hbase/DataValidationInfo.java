package com.caiw.dsg.hive2hbase;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * @Author: caiwei
 * @Description:
 * @Date: create in 2019/4/28 10:08
 */
@Parameters(separators = ":")
public class DataValidationInfo {

    @Parameter(names = "-keyName", description = "Hive table primary key", required = true, order = 0)
    private String keyName;

    @Parameter(names = "-sourceTableName", description = "Source hive table name", required = true, order = 0)
    private String sourceTableName;

    @Parameter(names = "-sourceDbName", description = "Source hive database name", required = true, order = 0)
    private String sourceDbName;

    @Parameter(names = "-targetTableName", description = "Target hive table name", required = true, order = 0)
    private String targetTableName;

    @Parameter(names = "-targetDbName", description = "Target hive database name", required = true, order = 0)
    private String targetDbName;

    @Parameter(names = "-help", help = true, order = 14)
    private boolean help;


    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getSourceTableName() {
        return sourceTableName;
    }

    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    public String getSourceDbName() {
        return sourceDbName;
    }

    public void setSourceDbName(String sourceDbName) {
        this.sourceDbName = sourceDbName;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    public String getTargetDbName() {
        return targetDbName;
    }

    public void setTargetDbName(String targetDbName) {
        this.targetDbName = targetDbName;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    @Override
    public String toString() {
        return "DataValidationInfo{" +
                "keyName='" + keyName + '\'' +
                ", sourceTableName='" + sourceTableName + '\'' +
                ", sourceDbName='" + sourceDbName + '\'' +
                ", targetTableName='" + targetTableName + '\'' +
                ", targetDbName='" + targetDbName + '\'' +
                ", help=" + help +
                '}';
    }
}
