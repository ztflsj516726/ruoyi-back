package com.ruoyi.corework.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.domain.AssetApply;
import com.ruoyi.corework.domain.AssetApplyDetail;
import com.ruoyi.corework.domain.dto.AssetApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetApplyQueryDto;
import com.ruoyi.corework.mapper.AssetApplyDetailMapper;
import com.ruoyi.corework.mapper.AssetApplyMapper;
import com.ruoyi.corework.service.IAssetApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:AssetApplyServiceImpl
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:28
 * @Version 1.0
 */
@Service
public class AssetApplyServiceImpl implements IAssetApplyService {

    @Autowired
    private AssetApplyMapper assetApplyMapper;

    @Autowired
    private AssetApplyDetailMapper assetApplyDetailMapper;

    @Override
    public int InsertAssetApply(AssetApplySaveDto assetApplyDto) {
        AssetApply assetApply = new AssetApply();
        BeanUtils.copyProperties(assetApplyDto, assetApply);
        assetApply.setCreateBy(SecurityUtils.getUsername());
        assetApply.setCreateTime(DateUtils.getNowDate());
        assetApply.setApplyTime(DateUtils.getNowDate());
        assetApply.setApplyUserId(SecurityUtils.getUserId());
        assetApply.setDeptId(SecurityUtils.getDeptId());
        assetApply.setStatus("draft");
        // 插入主表
        assetApplyMapper.insertAssetApply(assetApply);

        // 插入子表
        if (assetApply.getDetailList() != null && !assetApply.getDetailList().isEmpty()) {
            for (AssetApplyDetail detail : assetApply.getDetailList()) {
                detail.setApplyId(assetApply.getId());
            }
            assetApplyDetailMapper.insertAssetApplyDetails(assetApply.getDetailList());
        }
        return 1;
    }

    @Override
    public int updateAssetApply(AssetApplySaveDto assetApplyDto) {
        AssetApply assetApply1 = assetApplyMapper.selectAssetApplyById(assetApplyDto.getId());
        if(assetApply1 ==null){
            throw new RuntimeException("申请单不存在");
        }
        AssetApply assetApply = new AssetApply();
        BeanUtils.copyProperties(assetApplyDto, assetApply);
        assetApply.setUpdateBy(SecurityUtils.getUsername());
        assetApply.setUpdateTime(DateUtils.getNowDate());
        assetApply.setUpdateBy(SecurityUtils.getUsername());

        // 更新主表
        assetApplyMapper.updateAssetApply(assetApply);
        // 删除子表
        assetApplyMapper.deleteAssetApplyDetail(assetApply.getId());
        // 添加子表
        if (assetApply.getDetailList() != null && !assetApply.getDetailList().isEmpty()) {
            for (AssetApplyDetail detail : assetApply.getDetailList()) {
                detail.setApplyId(assetApply.getId());
            }
            assetApplyDetailMapper.insertAssetApplyDetails(assetApply.getDetailList());
        }
        return 1;
    }

    @Override
    public AssetApply selectAssetApplyById(Long id) {
        AssetApply assetApply = assetApplyMapper.selectAssetApplyById(id);
        List<AssetApplyDetail> detailList = assetApplyDetailMapper.selectAssetApplyByApplyId(id);
        assetApply.setDetailList(detailList);
        return assetApply;
    }

    @Override
    @Transactional
    public int deleteAssetApplyByIds(Long[] ids) {
        // 删除子表
        for (Long id : ids) {
            assetApplyMapper.deleteAssetApplyDetail(id);
        }
        // 删除主表
        return assetApplyMapper.deleteAssetApplyByIds(ids);
    }

    @Override
    public List<AssetApply> selectAssetList(AssetApplyQueryDto assetApplyQueryDto) {
        return assetApplyMapper.selectAssetApplyList(assetApplyQueryDto);
    }
}
