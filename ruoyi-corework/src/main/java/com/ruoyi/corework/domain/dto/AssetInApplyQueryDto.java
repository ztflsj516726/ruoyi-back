package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:AssetInApplyQueryDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-16:26
 * @Version 1.0
 */
@Data
@ApiModel("入库列表查询对象")
public class AssetInApplyQueryDto {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;


    @ApiModelProperty("申请单状态：draft待提交, pending审批中, approved已通过, rejected驳回")
    private String status;

    @ApiModelProperty("申请单号")
    private String applyCode;
}
