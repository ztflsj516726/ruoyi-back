package com.ruoyi.corework.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:BookSaveDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/17-11:35
 * @Version 1.0
 */
@Data
@ApiModel("图书添加对象")
public class BookSaveDto {

    @ApiModelProperty(value = "图书ID", example = "1001")
    private Long id;

    @ApiModelProperty(value = "书名")
    private String name;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "ISBN编号")
    private String isbn;

    @ApiModelProperty(value = "出版日期", example = "2020-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishDate;

    @ApiModelProperty(value = "状态，0=可借，1=已借出", example = "0")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
