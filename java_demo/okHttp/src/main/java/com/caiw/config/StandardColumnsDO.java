package com.caiw.config;

import java.io.Serializable;


public class StandardColumnsDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private String standardColumnsName;
    //
    private Integer standardTableId;
    //
    private String columnen;
    //
    private String namecn;
    //
    private String description;
    //
    private String datatype;
    //
    private Integer ispk;
    //
    private Integer nullable;
    //
    private String defaultvalue;
    //
    private String constraints;
    //
    private String columnvauletable;
    //
    private String comment;
    //
    private Integer interfacesourceid;
    //
    private String interfacesourcename;
    //
    private String interfacesourceurl;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setStandardColumnsName(String standardColumnsName) {
        this.standardColumnsName = standardColumnsName;
    }
    /**
     * 获取：
     */
    public String getStandardColumnsName() {
        return standardColumnsName;
    }
    /**
     * 设置：
     */
    public void setStandardTableId(Integer standardTableId) {
        this.standardTableId = standardTableId;
    }
    /**
     * 获取：
     */
    public Integer getStandardTableId() {
        return standardTableId;
    }
    /**
     * 设置：
     */
    public void setColumnen(String columnen) {
        this.columnen = columnen;
    }
    /**
     * 获取：
     */
    public String getColumnen() {
        return columnen;
    }
    /**
     * 设置：
     */
    public void setNamecn(String namecn) {
        this.namecn = namecn;
    }
    /**
     * 获取：
     */
    public String getNamecn() {
        return namecn;
    }
    /**
     * 设置：
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 获取：
     */
    public String getDescription() {
        return description;
    }
    /**
     * 设置：
     */
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
    /**
     * 获取：
     */
    public String getDatatype() {
        return datatype;
    }
    /**
     * 设置：
     */
    public void setIspk(Integer ispk) {
        this.ispk = ispk;
    }
    /**
     * 获取：
     */
    public Integer getIspk() {
        return ispk;
    }
    /**
     * 设置：
     */
    public void setNullable(Integer nullable) {
        this.nullable = nullable;
    }
    /**
     * 获取：
     */
    public Integer getNullable() {
        return nullable;
    }
    /**
     * 设置：
     */
    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }
    /**
     * 获取：
     */
    public String getDefaultvalue() {
        return defaultvalue;
    }
    /**
     * 设置：
     */
    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }
    /**
     * 获取：
     */
    public String getConstraints() {
        return constraints;
    }
    /**
     * 设置：
     */
    public void setColumnvauletable(String columnvauletable) {
        this.columnvauletable = columnvauletable;
    }
    /**
     * 获取：
     */
    public String getColumnvauletable() {
        return columnvauletable;
    }
    /**
     * 设置：
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * 获取：
     */
    public String getComment() {
        return comment;
    }
    /**
     * 设置：
     */
    public void setInterfacesourceid(Integer interfacesourceid) {
        this.interfacesourceid = interfacesourceid;
    }
    /**
     * 获取：
     */
    public Integer getInterfacesourceid() {
        return interfacesourceid;
    }
    /**
     * 设置：
     */
    public void setInterfacesourcename(String interfacesourcename) {
        this.interfacesourcename = interfacesourcename;
    }
    /**
     * 获取：
     */
    public String getInterfacesourcename() {
        return interfacesourcename;
    }
    /**
     * 设置：
     */
    public void setInterfacesourceurl(String interfacesourceurl) {
        this.interfacesourceurl = interfacesourceurl;
    }
    /**
     * 获取：
     */
    public String getInterfacesourceurl() {
        return interfacesourceurl;
    }

    public void setUpdatadate(String format) {
    }

    public void setCreatedate(String format) {
    }

    @Override
    public String toString() {
        return "StandardColumnsDO{" +
                "id=" + id +
                ", standardColumnsName='" + standardColumnsName + '\'' +
                ", standardTableId=" + standardTableId +
                ", columnen='" + columnen + '\'' +
                ", namecn='" + namecn + '\'' +
                ", description='" + description + '\'' +
                ", datatype='" + datatype + '\'' +
                ", ispk=" + ispk +
                ", nullable=" + nullable +
                ", defaultvalue='" + defaultvalue + '\'' +
                ", constraints='" + constraints + '\'' +
                ", columnvauletable='" + columnvauletable + '\'' +
                ", comment='" + comment + '\'' +
                ", interfacesourceid=" + interfacesourceid +
                ", interfacesourcename='" + interfacesourcename + '\'' +
                ", interfacesourceurl='" + interfacesourceurl + '\'' +
                '}';
    }
}