package com.ruoyi.corework.service;

import com.ruoyi.corework.domain.Asset;

import java.util.List;

/**
 * 物资信息Service接口
 * 
 * @author ruoyi
 * @date 2025-06-20
 */
public interface IAssetService
{
    /**
     * 查询物资信息
     * 
     * @param id 物资信息主键
     * @return 物资信息
     */
    public Asset selectAssetById(Long id);

    /**
     * 查询物资信息列表
     * 
     * @param asset 物资信息
     * @return 物资信息集合
     */
    public List<Asset> selectAssetList(Asset asset);

    /**
     * 新增物资信息
     * 
     * @param asset 物资信息
     * @return 结果
     */
    public int insertAsset(Asset asset);

    /**
     * 修改物资信息
     * 
     * @param asset 物资信息
     * @return 结果
     */
    public int updateAsset(Asset asset);

    /**
     * 批量删除物资信息
     * 
     * @param ids 需要删除的物资信息主键集合
     * @return 结果
     */
    public int deleteAssetByIds(Long[] ids);

    /**
     * 删除物资信息信息
     * 
     * @param id 物资信息主键
     * @return 结果
     */
    public int deleteAssetById(Long id);
}
