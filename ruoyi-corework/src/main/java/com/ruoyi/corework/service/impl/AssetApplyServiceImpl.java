package com.ruoyi.corework.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.EmailUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.constant.AssetOperStatus;
import com.ruoyi.corework.constant.AssetStatus;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.domain.AssetApply;
import com.ruoyi.corework.domain.AssetApplyDetail;
import com.ruoyi.corework.domain.AssetOper;
import com.ruoyi.corework.domain.dto.AssetApplySaveDto;
import com.ruoyi.corework.domain.dto.AssetApplyQueryDto;
import com.ruoyi.corework.domain.dto.MyTodoQueryDto;
import com.ruoyi.corework.mapper.AssetApplyDetailMapper;
import com.ruoyi.corework.mapper.AssetApplyMapper;
import com.ruoyi.corework.mapper.AssetMapper;
import com.ruoyi.corework.mapper.AssetOperMapper;
import com.ruoyi.corework.service.IAssetApplyService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetOperMapper assetOperMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public int InsertAssetApply(AssetApplySaveDto assetApplyDto) {
        AssetApply assetApply = new AssetApply();
        BeanUtils.copyProperties(assetApplyDto, assetApply);
        assetApply.setCreateBy(SecurityUtils.getUsername());
        assetApply.setCreateTime(DateUtils.getNowDate());
        assetApply.setApplyTime(DateUtils.getNowDate());
        assetApply.setApplyUserId(SecurityUtils.getUserId());
        assetApply.setDeptId(SecurityUtils.getDeptId());
        assetApply.setStatus(AssetStatus.DRAFT);
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
        if (assetApply1 == null) {
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
        assetApplyQueryDto.setApplyUserId(SecurityUtils.getUserId());
        return assetApplyMapper.selectAssetApplyList(assetApplyQueryDto);
    }



    @Override
    @Transactional
    public int updateStatus(Long id, String type) {
        AssetApply assetApply = assetApplyMapper.selectAssetApplyById(id);
        if (assetApply == null) {
            throw new RuntimeException("申请单不存在");
        }
        // 如果是要变成提交变成待审核状态 就需要先腾出来库存
        if (Objects.equals(type, AssetStatus.PENDING)) {
            List<AssetApplyDetail> assetApplyDetails = assetApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetApplyDetail assetApplyDetail : assetApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetApplyDetail.getAssetId());
                if (asset == null) {
                    throw new RuntimeException("该物资不存在");
                }
                if (assetApplyDetail.getCount() > asset.getUsableStock()) {
                    throw new RuntimeException(asset.getName() + "可用库存不足");
                }
                asset.setUsableStock(asset.getUsableStock() - assetApplyDetail.getCount());
                assetMapper.updateAsset(asset);
            }
        }
        SysUser sysUser = sysUserMapper.selectUserById(assetApply.getApplyUserId());

        // 拒绝 ：增加物资库存数量
        if (Objects.equals(type, AssetStatus.REJECTED)) {
            List<AssetApplyDetail> assetApplyDetails = assetApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetApplyDetail assetApplyDetail : assetApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetApplyDetail.getAssetId());
                if (asset == null) {
                    throw new RuntimeException("该物资不存在");
                }
                asset.setUsableStock(asset.getUsableStock() + assetApplyDetail.getCount());
                assetMapper.updateAsset(asset);
            }
            EmailUtil.sendApproveMail(sysUser.getEmail(), "资产申请单审核结果", "资产申请单审核结果：" + (Objects.equals(type, AssetStatus.APPROVED) ? "通过" : "未通过"));
        }

        // 归还：产生归还记录，增加可用库存数量
        if(Objects.equals(type, AssetStatus.BACK)){
            List<AssetApplyDetail> assetApplyDetails = assetApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetApplyDetail assetApplyDetail : assetApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetApplyDetail.getAssetId());
                if (asset == null) {
                    throw new RuntimeException("该物资不存在");
                }
                asset.setUsableStock(asset.getUsableStock() + assetApplyDetail.getCount());
                assetMapper.updateAsset(asset);
                AssetOper assetOper = AssetOper.builder()
                        .assetId(assetApplyDetail.getAssetId())
                        .operType(AssetOperStatus.BACK)
                        .operNum(assetApplyDetail.getCount())
                        .afterUseableStock(asset.getUsableStock())
                        .createBy(sysUser.getNickName())
                        .createTime(DateUtils.getNowDate())
                        .build();
                assetOperMapper.InsertAssetOper(assetOper);
            }
            EmailUtil.sendApproveMail(sysUser.getEmail(), "资产归还", "资产归还");
        }
        // 同意：产生一条借用记录
        if(Objects.equals(type, AssetStatus.APPROVED)){
            if(sysUser ==null){
                throw new RuntimeException("用户不存在");
            }
            List<AssetApplyDetail> assetApplyDetails = assetApplyDetailMapper.selectAssetApplyByApplyId(id);
            for (AssetApplyDetail assetApplyDetail : assetApplyDetails) {
                Asset asset = assetMapper.selectAssetById(assetApplyDetail.getAssetId());
                AssetOper assetOper = AssetOper.builder()
                        .assetId(assetApplyDetail.getAssetId())
                        .operType(AssetOperStatus.BORROW)
                        .operNum(assetApplyDetail.getCount())
                        .afterUseableStock(asset.getUsableStock())
                        .createBy(sysUser.getNickName())
                        .createTime(DateUtils.getNowDate())
                        .build();
                assetOperMapper.InsertAssetOper(assetOper);
            }
            EmailUtil.sendApproveMail(sysUser.getEmail(), "资产申请单审核结果", "资产申请单审核结果：" + (Objects.equals(type, AssetStatus.APPROVED) ? "通过" : "未通过"));
        }
        assetApply.setStatus(type);
        return assetApplyMapper.updateAssetApply(assetApply);
    }

    @Override
    public List<AssetApply> selectMyAssetApplyList(MyTodoQueryDto myTodoQueryDto) {
        AssetApplyQueryDto assetApplyQueryDto = new AssetApplyQueryDto();
        BeanUtils.copyProperties(myTodoQueryDto, assetApplyQueryDto);
        assetApplyQueryDto.setCheckUserId(SecurityUtils.getUserId());
        return assetApplyMapper.selectAssetApplyList(assetApplyQueryDto);
    }
}
