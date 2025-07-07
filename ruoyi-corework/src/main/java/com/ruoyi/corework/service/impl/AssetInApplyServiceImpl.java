package com.ruoyi.corework.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.constant.AssetApplyStatus;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.domain.AssetInApply;
import com.ruoyi.corework.domain.AssetInApplyDetail;
import com.ruoyi.corework.domain.dto.AssetInApplyQueryDto;
import com.ruoyi.corework.domain.dto.AssetInApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetOutApplySaveDto;
import com.ruoyi.corework.mapper.AssetInApplyDetailMapper;
import com.ruoyi.corework.mapper.AssetInApplyMapper;
import com.ruoyi.corework.mapper.AssetMapper;
import com.ruoyi.corework.service.IAssetInApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private AssetMapper assetMapper;

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

    @Transactional
    @Override
    public int updateAssetInApply(AssetInApplySaveDto assetInApplySaveDto) {

        AssetInApply assetInApply = new AssetInApply();
        BeanUtils.copyProperties(assetInApplySaveDto, assetInApply);

        if(!assetInApply.getStatus().equals(AssetApplyStatus.DRAFT)){
            throw new RuntimeException("入库申请单必须是草稿状态下才能修改");
        }

        int rows = assetInApplyMapper.updateAssetOutApply(assetInApply);

        if (!assetInApply.getDetailList().isEmpty()) {
            // 删除之前的包含该申请单的详情
            assetInApplyDetailMapper.deleteAssetInApplyDetailByIds(assetInApply.getId());
            // 插入新增的物资列表
            for (AssetInApplyDetail detail : assetInApplySaveDto.getDetailList()) {
                detail.setApplyId(assetInApply.getId());
                assetInApplyDetailMapper.InsertAssetInApplyDetail(detail);
            }
        }
        return rows;
    }

    @Override
    @Transactional
    public int deleteAssetInApplyByIds(Long[] ids) {
        // 删除附属申请单的申请单详情
        for (Long id : ids) {
            assetInApplyDetailMapper.deleteAssetInApplyDetailByIds(id);
        }
        //  删除申请单
        int rows = assetInApplyMapper.deleteAssetOutApplyByIds(ids);
        return rows;
    }

    @Override
    @Transactional
    public int updateStatus(Long id, String status) {
        AssetInApply assetInApply = assetInApplyMapper.selectAssetInApplyById(id);
        if (assetInApply == null) {
            throw new RuntimeException("该申请单不存在");
        }

        List<AssetInApplyDetail> details = assetInApplyDetailMapper.selectAssetInApplyDetailList(assetInApply.getId());
        if (CollectionUtils.isEmpty(details)) {
            throw new RuntimeException("物资明细不能为空！");
        }
        // 提交
        if (status.equals(AssetApplyStatus.PENDING)) {
            // 提交过后的一些逻辑操作
            if (!AssetApplyStatus.DRAFT.equals(assetInApply.getStatus())) {
                throw new RuntimeException("单据不是草稿状态，无法提交！");
            }
        }

        // 通过
        if (status.equals(AssetApplyStatus.APPROVED)) {
            // 通过过后的一些逻辑操作
            if (!AssetApplyStatus.PENDING.equals(assetInApply.getStatus())) {
                throw new RuntimeException("单据不是待审批状态，无法通过！");
            }
            // 进行入库
            for (AssetInApplyDetail detail : details) {
                Asset asset = assetMapper.selectAssetById(detail.getAssetId());
                asset.setUsableStock(asset.getUsableStock() + detail.getQuantity());
                asset.setTotalStock(asset.getTotalStock() + detail.getQuantity());
                asset.setUpdateTime(DateUtils.getNowDate());
                assetMapper.updateAsset(asset);
            }
        }

        // 拒绝
        if (status.equals(AssetApplyStatus.REJECTED)) {
            // 拒绝过后的一些逻辑操作
            if (!AssetApplyStatus.PENDING.equals(assetInApply.getStatus())) {
                throw new RuntimeException("单据不是待审批状态，无法拒绝！");
            }
        }

        // 通过
        return assetInApplyMapper.updateStatus(id, status);
    }

    private String generateApplyCode() {
        // 例如: RK202407040001
        String prefix = "RK" + DateUtils.dateTimeNow("yyyyMMdd");
        int randomNum = new Random().nextInt(9000) + 1000; // 4位随机数
        return prefix + randomNum;
    }
}
