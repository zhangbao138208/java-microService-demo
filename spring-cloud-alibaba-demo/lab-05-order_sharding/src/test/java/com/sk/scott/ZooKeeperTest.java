package com.sk.scott;

import com.sk.scott.utils.ZooKeeperUtils;
import com.sk.scott.utils.kv;
import org.junit.Test;

import java.util.List;

public class ZooKeeperTest {

    @Test
    public void insert(){
       // ZookeeperUtils.createNode("/shardingSphere/snowflake/c6w8/0-1-0","0:1");
        //ZookeeperUtils.createNode("/shardingSphere/snowflake/c6w8/1-0-0","1:0");
        //ZookeeperUtils.createNode("/shardingSphere/snowflake/c6w8/1-1-0","1:1");
        //ZookeeperUtils.createNode("/shardingSphere/snowflake/c6w8/2-0-0","2:0");
      //  ZooKeeperUtils.createNode("/shardingSphere/snowflake/c6w8/2-1-0","2:1");
    }

    @Test
    public void del(){
        //ZooKeeperUtils.Del("/shardingSphere/snowflake/c6w8/2-1-0");
    }

    @Test
    public void getNodes(){
//     List<kv> ret =  new ZooKeeperUtils().getKVByKey("/shardingSphere/snowflake/c6w8");
//        System.out.println("start");
//     ret.forEach(System.out::println);
    }
}

