package com.ruoyi.corework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName:AssetInApply
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-16:13
 * @Version 1.0
 */
@Data
@ApiModel("入库申请单")
public class AssetInApply {
    @ApiModelProperty(value = "入库申请单ID")
    private Long id;

    @ApiModelProperty(value = "入库单编号", required = true)
    private String applyCode;

    @ApiModelProperty(value = "入库单标题")
    private String applyTitle;

    @ApiModelProperty(value = "仓库ID", required = true)
    private Long warehouseId;

    @ApiModelProperty(value = "供应商名称")
    private String supplier;

    @ApiModelProperty(value = "单据状态（draft 草稿 pending 审批中 approved 已通过 rejected 已驳回）", required = true)
    private String status;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("入库物资列表")
    private List<AssetInApplyDetail> detailList; // 子表数据
}
