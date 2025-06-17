package com.ruoyi.corework.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName:BookDeleteDto
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/17-14:44
 * @Version 1.0
 */
@Data
@ApiModel("图书删除对象")
public class BookDeleteDto {

    @ApiModelProperty(value = "id集合", required = true)
    private List<Integer> ids;

}
