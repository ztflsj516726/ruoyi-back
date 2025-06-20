package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:AssetApplyQueryDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-16:47
 * @Version 1.0
 */
@Data
public class AssetApplyQueryDto {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty("申请所属部门")
    private Long deptId;

    @ApiModelProperty("申请单状态：draft待提交, pending审批中, approved已通过, rejected驳回")
    private String status;
}
