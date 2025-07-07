package com.ruoyi.corework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ClassName:IAssetOutApplyService
 * Package:IntelliJ IDEA
 * Description:
 * 申请单
 *
 * @Author ztf
 * @Create 2025/6/20-14:45
 * @Version 1.0
 */
@Data
@ApiModel("出库申请单")
public class AssetOutApply extends BaseEntity {

    @ApiModelProperty("出库申请单id")
    private Long id;

    @ApiModelProperty("出库申请单号")
    private String applyCode;

    @ApiModelProperty("申请人id")
    private Long applyUserId;

    @ApiModelProperty("申请所属部门")
    private Long deptId;

    @ApiModelProperty("申请所属部门翻译")
    private String deptName;

    @ApiModelProperty("申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyTime;

    @ApiModelProperty("申请单状态：draft待提交, pending审批中, approved已通过, rejected已驳回,back已归还")
    private String status;

    @ApiModelProperty("申领原因")
    private String reason;

    @ApiModelProperty("流程实例ID（备用)")
    private String flowId;

    @ApiModelProperty("申请物资列表")
    private List<AssetOutApplyDetail> detailList; // 子表数据
}
