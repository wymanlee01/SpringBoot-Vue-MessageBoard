package cn.enaium.message.dto;

import lombok.Data;

/**
 * 客户信息
 */
@Data
public class RiskRuleRecordReqDTO {
    /**
     * 上一次风控
     */
    private String lastRiskCode = "";
    /**
     * 客户来源
     */
    private String userSource = "";

    /**
     * 进件渠道
     */
    private String acqChannel = "";
    /**
     * 设备类型
     */
    private String deviceType = "";
    /**
     * 用户类型
     */
    private String userType = "";
    /**
     * app版本
     */
    private Integer version = null;
    /**
     * 手机尾号
     */
    private String tailNumber = "";
    /**
     * 权重
     */
    private Integer rand = null;

    private String matchedRule2Name = "";
    /**
     * 风控编码
     */
    private String riskCode = "";

    /**
     * 手机号
     */
    private String mobile = "";

    /**
     * 是否有授信
     */
    private String hasCreditRecord = "";

    /**
     * 拒绝次数
     */
    private Integer rejectCount;

    /**
     * 上一次拒绝风控
     */
    private String lastRejectRiskCode = "";

    /**
     * 用户id
     */
    private String appUserId = "";
    /**
     * 是否满额
     */
    private String isFullQuota = "";

    /**
     * 借款额度
     */
    private Integer quota;
}
