package com.ruoyi.corework.domain.dto;

import com.ruoyi.corework.domain.AssetOutApplyDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName:AssetOutApplySaveDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-16:32
 * @Version 1.0
 */
@ApiModel("申请单新增")
@Data
public class AssetOutApplySaveDto {

    @ApiModelProperty("申请单id")
    private Long id;

    @ApiModelProperty("申领原因")
    private String reason;

    @ApiModelProperty("申领物资列表")
    private List<AssetOutApplyDetail> detailList; // 子表数据
}
