package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.AssetApplyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:AssetApplyDetailMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:15
 * @Version 1.0
 */
@Mapper
public interface AssetApplyDetailMapper {

    int insertAssetApplyDetails(List<AssetApplyDetail> assetApplyDetailList);

    List<AssetApplyDetail> selectAssetApplyByApplyId(Long applyId);
}
