package com.ruoyi.corework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * ClassName:Book
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/16-16:09
 * @Version 1.0
 */

@Data
@ApiModel(description = "图书实体类")
public class Book extends BaseEntity implements Serializable  {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图书ID", example = "1001")
    private Long id;

    @ApiModelProperty(value = "书名", example = "三国演义")
    private String name;

    @ApiModelProperty(value = "作者", example = "罗贯中")
    private String author;

    @ApiModelProperty(value = "ISBN编号", example = "978-7-5399-1234-5")
    private String isbn;

    @ApiModelProperty(value = "出版日期", example = "2020-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishDate;

    @ApiModelProperty(value = "借阅状态，0=可借，1=已借出", example = "0")
    private String status;

}
