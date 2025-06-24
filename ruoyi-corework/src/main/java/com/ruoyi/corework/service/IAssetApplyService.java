package com.ruoyi.corework.service;

import com.ruoyi.corework.domain.AssetApply;
import com.ruoyi.corework.domain.dto.AssetApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetApplyQueryDto;
import com.ruoyi.corework.domain.dto.MyTodoQueryDto;

import java.util.List;

/**
 * ClassName:IAssetApplyService
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:27
 * @Version 1.0
 */

public interface IAssetApplyService {

    int InsertAssetApply(AssetApplySaveDto assetApplyDto);

    int updateAssetApply(AssetApplySaveDto assetApplyDto);

    AssetApply selectAssetApplyById(Long id);


    int deleteAssetApplyByIds(Long[] ids);

    List<AssetApply> selectAssetList(AssetApplyQueryDto assetApplyQueryDto);

    // 提交申请单
    int updateStatus(Long id,String type);

    // 查看个人代审批申请单
    List<AssetApply> selectMyAssetApplyList(MyTodoQueryDto myTodoQueryDto);

}
