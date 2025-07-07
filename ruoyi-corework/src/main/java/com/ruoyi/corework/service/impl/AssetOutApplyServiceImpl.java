package com.ruoyi.corework.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.constant.AssetApplyStatus;
import com.ruoyi.corework.constant.AssetOperStatus;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.domain.AssetOutApply;
import com.ruoyi.corework.domain.AssetOutApplyDetail;
import com.ruoyi.corework.domain.AssetOper;
import com.ruoyi.corework.domain.dto.AssetOutApplyQueryDto;
import com.ruoyi.corework.domain.dto.AssetOutApplySaveDto;
import com.ruoyi.corework.domain.dto.MyTodoQueryDto;
import com.ruoyi.corework.mapper.AssetOutApplyDetailMapper;
import com.ruoyi.corework.mapper.AssetOutApplyMapper;
import com.ruoyi.corework.mapper.AssetMapper;
import com.ruoyi.corework.mapper.AssetOperMapper;
import com.ruoyi.corework.service.IAssetOutApplyService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * ClassName:AssetOutApplyServiceImpl
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/20-15:28
 * @Version 1.0
 */
@Service
public class AssetOutApplyServiceImpl implements IAssetOutApplyService {

    @Autowired
    private AssetOutApplyMapper assetOutApplyMapper;

    @Autowired
    private AssetOutApplyDetailMapper assetOutApplyDetailMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetOperMapper assetOperMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public int InsertAssetInApply(AssetOutApplySaveDto assetApplyDto) {
        AssetOutApply assetOutApply = new AssetOutApply();
        BeanUtils.copyProperties(assetApplyDto, assetOutApply);
        assetOutApply.setCreateBy(SecurityUtils.getUsername());
        assetOutApply.setCreateTime(DateUtils.getNowDate());
        assetOutApply.setApplyTime(DateUtils.getNowDate());
        assetOutApply.setApplyUserId(SecurityUtils.getUserId());
        assetOutApply.setDeptId(SecurityUtils.getDeptId());
        assetOutApply.setStatus(AssetApplyStatus.DRAFT);
        assetOutApply.setApplyCode(generateApplyCode());
        // 插入主表
        assetOutApplyMapper.InsertAssetInApply(assetOutApply);

        // 插入子表
        if (assetOutApply.getDetailList() != null && !assetOutApply.getDetailList().isEmpty()) {
            for (AssetOutApplyDetail detail : assetOutApply.getDetailList()) {
                detail.setApplyId(assetOutApply.getId());
            }
            assetOutApplyDetailMapper.InsertAssetInApplyDetails(assetOutApply.getDetailList());
        }
        return 1;
    }

    @Override
    public int updateAssetInApply(AssetOutApplySaveDto assetApplyDto) {
        AssetOutApply assetOutApply1 = assetOutApplyMapper.selectAssetOutApplyById(assetApplyDto.getId());
        if (assetOutApply1 == null) {
            throw new RuntimeException("申请单不存在");
        }
        AssetOutApply assetOutApply = new AssetOutApply();
        BeanUtils.copyProperties(assetApplyDto, assetOutApply);
        assetOutApply.setUpdateBy(SecurityUtils.getUsername());
        assetOutApply.setUpdateTime(DateUtils.getNowDate());
        assetOutApply.setUpdateBy(SecurityUtils.getUsername());

        // 更新主表
        assetOutApplyMapper.updateAssetInApply(assetOutApply);
        // 删除子表
        assetOutApplyMapper.deleteAssetApplyDetail(assetOutApply.getId());
        // 添加子表
        if (assetOutApply.getDetailList() != null && !assetOutApply.getDetailList().isEmpty()) {
            for (AssetOutApplyDetail detail : assetOutApply.getDetailList()) {
                detail.setApplyId(assetOutApply.getId());
            }
            assetOutApplyDetailMapper.InsertAssetInApplyDetails(assetOutApply.getDetailList());
        }
        return 1;
    }

    @Override
    public AssetOutApply selectAssetOutApplyById(Long id) {
        AssetOutApply assetOutApply = assetOutApplyMapper.selectAssetOutApplyById(id);
        List<AssetOutApplyDetail> detailList = assetOutApplyDetailMapper.selectAssetApplyByApplyId(id);
        assetOutApply.setDetailList(detailList);
        return assetOutApply;
    }

    @Override
    @Transactional
    public int deleteAssetInApplyByIds(Long[] ids) {
        // 删除子表
        for (Long id : ids) {
            assetOutApplyMapper.deleteAssetApplyDetail(id);
        }
        // 删除主表
        return assetOutApplyMapper.deleteAssetInApplyByIds(ids);
    }

    @Override
    public List<AssetOutApply> selectAssetList(AssetOutApplyQueryDto assetOutApplyQueryDto) {
        assetOutApplyQueryDto.setApplyUserId(SecurityUtils.getUserId());
        return assetOutApplyMapper.selectAssetApplyInList(assetOutApplyQueryDto);
    }


    @Override
    @Transactional
    public int updateStatus(Long id, String type) {
        AssetOutApply assetOutApply = assetOutApplyMapper.selectAssetOutApplyById(id);
        if (assetOutApply == null) {
            throw new RuntimeException("申请单不存在");
        }
        // 如果是要变成提交变成待审核状态 就需要先腾出来库存 (提交)
        if (Objects.equals(type, AssetApplyStatus.PENDING)) {
            List<AssetOutApplyDetail> assetOutApplyDetails = assetOutApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetOutApplyDetail assetOutApplyDetail : assetOutApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetOutApplyDetail.getAssetId());
                if (asset == null) {
                    throw new RuntimeException("该物资不存在");
                }
                if (assetOutApplyDetail.getCount() > asset.getUsableStock()) {
                    throw new RuntimeException(asset.getName() + "可用库存不足");
                }
                asset.setUsableStock(asset.getUsableStock() - assetOutApplyDetail.getCount());
                assetMapper.updateAsset(asset);
            }
        }
        // 获取申请用户信息
        SysUser sysUser = sysUserMapper.selectUserById(assetOutApply.getApplyUserId());
        // 拒绝 ：增加物资库存数量
        if (Objects.equals(type, AssetApplyStatus.REJECTED)) {
            List<AssetOutApplyDetail> assetOutApplyDetails = assetOutApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetOutApplyDetail assetOutApplyDetail : assetOutApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetOutApplyDetail.getAssetId());
                if (asset == null) {
                    throw new RuntimeException("该物资不存在");
                }
                asset.setUsableStock(asset.getUsableStock() + assetOutApplyDetail.getCount());
                assetMapper.updateAsset(asset);
            }
            notificationService.sendApproveMail(sysUser.getEmail(), "资产申请单审核结果", "资产申请单审核结果：您的审批已被拒绝");
        }

        // 归还：产生归还记录，增加可用库存数量
        if (Objects.equals(type, AssetApplyStatus.BACK)) {
            List<AssetOutApplyDetail> assetOutApplyDetails = assetOutApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetOutApplyDetail assetOutApplyDetail : assetOutApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetOutApplyDetail.getAssetId());
                if (asset == null) {
                    throw new RuntimeException("该物资不存在");
                }
                asset.setUsableStock(asset.getUsableStock() + assetOutApplyDetail.getCount());
                assetMapper.updateAsset(asset);
                AssetOper assetOper = AssetOper.builder()
                        .assetId(assetOutApplyDetail.getAssetId())
                        .applyId(assetOutApply.getId())
                        .batchId(assetOutApply.getId())
                        .warehouseId(assetOutApply.getId())
                        .operType(AssetOperStatus.BACK)
                        .operNum(assetOutApplyDetail.getCount())
                        .beforeUseableStock(asset.getUsableStock() - assetOutApplyDetail.getCount())
                        .afterUseableStock(asset.getUsableStock())
                        .createBy(sysUser.getNickName())
                        .createTime(DateUtils.getNowDate())
                        .build();
                assetOperMapper.InsertAssetOper(assetOper);
            }
            notificationService.sendApproveMail("821477928@qq.com", "资产归还", "资产已经归还");
        }
        // 同意：产生一条借用记录
        if (Objects.equals(type, AssetApplyStatus.APPROVED)) {
            if (sysUser == null) {
                throw new RuntimeException("用户不存在");
            }
            List<AssetOutApplyDetail> assetOutApplyDetails = assetOutApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetOutApplyDetail assetOutApplyDetail : assetOutApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetOutApplyDetail.getAssetId());
                AssetOper assetOper = AssetOper.builder()
                        .assetId(assetOutApplyDetail.getAssetId())
                        .applyId(assetOutApply.getId())
                        .batchId(assetOutApply.getId())
                        .warehouseId(assetOutApply.getId())
                        .operType(AssetOperStatus.BORROW)
                        .operNum(assetOutApplyDetail.getCount())
                        .beforeUseableStock(asset.getUsableStock() + assetOutApplyDetail.getCount())
                        .afterUseableStock(asset.getUsableStock())
                        .createBy(sysUser.getNickName())
                        .createTime(DateUtils.getNowDate())
                        .build();
                assetOperMapper.InsertAssetOper(assetOper);
            }
            notificationService.sendApproveMail(sysUser.getEmail(), "资产申请单审核结果", "资产申请单审核结果：您的资产申请已通过");
        }
        assetOutApply.setStatus(type);
        return assetOutApplyMapper.updateAssetInApply(assetOutApply);
    }

    @Override
    public List<AssetOutApply> selectMyAssetApplyList(MyTodoQueryDto myTodoQueryDto) {
        AssetOutApplyQueryDto assetOutApplyQueryDto = new AssetOutApplyQueryDto();
        BeanUtils.copyProperties(myTodoQueryDto, assetOutApplyQueryDto);
        return assetOutApplyMapper.selectAssetApplyInList(assetOutApplyQueryDto);
    }

    private String generateApplyCode() {
        String prefix = "CK" + DateUtils.dateTimeNow("yyyyMMdd");
        int randomNum = new Random().nextInt(9000) + 1000; // 4位随机数
        return prefix + randomNum;
    }
}
