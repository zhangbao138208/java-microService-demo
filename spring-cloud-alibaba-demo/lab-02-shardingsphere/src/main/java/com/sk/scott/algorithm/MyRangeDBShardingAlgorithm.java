package com.sk.scott.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

public class MyRangeDBShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
       Long lower = rangeShardingValue.getValueRange().lowerEndpoint();
       Long upper = rangeShardingValue.getValueRange().lowerEndpoint();
        System.out.println(lower.toString()+"-----------"+upper.toString());
        return collection;
    }
}
