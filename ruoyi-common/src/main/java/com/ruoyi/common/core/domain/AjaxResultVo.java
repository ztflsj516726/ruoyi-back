package com.ruoyi.common.core.domain;

import com.ruoyi.common.constant.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应对象（适配 Swagger 和 Lombok）
 */
@Data
@ApiModel("统一响应对象")
public class AjaxResultVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态码（200 成功，500 失败，501 警告）")
    private int code;

    @ApiModelProperty("提示信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;

    public AjaxResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResultVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /** 成功 */
    public static <T> AjaxResultVo<T> success() {
        return new AjaxResultVo<>(HttpStatus.SUCCESS, "操作成功");
    }

    public static <T> AjaxResultVo<T> success(T data) {
        return new AjaxResultVo<>(HttpStatus.SUCCESS, "操作成功", data);
    }

    public static <T> AjaxResultVo<T> success(String msg, T data) {
        return new AjaxResultVo<>(HttpStatus.SUCCESS, msg, data);
    }

    /** 警告 */
    public static <T> AjaxResultVo<T> warn(String msg) {
        return new AjaxResultVo<>(HttpStatus.WARN, msg);
    }

    public static <T> AjaxResultVo<T> warn(String msg, T data) {
        return new AjaxResultVo<>(HttpStatus.WARN, msg, data);
    }

    /** 错误 */
    public static <T> AjaxResultVo<T> error() {
        return new AjaxResultVo<>(HttpStatus.ERROR, "操作失败");
    }

    public static <T> AjaxResultVo<T> error(String msg) {
        return new AjaxResultVo<>(HttpStatus.ERROR, msg);
    }

    public static <T> AjaxResultVo<T> error(String msg, T data) {
        return new AjaxResultVo<>(HttpStatus.ERROR, msg, data);
    }

    public static <T> AjaxResultVo<T> error(int code, String msg) {
        return new AjaxResultVo<>(code, msg);
    }
}
