package com.ruoyi.corework.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.mapper.AssetMapper;
import com.ruoyi.corework.service.IAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物资信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-06-20
 */
@Service
public class AssetServiceImpl implements IAssetService {
    @Autowired
    private AssetMapper assetMapper;

    /**
     * 查询物资信息
     *
     * @param id 物资信息主键
     * @return 物资信息
     */
    @Override
    public Asset selectAssetById(Long id) {
        return assetMapper.selectAssetById(id);
    }

    /**
     * 查询物资信息列表
     *
     * @param asset 物资信息
     * @return 物资信息
     */
    @Override
    public List<Asset> selectAssetList(Asset asset) {
        return assetMapper.selectAssetList(asset);
    }

    /**
     * 新增物资信息
     *
     * @param asset 物资信息
     * @return 结果
     */
    @Override
    public int insertAsset(Asset asset) {
        asset.setCreateTime(DateUtils.getNowDate());
        asset.setCreateBy(SecurityUtils.getUsername());
        return assetMapper.insertAsset(asset);
    }

    /**
     * 修改物资信息
     *
     * @param asset 物资信息
     * @return 结果
     */
    @Override
    public int updateAsset(Asset asset) {
        asset.setUpdateTime(DateUtils.getNowDate());
        asset.setUpdateBy(SecurityUtils.getUsername());
        return assetMapper.updateAsset(asset);
    }

    /**
     * 批量删除物资信息
     *
     * @param ids 需要删除的物资信息主键
     * @return 结果
     */
    @Override
    public int deleteAssetByIds(Long[] ids) {
        return assetMapper.deleteAssetByIds(ids);
    }

    /**
     * 删除物资信息信息
     *
     * @param id 物资信息主键
     * @return 结果
     */
    @Override
    public int deleteAssetById(Long id) {
        return assetMapper.deleteAssetById(id);
    }
}
