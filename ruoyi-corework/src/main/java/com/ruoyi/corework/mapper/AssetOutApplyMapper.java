package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.AssetOutApply;
import com.ruoyi.corework.domain.dto.AssetOutApplyQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:AssetOutApplyMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:15
 * @Version 1.0
 */

@Mapper
public interface AssetOutApplyMapper {

    // 新增申请单
    int InsertAssetOutApply(AssetOutApply assetOutApply);

    // 修改申请单
    int updateAssetOutApply(AssetOutApply assetOutApply);

    // 删除子表
    int deleteAssetApplyDetail(Long applyId);

    // 删除申请单
    int deleteAssetOutApplyByIds(Long[] ids);

    // 查询申请单
    AssetOutApply selectAssetOutApplyById(Long id);

    // 查询申请单
    List<AssetOutApply> selectAssetApplyList(AssetOutApplyQueryDto assetOutApplyQueryDto);
}
