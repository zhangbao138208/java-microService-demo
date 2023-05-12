package com.sk.scott.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        BigInteger shardingValuesB = BigInteger.valueOf(preciseShardingValue.getValue()) ;
        BigInteger resB = shardingValuesB.mod(new BigInteger("10")).add(new BigInteger("1")).
                mod(new BigInteger("4"));
       var resC =  resB.intValue()/2 +1;
       String key = preciseShardingValue.getLogicTableName()+"_"+resC;
       if (collection.contains(key)){
           return key;
       }
       throw new UnsupportedOperationException("route"+key+"is not supported");
    }
}
