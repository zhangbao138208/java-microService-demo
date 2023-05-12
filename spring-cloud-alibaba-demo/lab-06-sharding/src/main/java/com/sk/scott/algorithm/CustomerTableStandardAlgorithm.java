package com.sk.scott.algorithm;

import lombok.Getter;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

public class CustomerTableStandardAlgorithm<T extends Comparable<?>>
        implements StandardShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        System.out.println("自定义分表1");
        System.out.println(preciseShardingValue.getValue().toString());
        Long value = preciseShardingValue.getValue().longValue();
        String db_suffix="";
        int mask = 0b11111111;
        db_suffix = String.valueOf(mask & value>>9);
        for (String each : collection) {
            if (each.endsWith(db_suffix)) {
                return each;
            }
        }

        throw new UnsupportedOperationException("不支持的表" + db_suffix);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        System.out.println("自定义分表2");
        return collection;
    }

    @Override
    public void init(Properties props) {
        System.out.println("========自定义table分片==============");
    }

    @Override
    public String getType() {
        return "CUS_TABLE_INLINE";
    }

    @Getter
    private Properties props;
}
