package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.AssetInApplyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:AssetInApplyDetailMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-16:23
 * @Version 1.0
 */
@Mapper
public interface AssetInApplyDetailMapper {

    int InsertAssetInApplyDetail(AssetInApplyDetail assetInApplyDetail);

    List<AssetInApplyDetail> selectAssetInApplyDetailList(Long applyId);
}
