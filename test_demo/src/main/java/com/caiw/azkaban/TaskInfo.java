package com.caiw.azkaban;

public class TaskInfo {
    /**
     * azkaban项目名
     */
    private String project;
    /**
     * azkaban flow名字
     */
    private String flowName;
    /**
     * 数据源表
     */
    private String sourceTable;
    /**
     * 源表时间字段 （日表：part_daily，月表：part_monthly）
     */
    private String sourceDateField;
    /**
     * 是否全表同步
     */
    private boolean isFullSync = false;
    /**
     * 查询语句
     */
    private String selectClause;
    /**
     * 条件语句
     */
    private String whereClause;
    /**
     * 是否为日表（true为日表，false为月表）
     */
    private boolean isDayTable = true;
    /**
     * 账期
     */
    private String executeDate;
    /**
     * 目标表
     */
    private String targetTable;
    /**
     * ftp目标目录
     */
    private String ftpFilePath;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getSourceDateField() {
        return sourceDateField;
    }

    public void setSourceDateField(String sourceDateField) {
        this.sourceDateField = sourceDateField;
    }

    public boolean isFullSync() {
        return isFullSync;
    }

    public void setFullSync(boolean fullSync) {
        isFullSync = fullSync;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public boolean isDayTable() {
        return isDayTable;
    }

    public void setDayTable(boolean dayTable) {
        isDayTable = dayTable;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public String getFtpFilePath() {
        return ftpFilePath;
    }

    public void setFtpFilePath(String ftpFilePath) {
        this.ftpFilePath = ftpFilePath;
    }
}
