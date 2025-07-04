package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.AssetOutApplyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:AssetOutApplyDetailMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:15
 * @Version 1.0
 */
@Mapper
public interface AssetOutApplyDetailMapper {

    int InsertAssetOutApplyDetails(List<AssetOutApplyDetail> assetOutApplyDetailList);

    List<AssetOutApplyDetail> selectAssetApplyByApplyId(Long applyId);

    // 查询物资是否有绑定的申请单
    int selectAssetApplyDetailByAssetId(Long assetId);
}
