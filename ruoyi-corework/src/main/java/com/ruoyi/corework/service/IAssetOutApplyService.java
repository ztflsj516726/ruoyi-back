package com.ruoyi.corework.service;

import com.ruoyi.corework.domain.AssetOutApply;
import com.ruoyi.corework.domain.dto.AssetOutApplyQueryDto;
import com.ruoyi.corework.domain.dto.AssetOutApplySaveDto;
import com.ruoyi.corework.domain.dto.MyTodoQueryDto;

import java.util.List;

/**
 * ClassName:IAssetOutApplyService
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:27
 * @Version 1.0
 */

public interface IAssetOutApplyService {

    int InsertAssetInApply(AssetOutApplySaveDto assetApplyDto);

    int updateAssetInApply(AssetOutApplySaveDto assetApplyDto);

    AssetOutApply selectAssetOutApplyById(Long id);


    int deleteAssetInApplyByIds(Long[] ids);

    List<AssetOutApply> selectAssetList(AssetOutApplyQueryDto assetOutApplyQueryDto);

    // 提交申请单
    int updateStatus(Long id,String type);

    // 查看个人代审批申请单
    List<AssetOutApply> selectMyAssetApplyList(MyTodoQueryDto myTodoQueryDto);

}
