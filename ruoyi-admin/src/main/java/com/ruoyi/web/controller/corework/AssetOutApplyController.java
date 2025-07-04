package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.corework.constant.AssetApplyStatus;
import com.ruoyi.corework.domain.AssetOutApply;
import com.ruoyi.corework.domain.dto.AssetOutApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetOutApplyQueryDto;
import com.ruoyi.corework.service.IAssetOutApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ClassName:AssetOutApplyController
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:42
 * @Version 1.0
 */

@RestController
@RequestMapping("/assetOutApply")
@Api(tags = "物资出库申请管理")
public class AssetOutApplyController extends BaseController {

    @Autowired
    private IAssetOutApplyService iAssetOutApplyService;

    @PostMapping("/applySave")
    @ApiOperation("物资申请单新增/修改")
    public AjaxResult apply(@RequestBody AssetOutApplySaveDto assetOutApplySaveDto) {
        String operation = assetOutApplySaveDto.getId() == null ? "新增" : "修改";
        int result = assetOutApplySaveDto.getId() == null
                ? iAssetOutApplyService.InsertAssetOutApply(assetOutApplySaveDto)
                : iAssetOutApplyService.updateAssetOutApply(assetOutApplySaveDto);
        return result > 0 ? AjaxResult.success(operation + "成功") : AjaxResult.error(operation + "失败");
    }

    @GetMapping("/list")
    @ApiOperation("物资申请列表")
    public TableDataInfo<AssetOutApply> list(AssetOutApplyQueryDto assetOutApplyQueryDto) {
        startPage();
        List<AssetOutApply> list = iAssetOutApplyService.selectAssetList(assetOutApplyQueryDto);
        return getDataTable(list);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("申请单详情")
    public AjaxResultVo<AssetOutApply> detail(@PathVariable Long id) {
        return AjaxResultVo.success(iAssetOutApplyService.selectAssetOutApplyById(id));
    }

    @PostMapping("/delete")
    @ApiOperation("删除申请单")
    public AjaxResult delete(@RequestBody Long[] ids) {
        return iAssetOutApplyService.deleteAssetOutApplyByIds(ids) > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }

    @PostMapping("/submit/{id}")
    @ApiOperation("提交申请单")
    public AjaxResult submit(@PathVariable Long id) {
        if (id == null) {
            return AjaxResult.error("申请单id不能为空");
        }
        return iAssetOutApplyService.updateStatus(id, AssetApplyStatus.PENDING) > 0 ? AjaxResult.success("提交成功") : AjaxResult.error("提交失败");
    }

    @PostMapping("/back/{id}")
    @ApiOperation("归还")
    public AjaxResult back(@PathVariable Long id) {
        if (id == null) {
            return AjaxResult.error("申请单id不能为空");
        }
        return iAssetOutApplyService.updateStatus(id, AssetApplyStatus.BACK) > 0 ? AjaxResult.success("归还成功") : AjaxResult.error("归还失败");
    }
}
