package com.ruoyi.corework.domain.dto;

import com.ruoyi.corework.domain.AssetInApplyDetail;
import com.ruoyi.corework.domain.AssetOutApplyDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName:AssetInApplySaveDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-16:25
 * @Version 1.0
 */
@Data
public class AssetInApplySaveDto {
    @ApiModelProperty(value = "入库申请单ID")
    private Long id;

    @ApiModelProperty(value = "入库单标题")
    private String applyTitle;

    @ApiModelProperty(value = "仓库ID", required = true)
    private Long warehouseId;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("入库物资列表")
    private List<AssetInApplyDetail> detailList; // 子表数据

    @ApiModelProperty(value = "备注")
    private String remark;

}
