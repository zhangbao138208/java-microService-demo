package com.sk.scott.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class SentinelFlowLimitController {
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/testA")
    public String testA() {
      //  return "c语言中文网提醒您，服务访问成功------testA";
        return testAbySphU();
    }
    @GetMapping("/testB")
    public String testB() {
     //   return "c语言中文网提醒您，服务访问成功------testB";
        return testBbySphO();
    }

    @GetMapping("/testC")
    @SentinelResource(value = "testCbyAnnotation") //通过注解定义资源
    public String testC() {
        log.info("c语言中文网提醒您，服务访问成功------testC：" + serverPort);
        return "c语言中文网提醒您，服务访问成功------testC：" + serverPort;
    }

    /**
     * 通过 Sentinel 控制台定义流控规则
     *
     */
    @GetMapping("/testD")
    @SentinelResource(value = "testD-resource", blockHandler = "blockHandlerTestD") //通过注解定义资源
    public String testD() {
        initFlowRules();
        log.info("c语言中文网提醒您，服务访问成功------testD：" + serverPort);
        return "c语言中文网提醒您，服务访问成功------testD：" + serverPort;
    }


    /**
     * 通过代码定义流量控制规则
     */
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        //定义一个限流规则对象
        FlowRule rule = new FlowRule();
        //资源名称
        rule.setResource("testD-resource");
        //限流阈值的类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置 QPS 的阈值为 2
        rule.setCount(3);
        rules.add(rule);
        //定义限流规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 限流之后的逻辑
     * @param exception
     * @return
     */
    public String blockHandlerTestD(BlockException exception) {
        log.info(Thread.currentThread().getName() + "c语言中文网提醒您，TestD服务访问失败! 您已被限流，请稍后重试");
        return "c语言中文网提醒您，TestD服务访问失败! 您已被限流，请稍后重试";
    }

    /**
     * 通过 SphU 手动定义资源
     * @return
     */
    public String testAbySphU() {
        Entry entry = null;
        try {
            entry = SphU.entry("testAbySphU");
            //您的业务逻辑 - 开始
            log.info("c语言中文网提醒您，服务访问成功------testA："+serverPort);
            return "c语言中文网提醒您，服务访问成功------testA："+serverPort;
            //您的业务逻辑 - 结束
        } catch (BlockException e1) {
            //流控逻辑处理 - 开始
            log.info("c语言中文网提醒您，testA 服务被限流");
            return "c语言中文网提醒您，testA 服务被限流";
            //流控逻辑处理 - 结束
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * 通过 SphO 手动定义资源
     *
     * @return
     */
    public String testBbySphO() {
        if (SphO.entry("testBbySphO")) {
            // 务必保证finally会被执行
            try {
                log.info("c语言中文网提醒您，服务访问成功------testB：" + serverPort);
                return "c语言中文网提醒您，服务访问成功------testB：" + serverPort;
            } finally {
                SphO.exit();
            }
        } else {
            // 资源访问阻止，被限流或被降级
            //流控逻辑处理 - 开始
            log.info("c语言中文网提醒您，testB 服务被限流");
            return "c语言中文网提醒您，testB 服务被限流";
            //流控逻辑处理 - 结束
        }
    }
}