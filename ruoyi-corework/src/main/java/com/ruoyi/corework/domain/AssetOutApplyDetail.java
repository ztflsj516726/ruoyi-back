package com.ruoyi.corework.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:AssetOutApplyDetail
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-14:46
 * @Version 1.0
 */
@Data
@ApiModel("出库申请单明细")
public class AssetOutApplyDetail {
    @ApiModelProperty("申请物资信息主键id")
    private Long id;

    @ApiModelProperty("申请单id")
    private Long applyId;

    @ApiModelProperty("物资id")
    private Long assetId;

    @ApiModelProperty("物资数量")
    private Long count;
}
