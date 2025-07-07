package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.corework.constant.AssetApplyStatus;
import com.ruoyi.corework.domain.AssetInApply;
import com.ruoyi.corework.domain.dto.AssetInApplyQueryDto;
import com.ruoyi.corework.domain.dto.AssetInApplySaveDto;
import com.ruoyi.corework.service.impl.AssetInApplyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:AssetInApplyController
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-15:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/assetInApply")
@Api(tags = "物资入库申请管理")
public class AssetInApplyController extends BaseController {

    @Autowired
    private AssetInApplyServiceImpl assetInApplyService;

    @ApiOperation("入库申请新增/修改")
    @PostMapping("/applySave")
    public AjaxResult apply(@RequestBody AssetInApplySaveDto assetInApplySaveDto) {
        boolean success;
        if (assetInApplySaveDto.getId() == null) {
            success = assetInApplyService.InsertAssetInApply(assetInApplySaveDto) > 0;
        } else {
            success = assetInApplyService.updateAssetInApply(assetInApplySaveDto) > 0;
        }
        return success ? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("入库申请单列表")
    @GetMapping("/list")
    public TableDataInfo<AssetInApply> list(AssetInApplyQueryDto assetInApplyQueryDto) {
        startPage();
        List<AssetInApply> assetInApplyList = assetInApplyService.selectAssetList(assetInApplyQueryDto);
        return getDataTable(assetInApplyList);
    }

    @ApiOperation("入库申请单详情")
    @GetMapping("/detail/{id}")
    public AjaxResultVo<AssetInApply> detail(@PathVariable Long id) {
        AssetInApply assetInApply = assetInApplyService.selectAssetInApplyById(id);
        return AjaxResultVo.success(assetInApply);
    }

    @ApiOperation("入库申请单删除")
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody Long[] ids) {
        System.out.println("ids" + ids);
        int rows = assetInApplyService.deleteAssetInApplyByIds(ids);
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("入库申请单提交")
    @PostMapping("/submit/{id}")
    public AjaxResult submit(@PathVariable Long id) {
        int rows = assetInApplyService.updateStatus(id, AssetApplyStatus.PENDING);
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("入库申请单通过")
    @PostMapping("/approve/{id}")
    public AjaxResult approve(@PathVariable Long id) {
        int rows = assetInApplyService.updateStatus(id, AssetApplyStatus.APPROVED);
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("入库申请单拒绝")
    @PostMapping("/reject/{id}")
    public AjaxResult reject(@PathVariable Long id) {
        int rows = assetInApplyService.updateStatus(id, AssetApplyStatus.REJECTED);
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
