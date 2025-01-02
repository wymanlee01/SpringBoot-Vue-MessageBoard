package cn.enaium.message.config;

import cn.enaium.message.dto.RiskRuleRecordReqDTO;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DroolsConfig {

    private final Map<String, KieContainer> kieContainerCache = new ConcurrentHashMap<>();


    /**
     * 获取缓存的 KieContainer
     *
     * @param ruleFileName 规则文件名称
     * @return KieContainer
     */
    public KieContainer getKieContainer(String ruleFileName) {
        return kieContainerCache.computeIfAbsent(ruleFileName, this::createKieContainer);
    }

    /**
     * 创建新的 KieContainer
     *
     * @param ruleFileName 规则文件名称
     * @return KieContainer
     */
    private KieContainer createKieContainer(String ruleFileName) {
        try {
            KieServices kieServices = KieServices.get();
            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            Resource resource = kieServices.getResources().newClassPathResource("rules/" + ruleFileName, "UTF-8");
            kieFileSystem.write(resource);
            kieServices.newKieBuilder(kieFileSystem).buildAll();
            return kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        } catch (Exception e) {
            throw new RuntimeException("加载规则文件失败: " + ruleFileName, e);
        }
    }

    /**
     * 获取 KieSession
     *
     * @param ruleFileName 规则文件名称
     * @return KieSession
     */
    public KieSession getKieSession(String ruleFileName) {
        return getKieContainer(ruleFileName).newKieSession();
    }


    public String evaluateRules(String ruleFile, RiskRuleRecordReqDTO item) {
        KieSession kieSession = this.getKieSession(ruleFile);
        try {
            kieSession.insert(item);
            kieSession.fireAllRules();
        } finally {
            kieSession.dispose(); // 释放资源
        }
        return "规则执行完成";
    }


}
