package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:AssetOperStatDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/27-10:22
 * @Version 1.0
 */

@Data
public class AssetOperStatDto {

    @ApiModelProperty("开始日期")
    private Date startDate;

    @ApiModelProperty("结束日期")
    private Date endDate;

}
