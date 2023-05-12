package com.sk.scott.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

public class MyPreciseDBShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        BigInteger shardingValueB = BigInteger.valueOf(preciseShardingValue.getValue());
        BigInteger resB = (shardingValueB.mod(new BigInteger("2"))).add(new BigInteger("1"));
        String key = "m" + resB;
        if (collection.contains(key)) {
            return key;
        }
        throw new UnsupportedOperationException("route"+key+"is not supported");
    }
}
