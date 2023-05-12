package com.sk.scott.config;

import com.sk.scott.selector.importSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
//@EnableConfigurationProperties({Dept.class})
@Import(importSelector.class)
public class scottAutoConfiguration {
    @Bean(name = "scottName")
    public List<String>  getNames(){
        return List.of("scot1","33","ddd","test");
    }
}
