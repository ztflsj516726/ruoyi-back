package com.ruoyi.corework.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.constant.AssetApplyStatus;
import com.ruoyi.corework.domain.AssetInApply;
import com.ruoyi.corework.domain.AssetInApplyDetail;
import com.ruoyi.corework.domain.dto.AssetInApplyQueryDto;
import com.ruoyi.corework.domain.dto.AssetInApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetOutApplySaveDto;
import com.ruoyi.corework.mapper.AssetInApplyDetailMapper;
import com.ruoyi.corework.mapper.AssetInApplyMapper;
import com.ruoyi.corework.service.IAssetInApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * ClassName:AssetInApplyServiceImpl
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/4-15:42
 * @Version 1.0
 */
@Service
public class AssetInApplyServiceImpl implements IAssetInApplyService {


    @Autowired
    private AssetInApplyMapper assetInApplyMapper;

    @Autowired
    private AssetInApplyDetailMapper assetInApplyDetailMapper;


    @Override
    @Transactional
    public int InsertAssetInApply(AssetInApplySaveDto assetInApplySaveDto) {
        AssetInApply assetInApply = new AssetInApply();
        BeanUtils.copyProperties(assetInApplySaveDto, assetInApply);
        assetInApply.setCreateBy(SecurityUtils.getUsername());
        assetInApply.setCreateTime(DateUtils.getNowDate());
        // 设置申请单号
        assetInApply.setApplyCode(generateApplyCode());
        // 设置状态为草稿
        assetInApply.setStatus(AssetApplyStatus.DRAFT);
        int rows = assetInApplyMapper.InsertAssetOutApply(assetInApply);

        for (AssetInApplyDetail detail : assetInApplySaveDto.getDetailList()) {
            detail.setApplyId(assetInApply.getId());
            System.out.println("detail" + detail);
            assetInApplyDetailMapper.InsertAssetInApplyDetail(detail);
        }
        return rows;
    }

    @Override
    public List<AssetInApply> selectAssetList(AssetInApplyQueryDto assetInApplyQueryDto) {
        return assetInApplyMapper.selectAssetApplyList(assetInApplyQueryDto);
    }

    @Override
    public AssetInApply selectAssetInApplyById(Long id) {
        AssetInApply assetInApply = assetInApplyMapper.selectAssetInApplyById(id);
        assetInApply.setDetailList(assetInApplyDetailMapper.selectAssetInApplyDetailList(assetInApply.getId()));
        return assetInApply;
    }

    @Override
    public int updateAssetInApply(AssetInApplySaveDto assetInApplySaveDto) {
        AssetInApply assetInApply = new AssetInApply();
        BeanUtils.copyProperties(assetInApplySaveDto, assetInApply);
        return assetInApplyMapper.updateAssetOutApply(assetInApply);
    }

    private String generateApplyCode() {
        // 例如: RK202407040001
        String prefix = "RK" + DateUtils.dateTimeNow("yyyyMMdd");
        int randomNum = new Random().nextInt(9000) + 1000; // 4位随机数
        return prefix + randomNum;
    }
}
