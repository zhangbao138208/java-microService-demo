package com.sk.scott.config;

import com.sk.scott.utils.SnowflakeIdWorker;
import lombok.Getter;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;

import java.util.Properties;

public class SnowflakeKeyGenerator implements KeyGenerateAlgorithm {
    @Override
    public Comparable<Long> generateKey() {
        return SnowflakeIdWorker.generateId();
    }

    @Override
    public void init(Properties props) {
        System.out.println("========自定义雪花算法==============");
    }

    @Getter
    private Properties props;

    @Override
    public String getType() {
        return "SHLD_DEVICE_SNOWFLAKE";
    }
}
