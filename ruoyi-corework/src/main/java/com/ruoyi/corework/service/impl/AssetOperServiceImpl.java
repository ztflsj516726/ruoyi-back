package com.ruoyi.corework.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.constant.AssetOperStatus;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.domain.AssetOper;
import com.ruoyi.corework.domain.dto.AssetOperQueryDto;
import com.ruoyi.corework.domain.dto.AssetOperSaveDto;
import com.ruoyi.corework.mapper.AssetMapper;
import com.ruoyi.corework.mapper.AssetOperMapper;
import com.ruoyi.corework.service.IAssetOperService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * ClassName:AssetOperServiceImpl
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:31
 * @Version 1.0
 */

@Service
public class AssetOperServiceImpl implements IAssetOperService {

    @Autowired
    private AssetOperMapper assetOperMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Override
    @Transactional
    public int InsertAssetOper(AssetOperSaveDto assetOperSaveDto) {
        AssetOper assetOper =  AssetOper.builder().build();
        BeanUtils.copyProperties(assetOperSaveDto, assetOper);
        Asset asset = assetMapper.selectAssetById(assetOper.getAssetId());
        if (asset == null) {
            throw new RuntimeException("该物资不存在");
        }
        if (Objects.equals(assetOper.getOperType(), AssetOperStatus.In)) {
            Long totalStock = asset.getTotalStock();
            Long usableStock = asset.getUsableStock();
            asset.setTotalStock(totalStock + assetOper.getOperNum());
            asset.setUsableStock(usableStock + assetOper.getOperNum());
            System.out.println("asset" + asset);
            assetMapper.updateAsset(asset);
        } else if (Objects.equals(assetOper.getOperType(), AssetOperStatus.Out)) {
            Long totalStock = asset.getTotalStock();
            Long usableStock = asset.getUsableStock();
            if (usableStock < assetOper.getOperNum()) {
                throw new RuntimeException("该物资可用库存不足报废");
            }
            asset.setTotalStock(totalStock - assetOper.getOperNum());
            asset.setUsableStock(usableStock - assetOper.getOperNum());
            assetMapper.updateAsset(asset);
        }
        assetOper.setCreateTime(DateUtils.getNowDate());
        assetOper.setCreateBy(SecurityUtils.getUsername());
        assetOper.setAfterUseableStock(asset.getUsableStock());
        return assetOperMapper.InsertAssetOper(assetOper);
    }

    @Override
    public List<AssetOper> selectAssetOperList(AssetOperQueryDto assetOperQueryDto) {
        return assetOperMapper.selectAssetOperList(assetOperQueryDto);
    }
}
