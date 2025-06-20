package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.AssetApply;
import com.ruoyi.corework.domain.dto.AssetApplyQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:AssetApplyMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:15
 * @Version 1.0
 */

@Mapper
public interface AssetApplyMapper {

    // 新增申请单
    int insertAssetApply(AssetApply assetApply);

    // 修改申请单
    int updateAssetApply(AssetApply assetApply);

    // 删除子表
    int deleteAssetApplyDetail(Long applyId);

    // 删除申请单
    int deleteAssetApplyByIds(Long[] ids);

    // 查询申请单
    AssetApply selectAssetApplyById(Long id);

    // 查询申请单
    List<AssetApply> selectAssetApplyList(AssetApplyQueryDto assetApplyQueryDto);
}
