package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.corework.constant.AssetStatus;
import com.ruoyi.corework.domain.AssetApply;
import com.ruoyi.corework.domain.dto.MyTodoQueryDto;
import com.ruoyi.corework.service.IAssetApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:MyTodoController
 * Package:IntelliJ IDEA
 * Description:
 * 我的审批代办
 *
 * @Author ztf
 * @Create 2025/6/24-16:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/applyTodo")
@Api(tags = "我的审批代办")
public class MyTodoController extends BaseController {

    @Autowired
    private IAssetApplyService assetApplyService;

    @GetMapping("/list")
    @ApiOperation("代办列表")
    public TableDataInfo<AssetApply> list(MyTodoQueryDto myTodoQueryDto) {
        return getDataTable(assetApplyService.selectMyAssetApplyList(myTodoQueryDto));
    }

    @PostMapping("/approve/{id}")
    @ApiOperation("同意申请")
    public AjaxResult approve(@PathVariable Long id) {
        return assetApplyService.updateStatus(id, AssetStatus.APPROVED) > 0 ? success() : error();
    }

    @PostMapping("/reject/{id}")
    @ApiOperation("拒绝申请")
    public AjaxResult reject(@PathVariable Long id) {
        return assetApplyService.updateStatus(id, AssetStatus.REJECTED) > 0 ? success() : error();
    }
}
