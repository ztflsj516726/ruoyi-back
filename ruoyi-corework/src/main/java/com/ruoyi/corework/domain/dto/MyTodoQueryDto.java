package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:MyTodoQueryDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/24-16:56
 * @Version 1.0
 */
@Data
public class MyTodoQueryDto {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty("申请所属部门")
    private Long deptId;

    @ApiModelProperty("状态")
    private String status;
}
