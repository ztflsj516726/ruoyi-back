package com.ruoyi.corework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:AssetOper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:26
 * @Version 1.0
 */

@ApiModel(description = "入库报废")
@Data
@Builder
public class AssetOper {

    @ApiModelProperty("入库报废id")
    private Long id;

    @ApiModelProperty("物资id")
    private Long assetId;

    @ApiModelProperty("操作类型：入库in 报废out 借用borrow")
    private String operType;

    @ApiModelProperty("操作数量")
    private Long operNum;

    @ApiModelProperty("当前可用库存")
    private Long afterUseableStock;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("创建人")
    private String createBy;



}
