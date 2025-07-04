package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.AssetInApply;
import com.ruoyi.corework.domain.dto.AssetInApplyQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:AssetInApplyMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-15:53
 * @Version 1.0
 */
@Mapper
public interface AssetInApplyMapper {

    int InsertAssetOutApply(AssetInApply assetInApply);

    List<AssetInApply> selectAssetApplyList(AssetInApplyQueryDto assetInApplyQueryDto);

    AssetInApply selectAssetInApplyById(Long id);

    int updateAssetOutApply(AssetInApply assetInApply);

}
