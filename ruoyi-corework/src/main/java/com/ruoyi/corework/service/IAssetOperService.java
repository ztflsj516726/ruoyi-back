package com.ruoyi.corework.service;

import com.ruoyi.corework.domain.AssetOper;
import com.ruoyi.corework.domain.dto.AssetOperQueryDto;
import com.ruoyi.corework.domain.dto.AssetOperStatDto;
import com.ruoyi.corework.domain.vo.AssetOperStatDayVo;

import java.util.List;

/**
 * ClassName:IAssetOperService
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:28
 * @Version 1.0
 */
public interface IAssetOperService {

    // 入库报废列表
    List<AssetOper> selectAssetOperList(AssetOperQueryDto assetOperQueryDto);

    // 统计出入库数量(天)
    AssetOperStatDayVo selectAssetOperByDay(AssetOperStatDto assetOperStatDto);

}
