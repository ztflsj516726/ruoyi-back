package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.AssetOper;
import com.ruoyi.corework.domain.dto.AssetOperQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:AssetOperMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:35
 * @Version 1.0
 */

@Mapper
public interface AssetOperMapper {

    int InsertAssetOper(AssetOper assetOper);


    List<AssetOper> selectAssetOperList(AssetOperQueryDto assetOperQueryDto);
}
