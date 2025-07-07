package com.ruoyi.corework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName:AssetInApplyDetail
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-16:14
 * @Version 1.0
 */
@Data
@ApiModel("入库申请单明细")
public class AssetInApplyDetail {
    @ApiModelProperty(value = "明细ID")
    private Long id;

    @ApiModelProperty(value = "入库申请单ID", required = true)
    private Long applyId;

    @ApiModelProperty(value = "物资ID", required = true)
    private Long assetId;

    @ApiModelProperty(value = "物资名称（冗余）")
    private String assetName;

    @ApiModelProperty(value = "物资单位（冗余）")
    private String assetUnit;

    @ApiModelProperty(value = "入库数量", required = true)
    private Integer count;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "批次号（可选）")
    private String batchCode;

    @ApiModelProperty(value = "生产日期（可选）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productionDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
