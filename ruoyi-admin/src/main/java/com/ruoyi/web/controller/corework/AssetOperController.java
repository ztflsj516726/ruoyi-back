package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.corework.domain.AssetOper;
import com.ruoyi.corework.domain.dto.AssetOperQueryDto;
import com.ruoyi.corework.service.IAssetOperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:AssetOperController
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:40
 * @Version 1.0
 */

@RestController
@RequestMapping("/assetOper")
@Api(tags = "入库报废信息管理")
public class AssetOperController extends BaseController {


    @Autowired
    private IAssetOperService assetOperService;

    @ApiOperation("入库报废记录")
    @GetMapping("/list")
    public TableDataInfo<AssetOper> list(AssetOperQueryDto assetOperQueryDto) {
        return getDataTable(assetOperService.selectAssetOperList(assetOperQueryDto));
    }

    @ApiOperation("入库/报废")
    @PostMapping("/save")
    public AjaxResult save(@RequestBody AssetOper assetOper) {
        return assetOperService.InsertAssetOper(assetOper) > 0 ? success() : error();
    }
}
