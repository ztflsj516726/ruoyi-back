package com.ruoyi.corework.constant;

/**
 * ClassName:AssetApplyStatus
 * Package:IntelliJ IDEA
 * Description:
 * 申请状态枚举类
 *
 * @Author ztf
 * @Create 2025/6/24-14:09
 * @Version 1.0
 */
public class AssetApplyStatus {
    /**
     * 待提交
     */
    public static final String DRAFT = "draft";
    /**
     * 审批中
     */
    public static final String PENDING = "pending";
    /**
     * 已通过
     */
    public static final String APPROVED = "approved";
    /**
     * 已驳回
     */
    public static final String REJECTED = "rejected";

    /**
     * 已归还
     */
    public static final String BACK = "back";
}
