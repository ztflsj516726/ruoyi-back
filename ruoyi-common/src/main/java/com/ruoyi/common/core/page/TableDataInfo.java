package com.ruoyi.common.core.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("分页返回对象")
public class TableDataInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总记录数")
    private long total;

    @ApiModelProperty(value = "数据列表", name = "data")
    @JsonProperty("data")
    private List<T> rows;

    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("提示信息")
    private String msg;

    public TableDataInfo() {}

    public TableDataInfo(List<T> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
