package com.caiw.config;
import java.io.Serializable;
import java.util.List;


public class TableColumns implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    //
    private String standardTableName;
    //外键
    private Integer standardNameId;
    //
    private String description;
    //
    private String updatadate;
    //
    private String createdate;
    //
    private String comment;
    //
    private Integer status;
    //
    private String maintainertype;

    private List<StandardColumnsDO> columns;

    public List<StandardColumnsDO> getColumns() {
        return columns;
    }

    public void setColumns(List<StandardColumnsDO> columns) {
        this.columns = columns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStandardTableName() {
        return standardTableName;
    }

    public void setStandardTableName(String standardTableName) {
        this.standardTableName = standardTableName;
    }

    public Integer getStandardNameId() {
        return standardNameId;
    }

    public void setStandardNameId(Integer standardNameId) {
        this.standardNameId = standardNameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatadate() {
        return updatadate;
    }

    public void setUpdatadate(String updatadate) {
        this.updatadate = updatadate;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMaintainertype() {
        return maintainertype;
    }

    public void setMaintainertype(String maintainertype) {
        this.maintainertype = maintainertype;
    }
}