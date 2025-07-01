package com.ruoyi.corework.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.mapper.AssetApplyDetailMapper;
import com.ruoyi.corework.mapper.AssetApplyMapper;
import com.ruoyi.corework.mapper.AssetMapper;
import com.ruoyi.corework.service.IAssetService;
import com.ruoyi.system.domain.SysNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AssetApplyDetailMapper assetApplyDetailMapper;

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
        System.out.println("更新" + asset);
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
        for (Long id : ids) {
            int num = assetApplyDetailMapper.selectAssetApplyDetailByAssetId(id);
            // 该物资存在关联申请单
            if (num > 0) {
                Asset asset = assetMapper.selectAssetById(id);
                throw new RuntimeException(asset.getName() + "存在相关联申请单");
            }
        }
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
        int num = assetApplyDetailMapper.selectAssetApplyDetailByAssetId(id);
        // 该物资存在关联申请单
        if (num > 0) {
            Asset asset = assetMapper.selectAssetById(id);
            throw new RuntimeException(asset.getName() + "存在相关联申请单");

        }
        return assetMapper.deleteAssetById(id);
    }
}
