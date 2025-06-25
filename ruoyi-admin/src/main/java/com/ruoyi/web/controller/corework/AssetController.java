package com.ruoyi.web.controller.corework;


import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.AjaxResultVo;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.domain.dto.AssetApplySaveDto;
import com.ruoyi.corework.service.IAssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物资信息Controller
 *
 * @author ruoyi
 * @date 2025-06-20
 */
@RestController
@RequestMapping("/asset")
@Api(tags = "物资信息管理")
public class AssetController extends BaseController {
    @Autowired
    private IAssetService assetService;

    /**
     * 查询物资信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询物资信息列表")
    public TableDataInfo<Asset> list(Asset asset) {
        startPage();
        List<Asset> list = assetService.selectAssetList(asset);
        return getDataTable(list);
    }

    /**
     * 导出物资信息列表
     */
    @Log(title = "物资信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出物资信息列表")
    public void export(HttpServletResponse response, Asset asset) {
        List<Asset> list = assetService.selectAssetList(asset);
        ExcelUtil<Asset> util = new ExcelUtil<Asset>(Asset.class);
        util.exportExcel(response, list, "物资信息数据");
    }

    /**
     * 获取物资信息详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取物资信息详细信息")
    public AjaxResultVo<Asset> getInfo(@PathVariable("id") Long id) {
        return AjaxResultVo.success(assetService.selectAssetById(id));
    }

    /**
     * 新增物资信息
     */
    @Log(title = "物资信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增物资信息")
    public AjaxResult add(@RequestBody AssetApplySaveDto assetApplySaveDto) {
        Asset asset = new Asset();
        BeanUtils.copyProperties(assetApplySaveDto, asset);
        return toAjax(assetService.insertAsset(asset));
    }

    /**
     * 修改物资信息
     */
    @Log(title = "物资信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改物资信息")
    public AjaxResult edit(@RequestBody AssetApplySaveDto assetApplySaveDto) {
        Asset asset = new Asset();
        BeanUtils.copyProperties(assetApplySaveDto, asset);
        return toAjax(assetService.updateAsset(asset));
    }

    /**
     * 删除物资信息
     */
    @Log(title = "物资信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除物资信息")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(assetService.deleteAssetByIds(ids));
    }
}
