package com.sk.scott.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.sk.scott.dept")
public class Dept {
    private String name;
    private Integer id;
}
