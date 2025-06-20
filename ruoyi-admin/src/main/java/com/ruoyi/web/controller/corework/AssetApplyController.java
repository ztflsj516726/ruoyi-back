package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.corework.domain.AssetApply;
import com.ruoyi.corework.domain.dto.AssetApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetApplyQueryDto;
import com.ruoyi.corework.service.IAssetApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ClassName:AssetApplyController
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:42
 * @Version 1.0
 */

@RestController
@RequestMapping("/assetApply")
@Api(tags = "物资申请管理")
public class AssetApplyController extends BaseController {

    @Autowired
    private IAssetApplyService iAssetApplyService;

    @PostMapping("/applySave")
    @ApiOperation("物资新增/修改")
    public AjaxResult apply(@RequestBody AssetApplySaveDto assetApplyDto) {
        String operation = assetApplyDto.getId() == null ? "新增" : "修改";
        int result = assetApplyDto.getId() == null
                ? iAssetApplyService.InsertAssetApply(assetApplyDto)
                : iAssetApplyService.updateAssetApply(assetApplyDto);

        return result > 0 ? AjaxResult.success(operation + "成功") : AjaxResult.error(operation + "失败");
    }

    @GetMapping("/list")
    @ApiOperation("物资申请列表")
    public TableDataInfo<AssetApply> apply(AssetApplyQueryDto assetApplyQueryDto) {
        startPage();
        List<AssetApply> list = iAssetApplyService.selectAssetList(assetApplyQueryDto);
        return getDataTable(list);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("申请单详情")
    public AjaxResultVo<AssetApply> detail(@PathVariable Long id) {
        return AjaxResultVo.success(iAssetApplyService.selectAssetApplyById(id));
    }

    @PostMapping("/delete")
    @ApiOperation("删除申请单")
    public AjaxResult delete(@RequestBody Long[] ids) {
        return iAssetApplyService.deleteAssetApplyByIds(ids) > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }
}
