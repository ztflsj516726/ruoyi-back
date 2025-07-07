package com.ruoyi.corework.service;

import com.ruoyi.corework.domain.AssetInApply;
import com.ruoyi.corework.domain.dto.AssetInApplyQueryDto;
import com.ruoyi.corework.domain.dto.AssetInApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetOutApplySaveDto;

import java.util.List;

/**
 * ClassName:IAssetInApplyService
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-15:43
 * @Version 1.0
 */
public interface IAssetInApplyService {

    int InsertAssetInApply(AssetInApplySaveDto assetInApplySaveDto);

    List<AssetInApply> selectAssetList(AssetInApplyQueryDto assetInApplyQueryDto);

    AssetInApply  selectAssetInApplyById(Long id);

    int updateAssetInApply(AssetInApplySaveDto assetInApplySaveDto);

    int deleteAssetInApplyByIds(Long[] ids);

    int updateStatus(Long id, String status);

}
