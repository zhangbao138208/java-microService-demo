package com.sk.scott.algorithm;

import lombok.Getter;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;


import java.util.Collection;
import java.util.Properties;

public class CustomerDBStandardAlgorithm<T extends Comparable<?>> implements StandardShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        System.out.println("自定义分库1");
        System.out.println("111");
        System.out.println(preciseShardingValue.getValue());
        System.out.println(preciseShardingValue.getValue().toString());
        Long value = preciseShardingValue.getValue().longValue();
        String db_suffix="";
        int mask = 0b111111;
        db_suffix = String.valueOf(mask & value>>17);
        for (String each : collection) {
            if (each.endsWith(db_suffix)) {
                return each;
            }
        }

        throw new UnsupportedOperationException("不支持的数据库" + db_suffix);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        System.out.println("自定义分库2");
        return collection;
    }
    @Override
    public void init(Properties props) {
        System.out.println("========自定义db分片==============");
    }

    @Override
    public String getType() {
        return "CUS_DB_INLINE";
    }

    @Getter
    private Properties props;
}
