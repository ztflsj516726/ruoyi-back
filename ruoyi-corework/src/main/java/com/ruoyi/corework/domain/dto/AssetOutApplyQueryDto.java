package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:AssetOutApplyQueryDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-16:47
 * @Version 1.0
 */
@Data
public class AssetOutApplyQueryDto {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty("申请所属部门")
    private Long deptId;

    @ApiModelProperty("审批人")
    private Long checkUserId;

    @ApiModelProperty("创建人")
    private Long applyUserId;

    @ApiModelProperty("申请单状态：draft待提交, pending审批中, approved已通过, rejected驳回")
    private String status;
}
