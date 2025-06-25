package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:AssetOperQueryDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:30
 * @Version 1.0
 */
@Data
@ApiModel(description = "入库报废查询参数")
public class AssetOperQueryDto {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty("操作类型：入库in 报废out")
    private String operType;

    @ApiModelProperty("物资id")
    private String assetId;
}
