package cn.enaium.message.repository;

import cn.enaium.message.config.DroolsConfig;
import cn.enaium.message.dto.RiskRuleRecordReqDTO;
import cn.enaium.message.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Project: message
 * -----------------------------------------------------------
 * Copyright © 2020 | Enaium | All rights reserved.
 */
@SpringBootTest
class RuleTest {

    @Autowired
    private DroolsConfig droolsConfig;

    @Test
    void testRule() {
        RiskRuleRecordReqDTO dto = new RiskRuleRecordReqDTO();
        dto.setLastRejectRiskCode("YC");
       // dto.setUserSource("4");
        dto.setAcqChannel("SK");
       // dto.setUserType("NEW");
        dto.setIsFullQuota("1");
        dto.setMobile("62000000");
        System.out.println(JsonUtil.toJson(dto));
        droolsConfig.evaluateRules("riskRule.xlsx", dto);
        System.out.println(JsonUtil.toJson(dto));
    }

}