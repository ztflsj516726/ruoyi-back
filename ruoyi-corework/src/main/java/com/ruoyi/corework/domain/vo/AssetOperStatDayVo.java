package com.ruoyi.corework.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ClassName:AssetOperStatDayVo
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/27-9:59
 * @Version 1.0
 */
@Data
public class AssetOperStatDayVo {

    @ApiModelProperty("日期列表")
    private List<String> dateList;

    @ApiModelProperty("入库数量")
    private List<Long> inList;

    @ApiModelProperty("出库数量")
    private List<Long> outList;
}
