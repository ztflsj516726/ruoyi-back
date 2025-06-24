package com.ruoyi.corework.domain.dto;

import com.ruoyi.corework.domain.AssetApplyDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName:AssetApplySaveDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-16:32
 * @Version 1.0
 */
@ApiModel("申请单新增")
@Data
public class AssetApplySaveDto {

    @ApiModelProperty("申请单id")
    private Long id;

    @ApiModelProperty("审批人")
    private Long checkUserId;

    @ApiModelProperty("申领原因")
    private String reason;

    @ApiModelProperty("申领物资列表")
    private List<AssetApplyDetail> detailList; // 子表数据
}
