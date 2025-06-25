package com.ruoyi.corework.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:AssetSaveDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:09
 * @Version 1.0
 */
@Data
@ApiModel("物资dto")
public class AssetSaveDto {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "物资主键")
    private Long id;

    /**
     * 物资名称
     */
    @Excel(name = "物资名称")
    @ApiModelProperty(value = "物资名称")
    private String name;

    /**
     * 类别（办公/IT/家具）
     */
    @Excel(name = "类别", readConverterExp = "1=办公,2=IT设备,3=家具")
    @ApiModelProperty(value = "类别")
    private String category;

    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    @ApiModelProperty(value = "规格型号")
    private String model;

    /**
     * 单位（件/台/个）
     */
    @Excel(name = "单位", readConverterExp = "1=件,2=台,3=个")
    @ApiModelProperty(value = "单位")
    private String unit;


    /**
     * 购入日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购入日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "购入日期")
    private Date purchaseDate;
}
