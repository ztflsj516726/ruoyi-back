package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:AssetOperSaveDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/26-10:20
 * @Version 1.0
 */
@Data
@ApiModel("")
public class AssetOperSaveDto {

    @ApiModelProperty("物资id")
    private Long assetId;

    @ApiModelProperty("操作类型：入库in 报废out 借用borrow")
    private String operType;

    @ApiModelProperty("操作数量")
    private Long operNum;
}
